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

class RegisterActivity : AppCompatActivity() {
    private var et_id: EditText? = null
    private var et_pass: EditText? = null
    private var et_name: EditText? = null
    private var et_call: EditText? = null
    private var btn_register: Button? = null
    override fun onCreate(savedInstanceState: Bundle?) { //액티비티 시작 시 처음으로 실행되는 생명 주기
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        //find and set id
        et_id = findViewById(R.id.et_id)
        et_pass = findViewById(R.id.et_pass)
        et_name = findViewById(R.id.et_name)
        et_call = findViewById(R.id.et_call)
        btn_register = findViewById(R.id.btn_register)

        //회원가입 버튼 클릭 시 requestactivity로 넘겨주는 부분
        btn_register?.setOnClickListener(View.OnClickListener {
            //EditText에 현재 입력되어 있는 값 가져옴(get)
            val userID = et_id?.getText().toString()
            val userPassword = et_pass?.getText().toString()
            val userName = et_name?.getText().toString()
            val userCall = et_call?.getText().toString()
            //int userAge = integer.parseInt(et_age.getText().toString());
            val url = "http://54.180.149.204/Registeruser.php"
            val smpr =
                SimpleMultiPartRequest(Request.Method.POST, url, Response.Listener { response ->
                    //json object 형태로 변환
                    try {
                        val jsonObject = JSONObject(response)
                        val success = jsonObject.getBoolean("success") //PHP 파일에 success 선언
                        if (success) {
                            Toast.makeText(this, "회원 가입에 성공했습니다.", Toast.LENGTH_SHORT)
                                .show()
                            val intent = Intent(
                                this@RegisterActivity,
                                LoginActivity::class.java
                            ) //출발 액티비티, 이동할 액티비티
                            startActivity(intent)
                            finish()
                        } else {
                            Toast.makeText(this, "회원 가입에 실패했습니다.", Toast.LENGTH_SHORT)
                                .show()
                            return@Listener
                        }
                    } catch (e: JSONException) {
                        e.printStackTrace()
                    }
                }, null)
            smpr.addStringParam("userID", userID)
            smpr.addStringParam("userPassword", userPassword)
            smpr.addStringParam("userName", userName)
            smpr.addStringParam("userCall", userCall)

            //ReviewRequest reviewRequest = new ReviewRequest(rate, keyword1, keyword2, keyword3, comment, responseListener); //create class
            val queue = Volley.newRequestQueue(this@RegisterActivity)
            queue.add(smpr)
            //queue.add(reviewRequest);
        })
    }
}