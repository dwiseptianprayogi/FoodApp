package com.example.kantine_ktp.ui.home

import com.example.kantine_ktp.base.BasePresenter
import com.example.kantine_ktp.base.BaseView
import com.example.kantine_ktp.model.response.home.HomeResponse
import com.example.kantine_ktp.model.response.login.LoginResponse

interface HomeContract {

    interface  View: BaseView {
        fun onHomeSuccess(homeResponse:HomeResponse)
        fun onHomeFailed(message:String)

    }
    interface Presenter:HomeContract, BasePresenter {
        fun getHome()
    }
}