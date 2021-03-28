package com.example.kiosk2

import android.os.Parcel
import android.os.Parcelable

data class ContentsListModel(

    var image : Int,
    var title : String,
    var number : Int,
    var category : String

)