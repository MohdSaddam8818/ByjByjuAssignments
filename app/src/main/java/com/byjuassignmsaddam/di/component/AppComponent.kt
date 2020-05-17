package com.byjuassignmsaddam.app.di.component


import android.app.Application
import com.byjuassignmsaddam.ByJuApplication
import com.byjuassignmsaddam.app.di.builder.ActivityBuilder
import com.byjuassignmsaddam.app.di.module.AppModule


import com.byjuassignmsaddam.viewmodel.BaseViewModel.ViewModelModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import javax.inject.Singleton

@Singleton
@Component(modules = [AndroidInjectionModule::class, AppModule::class, ActivityBuilder::class, ViewModelModule::class ])
interface AppComponent {

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun application(application: Application): Builder

        fun build(): AppComponent
    }

    fun inject(app: ByJuApplication)

}