package com.byjuassignmentbysaddam.networkStatus.internal


import com.byjuassignmentbysaddam.networkStatus.Monitor

/**
 * Created by chweya on 29/08/17.
 */

class NoopMonitor : Monitor {
    override fun onStart() {
        //no-op
    }

    override fun onStop() {
        //no-op
    }
}
