package com.sangxiang.mvvm.viewmodel

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.sangxiang.mvvm.model.VideoModel
import com.sangxiang.mvvm.repository.MainRepository

class MainActivityViewModel(var mRepository: MainRepository) : ViewModel() {
    var  videoList: LiveData<List<VideoModel>> = MutableLiveData<List<VideoModel>>()

    fun getVideoLists(){
        videoList= mRepository.getPictures()
    }
}