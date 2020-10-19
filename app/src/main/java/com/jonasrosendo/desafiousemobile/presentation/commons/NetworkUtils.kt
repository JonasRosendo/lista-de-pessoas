package com.jonasrosendo.desafiousemobile.presentation.commons

import android.content.Context
import android.net.ConnectivityManager

object NetworkUtils {

    fun checkInternetConnection(context: Context): Boolean {
        var isDeviceConnected = false
        val connManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        connManager.allNetworks.forEach { network ->
            connManager.getNetworkInfo(network)?.apply {
                 isDeviceConnected = isConnected
            }
        }

        return isDeviceConnected
    }
}