package com.example.kantine_ktp.ui.profile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.example.kantine_ktp.R
import com.example.kantine_ktp.ektp
import com.example.kantine_ktp.model.response.login.User
import com.google.gson.Gson
import kotlinx.android.synthetic.main.fragment_profile.*


class ProfileFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val root = inflater.inflate(R.layout.fragment_profile, container, false)
        return root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val sectionPagerAdapter = SectionPagerAdapter(
            childFragmentManager
        )
        viewPager.adapter = sectionPagerAdapter
        tabLayout.setupWithViewPager(viewPager)


        var user = ektp.getApp().getUser()
        var userResponse = Gson().fromJson(user , User::class.java)

        tvName.text = userResponse.name
        tvEmail.text = userResponse.email

        if(!userResponse.profile_photo_url.isNullOrEmpty()){
            Glide.with(requireActivity())
                .load(userResponse.profile_photo_url)
                .into(ivPicture)
        }

    }


}