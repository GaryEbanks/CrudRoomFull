package com.example.practicaroom.database.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.practicaroom.database.dao.MainBaseDatos
import com.example.practicaroom.database.entities.ClasificacionEntity
import com.example.practicaroom.database.repository.ClasificacionRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ClasificacionViewModels(application: Application): AndroidViewModel(application) {
    val lista : LiveData<List<ClasificacionEntity>>
    private val repository: ClasificacionRepository

    init {
        val clasificacionDao =
            MainBaseDatos.getDataBase(application).clasificacionDao()
        repository = ClasificacionRepository(clasificacionDao)
        lista = repository.listado
    }

    fun agregarClasificacion(clasificacion: ClasificacionEntity){
        viewModelScope.launch(Dispatchers.IO) {
            repository.addClasificacion(clasificacion)
        }
    }

    fun actualizarClasificacion(clasificacion: ClasificacionEntity){
        viewModelScope.launch(Dispatchers.IO){
            repository.updateClasificacion(clasificacion)
        }
    }

    fun eliminarClasificacion(clasificacion: ClasificacionEntity){
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteClasificacion(clasificacion)
        }
    }

    fun eliminarTodo(){
        viewModelScope.launch(Dispatchers.IO){
            repository.deletaAll()
        }
    }
}