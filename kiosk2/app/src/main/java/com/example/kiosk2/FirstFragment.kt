package com.example.kiosk2

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment


class FirstFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragmentz

        val view: View = inflater.inflate(R.layout.fragment_first, container, false)


        val list_array = arrayListOf<ContentsListModel>(




        )


        val list_adapter = FirstFragAdapter(requireContext(), list_array)


        return view
    }
}