package com.example.practicecopy

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import kotlinx.android.synthetic.main.custom_loading_dialog.*

class CustomLoadingDialog(context: Context):Dialog(context) {

    lateinit var turnHorizontal : Animation

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.custom_loading_dialog)

        turnHorizontal = AnimationUtils.loadAnimation(context, R.anim.turn_horizontal)

        loading_img.startAnimation(turnHorizontal)
        //loading_img2.startAnimation(turnHorizontal)
    }
}