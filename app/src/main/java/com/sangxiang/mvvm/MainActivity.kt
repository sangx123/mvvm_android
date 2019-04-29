package com.sangxiang.mvvm

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProviders
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_main.*
import com.sangxiang.mvvm.R
import com.sangxiang.mvvm.model.VideoModel
import com.sangxiang.mvvm.sqlite.VideoDatabase
import com.sangxiang.mvvm.sqlite.VideoEntity
import com.sangxiang.mvvm.viewmodel.MainActivityViewModel
import io.reactivex.Observable
import android.icu.lang.UCharacter.GraphemeClusterBreak.T
import io.reactivex.ObservableEmitter
import io.reactivex.ObservableOnSubscribe
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.plugins.RxJavaPlugins.onSubscribe
import io.reactivex.schedulers.Schedulers
import org.reactivestreams.Subscriber



class MainActivity : AppCompatActivity() {
    lateinit var mViewModel: MainActivityViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        test.text = "111"
        val factory = InjectorUtils.provideMainActivityViewModelFactory(this.applicationContext)
        mViewModel = ViewModelProviders.of(this, factory).get(MainActivityViewModel::class.java)
        mViewModel.getVideoLists()
        mViewModel.videoList.observe(this, android.arch.lifecycle.Observer<List<VideoModel>> {
            Log.e("sangxiang","数据变化了")
            it?.let {
                var list1=ArrayList<VideoEntity>()
               for (item in it){
                   list1.add(VideoEntity(item.id,item.imageUrl,item.videoUrl))
               }
                insert(list1)

            }

        })


    }

    fun insert(list:List<VideoEntity>){
        Observable.create(ObservableOnSubscribe<String> { e ->
            VideoDatabase.getInstance(this).videoDao().insertAll(list)
            e.onNext("success")
        }).subscribeOn(Schedulers.io())
          .observeOn(AndroidSchedulers.mainThread())
          .subscribe(object: io.reactivex.Observer<String>{
              override fun onComplete() {

              }

              override fun onSubscribe(d: Disposable) {

              }

              override fun onNext(t: String) {
                    Log.e("sangxiang",t)
                    getAll()
              }

              override fun onError(e: Throwable) {

              }

          })
    }
    fun getAll(){
        Observable.create(ObservableOnSubscribe<LiveData<List<VideoEntity>>> { e ->
            e.onNext(VideoDatabase.getInstance(this).videoDao().getAll())
        }).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(object: io.reactivex.Observer<LiveData<List<VideoEntity>>>{
                    override fun onComplete() {

                    }

                    override fun onSubscribe(d: Disposable) {

                    }

                    override fun onNext(t: LiveData<List<VideoEntity>>) {
                        Log.e("sangxiang","111")
                    }

                    override fun onError(e: Throwable) {

                    }

                });
    }
}
