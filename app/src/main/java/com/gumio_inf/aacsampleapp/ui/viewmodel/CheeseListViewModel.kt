package com.gumio_inf.aacsampleapp.ui.viewmodel

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import com.gumio_inf.aacsampleapp.model.repository.CheeseRepository

class CheeseListViewModel(application: Application) : AndroidViewModel(application) {

    private val repository = CheeseRepository.getInstance(application)

    val cheeses = repository.loadAllCheeses()

}