package com.gumio_inf.aacsampleapp.repository

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.Room
import android.content.Context
import com.gumio_inf.aacsampleapp.api.CheeseApi
import com.gumio_inf.aacsampleapp.db.CheeseDatabase
import com.gumio_inf.aacsampleapp.vo.Cheese
import java.util.concurrent.Executor
import java.util.concurrent.Executors

class CheeseRepository(
        private val database: CheeseDatabase,
        private val api: CheeseApi,
        private val executor: Executor) {

    companion object {

        @Volatile
        private var instance: CheeseRepository? = null

        fun getInstance(context: Context): CheeseRepository =
                instance ?: synchronized(this) {
                    instance ?: CheeseRepository(
                            Room.databaseBuilder(context, CheeseDatabase::class.java, "cheese.db")
                                    .build(),
                            CheeseApi(),
                            Executors.newSingleThreadExecutor()).also { instance = it }
                }

    }

    fun loadAllCheeses(): LiveData<List<Cheese>> {
        syncIfNecessary()
        return database.cheese().all()
    }

    fun findCheeseById(id: Long): LiveData<Cheese?> {
        return database.cheese().byId(id)
    }

    fun updateCheese(cheese: Cheese) {
        executor.execute {
            database.cheese().update(cheese)
        }
    }

    private fun syncIfNecessary() {
        executor.execute {
            if (database.cheese().count() == 0) {
                database.cheese().insertAll(api.fetchCheeses())
            }
        }
    }

}

