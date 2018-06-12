package com.gumio_inf.aacsampleapp.model.api

import android.support.annotation.WorkerThread
import com.gumio_inf.aacsampleapp.model.vo.Ramen
import com.gumio_inf.aacsampleapp.model.vo.Ramens

class RamenApi {
    @WorkerThread
    fun fetchRamens(): List<Ramen> {
        // This method only returns a list, but let's pretend that this is a network call.
        return Ramens.RAMENS.mapIndexed { i, name -> Ramen((i + 1).toLong(), name, false) }
    }
}