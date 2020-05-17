package com.byjuassignmsaddam





import android.app.Activity
import android.app.Service
import android.content.Context
import androidx.multidex.MultiDex
import androidx.multidex.MultiDexApplication

import com.byjuassignmentbysaddam.interfaces.LifecycleDelegate
import com.byjuassignmentbysaddam.networkConnection.AuthWebServices
import com.byjuassignmsaddam.app.di.component.DaggerAppComponent

import com.byjuassignmsaddam.utility.AppLifeCycleHandler

import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import dagger.android.HasServiceInjector


import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class ByJuApplication : MultiDexApplication(), LifecycleDelegate,HasActivityInjector, HasServiceInjector {
    override fun onAppBackgrounded() {

    }

    override fun onAppForegrounded() {
    }

    companion object {
        lateinit var instance: ByJuApplication
    }

    private var headerInterceptor: Interceptor? = null
    @Inject
    internal lateinit var activityDispatchingAndroidInjector: DispatchingAndroidInjector<Activity>
    @Inject
    internal lateinit var serviceDispatchingAndroidInjector: DispatchingAndroidInjector<Service>

    override fun activityInjector() = activityDispatchingAndroidInjector
    override fun serviceInjector() = serviceDispatchingAndroidInjector
    private lateinit var authWebServices: AuthWebServices



    override fun attachBaseContext(base: Context) {
        super.attachBaseContext(base)
        MultiDex.install(this)
    }






    override fun onCreate() {
        super.onCreate()
        MultiDex.install(this)
        instance = this


        DaggerAppComponent.builder()
            .application(this)
            .build()
            .inject(this)

        initRetrofit()
        registerLifecycleHandler()


    }

    private fun registerLifecycleHandler() {
        val lifecycleHandler = AppLifeCycleHandler(this)
        registerActivityLifecycleCallbacks(lifecycleHandler)
        registerComponentCallbacks(lifecycleHandler)
    }
    /*fun rebuildObjectBoxInstance() {
        boxStore = MyObjectBox.builder().androidContext(this).build()
    }*/
    fun getEndpoint(): AuthWebServices = authWebServices


    private fun initRetrofit() {
        val httpClientBuilder = addNetworkInterceptor();

        val retrofit = Retrofit.Builder()
            .baseUrl(BuildConfig.SERVER_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .client(httpClientBuilder.build())
            .build()
        authWebServices = retrofit.create(AuthWebServices::class.java)
    }
    private val logger = HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
    private val okHttpClient = OkHttpClient.Builder()
        .addInterceptor(logger)
        .readTimeout(1, TimeUnit.MINUTES)
        .connectTimeout(1, TimeUnit.MINUTES)

    private fun addNetworkInterceptor(): OkHttpClient.Builder {

        headerInterceptor = Interceptor { chain ->
            val original = chain.request()
            var response: Response? = null
            val requestBuilder = original.newBuilder()
                //.header("token", token)
                .header("Content-Type", "application/json")
                .header("Accept", "application/json")
                .header("a-v", BuildConfig.VERSION_NAME)
                .method(original.method(), original.body())
            response = chain.proceed(requestBuilder.build())


            response
        }

        return okHttpClient.addInterceptor(headerInterceptor!!)
    }
}