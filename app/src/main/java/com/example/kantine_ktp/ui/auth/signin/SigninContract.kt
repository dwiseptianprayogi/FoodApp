package com.example.kantine_ktp.ui.auth.signin

import com.example.kantine_ktp.base.BasePresenter
import com.example.kantine_ktp.base.BaseView
import com.example.kantine_ktp.model.response.login.LoginResponse

interface SigninContract {

    interface  View: BaseView {
        fun onLoginSuccess(loginResponse:LoginResponse)
        fun onLoginFailed(message:String)

    }
    interface Presenter:SigninContract, BasePresenter {
        fun submitLogin(email: String, password:String)
    }
}