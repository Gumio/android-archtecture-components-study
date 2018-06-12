package com.gumio_inf.aacsampleapp.ui.viewmodel

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.Transformations
import com.gumio_inf.aacsampleapp.model.repository.CheeseRepository
import com.gumio_inf.aacsampleapp.model.vo.Cheese

class CheeseDetailViewModel(application: Application) : AndroidViewModel(application) {

    /** Data source */
    private val repository = CheeseRepository.getInstance(application)

    /** The ID of the cheese to show; this is an input from the UI */
    private val cheeseId = MutableLiveData<Long>()

    val cheese: LiveData<Cheese?> = Transformations.switchMap(cheeseId) { cheeseId ->
        repository.findCheeseById(cheeseId)
    }

    fun setCheeseId(cheeseId: Long) {
        this.cheeseId.value = cheeseId
    }

    fun toggleFavorite() {
        this.cheese.value?.let {
            repository.updateCheese(Cheese(it.id, it.name, !it.favorite))
        }
    }

}
