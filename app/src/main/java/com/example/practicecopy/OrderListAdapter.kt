package com.example.practicecopy

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView

class OrderListAdapter (val context: Context, val OrderList: ArrayList<orderData>) : BaseAdapter() {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        /* LayoutInflater */
        val view: View = LayoutInflater.from(context).inflate(R.layout.order_item, null)

        /* 위에서 생성된 view를 res-layout-order_item.xml 파일의 각 View와 연결 */
        val Del = view.findViewById<ImageButton>(R.id.trash)
        val menu = view.findViewById<TextView>(R.id.menuTv)
        val option = view.findViewById<TextView>(R.id.optionTv)
        val option2 = view.findViewById<TextView>(R.id.optionTv2)
        val option3 = view.findViewById<TextView>(R.id.optionTv3)
        val option4 = view.findViewById<TextView>(R.id.optionTv4)
        val price = view.findViewById<TextView>(R.id.priceTv)
        val number = view.findViewById<TextView>(R.id.number)

        /* ArrayList<Order>의 변수 Item의 이미지와 데이터를 ImageView와 TextView에 담는다. */
        val Item = OrderList[position]
        //val resourceId = context.resources.getIdentifier(Item.photo, "drawable", context.packageName)
        //Img.setImageResource(resourceId).toString() (왠지모르게 오류!)
        menu.text = Item.menu
        option.text = Item.option
        /*
        option2.text = Item.option2
        option3.text = Item.option3
        option4.text = Item.option4
        */
        number.text = Item.count.toString()
        price.text = Item.price.toString()

        Del.setOnClickListener(View.OnClickListener {
            //하나의 아이템 database 삭제 코드
            //값들은 Item.menu, Item.option, Item.option2.. Item.price 이런 식
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