package com.gumio_inf.aacsampleapp.ui.common

import android.arch.lifecycle.Lifecycle
import android.arch.lifecycle.LifecycleObserver
import android.arch.lifecycle.LifecycleOwner
import android.arch.lifecycle.OnLifecycleEvent
import android.util.Log

class MyLifecycleObserver: LifecycleObserver {
    // イベント登録しておくことで状態変化した時に呼び出される
    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    fun onCreate(owner: LifecycleOwner) = Log.d("Lifecycle Observer Event", "ON_CREATE")

    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    fun onStart(owner: LifecycleOwner) = Log.d("Lifecycle Observer Event", "ON_START")

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    fun onResume(owner: LifecycleOwner) = Log.d("Lifecycle Observer Event", "ON_RESUME")

    @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
    fun onPause(owner: LifecycleOwner) = Log.d("Lifecycle Observer Event", "ON_PAUSE")

    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    fun onStop(owner: LifecycleOwner) = Log.d("Lifecycle Observer Event", "ON_STOP")

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    fun onDestroy(owner: LifecycleOwner) {
        Log.d("Lifecycle Observer Event", "ON_DESTROY")
        owner.lifecycle.removeObserver(this)
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_ANY)
    fun onAny(owner: LifecycleOwner) = Log.d("Lifecycle Observer Event", "ON_ANY")
}