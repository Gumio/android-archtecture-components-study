package com.gumio_inf.aacsampleapp.ui.viewmodel

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.LiveData
import android.arch.lifecycle.Transformations
import android.icu.text.SimpleDateFormat
import com.gumio_inf.aacsampleapp.ui.common.ClockLiveData
import java.util.*

class ClockViewModel(application: Application): AndroidViewModel(application) {
    private val mTimeStringFormat = SimpleDateFormat("HH:mm", Locale.JAPAN)
    val strTime: LiveData<String>
    private val clock: ClockLiveData by lazy { ClockLiveData(application) }

    init {
        strTime = Transformations.map(clock) { it -> return@map mTimeStringFormat.format(it.time) }
    }
}