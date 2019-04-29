package com.sangxiang.mvvm.repository

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.content.Context
import android.util.Log
import com.sangxiang.mvvm.model.VideoModel
import com.xinfu.qianxiaozhuang.api.Api
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class MainRepository(context: Context) {

    fun getPictures(): LiveData<List<VideoModel>> {
        var videoList: MutableLiveData<List<VideoModel>> = MutableLiveData()
        Api.getApiService().getPictures()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(object : Observer<List<VideoModel>> {
                    override fun onComplete() {

                    }

                    override fun onSubscribe(d: Disposable) {
                        //mDisposables.add(d)
                    }

                    override fun onNext(t: List<VideoModel>) {
                        videoList.value=t
                    }

                    override fun onError(e: Throwable) {
                        videoList.value=null
                    }

                })

        return videoList
    }
}