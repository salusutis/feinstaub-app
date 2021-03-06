/*
 * Copyright © 2019 Marc Auberer. All rights reserved.
 */

package com.mrgames13.jimdo.feinstaubapp.network

import android.app.Activity
import android.content.Context
import android.util.Log
import android.widget.Toast
import com.mrgames13.jimdo.feinstaubapp.BuildConfig
import com.mrgames13.jimdo.feinstaubapp.R
import com.mrgames13.jimdo.feinstaubapp.tool.Constants
import io.ktor.client.HttpClient
import io.ktor.client.features.json.JsonFeature
import io.ktor.client.features.json.serializer.KotlinxSerializer
import io.ktor.http.HttpStatusCode
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

fun getNetworkClient(): HttpClient {
    return HttpClient {
        install(JsonFeature) {
            serializer = KotlinxSerializer()
        }
    }
}

fun handlePossibleErrors(activity: Activity, status: HttpStatusCode): Boolean {
    if(status != HttpStatusCode.OK) {
        Log.e(Constants.TAG, "Something went wrong during the backend request: " + status.value + " - " + status.description)
        CoroutineScope(Dispatchers.Main).launch {
            Toast.makeText(activity, R.string.error_try_again, Toast.LENGTH_SHORT).show()
        }
        return false
    }
    return true
}

fun getBackendMainUrl(context: Context): String {
    return "https://" + context.getString(R.string.host) + String.format(context.getString(R.string.path_main), BuildConfig.VERSION_CODE)
}

fun getBackendDataUrl(context: Context): String {
    return "https://" + context.getString(R.string.host) + context.getString(R.string.path_data)
}