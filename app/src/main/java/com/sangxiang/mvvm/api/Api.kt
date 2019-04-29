package com.xinfu.qianxiaozhuang.api

import android.widget.Toast
import com.google.gson.*
import com.google.gson.reflect.TypeToken
import okhttp3.*
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.io.File
import java.lang.reflect.Type
import java.net.URL
import java.text.SimpleDateFormat
import java.util.*
import java.util.concurrent.TimeUnit
import com.google.gson.GsonBuilder
import com.google.gson.Gson
import com.sangxiang.mvvm.BuildConfig


/**
 * retrofit网络请求
 */


class Api private constructor() {


    var BaseURL="http://private-04a55-videoplayer1.apiary-mock.com/"
    private val DEFAULT_TIMEOUT = 60
    lateinit var retrofit: Retrofit
    private lateinit var apiService: ApiService
    private lateinit var apiImageService: ApiService
    private var httpClientBuilder: OkHttpClient.Builder
    private lateinit var mReqLogFp: File
    var mCurDateTime: String? = null //yyyy-MM-dd HH:mm:ss
    //构造方法私有
    init {
        //手动创建一个OkHttpClient并设置超时时间
        httpClientBuilder = OkHttpClient.Builder()
        //mReqLogFp = App.requestLog
        //httpClientBuilder.addInterceptor(AddHeaderInterceptor())
        httpClientBuilder.addInterceptor(HttpLoggingInterceptor().setLevel(if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY else HttpLoggingInterceptor.Level.NONE))
        httpClientBuilder.readTimeout(DEFAULT_TIMEOUT.toLong(), TimeUnit.SECONDS)
        httpClientBuilder.connectTimeout(DEFAULT_TIMEOUT.toLong(), TimeUnit.SECONDS)
        createApiService()
    }

    fun createApiService() {
//        此方法可以设置成Date类型
        val gson = GsonBuilder()
                .setDateFormat("yyyy-MM-dd HH:mm:ss")
                .create()
        retrofit = Retrofit.Builder()
//                .addConverterFactory(ShowContentConverter())
//                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .baseUrl(BaseURL)
                .client(httpClientBuilder.build())
                .build()
        apiService = retrofit.create(ApiService::class.java)
    }

    //在访问EmucooRequest时创建单例
    private object SingletonHolder {
        internal val INSTANCE = Api()
    }

    //获取单例
    companion object {
        private fun getInstance(): Api {
            return SingletonHolder.INSTANCE
        }

        @JvmStatic
        fun getApiService(): ApiService {
            return getInstance().apiService
        }

    }


}