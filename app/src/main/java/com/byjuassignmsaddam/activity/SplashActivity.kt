package com.byjuassignmsaddam.activity


import android.os.Bundle
import android.os.Handler
import androidx.databinding.DataBindingUtil
import com.byjuassignmsaddam.R
import com.byjuassignmsaddam.databinding.ActivitySplashBinding
import com.byjuassignmsaddam.utility.ActivityController
import com.byjuassignmsaddam.viewmodel.SplashViewModel
import com.ludis.fitness.app.mvvm.baseMVVM.BaseMvvmActivity
import javax.inject.Inject

class SplashActivity :BaseMvvmActivity<SplashViewModel>() {

     val handle = Handler()
    @Inject
    lateinit var splashViewModel:SplashViewModel
    override fun getVM(): SplashViewModel =splashViewModel
    lateinit var binding:ActivitySplashBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=DataBindingUtil.setContentView(this,R.layout.activity_splash)
        setView()
    }


    fun setView(){
        Thread.sleep(4000)
         ActivityController.startActivity(this,MainActivity::class.java,true)

    }
}
