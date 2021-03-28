package com.example.kiosk2


import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter


class FragmentAdapter(fm : FragmentManager) : FragmentPagerAdapter(fm) {
    override fun getItem(position: Int): Fragment {
        return when(position) {
            0 -> {
                FirstFragment()
            }
            1 -> {
                return FirstFragment()
            }
            else -> {
                return FirstFragment()
            }
        }
    }

    override fun getCount(): Int {
        return 3
    }

}