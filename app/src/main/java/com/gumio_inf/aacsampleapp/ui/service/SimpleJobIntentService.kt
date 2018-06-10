package com.gumio_inf.aacsampleapp.ui.service

import android.arch.lifecycle.Lifecycle
import android.arch.lifecycle.ProcessLifecycleOwner
import android.content.Context
import android.content.Intent
import android.os.Handler
import android.os.SystemClock
import android.support.v4.app.JobIntentService
import android.util.Log
import android.widget.Toast
import com.gumio_inf.aacsampleapp.ui.activity.DialogActivity


class SimpleJobIntentService: JobIntentService() {

    companion object {
        private const val JOB_ID = 1000
        fun enqueueWork(context: Context, work: Intent) {
            enqueueWork(context, SimpleJobIntentService::class.java, JOB_ID, work)
        }
    }

    // サービス内容
    override fun onHandleWork(intent: Intent) {
        Log.i("SimpleJobIntentService", "Executing work: $intent")
        var label = intent.getStringExtra("label")
        if (label == null) {
            label = intent.toString()
        }
        toast("Executing")
        for (i in 0..4) {
            Log.i("SimpleJobIntentService", "Running service " + (i + 1)
                    + "/5 @ " + SystemClock.elapsedRealtime())
            try {
                Thread.sleep(1000)
            } catch (e: InterruptedException) {
            }

        }
        Log.i("SimpleJobIntentService", "Completed service @ " + SystemClock.elapsedRealtime())
    }

    // 実行終了
    override fun onDestroy() {
        super.onDestroy()
        // アプリがforegroundなら、ダイアログテーマのActivityに遷移
        if (ProcessLifecycleOwner.get().lifecycle.currentState.isAtLeast(Lifecycle.State.STARTED)) {
            startActivity(Intent(this, DialogActivity::class.java))
        } else {
            // backgroundならならToastを出すようにする
            toast("All Work Complete!!")
        }
    }

    private val mHandler = Handler()
    private fun toast(text: CharSequence) {
        mHandler.post{ Toast.makeText(this@SimpleJobIntentService, text, Toast.LENGTH_SHORT).show() }
    }
}