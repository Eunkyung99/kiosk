package com.example.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        button.setOnClickListener{
            val intent= Intent(this,MenuActivity::class.java)
            startActivity(intent)
        }

        button2.setOnClickListener{
            val intent= Intent(this,LoginActivity::class.java)
            startActivity(intent)
        }
    }

}