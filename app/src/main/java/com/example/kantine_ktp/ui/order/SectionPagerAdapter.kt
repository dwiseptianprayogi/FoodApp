package com.example.kantine_ktp.ui.order

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.example.kantine_ktp.model.response.transaction.Data
import com.example.kantine_ktp.ui.order.inprogress.InprogressFragment
import com.example.kantine_ktp.ui.order.pastorder.PastOrderFragment

class SectionPagerAdapter (fm:FragmentManager): FragmentPagerAdapter(fm){

    var inprogressList:ArrayList<Data>? = ArrayList()
    var pastorderList:ArrayList<Data>? = ArrayList()

    override fun getPageTitle(position: Int): CharSequence? {
        return when(position){
            0->"In Progress"
            1->"Past Orders"
            else->""
        }
    }

    override fun getCount(): Int {
        return 2
    }

    override fun getItem(position: Int): Fragment {
        var fragment : Fragment
        return when(position){
            0 ->{
                fragment = InprogressFragment()
                val bundle = Bundle()
                bundle.putParcelableArrayList("data", inprogressList)
                fragment.arguments = bundle
                return fragment
            }
            1 ->{
                fragment = PastOrderFragment()
                val bundle = Bundle()
                bundle.putParcelableArrayList("data", pastorderList)
                fragment.arguments = bundle
                return fragment
            }
            else -> {
                fragment = InprogressFragment()
                val bundle = Bundle()
                bundle.putParcelableArrayList("data", inprogressList)
                fragment.arguments = bundle
                return fragment
            }
        }
    }

    fun setData(inprogressListParms:ArrayList<Data>?, pastorderListParms:ArrayList<Data>? ){
        inprogressList = inprogressListParms
        pastorderList = pastorderListParms
    }
}