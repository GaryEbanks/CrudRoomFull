package com.example.practicaroom.database.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.practicaroom.database.entities.ClasificacionEntity

@Dao
interface ClasificacionDao {

        @Insert (onConflict = OnConflictStrategy.REPLACE)
        suspend fun insert(clasificacion: ClasificacionEntity)

        @Query("SELECT * FROM TblClasificacion")
        suspend fun getAll(): List<ClasificacionEntity>

        @Query("SELECT * FROM TblClasificacion")
        fun getAllRealData(): LiveData<List<ClasificacionEntity>>

        @Update
        fun update(clasificacion: ClasificacionEntity)

        @Delete
        fun delete(clasificacion: ClasificacionEntity)

        @Query("DELETE FROM TblClasificacion")
        suspend fun deleteAll()
}
