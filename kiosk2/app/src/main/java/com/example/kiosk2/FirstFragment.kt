package com.example.kiosk2

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.fragment_first.view.*


class FirstFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragmentz

        val view: View = inflater.inflate(R.layout.fragment_first, container, false)


        val list_array = arrayListOf<ContentsListModel>(
            ContentsListModel(R.drawable.ai,"b",1, "d"),
            ContentsListModel(R.drawable.ai,"b",1, "d"),
            ContentsListModel(R.drawable.ai,"b",1, "d"),
            ContentsListModel(R.drawable.ai,"b",1, "d"),
            ContentsListModel(R.drawable.ai,"b",1, "d"),




        )


        val list_adapter = FirstFragAdapter(requireContext(), list_array)
        view.listview_first_fragment.adapter=list_adapter


        return view
    }
}