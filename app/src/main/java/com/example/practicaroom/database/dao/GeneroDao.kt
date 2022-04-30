package com.example.practicaroom.database.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.practicaroom.database.entities.GeneroEntity

@Dao
interface GeneroDao {

    @Insert (onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(genero: GeneroEntity)

    @Query("SELECT * FROM TblGenero")
    suspend fun getAll(): List<GeneroEntity>

    @Query("SELECT * FROM TblGenero")
    fun getAllRealData(): LiveData<List<GeneroEntity>>

    @Update
    fun update(genero: GeneroEntity)

    @Delete
    fun delete(genero: GeneroEntity)

    @Query("DELETE FROM TblGenero")
    suspend fun deleteAll()
}