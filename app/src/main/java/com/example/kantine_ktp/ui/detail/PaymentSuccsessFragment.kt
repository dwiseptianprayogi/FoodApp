package com.example.kantine_ktp.ui.detail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.kantine_ktp.R
import kotlinx.android.synthetic.main.fragment_payment_succsess.*

class PaymentSuccsessFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_payment_succsess, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        (activity as DetailActivity).toolDetail()

        btnOtherFood.setOnClickListener {
            requireActivity().finish()
        }
        btnMyOrder.setOnClickListener {
            requireActivity().finish()
        }
    }
}