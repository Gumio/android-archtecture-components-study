package com.gumio_inf.aacsampleapp.model.repository

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.Room
import android.content.Context
import com.gumio_inf.aacsampleapp.model.api.RamenApi
import com.gumio_inf.aacsampleapp.model.db.RamenDatabase
import com.gumio_inf.aacsampleapp.model.vo.Ramen
import java.util.concurrent.Executor
import java.util.concurrent.Executors

class RamenRepository(
    private val database: RamenDatabase,
    private val api: RamenApi,
    private val executor: Executor) {

        companion object {

            @Volatile
            private var instance: RamenRepository? = null

            fun getInstance(context: Context): RamenRepository =
                    instance ?: synchronized(this) {
                        instance
                                ?: RamenRepository(
                                        Room.databaseBuilder(
                                                context, RamenDatabase::class.java, "ramen.db"
                                        ).build(),
                                        RamenApi(),
                                        Executors.newSingleThreadExecutor()
                                ).also { instance = it }
                    }

        }

        fun loadAllRamens(): LiveData<List<Ramen>> {
            syncIfNecessary()
            return database.ramen().all()
        }

        fun findRamenById(id: Long): LiveData<Ramen?> {
            return database.ramen().byId(id)
        }

        fun updateRamen(ramen: Ramen) {
            executor.execute {
                database.ramen().update(ramen)
            }
        }

        private fun syncIfNecessary() {
            executor.execute {
                if (database.ramen().count() == 0) {
                    database.ramen().insertAll(api.fetchRamens())
                }
            }
        }
}