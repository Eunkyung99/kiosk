package com.example.practicecopy

import android.app.Activity
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.MotionEvent
import android.view.View
import android.view.Window
import android.widget.AdapterView
import android.widget.GridView
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_option.*
import kotlinx.android.synthetic.main.optionview_item.view.*
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.MalformedURLException
import java.net.URL
import java.util.ArrayList
import java.io.Serializable


class OptionActivity : Activity() {
    var gridview: GridView? = null
    var optionviewAdapter: OptionviewAdapter? = null
    var optionItems = ArrayList<optionData>()
    var totalOption = ArrayList<optionSelected>()
    var totalPrice = 0
    var menuID = 0
    var optionID = 0
    var count = 1
    var basePrice = 0
    var optionName = ""
    var menuName : String? = ""
    var lock : Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //타이틀바 없애기
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        setContentView(R.layout.activity_option)

        //데이터 가져오기
        val intent = intent
        menuID = intent.getIntExtra("menuID", 1)
        val price = intent.getIntExtra("price", 0)
        menuName = intent.getStringExtra("menuName")
        val tv = findViewById<TextView>(R.id.tv_total)
        basePrice = price //가격 업데이트
        totalPrice += basePrice
        tv.text = basePrice.toString()

        loadDB(menuID)

        gridview = findViewById(R.id.gv_option)
        optionviewAdapter = OptionviewAdapter(layoutInflater, optionItems)
        gridview?.setAdapter(optionviewAdapter)
        gridview?.setOnItemClickListener(AdapterView.OnItemClickListener { parent, view, position, id ->
            var selected = optionItems[position].selected
            if (!lock) {
                if (!selected) { //옵션 선택
                    optionItems[position].selected = true
                    optionID = optionItems[position].optionID //변수
                    optionName = optionItems[position].optionName
                    totalOption.add(optionSelected(menuID, optionID, optionName)) //배열로 저장
                    val optionPrice = optionItems[position].price //변수
                    basePrice += optionPrice
                    totalPrice += optionPrice
                    tv.text = totalPrice.toString()
                    view.tv_optionname.setTextColor(Color.parseColor("#E91E63"))
                    view.tv_optionprice.setTextColor(Color.parseColor("#E91E63"))
                    //view.setBackgroundColor(Color.GRAY)
                    //배열에 옵션ID 배열(옵션 여러 개 가능), 메뉴ID, 가격(totalPrice), 수량 저장하기
                } else { //옵션 선택 해제
                    optionItems[position].selected = false
                    optionID = optionItems[position].optionID
                    optionName = optionItems[position].optionName
                    totalOption.remove(optionSelected(menuID, optionID, optionName)) //배열에서 삭제
                    val optionPrice = optionItems[position].price
                    basePrice -= optionPrice
                    totalPrice -= optionPrice
                    tv.text = totalPrice.toString()
                    //view.setBackgroundColor(Color.TRANSPARENT)
                    view.tv_optionname.setTextColor(Color.parseColor("#FF000000"))
                    view.tv_optionprice.setTextColor(Color.parseColor("#FF000000"))
                    //배열에 옵션ID 배열(옵션 여러 개 가능), 메뉴ID, 가격(totalPrice), 수량 저장하기
                }
            }

        })
    }

    private fun loadDB(menuID: Int) {
        object : Thread() {
            override fun run() {
                val serverUri = "http://54.180.149.204/option.php"
                /* 한글 전송 처리
                try {
                    storeName = URLEncoder.encode(storeName, "utf-8")
                } catch (e: UnsupportedEncodingException) {
                    e.printStackTrace()
                }
                */
                val getUrl = "$serverUri?menuID=$menuID" //add
                try {
                    //val url = URL(serverUri)
                    val url = URL(getUrl) //add
                    val connection = url.openConnection() as HttpURLConnection
                    connection.requestMethod = "GET"
                    connection.doInput = true
                    connection.doOutput = true //add
                    connection.useCaches = false
                    val `is` = connection.inputStream
                    val isr = InputStreamReader(`is`)
                    val reader = BufferedReader(isr)
                    val buffer = StringBuffer()
                    var line = reader.readLine()
                    while (line != null) {
                        buffer.append(line + "")
                        line = reader.readLine()
                    }
                    // 데이터 확인
                    //runOnUiThread { AlertDialog.Builder(this@CafelistActivity).setMessage(buffer.toString()).create().show() }

                    //읽어온 문자열에서 레코드(row) 별로 분리하며 배열로 리턴하기
                    val rows = buffer.toString().split(";").toTypedArray()

                    //대량의 데이터 초기화
                    optionItems.clear()
                    runOnUiThread { optionviewAdapter!!.notifyDataSetChanged() }

                    for (row in rows) {
                        //한 줄 데이터에서 한 칸씩 분리
                        val datas = row.split("&").toTypedArray()
                        if (datas.size != 5) continue //항목 5개
                        //변수 이름 변경 완료!!
                        val optionID = datas[0].toInt() //int 형으로 받기
                        val menuID = datas[1].toInt()
                        val optionName = datas[2]
                        val price = datas[3].toInt()
                        val optionImage = "http://54.180.149.204/option/" + datas[4] //이미지 상대 경로이므로
                        optionItems.add(optionData(optionID, menuID, optionName, price, optionImage))

                        //그리드뷰 갱신
                        runOnUiThread { optionviewAdapter!!.notifyDataSetChanged() }
                    }

                } catch (e: MalformedURLException) {
                    e.printStackTrace()
                } catch (e: IOException) {
                    e.printStackTrace()
                }
            }
        }.start()
    }

    fun plusCount(v: View?) {
        lock=true //option 변경 불가 
        count+=1
        totalPrice=basePrice*count
        val tv_plus = findViewById<TextView>(R.id.tv_total)
        tv_plus.text = totalPrice.toString()
    }

    fun minusCount(v: View?) {
        lock=true //option 변경  불가
        if(count>1)
            count-=1
        totalPrice=basePrice*count
        val tv_minus = findViewById<TextView>(R.id.tv_total)
        tv_minus.text = totalPrice.toString()
    }

    //확인 버튼 클릭
    fun mOnClose(v: View?) {
        //액티비티(팝업) 닫기
        finish()
    }

    fun mOnSelect(v: View?) {
        //액티비티(팝업) 닫기
        val returnIntent = Intent()
        //메뉴 화면에 옵션 이름, 메뉴 이름, 총 가격 전송하기
        returnIntent.putExtra("price", totalPrice)
        returnIntent.putExtra("menuID", menuID)
        returnIntent.putExtra("menuName", menuName)
        returnIntent.putExtra("count", count)
        if (totalOption.toString()=="[]"){
            returnIntent.putExtra("optionSelected", optionSelected(menuID, 0, ""))
        }
        else { returnIntent.putExtra("optionSelected",totalOption) }
        setResult(Activity.RESULT_OK, returnIntent)
        finish()
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        //바깥레이어 클릭시 안닫히게
        return if (event.action == MotionEvent.ACTION_OUTSIDE) {
            false
        } else true
    }

    override fun onBackPressed() {
        //안드로이드 백버튼 막기
        return
    }

    fun Delete(view: View) {}
}