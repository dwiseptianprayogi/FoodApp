package com.example.kantine_ktp.ui.detail

import com.example.kantine_ktp.model.response.checkout.CheckoutResponse
import com.example.kantine_ktp.base.BasePresenter
import com.example.kantine_ktp.base.BaseView

interface PaymentContract {

    interface  View: BaseView {
        fun onCheckoutSuccess(checkoutResponse: CheckoutResponse, view: android.view.View)
        fun onCheckoutFailed(message:String)

    }
    interface Presenter:PaymentContract, BasePresenter {
        fun getCheckOut(foodId:String, userId: String, quantity:String, total:String, view: android.view.View)
    }
}