package com.example.kantine_ktp.ui.order

import com.example.kantine_ktp.network.HttpClient
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class OrderPresenter (private val view:OrderContract.View):OrderContract.Presenter {

    private var mCompositeDisposable:CompositeDisposable?

    init{
        this.mCompositeDisposable = CompositeDisposable()
    }

    override fun getTransaction() {
        view.showLoading()
        val disposable = HttpClient.getInstance().getApi()!!.transaction()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    view.dissmissLoading()
                    if (it.meta?.status.equals("success", true)){
//                        view.dissmissLoading()
                        it.data?.let { it1 -> view.onTransactionSuccess(it1) }
                    } else{
                        it.meta?.message?.let{it1->view.onTransactionFailed(it1)}
                    }
                },
                {
                    view.dissmissLoading()
                    view.onTransactionFailed(it.message.toString())
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