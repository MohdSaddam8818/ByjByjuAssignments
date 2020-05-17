package com.byjuassignmsaddam.roomDataBase.local

import androidx.room.Database
import androidx.room.RoomDatabase

import com.byjuassignmentbysaddam.networkConnection.Response.ArticlesResponse
import com.byjuassignmentbysaddam.networkConnection.Response.NewsDetailModel
import com.byjuassignmsaddam.interfaces.NewsDao


@Database(entities = [NewsDetailModel::class], version = 1)
abstract class AppDatabase : RoomDatabase() {

    abstract fun userDao(): NewsDao

}