package com.example.practicecopy

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.gridview_item.view.*
import java.util.ArrayList

class OptionviewAdapter(var inflater: LayoutInflater, var optionItems: ArrayList<optionData>) : BaseAdapter(){

    override fun getCount(): Int {
        return optionItems.size
    }

    override fun getItem(position: Int): Any {
        return optionItems[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getView(position: Int, view: View?, viewGroup: ViewGroup): View? {
        var view = view
        if (view == null) {
            view = inflater.inflate(R.layout.menuview_item, viewGroup, false)
        }
        val iv = view?.findViewById<ImageView>(R.id.iv_menu)
        val tv = view?.findViewById<TextView>(R.id.tv_menuname)
        val tv2 = view?.findViewById<TextView>(R.id.tv_price)
        //LinearLayout clickArea = (LinearLayout)view.findViewById(R.id.clickarea);

        val optionData = optionItems[position] //리스트뷰 set 과정
        tv?.text = optionData.optionName
        tv2?.text = optionData.price.toString()
        if (view != null) {
            if (iv != null) {
                Glide.with(view).load(optionData.optionImage).into(iv)
            }
        }
        return view
    }

}