package com.example.kiosk

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.activity_main.*

class login : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        loginButton.setOnClickListener{
            val intent= Intent(this,ResultActivity::class.java)
            intent.putExtra("id",idInput.text.toString())
            intent.putExtra("password",passwordInput.text.toString())
            startActivity(intent)
        }
        signupButton.setOnClickListener{
            val intent= Intent(this,ResultActivity::class.java)
            startActivity(intent)
        }
    }
}