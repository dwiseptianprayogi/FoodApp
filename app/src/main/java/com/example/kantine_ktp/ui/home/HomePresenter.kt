package com.example.kantine_ktp.ui.home

import com.example.kantine_ktp.network.HttpClient
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class HomePresenter (private val view:HomeContract.View):HomeContract.Presenter {

    private var mCompositeDisposable:CompositeDisposable?

    init{
        this.mCompositeDisposable = CompositeDisposable()
    }

    override fun getHome() {
        view.showLoading()
        val disposable = HttpClient.getInstance().getApi()!!.home()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    view.dissmissLoading()
                    if (it.meta?.status.equals("success", true)){
//                        view.dissmissLoading()
                        it.data?.let { it1 -> view.onHomeSuccess(it1) }
                    } else{
                        it.meta?.message?.let{it1->view.onHomeFailed(it1)}
                    }
                },
                {
                    view.dissmissLoading()
                    view.onHomeFailed(it.message.toString())
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