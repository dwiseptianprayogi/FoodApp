package com.example.kantine_ktp.ui.home.newtaste

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.kantine_ktp.R
import com.example.kantine_ktp.model.dummy.HomeVerticalModel
import com.example.kantine_ktp.model.response.home.Data
import com.example.kantine_ktp.ui.detail.DetailActivity
import kotlinx.android.synthetic.main.fragment_home.*

class HomeNewTasteFragment : Fragment(), HomeNewTasteAdapter.ItemAdapterCallback{

//    private var foodList : ArrayList<HomeVerticalModel> = ArrayList()
    private var newTasteList : ArrayList<Data>? = ArrayList()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home_new_taste, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        newTasteList = arguments?.getParcelableArrayList("data")

        val  adapter = HomeNewTasteAdapter(newTasteList!!, this)
        val layoutManager: RecyclerView.LayoutManager = LinearLayoutManager(activity)
        rcList.layoutManager = layoutManager
        rcList.adapter = adapter

//        initDataDummy()
    }


//    fun initDataDummy(){
//        foodList = ArrayList()
//        foodList.add(HomeVerticalModel("Cheryy Healty","10000", "", 5f))
//        foodList.add(HomeVerticalModel("Buger Tamayo", "10000","", 4f))
//        foodList.add(HomeVerticalModel("Bakwan Cihuy", "10000","", 4.5f))
//
//    }

    override fun onClick(v: View, data: Data) {
        val detail = Intent(activity, DetailActivity::class.java).putExtra("data", data)
        startActivity(detail)
    }
}