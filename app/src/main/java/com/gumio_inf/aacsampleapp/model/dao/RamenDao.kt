package com.gumio_inf.aacsampleapp.model.dao

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.*
import com.gumio_inf.aacsampleapp.model.vo.Ramen

@Dao
interface RamenDao {
    @Query("SELECT * FROM Ramen")
    fun all(): LiveData<List<Ramen>>

    @Query("SELECT * FROM Ramen WHERE id = :id")
    fun byId(id: Long): LiveData<Ramen?>

    @Query("SELECT COUNT(*) FROM Ramen")
    fun count(): Int

    @Insert
    fun insertAll(ramens: List<Ramen>)

    @Delete
    fun delete(ramen: Ramen)

    @Update
    fun update(ramen: Ramen)
}