package com.example.kantine_ktp.ui.order

import com.example.kantine_ktp.model.response.transaction.TransactionResponse
import com.example.kantine_ktp.base.BasePresenter
import com.example.kantine_ktp.base.BaseView

interface OrderContract {

    interface  View: BaseView {
        fun onTransactionSuccess(transactionResponse: TransactionResponse)
        fun onTransactionFailed(message:String)

    }
    interface Presenter:OrderContract, BasePresenter {
        fun getTransaction()
    }
}