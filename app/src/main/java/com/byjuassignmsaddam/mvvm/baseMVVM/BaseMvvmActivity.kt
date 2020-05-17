package com.ludis.fitness.app.mvvm.baseMVVM

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.*
import androidx.lifecycle.ViewModelProvider.Factory
import com.byjuassignmentbysaddam.networkStatus.NetworkConnection
import com.byjuassignmsaddam.viewmodel.BaseViewModel.BaseViewModel


import dagger.android.AndroidInjection
import javax.inject.Inject


abstract class BaseMvvmActivity<out VM : BaseViewModel> : AppCompatActivity() , LifecycleOwner {

    @Inject
    lateinit var viewModelFactory: Factory

    lateinit var vm : BaseViewModel
    lateinit var networkConnection: NetworkConnection
    override fun onCreate(savedInstanceState: Bundle?) {
        performDI()
        super.onCreate(savedInstanceState)
        lifecycle.handleLifecycleEvent(Lifecycle.Event.ON_CREATE)
        setViewModel()
    }

    fun setViewModel( ) {
        vm= ViewModelProviders.of(this, viewModelFactory)[getVM().javaClass]

    }

    private fun performDI() = AndroidInjection.inject(this)
    abstract    fun getVM() : VM


    //private val lifecycle:LifecycleRegistry by lazy{ LifecycleRegistry(this) }
    private val lifecycle =  LifecycleRegistry(this)

    override fun onStart() {

        lifecycle.handleLifecycleEvent(Lifecycle.Event.ON_START)
        super.onStart()
    }

    override fun onResume(){

        lifecycle.handleLifecycleEvent(Lifecycle.Event.ON_RESUME)
        super.onResume()
    }

    override fun onPause(){

        lifecycle.handleLifecycleEvent(Lifecycle.Event.ON_PAUSE)
        super.onPause()
    }

    override fun onStop(){

        lifecycle.handleLifecycleEvent(Lifecycle.Event.ON_STOP)
        super.onStop()
    }

    override fun onDestroy(){

        lifecycle.handleLifecycleEvent(Lifecycle.Event.ON_DESTROY)
        super.onDestroy()
    }

    fun onAny(){
        lifecycle.handleLifecycleEvent(Lifecycle.Event.ON_ANY)
    }

    override  fun getLifecycle() = lifecycle


}