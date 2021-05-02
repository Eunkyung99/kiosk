package com.example.practicecopy

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_order.*

class OrderActivity : AppCompatActivity() {
    lateinit var dbHelper : DBHelper
    lateinit var database :SQLiteDatabase

    var orderList = arrayListOf<orderData>()

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
            val Order = "select mytable.menuID, mytable.menuName, mytable.price, mytable.count, OptionTable.optionID, OptionTable.OptionName" +
                    " from mytable LEFT OUTER JOIN OptionTable ON mytable.menuID = OptionTable.menuID"
            var cursor = database.rawQuery(Order, null)
            try {
                if (cursor != null) {
                    if (cursor.moveToFirst()) {
                        do {
                            val menuID = cursor.getLong(cursor.getColumnIndex("menuID"))
                            val menuName = cursor.getString(cursor.getColumnIndex("menuName"))
                            val optionID = cursor.getString(cursor.getColumnIndex("optionID"))
                            val price = cursor.getLong(cursor.getColumnIndex("price"))
                            val count = cursor.getString(cursor.getColumnIndex("count"))
                            val OptionName = cursor.getString(cursor.getColumnIndex("OptionName"))
                            orderList.add(orderData(menuName, price.toInt(), OptionName, count))
                            System.out.println(orderList)
                        } while (cursor.moveToNext())
                    }
                    cursor.close()

                }
            } finally {
            }
        }

    fun Delete(view: View) { //전체 삭제 기능
        database.execSQL("delete from " +"mytable")
        finish()

    }


}
