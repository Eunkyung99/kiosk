package com.example.practicecopy

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import com.example.practicecopy.Fragment.FragmentAdpater
import com.google.android.material.tabs.TabLayout
import kotlinx.android.synthetic.main.activity_lecture.*
import kotlinx.android.synthetic.main.custom_tab.view.*

class LectureActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lecture)

        val fragmentAdapter = FragmentAdpater(supportFragmentManager)
        list_viewpager.adapter = fragmentAdapter

        tab_layout.addTab(tab_layout.newTab().setCustomView(createTabView("Chinese_food")))
        tab_layout.addTab(tab_layout.newTab().setCustomView(createTabView("Korean_food")))
        tab_layout.addTab(tab_layout.newTab().setCustomView(createTabView("Western_style")))
        tab_layout.addTab(tab_layout.newTab().setCustomView(createTabView("Snack_bar")))
        tab_layout.addTab(tab_layout.newTab().setCustomView(createTabView("Chicken_Pizza")))
        tab_layout.addTab(tab_layout.newTab().setCustomView(createTabView("Cafe_dessert")))

        list_viewpager.addOnPageChangeListener(TabLayout.TabLayoutOnPageChangeListener(tab_layout))

        tab_layout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener{
            override fun onTabReselected(tab: TabLayout.Tab?) {

            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {

            }

            override fun onTabSelected(tab: TabLayout.Tab?) {
                if (tab != null) {
                    list_viewpager.currentItem = tab.position
                }
            }

        })
    }

    private fun createTabView(tabName : String) : View {
        val tabView = LayoutInflater.from(baseContext).inflate(R.layout.custom_tab, null)

        tabView.txt_name.text = tabName

        return tabView
    }


}