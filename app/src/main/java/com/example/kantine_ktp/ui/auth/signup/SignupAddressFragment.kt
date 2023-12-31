package com.example.kantine_ktp.ui.auth.signup

import android.app.Dialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.Navigation
import com.example.kantine_ktp.R
import com.example.kantine_ktp.ektp
import com.example.kantine_ktp.model.request.RegisterRequest
import com.example.kantine_ktp.model.response.login.LoginResponse
import com.example.kantine_ktp.ui.auth.AuthActivity
import com.google.gson.Gson
import kotlinx.android.synthetic.main.fragment_signup_address.*

class SignupAddressFragment : Fragment(), SignupContract.View {

    private lateinit var data : RegisterRequest

    lateinit var presenter: SignupPresenter
    var progressDialog:Dialog?=null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_signup_address, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        presenter = com.example.kantine_ktp.ui.auth.signup.SignupPresenter(this)
        data = arguments?.getParcelable<RegisterRequest>("data")!!

        initDummy()
        initListener()
        initView()

    }

    private fun initListener() {
        btnSignUpNow.setOnClickListener {
            var phone = etPhoneNumber.text.toString()
            var address = etAddress.text.toString()
            var houseNumber = etHouseNumber.text.toString()
            var city = etCity.text.toString()

            data.let {
                it.address = address
                it.city = city
                it.houseNumber = houseNumber
                it.phoneNumber = phone
            }

            if(phone.isNullOrEmpty()){
                etPhoneNumber.error = "Silahkan Masukan Nomor Telepon"
                etPhoneNumber.requestFocus()
            }
            else if(address.isNullOrEmpty()){
                etAddress.error = "Silahkan Masukan Address"
                etAddress.requestFocus()
            }
            else if(houseNumber.isNullOrEmpty()){
                etHouseNumber.error = "Silahkan Masukan House Number"
                etHouseNumber.requestFocus()
            }
            else if(city.isNullOrEmpty()){
                etCity.error = "Silahkan Masukan City"
                etCity.requestFocus()
            }
            else{
                presenter.submitRegister(data, it)
//                presenter.submitPhotoRegister(data.filePath!!,it)
            }
        }
    }

    private fun initDummy(){
        etPhoneNumber.setText("0892176323")
        etAddress.setText("Jalan HR.Edsukma")
        etHouseNumber.setText("97")
        etCity.setText("Bogor")
    }

    override fun onRegisterSuccess(loginResponse: LoginResponse, view: View) {
        ektp.getApp().setToken(loginResponse.access_token)

        val gson = Gson()
        val json = gson.toJson(loginResponse.user)
        ektp.getApp().setUser(json)

        if(data.filePath == null){
            Navigation.findNavController(view)
                .navigate(R.id.action_singup_success, null)

            (activity as AuthActivity).toolbarSignUpSuccess()
        }
        else{
            presenter.submitPhotoRegister(data.filePath!!,view)
        }
    }

    override fun onRegisterPhotoSuccess(view: View) {
        Navigation.findNavController(view)
            .navigate(R.id.action_singup_success, null)

        (activity as AuthActivity).toolbarSignUpSuccess()
    }

    override fun onRegisterFailed(message: String) {
        Toast.makeText(activity, message, Toast.LENGTH_LONG).show()
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