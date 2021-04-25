package com.example.myapplication3

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {


    var orderList = arrayListOf<Order>(
        Order("삼선짜장", "6,500원", "곱배기", "dog00"),
        Order("짬뽕", "5,000원", "", "dog01"),
        Order("탕수육", "11,000", "소", "dog02"),


    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val orderAdapter = OrderListAdapter(this, orderList)
        listView.adapter = orderAdapter
    }
}