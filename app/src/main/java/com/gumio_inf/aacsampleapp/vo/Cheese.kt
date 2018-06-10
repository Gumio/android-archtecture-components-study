package com.gumio_inf.aacsampleapp.vo

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

@Entity
data class Cheese(
        @PrimaryKey
        val id: Long,
        val name: String,
        val favorite: Boolean)