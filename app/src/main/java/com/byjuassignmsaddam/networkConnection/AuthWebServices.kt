package com.byjuassignmentbysaddam.networkConnection

import com.byjuassignmentbysaddam.networkConnection.Response.NewsListData
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface AuthWebServices {


    @GET("everything")
    fun getNewsList(@Query("q")qName:String,@Query("from")todayDate:String,@Query("sortBy")sortBy:String,@Query("apiKey")apiKey:String): Observable<NewsListData>
}