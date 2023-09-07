package com.example.kantine_ktp.ui.auth.signup

import android.net.Uri
import com.example.kantine_ktp.base.BasePresenter
import com.example.kantine_ktp.base.BaseView
import com.example.kantine_ktp.model.request.RegisterRequest
import com.example.kantine_ktp.model.response.login.LoginResponse

interface SignupContract {

    interface  View: BaseView {
        fun onRegisterSuccess(loginResponse:LoginResponse, view:android.view.View)
        fun onRegisterPhotoSuccess(view:android.view.View)
        fun onRegisterFailed(message:String)

    }
    interface Presenter:SignupContract, BasePresenter {
        fun submitRegister(registerRequest: RegisterRequest, view:android.view.View)
        fun submitPhotoRegister(filePath:Uri, view:android.view.View)
    }
}