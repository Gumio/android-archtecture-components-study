package com.gumio_inf.aacsampleapp.ui.activity

import android.arch.lifecycle.*
import android.content.Intent
import android.os.Bundle
import android.os.PersistableBundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.View
import com.gumio_inf.aacsampleapp.R
import com.gumio_inf.aacsampleapp.ui.service.SimpleJobIntentService
import kotlinx.android.synthetic.main.activity_lifecycles.*
import kotlinx.coroutines.experimental.CommonPool
import kotlinx.coroutines.experimental.android.UI
import kotlinx.coroutines.experimental.async
import kotlinx.coroutines.experimental.delay
import kotlinx.coroutines.experimental.launch
import kotlinx.coroutines.experimental.rx2.await
import kotlinx.coroutines.experimental.rx2.rxSingle

class LifecyclesActivity : AppCompatActivity(), LifecycleOwner, GenericLifecycleObserver {

    var cnt = 0

    object RxModel {
        fun returnTenAsync() = rxSingle(CommonPool) {
            delay(2000)
            return@rxSingle 10
        }

        fun returnTwentyAsync() = rxSingle(CommonPool) {
            delay(2000)
            return@rxSingle 20
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lifecycles)

        ProcessLifecycleOwner.get().lifecycle.addObserver(this)
        Log.d("onCreate -> ", lifecycle.currentState.name)

        val lifecycle: Lifecycle = lifecycle
        val state: Lifecycle.State = lifecycle.currentState
        val lifecycleRegistry = LifecycleRegistry(this)

        progressBar.max = 100
        progressBar.secondaryProgress = 75
        progressBar.isIndeterminate = true

        buttonAsync.setOnClickListener {
            onRxButtonClick()
        }

        buttonJobIntent.setOnClickListener {
            SimpleJobIntentService.enqueueWork(this, Intent(this, SimpleJobIntentService::class.java))
        }
    }

    private fun onRxButtonClick() = launch(UI) {
        setButtonsEnabled(false)
        resultText.text = cnt.toString()
        progressBar.visibility = View.VISIBLE

        val ten = async(CommonPool) { RxModel.returnTenAsync().await() }
        val twenty = async(CommonPool) { RxModel.returnTwentyAsync().await() }
        val result = ten.await() * twenty.await()
        cnt += result

        if (lifecycle.currentState.isAtLeast(Lifecycle.State.STARTED)) {
            resultText.text = cnt.toString()
        }

        progressBar.visibility = View.GONE
        setButtonsEnabled(true)
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    override fun onStart() {
        super.onStart()
        Log.d("onStart -> ", lifecycle.currentState.name)
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    override fun onResume() {
        super.onResume()
        Log.d("onResume -> ", lifecycle.currentState.name)
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
    override fun onPause() {
        super.onPause()
        Log.d("onPause -> ", lifecycle.currentState.name)

    }

    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    override fun onStop() {
        super.onStop()
        Log.d("onStop -> ", lifecycle.currentState.name)

    }

    @OnLifecycleEvent(Lifecycle.Event.ON_ANY)
    override fun onSaveInstanceState(outState: Bundle?, outPersistentState: PersistableBundle?) {
        super.onSaveInstanceState(outState, outPersistentState)
        Log.d("onSaveInstanceState -> ", lifecycle.currentState.name)
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    override fun onDestroy() {
        super.onDestroy()
        Log.d("onDestroy -> ", lifecycle.currentState.name)
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_ANY)
    override fun onRestart() {
        super.onRestart()
        Log.d("onRestart -> ", lifecycle.currentState.name)
    }

    private fun setButtonsEnabled(enabled: Boolean) {
        buttonAsync.isEnabled = enabled
    }

    override fun onStateChanged(source: LifecycleOwner?, event: Lifecycle.Event?) {
        Log.d("ProcessLifecycle", event?.name)
    }
}

        // 現在のライフサイクルがxxの状態より後だったら~するって時に使えるのかな？
//        val flg: Boolean = Lifecycle.State.CREATED.isAtLeast(Lifecycle.State.RESUMED)
//        if (flg) {
//
//        }

//        observer = MyLifecycleObserver()
//        lifecycle.addObserver(observer)

//        ProcessLifecycleOwner.get().lifecycle.addObserver(
//                GenericLifecycleObserver { source, event ->
//                    Log.d("ProcessLifecycleOwner : onStateChanged", "${source} , ${event.name}")
//                }
//        }
