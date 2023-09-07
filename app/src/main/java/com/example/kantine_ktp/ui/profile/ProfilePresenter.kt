package com.example.kantine_ktp.ui.profile

import com.example.kantine_ktp.network.HttpClient
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class ProfilePresenter (private val view:ProfileContract.View):ProfileContract.Presenter {

    private var mCompositeDisposable:CompositeDisposable?

    init{
        this.mCompositeDisposable = CompositeDisposable()
    }

    override fun getProfile(token:String) {
//        var user = ektp.getApp().getUser()
//        var userResponse = Gson().fromJson(user , LoginResponse::class.java)

        view.showLoading()
        val disposable = HttpClient.getInstance().getApi()!!.logout(token)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    view.dissmissLoading()
                    if (it.meta?.status.equals("success", true)){
//                        view.dissmissLoading()
                        it.data?.let { it1 -> view.onLogoutSuccess(it) }
                    } else{
                        it.meta?.message?.let{it1->view.onLogoutFailed(it1)}
                    }
                },
                {
                    view.dissmissLoading()
                    view.onLogoutFailed(it.message.toString())
                }
            )
        mCompositeDisposable!!.add(disposable)
    }

    override fun subscribe() {

    }

    override fun unSubscribe() {
        mCompositeDisposable!!.clear()
    }

}