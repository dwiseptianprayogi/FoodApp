package com.example.kantine_ktp.ui.profile.account

import android.app.Dialog
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.kantine_ktp.R
import com.example.kantine_ktp.ektp
import com.example.kantine_ktp.model.dummy.ProfileMenuModel
import com.example.kantine_ktp.model.response.Wrapper
import com.example.kantine_ktp.ui.auth.AuthActivity
import com.example.kantine_ktp.ui.profile.ProfileContract
import com.example.kantine_ktp.ui.profile.ProfileMenuAdapter
import com.example.kantine_ktp.ui.profile.ProfilePresenter
import kotlinx.android.synthetic.main.fragment_profile_account.*


class ProfileAccountFragment : Fragment(), ProfileMenuAdapter.ItemAdapterCallback, ProfileContract.View{

    private var menuArrayList : ArrayList<ProfileMenuModel> = ArrayList()
    private lateinit var presenter: ProfilePresenter
    var progressDialog:Dialog?=null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_profile_account, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        initDataDummy()

        presenter = ProfilePresenter(this)

        var adapter = ProfileMenuAdapter(menuArrayList, this)
        var layoutManager : RecyclerView.LayoutManager = LinearLayoutManager(activity)
        rcList.layoutManager = layoutManager
        rcList.adapter = adapter

        val pref = ektp.getApp().getToken()
//        val pref2 = ektp.getApp().getToken()
        Toast.makeText(activity, pref.toString(), Toast.LENGTH_LONG).show()

//        var userResponse = Gson().fromJson(user , LoginResponse::class.java)

        btn_logout.setOnClickListener {
            var user = ektp.getApp().getToken()
            presenter.getProfile(user.toString())
        }
    }


    override fun onLogoutSuccess(message: Wrapper<Any>){
        ektp.getApp().removeDataFromPref()
        Toast.makeText(activity, "Logout Account Berhasil", Toast.LENGTH_LONG).show()
        val signin = Intent(activity, AuthActivity::class.java)
        startActivity(signin)
        activity?.finish()
    }

    override fun onLogoutFailed(message: String) {
        Toast.makeText(activity, message, Toast.LENGTH_LONG).show()
    }

    override fun showLoading() {
        progressDialog?.show()
    }

    override fun dissmissLoading() {
        progressDialog?.dismiss()
    }

    fun initDataDummy(){
        menuArrayList = ArrayList()
        menuArrayList.add(ProfileMenuModel("Edit Profile"))
        menuArrayList.add(ProfileMenuModel("Home Address"))
        menuArrayList.add(ProfileMenuModel("Security"))
        menuArrayList.add(ProfileMenuModel("Payments"))
    }

    override fun onClick(v: View, data: ProfileMenuModel) {
        Toast.makeText(context, "menu: "+data.title, Toast.LENGTH_LONG).show()
    }

}