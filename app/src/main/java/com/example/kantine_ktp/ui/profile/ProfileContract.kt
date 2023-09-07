package com.example.kantine_ktp.ui.profile


import com.example.kantine_ktp.base.BasePresenter
import com.example.kantine_ktp.base.BaseView
import com.example.kantine_ktp.model.response.Wrapper

interface ProfileContract {

    interface  View: BaseView {
        fun onLogoutSuccess(message: Wrapper<Any>)
        fun onLogoutFailed(message:String)

    }
    interface Presenter:ProfileContract, BasePresenter {
        fun getProfile(token: String)
    }
}