package com.example.practicecopy


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class CompleteActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_complete)

        //테이블번호가 PaymentActivity에서 intent로 넘어온다
        val tablenumber = intent.getStringExtra("tableNumber")
        val userID = intent.getStringExtra("userID")

    }
}