package com.alqiran.portfoliomainadmin.utils

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import com.alqiran.portfoliomainadmin.BaseApplication

fun isOnline(): Boolean {
    val context = BaseApplication.appContext.applicationContext
    val connectivityManager =
        context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    val network = connectivityManager.activeNetwork ?: return false
    val capabilities = connectivityManager.getNetworkCapabilities(network) ?: return false
    return capabilities.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)
}