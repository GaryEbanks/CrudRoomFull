package com.example.practicaroom.database.repository

import androidx.lifecycle.LiveData
import com.example.practicaroom.database.dao.ClasificacionDao
import com.example.practicaroom.database.entities.ClasificacionEntity

class ClasificacionRepository(private val dao: ClasificacionDao) {
    val listado : LiveData<List<ClasificacionEntity>> =
        dao.getAllRealData()

    suspend fun addClasificacion(clasificacion: ClasificacionEntity){
        dao.insert(clasificacion)
    }

    suspend fun updateClasificacion(clasificacion: ClasificacionEntity){
        dao.update(clasificacion)
    }

    suspend fun deleteClasificacion(clasificacion: ClasificacionEntity){
        dao.delete(clasificacion)
    }

    suspend fun deletaAll(){
        dao.deleteAll()
    }

}