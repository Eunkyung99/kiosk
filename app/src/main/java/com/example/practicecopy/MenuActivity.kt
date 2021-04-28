package com.example.practicecopy

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.Button
import android.widget.GridView
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_menu.*
import kotlinx.android.synthetic.main.fragment_first.view.*
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.MalformedURLException
import java.net.URL
import java.util.ArrayList

class MenuActivity : AppCompatActivity() {
    var gridview1: GridView? = null
    var menuviewAdapter1: MenuviewAdapter? = null
    var menuItems = ArrayList<menuData>()
    var gridview2: GridView? = null
    var menuviewAdapter2: MenuviewAdapter? = null
    var menuItems2 = ArrayList<menuData>()
    var gridview3: GridView? = null
    var menuviewAdapter3: MenuviewAdapter? = null
    var menuItems3 = ArrayList<menuData>()
    var gridview4: GridView? = null
    var menuviewAdapter4: MenuviewAdapter? = null
    var menuItems4 = ArrayList<menuData>()

    var menu = 0
    var optionID = 0
    var price = 0
    var count = 0

    private var tv_price : TextView? = null

    var totalPrice = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu)

        val intent = intent

        val categoryID = intent.getIntExtra("categoryID", 1001)
        val storeID = intent.getIntExtra("storeID", 1)
        val storeName = intent.getStringExtra("storeName")
        val storeImage = intent.getStringExtra("storeImage")
        val storeAddress = intent.getStringExtra("storeAddress")
        store.setText(storeName)

        tv_price= findViewById<TextView>(R.id.totalprice)
        loadDB(storeID)

        gridview1 = findViewById(R.id.gridview1)
        menuviewAdapter1 = MenuviewAdapter(layoutInflater, menuItems)
        gridview1?.setAdapter(menuviewAdapter1)
        gridview1?.setOnItemClickListener(AdapterView.OnItemClickListener { parent, view, position, id ->
            val menuID = menuItems[position].menuID
            val price = menuItems[position].price
            val menuName = menuItems[position].menuName
            val intent = Intent(applicationContext, OptionActivity::class.java)
            intent.putExtra("price", price)
            intent.putExtra("menuID", menuID)
            intent.putExtra("menuName", menuName)
            startActivityForResult(intent, 99) //옵션 액티비티에서 값 전달 받기 위해
        })

        gridview2 = findViewById(R.id.gridview2)
        menuviewAdapter2 = MenuviewAdapter(layoutInflater, menuItems2)
        gridview2?.setAdapter(menuviewAdapter2)
        gridview2?.setOnItemClickListener(AdapterView.OnItemClickListener { parent, view, position, id ->
            val menuID2 = menuItems2[position].menuID
            val price2 = menuItems2[position].price
            val menuName2 = menuItems2[position].menuName
            val intent = Intent(applicationContext, OptionActivity::class.java)
            intent.putExtra("price", price2)
            intent.putExtra("menuID", menuID2)
            intent.putExtra("menuName", menuName2)
            startActivityForResult(intent, 99) //옵션 액티비티에서 값 전달 받기 위해
        })

        gridview3 = findViewById(R.id.gridview3)
        menuviewAdapter3 = MenuviewAdapter(layoutInflater, menuItems3)
        gridview3?.setAdapter(menuviewAdapter3)
        gridview3?.setOnItemClickListener(AdapterView.OnItemClickListener { parent, view, position, id ->
            val menuID3 = menuItems3[position].menuID
            val price3 = menuItems3[position].price
            val menuName3 = menuItems3[position].menuName
            val intent = Intent(applicationContext, OptionActivity::class.java)
            intent.putExtra("price", price3)
            intent.putExtra("menuID", menuID3)
            intent.putExtra("menuName", menuName3)
            startActivityForResult(intent, 99) //옵션 액티비티에서 값 전달 받기 위해
        })

        gridview4 = findViewById(R.id.gridview4)
        menuviewAdapter4 = MenuviewAdapter(layoutInflater, menuItems4)
        gridview4?.setAdapter(menuviewAdapter4)
        gridview4?.setOnItemClickListener(AdapterView.OnItemClickListener { parent, view, position, id ->
            val menuID4 = menuItems4[position].menuID
            val price4 = menuItems4[position].price
            val menuName4 = menuItems4[position].menuName
            val intent = Intent(applicationContext, OptionActivity::class.java)
            intent.putExtra("price", price4)
            intent.putExtra("menuID", menuID4)
            intent.putExtra("menuName", menuName4)
            startActivityForResult(intent, 99) //옵션 액티비티에서 값 전달 받기 위해
        })

    }

    private fun loadDB(storeID: Int) {
        object : Thread() {
            override fun run() {
                val serverUri = "http://54.180.149.204/menu.php"
                /* 한글 전송 처리
                try {
                    storeName = URLEncoder.encode(storeName, "utf-8")
                } catch (e: UnsupportedEncodingException) {
                    e.printStackTrace()
                }
                */
                val getUrl = "$serverUri?storeID=$storeID&menuCategory=1" //add
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
                    menuItems.clear()
                    runOnUiThread { menuviewAdapter1!!.notifyDataSetChanged() }

                    for (row in rows) {
                        //한 줄 데이터에서 한 칸씩 분리
                        val datas = row.split("&").toTypedArray()
                        if (datas.size != 6) continue //항목 5개
                        //변수 이름 변경 완료!!
                        val menuID = datas[0].toInt() //int 형으로 받기
                        val menuName = datas[1]
                        val menuCategory = datas[2].toInt()
                        val storeID = datas[3].toInt()
                        val price = datas[4].toInt()
                        val menuImage = "http://54.180.149.204/menu/" + datas[5] //이미지 상대 경로이므로
                        menuItems.add(menuData(menuID, menuName, menuCategory, storeID, price, menuImage))

                        //그리드뷰 갱신
                        runOnUiThread { menuviewAdapter1!!.notifyDataSetChanged() }
                    }

                } catch (e: MalformedURLException) {
                    e.printStackTrace()
                } catch (e: IOException) {
                    e.printStackTrace()
                }
                    val getUrl2 = "$serverUri?storeID=$storeID&menuCategory=2" //add
                    try {
                        val url = URL(getUrl2) //add
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

                        val rows = buffer.toString().split(";").toTypedArray()

                        menuItems2.clear()
                        runOnUiThread { menuviewAdapter2!!.notifyDataSetChanged() }

                        for (row in rows) {
                            //한 줄 데이터에서 한 칸씩 분리
                            val datas = row.split("&").toTypedArray()
                            if (datas.size != 6) continue //항목 5개
                            //변수 이름 변경 완료!!
                            val menuID = datas[0].toInt() //int 형으로 받기
                            val menuName = datas[1]
                            val menuCategory = datas[2].toInt()
                            val storeID = datas[3].toInt()
                            val price = datas[4].toInt()
                            val menuImage = "http://54.180.149.204/menu/" + datas[5] //이미지 상대 경로이므로
                            menuItems2.add(menuData(menuID, menuName, menuCategory, storeID, price, menuImage))

                            runOnUiThread { menuviewAdapter2!!.notifyDataSetChanged() }
                        }

                } catch (e: MalformedURLException) {
                    e.printStackTrace()
                } catch (e: IOException) {
                    e.printStackTrace()
                }

                val getUrl3 = "$serverUri?storeID=$storeID&menuCategory=3" //add
                try {
                    val url = URL(getUrl3) //add
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

                    val rows = buffer.toString().split(";").toTypedArray()

                    menuItems3.clear()
                    runOnUiThread { menuviewAdapter3!!.notifyDataSetChanged() }

                    for (row in rows) {
                        //한 줄 데이터에서 한 칸씩 분리
                        val datas = row.split("&").toTypedArray()
                        if (datas.size != 6) continue //항목 5개
                        //변수 이름 변경 완료!!
                        val menuID = datas[0].toInt() //int 형으로 받기
                        val menuName = datas[1]
                        val menuCategory = datas[2].toInt()
                        val storeID = datas[3].toInt()
                        val price = datas[4].toInt()
                        val menuImage = "http://54.180.149.204/menu/" + datas[5] //이미지 상대 경로이므로
                        menuItems3.add(menuData(menuID, menuName, menuCategory, storeID, price, menuImage))

                        runOnUiThread { menuviewAdapter3!!.notifyDataSetChanged() }
                    }

                } catch (e: MalformedURLException) {
                    e.printStackTrace()
                } catch (e: IOException) {
                    e.printStackTrace()
                }

                val getUrl4 = "$serverUri?storeID=$storeID&menuCategory=4" //add
                try {
                    val url = URL(getUrl4) //add
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

                    val rows = buffer.toString().split(";").toTypedArray()

                    menuItems4.clear()
                    runOnUiThread { menuviewAdapter4!!.notifyDataSetChanged() }

                    for (row in rows) {
                        //한 줄 데이터에서 한 칸씩 분리
                        val datas = row.split("&").toTypedArray()
                        if (datas.size != 6) continue //항목 5개
                        //변수 이름 변경 완료!!
                        val menuID = datas[0].toInt() //int 형으로 받기
                        val menuName = datas[1]
                        val menuCategory = datas[2].toInt()
                        val storeID = datas[3].toInt()
                        val price = datas[4].toInt()
                        val menuImage = "http://54.180.149.204/menu/" + datas[5] //이미지 상대 경로이므로
                        menuItems4.add(menuData(menuID, menuName, menuCategory, storeID, price, menuImage))

                        runOnUiThread { menuviewAdapter4!!.notifyDataSetChanged() }
                    }

                } catch (e: MalformedURLException) {
                    e.printStackTrace()
                } catch (e: IOException) {
                    e.printStackTrace()
                }
            }
        }.start()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if(resultCode == Activity.RESULT_OK){
            val menuID = data?.getIntExtra("menuID", 0)
            val menuName = data?.getStringExtra("menuName")
            val optionID = data?.getIntExtra("optionID", 0)
            val price = data?.getIntExtra("price", 0)
            val count = data?.getIntExtra("count", 0)
            val totalOption = data?.getSerializableExtra("optionSelected")
            //이 정보들+아래 총 가격(totalPrice) 다 디비에 저장해야 함(아래 함수에 구현) *totalOption은 배열(optionSelected 클래스)임에 주의
            //값을 전역변수로 해야 아래 함수에서 접근할 수 있을것 같음

            if (price != null) {
                totalPrice += price
            } //가격 업데이트
            //넘겨받은 값들 바탕으로 주문 확인 버튼 클릭 시 주문 확인 메뉴로
            //넘길 것 storeID, totalPrice, menuID, optionID, number
            //주문 확인 화면에서 넘겨받은값+테이블번호 ->내부 데이터베이스에 저장
            tv_price?.text = totalPrice.toString()
        }
    }

    fun moveToPay(v: View?){
        val intent = Intent(this, OrderActivity::class.java)
        // 장바구니 화면으로 넘어가는 부분 전송할 값 있으면 아래 함수 사용
        // intent.putExtra("categoryID", categoryID)
        //세환님 위에서 받은 메뉴아이디, 옵션 아이디, 가격, 수량 데이터베이스에 insert 해주는 부분

        startActivity(intent)
    }
}