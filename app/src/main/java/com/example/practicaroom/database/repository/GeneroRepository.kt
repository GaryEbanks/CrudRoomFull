package com.example.practicaroom.database.repository

import androidx.lifecycle.LiveData
import com.example.practicaroom.database.dao.GeneroDao
import com.example.practicaroom.database.entities.GeneroEntity

class GeneroRepository(private val dao: GeneroDao) {
    val listado : LiveData<List<GeneroEntity>> =
        dao.getAllRealData()

    suspend fun addGenero(genero: GeneroEntity){
        dao.insert(genero)
    }

    suspend fun updateGenero(genero: GeneroEntity){
        dao.update(genero)
    }

    suspend fun deleteGenero(genero: GeneroEntity){
        dao.delete(genero)
    }

    suspend fun deleteAll(){
        dao.deleteAll()
    }
}