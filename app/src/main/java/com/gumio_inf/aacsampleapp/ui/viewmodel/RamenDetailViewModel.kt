package com.gumio_inf.aacsampleapp.ui.viewmodel

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.Transformations
import com.gumio_inf.aacsampleapp.model.repository.RamenRepository
import com.gumio_inf.aacsampleapp.model.vo.Ramen

class RamenDetailViewModel(application: Application) : AndroidViewModel(application) {

    /** Data source */
    private val repository = RamenRepository.getInstance(application)

    private val ramenId = MutableLiveData<Long>()

    val ramen: LiveData<Ramen?> = Transformations.switchMap(ramenId) { ramenId ->
        repository.findRamenById(ramenId)
    }

    fun setRamenId(ramenId: Long) {
        this.ramenId.value = ramenId
    }

    fun toggleFavorite() {
        this.ramen.value?.let {
            repository.updateRamen(Ramen(it.id, it.name, !it.favorite))
        }
    }

}
