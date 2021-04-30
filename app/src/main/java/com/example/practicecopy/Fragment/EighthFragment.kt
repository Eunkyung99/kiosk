package com.example.practicecopy.Fragment

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ListView
import com.example.practicecopy.MenuActivity
import com.example.practicecopy.MyAdapter
import com.example.practicecopy.R
import com.example.practicecopy.storeData
import kotlinx.android.synthetic.main.fragment_first.view.*
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.MalformedURLException
import java.net.URL
import java.util.*

class EighthFragment : Fragment() {
    var listView: ListView? = null
    var myAdapter: MyAdapter? = null
    var storeItems = ArrayList<storeData>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view : View = inflater.inflate(R.layout.fragment_first, container, false)

        //var intent = getIntent() //intent 받아옴
        //val categoryID = intent.getStringExtra("categoryID")
        loadDB(1008) //add

        listView = view.findViewById(R.id.listView)
        myAdapter = MyAdapter(layoutInflater, storeItems)
        listView?.setAdapter(myAdapter)
        listView?.setOnItemClickListener(AdapterView.OnItemClickListener { parent, view, position, id ->
            val storeeID = storeItems[position].storeID //지현님 리스트뷰 어레이에서 position 인덱스로 참조
            val intent = Intent(requireContext(), MenuActivity::class.java)
            intent.putExtra(
                "categoryID", 1008)
            startActivity(intent)
        })

        /*이제 리스트뷰하나 누를때마다 그 리스트뷰 아이템 position에 해당하는 number나 title값이 intent에
        담겨 전달됩니다. 그래서 각 menu화면 왼쪽상단 확인하시면 잘 안보이시겠지만 가게이름이 각각 다르게
        표시되는 걸 확인할 수 있을겁니다.*/
        view.listView.setOnItemClickListener(AdapterView.OnItemClickListener { parent, view, position, id ->
            val categoryID = storeItems[position].categoryID
            val storenumber = storeItems[position].storeID
            val storename=storeItems[position].storeName
            val storeaddress=storeItems[position].storeAddress
            val storeImage=storeItems[position].storeImage
            val intent = Intent(requireContext(), MenuActivity::class.java)
            //지현님 인텐트로 전송해줄 변수 *userID 위에서 선언한 값 전송됨
            intent.putExtra("categoryID", categoryID)
            intent.putExtra("storeID", storenumber)
            intent.putExtra("storeName",storename)
            intent.putExtra("storeAddress", storeaddress)
            intent.putExtra("storeImage", storeImage)
            startActivity(intent)
        })

        return view
    }

    private fun loadDB(categoryID: Int) {
        object : Thread() {
            override fun run() {
                val serverUri = "http://54.180.149.204/store.php"
                /* 한글 전송 처리
                try {
                    storeName = URLEncoder.encode(storeName, "utf-8")
                } catch (e: UnsupportedEncodingException) {
                    e.printStackTrace()
                }
                */
                val getUrl = "$serverUri?categoryID=$categoryID" //add
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
                    storeItems.clear()
                    runOnUiThread { myAdapter!!.notifyDataSetChanged() }

                    for (row in rows) {
                        //한 줄 데이터에서 한 칸씩 분리
                        val datas = row.split("&").toTypedArray()
                        if (datas.size != 5) continue //항목 5개
                        //변수 이름 변경 완료!!
                        val storeID = datas[0].toInt() //int 형으로 받아야 해요
                        val categoryID = datas[1].toInt() //int 형으로 받아야 해요
                        val storeName = datas[2]
                        val storeAddress = datas[3]
                        val storeImage = "http://54.180.149.204/store/" + datas[4] //이미지 상대 경로이므로
                        storeItems.add(storeData(storeID, categoryID, storeName, storeAddress, storeImage))

                        //리스트뷰 갱신
                        runOnUiThread { myAdapter!!.notifyDataSetChanged() }
                    }

                } catch (e: MalformedURLException) {
                    e.printStackTrace()
                } catch (e: IOException) {
                    e.printStackTrace()
                }
            }
        }.start()
    }

    fun runOnUiThread(action: () -> Unit) {
        this ?: return
        if (!isAdded) return // Fragment not attached to an Activity
        activity?.runOnUiThread(action)
    }
}