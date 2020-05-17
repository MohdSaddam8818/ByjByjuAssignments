package com.byjuassignmsaddam.activity

import android.content.Intent
import android.net.ConnectivityManager
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.databinding.DataBindingUtil
import com.byjuassignmentbysaddam.networkStatus.Monitor
import com.byjuassignmentbysaddam.networkStatus.NetworkConnection

import com.byjuassignmsaddam.databinding.ActivityMainBinding
import com.byjuassignmsaddam.viewmodel.MainActivityViewModel
import com.ludis.fitness.app.mvvm.baseMVVM.BaseMvvmActivity
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.byjuassignmentbysaddam.adapter.NewsListAdapter
import com.byjuassignmentbysaddam.interfaces.NewsListener
import com.byjuassignmentbysaddam.networkConnection.Response.ArticlesResponse
import com.byjuassignmentbysaddam.networkConnection.Response.NewsDetailModel
import com.byjuassignmentbysaddam.networkConnection.Response.NewsListData
import com.byjuassignmsaddam.R
import com.byjuassignmsaddam.constant.AppConstant

import io.reactivex.Observable
import com.byjuassignmsaddam.utility.CommonUtils
import com.byjuassignmsaddam.utility.showSnack


import com.byjuassignmsaddam.roomDataBase.DataBaseClient
import com.byjuassignmsaddam.utility.ActivityController
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.lang.reflect.Array.get
import javax.inject.Scope
import kotlin.collections.ArrayList
import kotlin.coroutines.suspendCoroutine


class MainActivity : BaseMvvmActivity<MainActivityViewModel>(), NewsListener {


    @Inject
    lateinit var mainActivityViewModel: MainActivityViewModel

    override fun getVM(): MainActivityViewModel = mainActivityViewModel
    lateinit var binding: ActivityMainBinding
    lateinit var adapterNews: NewsListAdapter
    val compositeDisposable: CompositeDisposable = CompositeDisposable()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        setToolBar()
        networkConnection = NetworkConnection.from(this)?.monitor(object : Monitor.ConnectivityListener {
                override fun onConnectivityChanged(
                    connectionType: Int,
                    isConnected: Boolean,
                    isFast: Boolean
                ) {
                    val type: String
                    val speed: String
                    if (isConnected) {
                        when (connectionType) {
                            -1 -> {
                                type = "Any"
                                initView()
                            }
                            ConnectivityManager.TYPE_WIFI -> {
                                type = "Wifi"

                                initView()
                            }
                            ConnectivityManager.TYPE_MOBILE -> {
                                type = "Mobile"

                                initView()
                            }
                            else -> {
                                type = "Unknown"

                                initView()
                            }
                        }

                        if (isFast)
                            speed = "Fast"
                        else if (type == "Unknown")
                            speed = "n/A"
                        else
                            speed = "Slow"
                    } else {
                        showSnack().isConnected(false, this@MainActivity)
                        speed = "n/A"
                        initView()
                    }
                }

            })!!

    }

    /**
     * @return manage  ToolBar
     */
    //manage send result
    fun setToolBar() {
        binding!!.tbProfile.tvTitle.setText("HEADLINES")
        binding!!.tbProfile.ivToolBar.visibility = View.GONE


    }


    fun initView() {
        if (CommonUtils().isOnline(this)) {
            // Delete Data to Room Database
            setOfLineData(true)
            serviceConnection(true)
        } else {
            showSnack().isConnected(false, this)
            serviceConnection(false)
        }
    }

    /**
     * @return manage serviceConnection api Response
     */
    //manage send request and result
    fun serviceConnection(netStates: Boolean) {
        if (netStates) {
            val sdf = SimpleDateFormat("yyyy/MM/dd")
            val currentDate = sdf.format(Date())
            mainActivityViewModel.getNewsList(
                "bitcoin",
                currentDate,
                "publishedAt",
                "a2a6e1b115ba40569815c341b356d939"
            )
            mainActivityViewModel.response.observe(this, Observer {
                it?.articles
                DataManagement(it?.articles!!)
                it?.articles?.let { it1 -> SetView(DataManagement(it?.articles!!)) }
                Observable.just(it.articles)
                    .subscribeOn(Schedulers.io())
                    .subscribe { result ->
                        DataBaseClient.getInstance(applicationContext).appDataBase.userDao().insertAll(DataManagement(it?.articles!!))

                    }
            })

            mainActivityViewModel.liveData.observe(this, Observer {

                if (it.equals("Error")) {
                }

            })
        } else {
            //Get data to Room Database
            setOfLineData(false)

        }
    }


    override fun OnClickListener(newsList: NewsDetailModel) {

        var intent= Intent()
         intent.putExtra(AppConstant.NewsDetails,newsList)
        ActivityController.startActivity(this,NewsDetailsActivity::class.java,intent,false)


    }


    fun setOfLineData(netStatus: Boolean) {

        mainActivityViewModel.loadOflineData(this)
        mainActivityViewModel.OflineResponse.observe(this, Observer {
            if (!netStatus) {
                SetView(it)
            } else {
                if(it.size>0){
                   /* Observable.just(it)
                        .subscribeOn(Schedulers.io())
                        .subscribe { result ->
                            for (i in result.indices) {
                                DataBaseClient.getInstance(applicationContext).appDataBase.userDao()
                                    .delete(it.get(i))
                            }

                        }*/
                }



            }
        })


    }


    fun DataManagement(apiDataLiat:ArrayList<ArticlesResponse>):ArrayList<NewsDetailModel>{
        var response: ArrayList<NewsDetailModel> = ArrayList()
             for (i in apiDataLiat.indices){
                 var newsDetailModel:NewsDetailModel = NewsDetailModel(0,apiDataLiat.get(i).author,apiDataLiat.get(i).title,apiDataLiat.get(i).description,
                     apiDataLiat.get(i).url,apiDataLiat.get(i).urlToImage,apiDataLiat.get(i).publishedAt,apiDataLiat.get(i).content,apiDataLiat.get(i).source!!.name)
               response.add(newsDetailModel)
             }


        return response
    }


    fun SetView(NewsLists: List<NewsDetailModel>) {
        adapterNews = NewsLists?.let { it1 -> NewsListAdapter(this, it1, this) }!!
        binding.rvNewsList.layoutManager = LinearLayoutManager(this)
        binding.rvNewsList.setHasFixedSize(true)
        binding.rvNewsList.isNestedScrollingEnabled = false
        binding.rvNewsList.adapter = adapterNews
    }


}