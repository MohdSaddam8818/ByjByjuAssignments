package com.byjuassignmsaddam.interfaces


import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.byjuassignmentbysaddam.networkConnection.Response.NewsDetailModel
import io.reactivex.Flowable


@Dao
interface NewsDao {


    @Query("SELECT * FROM newsDetailModel")
     fun getAll():  Flowable<List<NewsDetailModel>>

    @Insert
     fun insertAll(users: List<NewsDetailModel>)

    @Delete
     fun delete(user: NewsDetailModel)

    @Update
     fun update(task: NewsDetailModel)

}
