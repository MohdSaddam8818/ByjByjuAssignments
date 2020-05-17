package com.byjuassignmsaddam.viewmodel

import android.content.Context
import androidx.lifecycle.MutableLiveData
import com.byjuassignmentbysaddam.networkConnection.Response.ArticlesResponse
import com.byjuassignmentbysaddam.networkConnection.Response.NewsDetailModel
import com.byjuassignmentbysaddam.networkConnection.Response.NewsListData
import com.byjuassignmsaddam.ByJuApplication

import com.byjuassignmsaddam.viewmodel.BaseViewModel.BaseViewModel
import com.byjuassignmsaddam.roomDataBase.DataBaseClient
import com.byjuassignmsaddam.utility.SingleLiveEvent
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class MainActivityViewModel @Inject constructor() : BaseViewModel() {

    var liveData = SingleLiveEvent<String>()
    var response: MutableLiveData<NewsListData> = MutableLiveData()
    var OflineResponse: MutableLiveData<List<NewsDetailModel>> = MutableLiveData()

    fun getNewsList(q: String, from: String, sortBy: String, apiKey: String) {

            try {
                compositeDisposable.add(ByJuApplication.instance.getEndpoint().getNewsList(q, from, sortBy, apiKey)
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribeOn(Schedulers.io())
                        .subscribe({ result ->

                            liveData.postValue("Success")

                            response.postValue(result)


                        }, {
                            //RxJavaPlugins.onError(it)
                            handleEvaluationException(it)
                            handleApiError(it)
                            liveData.postValue("Error")


                        })


                )


            } catch (e: Exception) {
                liveData.postValue("Something Went Wrong")

            }


    }
    fun loadOflineData(context:Context) {
        compositeDisposable.add(DataBaseClient.getInstance(context).appDataBase.userDao().getAll()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                OflineResponse.postValue(it)


            }))


    }

}