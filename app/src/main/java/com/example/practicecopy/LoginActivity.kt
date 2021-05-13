package com.example.practicecopy

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.request.SimpleMultiPartRequest
import com.android.volley.toolbox.Volley
import org.json.JSONException
import org.json.JSONObject

class LoginActivity : AppCompatActivity() {
    private var et_id: EditText? = null
    private var et_pass: EditText? = null
    private var btn_login: Button? = null
    private var btn_register: Button? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        et_id = findViewById(R.id.et_id)
        et_pass = findViewById(R.id.et_pass)
        btn_login = findViewById(R.id.btn_login)
        btn_register = findViewById(R.id.btn_register)
        btn_register?.setOnClickListener(View.OnClickListener {
            val intent =
                Intent(this@LoginActivity, RegisterActivity::class.java) //login 화면에서 회원가입으로 이동
            startActivity(intent)
        })
        btn_login?.setOnClickListener(View.OnClickListener {
            //EditText에 현재 입력되어 있는 값 가져옴(get)
            val userID = et_id?.getText().toString()
            val userPassword = et_pass?.getText().toString()

            //int userAge = integer.parseInt(et_age.getText().toString());
            val url = "http://54.180.149.204/Loginuser.php"
            val smpr =
                SimpleMultiPartRequest(Request.Method.POST, url, Response.Listener { response ->
                    //json object 형태로 변환
                    try {
                        val jsonObject = JSONObject(response)
                        val success = jsonObject.getBoolean("success") //PHP 파일에 success 선언
                        if (success) {
                            val userID = jsonObject.getString("userID")
                            //val userPassword = jsonObject.getString("userPassword") //php의 json response 값
                            val userName =jsonObject.getString("name")
                            val phone =jsonObject.getString("phone")
                            //int stamp = jsonObject.getInt("stamp");
                            Toast.makeText(this, "로그인에 성공했습니다.", Toast.LENGTH_SHORT)
                                .show()
                            val intent = Intent(
                                this@LoginActivity,
                                MainActivity::class.java
                            ) //출발 액티비티, 이동할 액티비티
                            intent.putExtra("userID", userID) //send userID to SubActivity
                            intent.putExtra("userName", userName)
                            intent.putExtra("sign", 1)
                            //intent.putExtra("stamp", stamp); //send stamp to SubActivity
                            //intent.putExtra("userPassword", userPassword); //send userPassword to SubActivity
                            startActivity(intent)
                            finish()
                        } else {
                            Toast.makeText(this, "로그인에 실패했습니다.", Toast.LENGTH_SHORT)
                                .show()
                            return@Listener
                        }
                    } catch (e: JSONException) {
                        e.printStackTrace()
                    }
                }, null)
            smpr.addStringParam("userID", userID+"")
            smpr.addStringParam("userPassword", userPassword)

            //ReviewRequest reviewRequest = new ReviewRequest(rate, keyword1, keyword2, keyword3, comment, responseListener); //create class
            val queue = Volley.newRequestQueue(this@LoginActivity)
            queue.add(smpr)
            //queue.add(reviewRequest);
        })
    }
}