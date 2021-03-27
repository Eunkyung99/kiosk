package com.example.kiosk

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        한식.setOnClickListener{
            val intent= Intent(this,ResultActivity::class.java)
            startActivity(intent)
        }
        양식.setOnClickListener {
            val intent= Intent(this,ResultActivity::class.java)
            startActivity(intent)
        }
        중국집.setOnClickListener {
            val intent= Intent(this,ResultActivity::class.java)
            startActivity(intent)
        }
        분식.setOnClickListener {
            val intent= Intent(this,ResultActivity::class.java)
            startActivity(intent)
        }
        치킨.setOnClickListener {
            val intent= Intent(this,ResultActivity::class.java)
            startActivity(intent)
        }
        피자.setOnClickListener {
            val intent= Intent(this,ResultActivity::class.java)
            startActivity(intent)
        }
        카페디저트.setOnClickListener {
            val intent= Intent(this,ResultActivity::class.java)
            startActivity(intent)
        }
        야식.setOnClickListener {
            val intent= Intent(this,ResultActivity::class.java)
            startActivity(intent)
        }
    }


}