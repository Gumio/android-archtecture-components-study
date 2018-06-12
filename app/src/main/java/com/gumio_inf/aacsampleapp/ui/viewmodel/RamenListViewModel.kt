package com.gumio_inf.aacsampleapp.ui.viewmodel

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import com.gumio_inf.aacsampleapp.model.repository.RamenRepository

class RamenListViewModel(application: Application) : AndroidViewModel(application) {

    private val repository = RamenRepository.getInstance(application)

    val ramens = repository.loadAllRamens()

}