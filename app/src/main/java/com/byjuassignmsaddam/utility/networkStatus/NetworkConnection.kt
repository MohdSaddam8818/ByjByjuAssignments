package com.byjuassignmentbysaddam.networkStatus

import android.content.Context
import android.util.Log
import com.byjuassignmentbysaddam.networkStatus.internal.DefaultMonitorFactory


import java.lang.ref.WeakReference
import java.util.HashSet

/**
 * Created by chweya on 29/08/17.
 */

class NetworkConnection private constructor(context: Context) {
    private val contextRef: WeakReference<Context>
    private val monitors: MutableSet<Monitor>

    init {
        monitors = HashSet()
        this.contextRef = WeakReference(context)
    }

    fun monitor(connectionType: Int, listener: Monitor.ConnectivityListener): NetworkConnection? {
        val context = contextRef.get()
        if (context != null)
            monitors.add(DefaultMonitorFactory().create(context, connectionType, listener))

        start()
        return tovuti
    }

    fun monitor(listener: Monitor.ConnectivityListener): NetworkConnection? {
        return monitor(-1, listener)
    }

    fun start() {
        for (monitor in monitors) {
            monitor.onStart()
        }

        if (monitors.size > 0)
            Log.i(TAG, "started tovuti")
    }

    fun stop() {
        for (monitor in monitors) {
            monitor.onStop()
        }
    }

    companion object {
        private val TAG = "NetworkConnection"
        private val lock = Any()

        @Volatile
        private var tovuti: NetworkConnection? = null

        fun from(context: Context): NetworkConnection? {
            if (tovuti == null) {
                synchronized(lock) {
                    if (tovuti == null) {
                        tovuti = NetworkConnection(context)
                    }
                }
            }
            return tovuti
        }
    }
}
