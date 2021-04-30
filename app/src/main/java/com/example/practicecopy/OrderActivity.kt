package com.example.practicecopy

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_order.*

class OrderActivity : AppCompatActivity() {
    lateinit var dbHelper : DBHelper
    lateinit var database :SQLiteDatabase

    var orderList = arrayListOf<orderData>(
        orderData("삼선짜장", 6500, "곱빼기", "dog00")
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        System.out.println(orderList)
        System.out.println("OrderActivity 들어옴")
        dbHelper = DBHelper(this, "newdb.db", null, 1)
        database = dbHelper.writableDatabase

        selectOrder()
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_order)
        val orderAdapter = OrderListAdapter(this, orderList)
        listView.adapter = orderAdapter

    }
        fun selectOrder() {
            System.out.println("SelectOrder 들어옴")
            val Order = "select * from mytable"
            var cursor = database.rawQuery(Order, null)
            try {
                if (cursor != null) {
                    if (cursor.moveToFirst()) {
                        do {
                            val menuID = cursor.getLong(cursor.getColumnIndex("menuID"))
                            val menuName = cursor.getString(cursor.getColumnIndex("menuName"))
                            System.out.println("Order : " + menuName)
                            val optionID = cursor.getString(cursor.getColumnIndex("optionID"))
                            val price = cursor.getLong(cursor.getColumnIndex("price"))
                            val count = cursor.getString(cursor.getColumnIndex("count"))
                            orderList.add(orderData(menuName, price.toInt(), optionID, count))
                            System.out.println(orderList)
                        } while (cursor.moveToNext())
                    }
                    cursor.close()

                }
            } finally {
                database.close()
            }
        }
    }
