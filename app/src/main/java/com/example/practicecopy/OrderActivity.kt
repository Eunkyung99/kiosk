package com.example.practicecopy

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_order.*

class OrderActivity : AppCompatActivity() {

    var orderList = arrayListOf<orderData>(
        orderData("삼선짜장", 6500, "곱빼기", "dog00") //이 부분 데이터베이스에서 출력하는걸로 변경
    )
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_order)
        val orderAdapter = OrderListAdapter(this, orderList)
        listView.adapter = orderAdapter
    }
}