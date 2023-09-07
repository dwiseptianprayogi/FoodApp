package com.example.kantine_ktp

import android.content.SharedPreferences
import android.content.SharedPreferences.Editor
import androidx.multidex.MultiDexApplication
import androidx.preference.PreferenceManager
import com.example.kantine_ktp.network.HttpClient

class ektp : MultiDexApplication(){
    companion object{
        lateinit var instantce : ektp

        fun getApp():ektp{
            return instantce
        }
    }

    override fun onCreate() {
        super.onCreate()
        instantce = this
    }

    fun getPreferences():SharedPreferences{
        return PreferenceManager.getDefaultSharedPreferences(this)
    }

    fun setToken(token:String){
        getPreferences().edit().putString("PREFERENCES_TOKEN", token).apply()
        HttpClient.getInstance().buildRetrofitClient(token)
    }

    fun getToken():String?{
        return getPreferences().getString("PREFERENCES_TOKEN", null)
    }

    fun setUser(user:String){
        getPreferences().edit().putString("PREFERENCES_USER", user).apply()
        HttpClient.getInstance().buildRetrofitClient(user)
    }

    fun getUser():String?{
        return getPreferences().getString("PREFERENCES_USER", null)
    }

    fun removeDataFromPref() {
        val pref = getPreferences().edit()
        val editor:Editor = pref
        editor.remove("PREFERENCES_TOKEN")
        editor.apply()
    }

}