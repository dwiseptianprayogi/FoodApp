package com.example.kantine_ktp.ui.auth.signup

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.Navigation
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.kantine_ktp.R
import com.example.kantine_ktp.model.request.RegisterRequest
import com.example.kantine_ktp.ui.auth.AuthActivity
import com.github.dhaval2404.imagepicker.ImagePicker
import kotlinx.android.synthetic.main.fragment_signin.*
import kotlinx.android.synthetic.main.fragment_signup.*
import kotlinx.android.synthetic.main.fragment_signup.etEmail
import kotlinx.android.synthetic.main.fragment_signup.etPassword

class SignupFragment : Fragment() {

    var filePath:Uri? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_signup, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        initDummmy()
        initListener()

    }

    private fun initListener() {
        ivProfile.setOnClickListener {
            ImagePicker.with(this)
                .cameraOnly()
                .start()
        }
        btnContinue.setOnClickListener {

            var fullName = etFullName.text.toString()
            var email = etEmail.text.toString()
            var password = etPassword.text.toString()

            if (fullName.isNullOrEmpty()){
                etFullName.error = "Silahkan Masukan Nama Lengkap"
                etFullName.requestFocus()
            }
            else if (email.isNullOrEmpty()){
                etEmail.error = "Silahkan Masukan Alamat Email"
                etEmail.requestFocus()
            }
            else if (password.isNullOrEmpty()){
                etPassword.error = "Silahkan Masukan Password"
            }
            else{
                var data = RegisterRequest(
                    fullName,
                    email,
                    password,
                    password,
                    "","","","",
                    filePath
                )

                var bundle = Bundle()
                bundle.putParcelable("data", data)

                Navigation.findNavController(it)
                    .navigate(R.id.action_singup_address, bundle)

                (activity as AuthActivity).toolbarSignUpAddress()
            }
        }
    }

    private fun initDummmy(){
        etFullName.setText("dwi septian prayogi")
        etEmail.setText("dwi@gmail.com")
        etPassword.setText("apollo27")
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if(resultCode == Activity.RESULT_OK){
            filePath = data?.data

            Glide.with(this)
                .load(filePath)
                .apply(RequestOptions.circleCropTransform())
                .into(ivProfile)
        }
        else if(requestCode==ImagePicker.RESULT_ERROR){
            Toast.makeText(context, ImagePicker.getError(data), Toast.LENGTH_SHORT).show()
        }
        else{
            Toast.makeText(context,"Task Cancelled", Toast.LENGTH_SHORT).show()
        }
    }
}