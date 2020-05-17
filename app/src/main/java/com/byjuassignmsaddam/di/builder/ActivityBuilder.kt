package com.byjuassignmsaddam.app.di.builder


import com.byjuassignmsaddam.activity.MainActivity
import com.byjuassignmsaddam.activity.NewsDetailsActivity
import com.byjuassignmsaddam.activity.SplashActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector


@Module
abstract class ActivityBuilder {

    @ContributesAndroidInjector
    internal abstract fun bindMainActivity(): MainActivity

    @ContributesAndroidInjector
    internal  abstract fun bindNewsDetailsActivity():NewsDetailsActivity

    @ContributesAndroidInjector
    internal  abstract fun bindSplashActivity():SplashActivity
}


