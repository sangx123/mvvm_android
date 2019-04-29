package com.xinfu.qianxiaozhuang.api

import com.sangxiang.mvvm.model.VideoModel
import io.reactivex.Observable
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.http.*

/**
 * 定义接口
 */
@JvmSuppressWildcards
interface ApiService {

    /**
     * 获取videoList
     */
    @GET("pictures")
    fun getPictures(): Observable<List<VideoModel>>
}

