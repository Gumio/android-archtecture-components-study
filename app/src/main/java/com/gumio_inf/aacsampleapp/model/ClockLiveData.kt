package com.gumio_inf.aacsampleapp.model

import android.arch.lifecycle.MutableLiveData
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.util.Log
import java.util.*

class ClockLiveData(private val mAppContext: Context): MutableLiveData<Date>() {

    init {
        postValue(Calendar.getInstance().time)
    }

    private val mTimeTickBroadcastReceiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context, intent: Intent) {
            postValue(Calendar.getInstance().time)
            Log.d("date time now:", this@ClockLiveData.value.let { toString() })
        }
    }

    override fun onActive() {
        super.onActive()
        Log.d("onActive", "Called!!!!!!!!")
        val intentFilter = IntentFilter().apply {
            addAction(Intent.ACTION_TIME_TICK)
        }
        mAppContext.registerReceiver(mTimeTickBroadcastReceiver, intentFilter)
    }

    override fun onInactive() {
        super.onInactive()
        Log.d("onInactive", "Called!!!!!!!!")
        mAppContext.unregisterReceiver(mTimeTickBroadcastReceiver)
    }
}