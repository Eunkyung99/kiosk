package com.example.practicecopy

import android.content.Context
import android.content.Intent
import android.database.sqlite.SQLiteDatabase
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageButton
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_order.*
import kotlin.properties.Delegates

class OrderActivity : AppCompatActivity() {
    lateinit var dbHelper : DBHelper
    lateinit var database :SQLiteDatabase

    var orderList = arrayListOf<orderData>()
    var orderPrice = 0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_order)

        //결제버튼클릭하면 paymentActivity로 테이블번호 intent로 전송및 액티비티전환
        button.setOnClickListener{
            val intent= Intent(this,PaymentActivity::class.java)
            intent.putExtra("tableNumber",editTextTextPersonName2.text.toString())
            startActivity(intent)
        }

        class OrderListAdapter (val context: Context, val OrderList: ArrayList<orderData>) : BaseAdapter() {

            override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {

                /* LayoutInflater */
                val view: View = LayoutInflater.from(context).inflate(R.layout.order_item, null)

                /* 위에서 생성된 view를 res-layout-order_item.xml 파일의 각 View와 연결 */
                val Del = view.findViewById<ImageButton>(R.id.trash)
                val menu = view.findViewById<TextView>(R.id.menuTv)
                val option = view.findViewById<TextView>(R.id.optionTv)
                val price = view.findViewById<TextView>(R.id.priceTv)
                val number = view.findViewById<TextView>(R.id.number)
                val priceView = findViewById<TextView>(R.id.orderprice)

                /* ArrayList<Order>의 변수 Item의 이미지와 데이터를 ImageView와 TextView에 담는다. */
                val Item = OrderList[position]
                menu.text = Item.menu
                option.text = Item.option
                number.text = Item.count.toString()
                price.text = Item.price.toString()


                Del.setOnClickListener(View.OnClickListener {

                    val ID = Item.ID
                    val Price = Item.price
                    orderPrice -= Price
                    priceView?.text = orderPrice.toString()
                    OrderList.removeAt(position)
                    database.execSQL("delete from " +"mytable"+ " WHERE id ="+ID)
                    database.execSQL("delete from " +"OptionTable"+ " WHERE id ="+ID)

                    this.notifyDataSetChanged()
                })

                return view
            }
            override fun getItem(position: Int): Any {
                return OrderList[position]
            }

            override fun getItemId(position: Int): Long {
                return 0
            }

            override fun getCount(): Int {
                return OrderList.size
            }

        }

        //totalPrice 값은 확인
        //아래 부분 적용이 안 됨

        val priceView = findViewById<TextView>(R.id.orderprice)

        dbHelper = DBHelper(this, "newdb.db", null, 1)
        database = dbHelper.writableDatabase
        selectOrder()
        priceView?.text = orderPrice.toString()
        val orderAdapter = OrderListAdapter(this, orderList)
        listView.adapter = orderAdapter
    }
    fun selectOrder() {

            val Order = "select mytable.id, mytable.menuID, mytable.menuName, mytable.price, mytable.count, OptionTable.optionID, OptionTable.OptionName" +
                    " from mytable LEFT OUTER JOIN OptionTable ON mytable.id = OptionTable.id"
            var cursor = database.rawQuery(Order, null)
            try {
                if (cursor != null) {
                    if (cursor.moveToFirst()) {
                        do {
                            val ID = cursor.getString(cursor.getColumnIndex("id"))
                            val menuID = cursor.getLong(cursor.getColumnIndex("menuID"))
                            val menuName = cursor.getString(cursor.getColumnIndex("menuName"))
                            val price = cursor.getLong(cursor.getColumnIndex("price"))
                            val count = cursor.getString(cursor.getColumnIndex("count"))
                            val OptionName = cursor.getString(cursor.getColumnIndex("OptionName"))

                            orderPrice += price.toInt()
                            orderList.add(orderData(menuName, price.toInt(), OptionName, count.toInt(),menuID.toInt(),ID.toInt()))

                        } while (cursor.moveToNext())
                    }
                    cursor.close()

                }
            } finally {
            }
        }
    fun Delete(view: View) { //전체 삭제 기능
        database.execSQL("delete from " +"mytable")
        database.execSQL("delete from " +"OptionTable")
        finish()

    }




}
