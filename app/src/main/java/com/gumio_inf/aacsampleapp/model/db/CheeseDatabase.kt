package com.gumio_inf.aacsampleapp.model.db

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import com.gumio_inf.aacsampleapp.model.dao.CheeseDao
import com.gumio_inf.aacsampleapp.model.vo.Cheese

@Database(entities = [Cheese::class], version = 1)
abstract class CheeseDatabase : RoomDatabase() {
    abstract fun cheese(): CheeseDao
}