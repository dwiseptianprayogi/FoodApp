package com.example.kantine_ktp.ui.order.inprogress

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.kantine_ktp.model.response.transaction.Data
import com.example.kantine_ktp.R
import kotlinx.android.synthetic.main.fragment_inprogress.*


class InprogressFragment : Fragment(), InprogressAdapter.ItemAdapterCallback{

    private var adapter:InprogressAdapter?=null
    var inprogressList : ArrayList<Data>? = ArrayList()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_inprogress, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        inprogressList = arguments?.getParcelableArrayList("data")

        if(!inprogressList.isNullOrEmpty()){
            adapter = InprogressAdapter(inprogressList!!, this)
            val layoutManager = LinearLayoutManager(activity)
            rcList.layoutManager = layoutManager
            rcList.adapter = adapter
        }
    }

    override fun onClick(v: View, data: Data) {
        Toast.makeText(activity, "test", Toast.LENGTH_LONG).show()
    }

}