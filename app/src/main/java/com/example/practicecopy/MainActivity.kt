package com.example.practicecopy

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.viewpager.widget.ViewPager
import com.example.practicecopy.Fragment.*
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    var btn_log: Button? = null

    internal lateinit var viewpager : ViewPager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btn_log = findViewById(R.id.btn_log)
        btn_log?.setOnClickListener(View.OnClickListener {
            val intent = Intent(this@MainActivity, LoginActivity::class.java)
            startActivity(intent)
        })

        val userID = intent.getStringExtra("userID")
        val userName = intent.getStringExtra("userName")
        val sign = intent.getIntExtra("sign", 2)
        if(sign==1){
            btn_log?.setText(userName)
        }


        val img = arrayOf(
            R.drawable.korean,
            R.drawable.chinese,
            R.drawable.japanese,
            R.drawable.western,
            R.drawable.meat,
            R.drawable.snack,
            R.drawable.fastfood,
            R.drawable.dessert,
            R.drawable.asian
        )

        val text = arrayOf(
            "한식",
            "중식",
            "일식",
            "양식",
            "고기",
            "분식",
            "패스트푸드",
            "디저트",
            "아시안"
        )

        val category = arrayOf(
            1001,
            1002,
            1003,
            1004,
            1005,
            1006,
            1007,
            1008,
            1009
        )

        val gridviewAdapter = GridviewAdapter(this, img, text, category)
        gridview.adapter = gridviewAdapter

        gridview.setOnItemClickListener{adapterView, view, position, l ->
            val intent = Intent(this, StoreListActivity::class.java)
            val categoryID = category[position]
            intent.putExtra("categoryID", categoryID)
            intent.putExtra("userID", userID)
            startActivity(intent)
        }
    }
}