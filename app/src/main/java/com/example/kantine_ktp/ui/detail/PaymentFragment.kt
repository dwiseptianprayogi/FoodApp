package com.example.kantine_ktp.ui.detail

import android.app.Dialog
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.Navigation
import com.example.kantine_ktp.model.response.checkout.CheckoutResponse
import com.bumptech.glide.Glide
import com.example.kantine_ktp.R
import com.example.kantine_ktp.ektp
import com.example.kantine_ktp.model.response.home.Data
import com.example.kantine_ktp.model.response.login.User
import com.example.kantine_ktp.utils.Helpers.formatPrice
import com.google.gson.Gson
import kotlinx.android.synthetic.main.fragment_payment.*

class PaymentFragment : Fragment(), PaymentContract.View{

    var progressDialog:Dialog?=null
    lateinit var presenter: PaymentPresenter
    var total:Int = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_payment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        (activity as DetailActivity).toolbarPayment()

        var data = arguments?.getParcelable<Data>("data")
        initView(data)

        initView()
        presenter = PaymentPresenter(this)


    }

    private fun initView(){
        progressDialog = Dialog(requireContext())
        val dialogLayout = layoutInflater.inflate(R.layout.dialog_loader, null)

        progressDialog?.let {
            it.setContentView(dialogLayout)
            it.setCancelable(false)
            it.window?.setBackgroundDrawableResource(android.R.color.transparent)
        }
    }

    private fun initView(data: Data?) {
        tvTitle.text = data?.name
        tvPrice.formatPrice(data?.price.toString())

        Glide.with(requireContext())
            .load(data?.picturePath)
            .into(ivPoster)

        tvNameItem.text = data?.name
        tvHarga.formatPrice(data?.price.toString())

        if (!data?.price.toString().isNullOrEmpty()){
            val totalTax = data?.price?.div(11)
            tvTax.formatPrice(totalTax.toString())

            total = data?.price!!+totalTax!!+5000
            tvTotal.formatPrice(total.toString())
        }
        else{
            tvPrice.text = "IDR. 0"
            tvTax.text = "IDR. 0"
            tvTotal.text = "IDR. 0"
            total = 0
        }

        var user = ektp.getApp().getUser()
        var userResponse = Gson().fromJson(user, User::class.java)

        tvName.text = userResponse?.name
        tvPhoneNo.text = userResponse?.phoneNumber
        tvAddress.text = userResponse?.address
        tvCity.text = userResponse?.city

        btnCheckout.setOnClickListener {
            presenter.getCheckOut(
                data?.id.toString(),
                userResponse?.id.toString(),
                "1",
                total.toString(),
                it
            )

        }

    }

    override fun onCheckoutSuccess(checkoutResponse: CheckoutResponse, view: View) {
        val intent = Intent(Intent.ACTION_VIEW)
        intent.data = Uri.parse(checkoutResponse.paymentUrl)
        startActivity(intent)

        Navigation.findNavController(view).navigate(R.id.action_payment_success)
    }

    override fun onCheckoutFailed(message: String) {
        Toast.makeText(context,message, Toast.LENGTH_LONG).show()
    }

    override fun showLoading() {
        progressDialog?.show()
    }

    override fun dissmissLoading() {
        progressDialog?.dismiss()
    }

}