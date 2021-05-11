package com.example.practicecopy

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.os.Handler
import android.view.View
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.request.SimpleMultiPartRequest
import com.android.volley.toolbox.Volley
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_payment.*
import org.json.JSONException
import org.json.JSONObject

class PaymentActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_payment)

        //테이블번호가 orderActivity에서 intent로 넘어온다
        val userID = intent.getStringExtra("userID")
        val tablenumber = intent.getStringExtra("tableNumber")
        val storeID = intent.getIntExtra("storeID", 0)

        // 결제완료버튼 클릭시 : 다음 액티비티로 전환, 받은 tableNumber를 다시 인텐트로 CompleteActivity에전송
        button.setOnClickListener({
            val url = "http://54.180.149.204/Order.php"
            val smpr =
                SimpleMultiPartRequest(Request.Method.POST, url, Response.Listener { response ->
                    //json object 형태로 변환
                    try {
                        val jsonObject = JSONObject(response)
                        val success = jsonObject.getBoolean("success") //PHP 파일에 success 선언
                        if (success) {
                            Toast.makeText(this, "주문에 성공했습니다.", Toast.LENGTH_SHORT)
                                .show()
                            val intent=Intent(this,CompleteActivity::class.java)
                            //intent.putExtra("tableNumber",tablenumber)
                            //intent.putExtra("userID", userID)
                            startActivity(intent)
                            finish()
                        } else {
                            Toast.makeText(this, "주문에 실패했습니다.", Toast.LENGTH_SHORT)
                                .show()
                            return@Listener
                        }
                    } catch (e: JSONException) {
                        e.printStackTrace()
                    }
                }, null)
            smpr.addStringParam("userID", userID)
            smpr.addStringParam("tableID", tablenumber)
            smpr.addStringParam("storeID", storeID.toString())

            //ReviewRequest reviewRequest = new ReviewRequest(rate, keyword1, keyword2, keyword3, comment, responseListener); //create class
            val queue = Volley.newRequestQueue(this@PaymentActivity)
            queue.add(smpr)
        })


        //결제완료버튼 숨기기
        button.visibility = View.INVISIBLE

        //애니메이션 효과 시작
        val loadingAnimDialog = CustomLoadingDialog(this)
        loadingAnimDialog.show()

        //10초 뒤에 다음을 실행. Handler().postDelayed({ }, 4000L)함수 사용함.
        Handler().postDelayed({
            //로딩 애니메이션효과 중단
            loadingAnimDialog.dismiss()
            //결제완료버튼 띄우기
            button.visibility=View.VISIBLE }, 5000L)


    }


}