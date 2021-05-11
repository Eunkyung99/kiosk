package com.example.practicecopy

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import com.example.practicecopy.Fragment.*
import com.google.android.material.tabs.TabLayout
import kotlinx.android.synthetic.main.activity_store_list.*
import kotlinx.android.synthetic.main.custom_tab.view.*

class StoreListActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_store_list)

        val categoryID = intent.getIntExtra("categoryID", 1001)

        val fragmentAdapter = FragmentAdpater(supportFragmentManager)
        list_viewpager.adapter = fragmentAdapter

        var pos = categoryID-1001
        list_viewpager.currentItem = pos

        tab_layout.addTab(tab_layout.newTab().setCustomView(createTabView("한식")))
        tab_layout.addTab(tab_layout.newTab().setCustomView(createTabView("중식")))
        tab_layout.addTab(tab_layout.newTab().setCustomView(createTabView("일식")))
        tab_layout.addTab(tab_layout.newTab().setCustomView(createTabView("양식")))
        tab_layout.addTab(tab_layout.newTab().setCustomView(createTabView("고기")))
        tab_layout.addTab(tab_layout.newTab().setCustomView(createTabView("분식")))
        tab_layout.addTab(tab_layout.newTab().setCustomView(createTabView("패스트푸드")))
        tab_layout.addTab(tab_layout.newTab().setCustomView(createTabView("디저트")))
        tab_layout.addTab(tab_layout.newTab().setCustomView(createTabView("아시안")))



        tab_layout.getTabAt(0)?.setIcon(R.drawable.korean)
        tab_layout.getTabAt(1)?.setIcon(R.drawable.chinese)
        tab_layout.getTabAt(2)?.setIcon(R.drawable.japanese)
        tab_layout.getTabAt(3)?.setIcon(R.drawable.western)
        tab_layout.getTabAt(4)?.setIcon(R.drawable.meat)
        tab_layout.getTabAt(5)?.setIcon(R.drawable.snack)
        tab_layout.getTabAt(6)?.setIcon(R.drawable.fastfood)
        tab_layout.getTabAt(7)?.setIcon(R.drawable.dessert)
        tab_layout.getTabAt(8)?.setIcon(R.drawable.asian)

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