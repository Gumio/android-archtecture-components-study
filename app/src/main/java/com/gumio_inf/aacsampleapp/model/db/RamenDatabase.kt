package com.gumio_inf.aacsampleapp.model.db

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import com.gumio_inf.aacsampleapp.model.dao.RamenDao
import com.gumio_inf.aacsampleapp.model.vo.Ramen

@Database(entities = [Ramen::class], version = 1)
abstract class RamenDatabase: RoomDatabase() {
        abstract fun ramen(): RamenDao
}