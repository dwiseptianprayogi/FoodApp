package com.example.kantine_ktp.ui.detail

import android.view.View
import com.example.kantine_ktp.network.HttpClient
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class PaymentPresenter (private val view:PaymentContract.View):PaymentContract.Presenter {

    private var mCompositeDisposable:CompositeDisposable?

    init{
        this.mCompositeDisposable = CompositeDisposable()
    }

    override fun getCheckOut(
        foodId: String,
        userId: String,
        quantity:String,
        total: String,
        viewParms: View
    ) {
        view.showLoading()
        val disposable = HttpClient.getInstance().getApi()!!.checkout(
            foodId,
            userId,
            quantity,
            total,
            "DELIVERED"
        )
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    view.dissmissLoading()
                    if (it.meta?.status.equals("success", true)){
                        it.data?.let { it1 -> view.onCheckoutSuccess(it1, viewParms) }
                    } else{
                        it.meta?.message?.let{it1->view.onCheckoutFailed(it1)}
                    }
                },
                {
                    view.dissmissLoading()
                    view.onCheckoutFailed(it.message.toString())
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