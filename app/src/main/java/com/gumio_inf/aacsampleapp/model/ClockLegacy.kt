package com.gumio_inf.aacsampleapp.model

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.util.Log
import java.util.*


class ClockLegacy(private val context: Context) {

    interface ClockListener {
        fun onReceive(date: Date)
    }

    private var mListener: ClockListener? = null

    private val mTimeTickBroadcastReceiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context, intent: Intent) {
            if (mListener != null) {
                mListener?.onReceive(Calendar.getInstance().time)
                Log.d("date time now:", Calendar.getInstance().time.toString())
            }
        }
    }

    fun setClockListener(listener: ClockListener) {
        if (mListener != null) {
            return
        }
        mListener = listener
        val intentFilter = IntentFilter().apply {
            addAction(Intent.ACTION_TIME_TICK)
        }
        context.registerReceiver(mTimeTickBroadcastReceiver, intentFilter)
    }

    fun removeClockListener() {
        mListener = null
        context.unregisterReceiver(mTimeTickBroadcastReceiver)
    }
}