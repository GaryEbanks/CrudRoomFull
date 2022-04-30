package com.example.practicaroom.database.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.practicaroom.database.dao.MainBaseDatos
import com.example.practicaroom.database.entities.GeneroEntity
import com.example.practicaroom.database.repository.GeneroRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class GeneroViewModels(application: Application): AndroidViewModel(application) {
    val lista : LiveData<List<GeneroEntity>>
    private val repository: GeneroRepository

    init {
        val generoDao =
            MainBaseDatos.getDataBase(application).generoDao()
        repository = GeneroRepository(generoDao)
        lista = repository.listado
    }

        fun agregarGenero(genero: GeneroEntity){
            viewModelScope.launch(Dispatchers.IO){
                repository.addGenero(genero)
            }
        }

        fun actualizarGenero(genero: GeneroEntity){
            viewModelScope.launch(Dispatchers.IO){
                repository.updateGenero(genero)
            }
        }

        fun eliminarGenero(genero: GeneroEntity){
            viewModelScope.launch(Dispatchers.IO){
                repository.deleteGenero(genero)
            }
        }

        fun eliminarTodo(){
            viewModelScope.launch(Dispatchers.IO){
                repository.deleteAll()
            }
        }
}