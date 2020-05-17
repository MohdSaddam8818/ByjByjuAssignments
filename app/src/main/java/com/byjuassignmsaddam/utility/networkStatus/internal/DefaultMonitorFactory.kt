package com.byjuassignmentbysaddam.networkStatus.internal

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import androidx.core.content.ContextCompat

import com.byjuassignmentbysaddam.networkStatus.Monitor
import com.byjuassignmentbysaddam.networkStatus.MonitorFactory


/**
 * Created by chweya on 29/08/17.
 */

class DefaultMonitorFactory : MonitorFactory {

    override fun create(
        context: Context,
        connectionType: Int,
        listener: Monitor.ConnectivityListener
    ): Monitor {

        val permissionResult = ContextCompat.checkSelfPermission(context, ACCESS_NETWORK_PERMISSION)
        val hasPermission = permissionResult == PackageManager.PERMISSION_GRANTED

        return if (hasPermission)
            DefaultMonitor(context, listener, connectionType)
        else
            NoopMonitor()
    }

    companion object {
        val ACCESS_NETWORK_PERMISSION = Manifest.permission.ACCESS_NETWORK_STATE
    }
}
