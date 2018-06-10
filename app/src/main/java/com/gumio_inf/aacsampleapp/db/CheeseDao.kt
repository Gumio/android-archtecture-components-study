package com.gumio_inf.aacsampleapp.db

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.*
import com.gumio_inf.aacsampleapp.vo.Cheese

@Dao
interface CheeseDao {

    @Query("SELECT * FROM Cheese")
    fun all(): LiveData<List<Cheese>>

    @Query("SELECT * FROM Cheese WHERE id = :id")
    fun byId(id: Long): LiveData<Cheese?>

    @Query("SELECT COUNT(*) FROM Cheese")
    fun count(): Int

    @Insert
    fun insertAll(cheeses: List<Cheese>)

    @Delete
    fun delete(cheese: Cheese)

    @Update
    fun update(cheese: Cheese)

}