package com.byjuassignmsaddam.utility


import android.annotation.SuppressLint
import android.content.Context
import android.net.ConnectivityManager
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*


public class  CommonUtils {


    fun isOnline(context: Context): Boolean {
        val conMgr = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val netInfo = conMgr.activeNetworkInfo
        return !(netInfo == null || !netInfo.isConnected || !netInfo.isAvailable)
    }

    fun getDateFormet(currentDate: String): String {
        @SuppressLint("SimpleDateFormat") val format = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss")
        var returnCurrentDate = ""
        try {
            val newDate = format.parse(currentDate)
            @SuppressLint("SimpleDateFormat") val objFormatter = SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH)
            returnCurrentDate = objFormatter.format(newDate!!)

        } catch (e: ParseException) {
            e.printStackTrace()
        }

        return returnCurrentDate
    }

}