package com.example.practicecopy

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import java.util.ArrayList

class MyAdapter(var inflater: LayoutInflater, var storeItems: ArrayList<storeData>) : BaseAdapter() {
    override fun getCount(): Int {
        return storeItems.size
    }

    override fun getItem(position: Int): Any {
        return storeItems[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getView(position: Int, view: View?, viewGroup: ViewGroup): View? {
        var view = view
        if (view == null) {
            view = inflater.inflate(R.layout.listview_item, viewGroup, false)
        }
        val tv = view?.findViewById<TextView>(R.id.tv_storeName)
        val iv = view?.findViewById<ImageView>(R.id.iv_storeImage)
        val tv2 = view?.findViewById<TextView>(R.id.tv_storeAddress)
        //LinearLayout clickArea = (LinearLayout)view.findViewById(R.id.clickarea);

        val storeData = storeItems[position] //리스트뷰 set 과정
        tv?.text = storeData.storeName
        tv2?.text = storeData.storeAddress
        if (view != null) {
            if (iv != null) {
                Glide.with(view).load(storeData.storeImage).into(iv)
            }
        }
        return view
    }
}