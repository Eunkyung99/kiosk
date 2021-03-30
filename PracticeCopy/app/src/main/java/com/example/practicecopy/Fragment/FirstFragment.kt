package com.example.practicecopy.Fragment

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.core.content.ContextCompat.getDrawable
import com.example.practicecopy.ContentListModel
import com.example.practicecopy.FirstFragAdapter
import com.example.practicecopy.R
import kotlinx.android.synthetic.main.fragment_first.view.*
import java.util.*

class FirstFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view : View = inflater.inflate(R.layout.fragment_first, container, false)

        val list_array = arrayListOf<ContentListModel>(

            ContentListModel((R.drawable.chinese_food), "b0", 1, "d0"),
            ContentListModel((R.drawable.korean_food), "b1", 2, "d1"),
            ContentListModel((R.drawable.western_style), "b2", 3, "d2"),
            ContentListModel((R.drawable.snack_bar), "b3", 4, "d3"),
            ContentListModel((R.drawable.chicken_pizza), "b4", 5, "d4"),
            ContentListModel((R.drawable.cafe_dessert), "b5", 6, "d5")
        )

        val list_adapter = FirstFragAdapter(requireContext(), list_array)
        view.listview_first_fragment.adapter = list_adapter

        return view
    }
}