package com.byjuassignmsaddam.roomDataBase


import android.content.Context

import androidx.room.Room

import com.byjuassignmsaddam.roomDataBase.local.AppDatabase

class DataBaseClient(private val mContext: Context) {

    val appDataBase: AppDatabase

    init {
        appDataBase = Room.databaseBuilder(mContext, AppDatabase::class.java, "NewsSaddam").build()
    }

    companion object {
        private var mInstance: DataBaseClient? = null

        @Synchronized
        fun getInstance(mContext: Context): DataBaseClient {
            if (mInstance == null)
                mInstance = DataBaseClient(mContext)
            return mInstance as DataBaseClient
        }
    }
}
