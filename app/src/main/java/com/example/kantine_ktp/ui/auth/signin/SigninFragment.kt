package com.example.kantine_ktp.ui.auth.signin

import android.app.Dialog
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.kantine_ktp.R
import com.example.kantine_ktp.ektp
import com.example.kantine_ktp.model.response.login.LoginResponse
import com.example.kantine_ktp.ui.MainActivity
import com.example.kantine_ktp.ui.auth.AuthActivity
import com.google.gson.Gson
import kotlinx.android.synthetic.main.fragment_signin.*

class SigninFragment : Fragment(), SigninContract.View {

    lateinit var presenter:SigninPresenter
    var progressDialog : Dialog? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_signin, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        presenter = com.example.kantine_ktp.ui.auth.signin.SigninPresenter(this)

        if (!ektp.getApp().getToken().isNullOrEmpty()){
            val home = Intent(activity, MainActivity::class.java)
            startActivity(home)
            activity?.finish()
        }

        initDummy()
        initView()

        btnSignUp.setOnClickListener{
            val signUp = Intent(activity, AuthActivity::class.java)
            signUp.putExtra("page_request", 2)
            startActivity(signUp)
        }

        btnSignIn.setOnClickListener {

            var email = etEmail.text.toString()
            var password = etPassword.text.toString()

            if(email.isNullOrEmpty()){
                etEmail.error = "Silahkan Masukan Email Anda"
                etEmail.requestFocus()
            }
            else if(password.isNullOrEmpty()){
                etPassword.error = "Silahkan Masukan Password Anda"
                etPassword.requestFocus()
            }
            else{
                presenter.submitLogin(email,password)
            }
        }
    }

    override fun onLoginSuccess(loginResponse: LoginResponse) {

        ektp.getApp().setToken(loginResponse.access_token)

        val gson = Gson()
        val json = gson.toJson(loginResponse.user)
        ektp.getApp().setUser(json)

        val home = Intent(activity, MainActivity::class.java)
        startActivity(home)
        activity?.finish()
    }

    override fun onLoginFailed(message: String) {
        Toast.makeText(activity, message,Toast.LENGTH_SHORT).show()
    }

    private fun initDummy(){
        etEmail.setText("dwi@gmail.com")
        etPassword.setText("apollo27")
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

    override fun showLoading() {
        progressDialog?.show()
    }

    override fun dissmissLoading() {
        progressDialog?.dismiss()
    }
}