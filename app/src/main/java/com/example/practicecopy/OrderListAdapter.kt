package com.example.practicecopy

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView

class OrderListAdapter (val context: Context, val OrderList: ArrayList<orderData>) : BaseAdapter() {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        /* LayoutInflater */
        val view: View = LayoutInflater.from(context).inflate(R.layout.order_item, null)

        /* 위에서 생성된 view를 res-layout-order_item.xml 파일의 각 View와 연결 */
        val Img = view.findViewById<ImageView>(R.id.PhotoImg)
        val menu = view.findViewById<TextView>(R.id.menuTv)
        val option = view.findViewById<TextView>(R.id.optionTv)
        val price = view.findViewById<TextView>(R.id.priceTv)

        /* ArrayList<Order>의 변수 Item의 이미지와 데이터를 ImageView와 TextView에 담는다. */
        val Item = OrderList[position]
        val resourceId = context.resources.getIdentifier(Item.photo, "drawable", context.packageName)
        Img.setImageResource(resourceId)
        menu.text = Item.menu
        option.text = Item.option
        price.text = Item.price.toString()

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