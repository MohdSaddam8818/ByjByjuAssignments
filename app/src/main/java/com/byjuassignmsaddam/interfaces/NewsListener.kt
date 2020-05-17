package com.byjuassignmentbysaddam.interfaces

import android.view.View
import com.byjuassignmentbysaddam.networkConnection.Response.NewsDetailModel
import com.byjuassignmentbysaddam.networkConnection.Response.NewsListData

interface NewsListener {
    fun OnClickListener(newsList: NewsDetailModel)
}