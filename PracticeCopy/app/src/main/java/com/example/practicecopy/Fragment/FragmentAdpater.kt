package com.example.practicecopy.Fragment

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter

class FragmentAdpater(fm : FragmentManager) : FragmentPagerAdapter(fm){
    override fun getItem(position: Int): Fragment {
        return when(position){
            0 -> {
                FirstFragment()
            }
            1 -> {
                SecondFragment()
            }
            2 -> {
                ThirdFragment()
            }
            3 -> {
                FourthFragment()
            }
            4 -> {
                FifthFragment()
            }
            else -> {
                SixthFragment()
            }
        }
    }

    override fun getCount(): Int {
        return 6
    }

}