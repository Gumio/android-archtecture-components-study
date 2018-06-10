package com.gumio_inf.aacsampleapp.model

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.LiveData
import android.arch.lifecycle.Transformations
import android.icu.text.SimpleDateFormat
import java.util.*

class ClockViewModel(application: Application): AndroidViewModel(application) {
    private val mTimeStringFormat = SimpleDateFormat("HH:mm", Locale.JAPAN)
    val strTime: LiveData<String>
    private val clock: ClockLiveData by lazy { ClockLiveData(application) }

    init {
        strTime = Transformations.map(clock) { return@map mTimeStringFormat.format(it.time) }
    }
}