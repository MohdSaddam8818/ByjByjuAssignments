package com.byjuassignmentbysaddam.networkStatus

/**
 * Created by chweya on 29/08/17.
 */

interface Monitor : LifecycleListener {

    interface ConnectivityListener {
        fun onConnectivityChanged(connectionType: Int, isConnected: Boolean, isFast: Boolean)
    }
}
