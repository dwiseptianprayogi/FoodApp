package com.example.kantine_ktp.ui.auth

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.navigation.NavOptions
import androidx.navigation.Navigation
import com.example.kantine_ktp.R
import kotlinx.android.synthetic.main.layout_toolbar.*

class AuthActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_auth)

        val pageRequest = intent.getIntExtra("page_request",0)
        if(pageRequest == 2){

            toolbarSignUp()

            val navOptions = NavOptions.Builder()
                .setPopUpTo(R.id.fragmentSignIn, true)
                .build()
            Navigation.findNavController(findViewById(R.id.authHostFragment))
                .navigate(R.id.action_singup, null, navOptions)
        }
        else if(pageRequest == 1){

//            toolbarSignUp()

            NavOptions.Builder()
                .setPopUpTo(R.id.fragmentSignIn, true)
                .build()
//            Navigation.findNavController(findViewById(R.id.authHostFragment))
//                .navigate(R.id.action_singup, null, navOptions)
        }
    }

    fun toolbarSignUp(){
        toolbar.title = "Sign Up"
        toolbar.subtitle = "Register and eat"
        toolbar.navigationIcon = resources.getDrawable(R.drawable.arrow_back_ios_24px, null)
        toolbar.setNavigationOnClickListener { onBackPressed() }
    }

    fun toolbarSignUpAddress(){
        toolbar.title = "Address"
        toolbar.subtitle = "Make sure it's a valid"
        toolbar.navigationIcon = resources.getDrawable(R.drawable.arrow_back_ios_24px, null)
        toolbar.setNavigationOnClickListener { onBackPressed() }
    }

    fun toolbarSignUpSuccess(){
        toolbar.visibility = View.GONE
    }
}