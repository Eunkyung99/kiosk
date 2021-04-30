package com.example.practicecopy.Fragment

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter

class FragmentAdpater(fm : FragmentManager) : FragmentPagerAdapter(fm){
    private val fragmentTitleList = mutableListOf("A","B","C","D","E","F","G","H","I")

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
            5 -> {
                SixthFragment()
            }
            6 -> {
                SeventhFragment()
            }
            7 -> {
                EighthFragment()
            }
            else -> {
                NinethFragment()
            }
        }
    }

    override fun getCount(): Int {
        return 9
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return fragmentTitleList[position]
        //return null
    }

}