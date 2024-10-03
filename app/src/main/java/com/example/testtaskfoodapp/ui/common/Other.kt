package com.example.testtaskfoodapp.ui.common

import android.util.Log
import com.example.testtaskfoodapp.BuildConfig

fun logDebug(tag: String, msg: String){
    if(BuildConfig.DEBUG){
        Log.e(tag, msg)
    }
}

