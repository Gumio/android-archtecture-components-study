package com.gumio_inf.aacsampleapp.ui.fragment

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import com.gumio_inf.aacsampleapp.repository.CheeseRepository

class CheeseListViewModel(application: Application) : AndroidViewModel(application) {

    private val repository = CheeseRepository.getInstance(application)

    val cheeses = repository.loadAllCheeses()

}