package com.example.kantine_ktp.ui.profile.foodmarket

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.kantine_ktp.R
import com.example.kantine_ktp.model.dummy.ProfileMenuModel
import com.example.kantine_ktp.ui.profile.ProfileMenuAdapter
import kotlinx.android.synthetic.main.fragment_profile_account.*


class ProfileFoodmarketFragment : Fragment(), ProfileMenuAdapter.ItemAdapterCallback {

    private var menuArrayList : ArrayList<ProfileMenuModel> = ArrayList()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_profile_account, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        initDataDummy()

        var adapter = ProfileMenuAdapter(menuArrayList, this)
        var layoutManager : RecyclerView.LayoutManager = LinearLayoutManager(activity)
        rcList.layoutManager = layoutManager
        rcList.adapter = adapter

        btn_logout.visibility = View.GONE
    }

    fun initDataDummy(){
        menuArrayList = ArrayList()
        menuArrayList.add(ProfileMenuModel("Rate Apps"))
        menuArrayList.add(ProfileMenuModel("Help Center"))
        menuArrayList.add(ProfileMenuModel("Privacy & Policy"))
        menuArrayList.add(ProfileMenuModel("Terms & Condition"))
    }

    override fun onClick(v: View, data: ProfileMenuModel) {
        Toast.makeText(context, "menu: "+data.title, Toast.LENGTH_LONG).show()
    }
}