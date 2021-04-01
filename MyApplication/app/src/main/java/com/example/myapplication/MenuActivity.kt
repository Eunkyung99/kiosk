package com.example.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_menu.*

class MenuActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu)

        pay.setOnClickListener{
            val intent= Intent(this,OrderActivity::class.java)
            /*orderActivity로 넘어갈때 인텐트에 선택한 메뉴에 관한 데이터를 담아주는 코드 작성필요 */
            startActivity(intent)
        }
        /*fragment 하나를 클릭할때마다 합계 텍스트뷰가 업데이트되는 코드작성필요*
        sum. */

        //짜장류 메뉴의 사진들 및 이름들
        val img1 = arrayOf(
                R.drawable.food,
                R.drawable.food,
                R.drawable.food,
                R.drawable.food,
                R.drawable.food,
                R.drawable.food
        )
        val txt1 = arrayOf(
                "삼선짜장",
                "쟁반짜장",
                "fresh",
                "fresh",
                "fresh",
                "fresh"
        )
        //짬뽕류 메뉴의 사진들 및 이름들
        val img2 = arrayOf(
                R.drawable.food,
                R.drawable.food,
                R.drawable.food,
                R.drawable.food,
                R.drawable.food,
                R.drawable.food
        )
        val txt2 = arrayOf(
                "삼선짬뽕",
                "차돌짬뽕",
                "fresh",
                "fresh",
                "fresh",
                "fresh"
        )
        //밥류 메뉴의 사진들 및 이름들
        val img3 = arrayOf(
                R.drawable.food,
                R.drawable.food,
                R.drawable.food,
                R.drawable.food,
                R.drawable.food,
                R.drawable.food
        )
        val txt3 = arrayOf(
                "짜장 볶음밥",
                "게살 볶음밥",
                "유산슬 덮밥",
                "fresh",
                "fresh",
                "fresh"
        )
        //음료 메뉴의 사진들 및 이름들
        val img4 = arrayOf(
                R.drawable.food,
                R.drawable.food,
                R.drawable.food,
                R.drawable.food,
                R.drawable.food,
                R.drawable.food
        )
        val txt4 = arrayOf(
                "fresh",
                "fresh",
                "fresh",
                "fresh",
                "fresh",
                "fresh"
        )

        val gridviewAdapter1 = GridviewAdapter(this, img1, txt1)
        gridview1.adapter = gridviewAdapter1

        val gridviewAdapter2=GridviewAdapter(this, img2, txt2)
        gridview2.adapter = gridviewAdapter2

        val gridviewAdapter3=GridviewAdapter(this, img3, txt3)
        gridview3.adapter = gridviewAdapter3

        val gridviewAdapter4=GridviewAdapter(this, img4, txt4)
        gridview4.adapter = gridviewAdapter4



    }
}