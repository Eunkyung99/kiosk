package com.example.practicecopy.Fragment

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import androidx.core.content.ContextCompat
import androidx.core.content.ContextCompat.getDrawable
import com.example.practicecopy.ContentListModel
import com.example.practicecopy.FirstFragAdapter
import com.example.practicecopy.Fragment.MarketInfo.MarketInfoActivity
import com.example.practicecopy.R
import kotlinx.android.synthetic.main.fragment_first.*
import kotlinx.android.synthetic.main.fragment_first.view.*
import java.util.*

class FirstFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view : View = inflater.inflate(R.layout.fragment_first, container, false)

        val list_array = arrayListOf<ContentListModel>(

            ContentListModel((R.drawable.chinese_food), "b0", 1, "d0"),
            ContentListModel((R.drawable.korean_food), "b1", 2, "d1"),
            ContentListModel((R.drawable.western_style), "b2", 3, "d2"),
            ContentListModel((R.drawable.snack_bar), "b3", 4, "d3"),
            ContentListModel((R.drawable.chicken_pizza), "b4", 5, "d4"),
            ContentListModel((R.drawable.cafe_dessert), "b5", 6, "d5")
        )

        val list_adapter = FirstFragAdapter(requireContext(), list_array)
        view.listview_first_fragment.adapter = list_adapter
        view.listview_first_fragment.setOnItemClickListener{ adapterView,view,i,l->
            val intent = Intent(requireContext(), MarketInfoActivity::class.java)
            //intent.put
            startActivity(intent)

        }
        /*이제 리스트뷰하나 누를때마다 그 리스트뷰 아이템 position에 해당하는 number나 title값이 intent에
        담겨 전달됩니다. 그래서 각 menu화면 왼쪽상단 확인하시면 잘 안보이시겠지만 가게이름이 각각 다르게
        표시되는 걸 확인할 수 있을겁니다.*/
        view.listview_first_fragment.setOnItemClickListener(AdapterView.OnItemClickListener { parent, view, position, id ->
            val storenumber = list_array[position].number //지현님 리스트뷰 어레이에서 position 인덱스로 참조
            val storename=list_array[position].title
            val intent = Intent(requireContext(), MarketInfoActivity::class.java)
             //지현님 인텐트로 전송해줄 변수 *userID 위에서 선언한 값 전송됨
            intent.putExtra("storeID", storenumber)
            intent.putExtra("storeName",storename)//지현님 인텐트로 전송해줄 변수 *각 리스트뷰 클릭할 때마다 다른 값 -> 어댑터 안에 있는 변수값 전송될 것
            startActivity(intent)
        })

        return view
    }
}