package com.example.practicecopy

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.os.Handler
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_payment.*

class PaymentActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_payment)

        //테이블번호가 orderActivity에서 intent로 넘어온다
        val tablenumber = intent.getStringExtra("tableNumber")
        // 결제완료버튼 클릭시 : 다음 액티비티로 전환, 받은 tableNumber를 다시 인텐트로 CompleteActivity에전송
        button.setOnClickListener({
            val intent=Intent(this,CompleteActivity::class.java)
            intent.putExtra("tableNumber",tablenumber)
            startActivity(intent)
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
            button.visibility=View.VISIBLE }, 10000L)


    }


}