package com.example.practicecopy

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DBHelper(context: Context?, name: String?, factory: SQLiteDatabase.CursorFactory?, version: Int) : SQLiteOpenHelper(context, name, factory, version) {

    override fun onCreate(db: SQLiteDatabase) {
        val COL_ID = "id"
        val COL_menuID ="menuID"
        val COL_menuName ="menuName"
        val COL_Count ="count"
        val COL_totalOption ="totalOption"
        val COL_price ="price"
        val COL_optionID ="optionID"
        var sql : String = "CREATE TABLE if not exists mytable"+"(" +
                COL_ID + " integer primary key autoincrement," +
                COL_menuID + " integer," +
                COL_menuName + " TEXT," +
                COL_Count + " integer," +
                COL_totalOption + " integer," +
                COL_price + " integer," +
                COL_optionID + " integer" +
                ")"
        db.execSQL(sql)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        val sql : String = "DROP TABLE if exists mytable"

        db.execSQL(sql)
        onCreate(db)
    }

}