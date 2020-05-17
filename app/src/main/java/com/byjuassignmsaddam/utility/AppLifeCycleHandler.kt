package com.byjuassignmsaddam.utility

import android.app.Activity
import android.app.Application.ActivityLifecycleCallbacks
import android.content.ComponentCallbacks2
import android.content.res.Configuration
import android.os.Bundle
import com.byjuassignmentbysaddam.interfaces.LifecycleDelegate

class AppLifeCycleHandler(val lifecycleDelegate: LifecycleDelegate) : ActivityLifecycleCallbacks, ComponentCallbacks2 {

    //using to only send callback once in a single session.
    private var startCount = 0

    override fun onActivityStarted(activity: Activity?) {
        //No Implementation Required
        if (startCount == 0) {
            lifecycleDelegate.onAppForegrounded()
        }
        ++startCount
    }

    override fun onActivityStopped(activity: Activity?) {
        //No Implementation Required
        --startCount
        if (startCount == 0) {
            lifecycleDelegate.onAppBackgrounded()
        }
    }

    //region Unimplemented Methods

    override fun onActivityResumed(activity: Activity?) {

    }

    override fun onTrimMemory(level: Int) {

    }

    override fun onActivityDestroyed(activity: Activity?) {
        //No Implementation Required
    }

    override fun onActivitySaveInstanceState(activity: Activity?, outState: Bundle?) {
        //No Implementation Required
    }



    override fun onActivityCreated(activity: Activity?, savedInstanceState: Bundle?) {
        //No Implementation Required
    }

    override fun onConfigurationChanged(newConfig: Configuration?) {
        //No Implementation Required
    }

    override fun onActivityPaused(activity: Activity?) {
        //No Implementation Required
    }

    override fun onLowMemory() {
        //No Implementation Required
    }
    //endregion

}