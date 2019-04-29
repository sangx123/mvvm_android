package com.sangxiang.mvvm.viewmodel

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import com.sangxiang.mvvm.repository.MainRepository

class MainViewModelFactory(var mRepository: MainRepository) : ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return MainActivityViewModel(mRepository) as T
    }
}