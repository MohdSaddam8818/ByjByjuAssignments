package com.byjuassignmentbysaddam.networkStatus

import android.content.Context

/**
 * Created by chweya on 29/08/17.
 */

interface MonitorFactory {

    fun create(
        context: Context,
        connectionType: Int,
        listener: Monitor.ConnectivityListener
    ): Monitor
}
