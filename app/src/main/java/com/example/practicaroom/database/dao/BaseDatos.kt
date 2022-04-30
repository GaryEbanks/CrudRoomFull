package com.example.practicaroom.database.dao

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.practicaroom.database.entities.ClasificacionEntity
import com.example.practicaroom.database.entities.GeneroEntity

interface MainDataBaseProvider{
    fun clasificacionDao(): ClasificacionDao
    fun generoDao(): GeneroDao
}

@Database (entities = [ClasificacionEntity::class, GeneroEntity::class],
    version = 1
)

abstract class MainBaseDatos: RoomDatabase(), MainDataBaseProvider {
    abstract override fun clasificacionDao(): ClasificacionDao
    abstract override fun generoDao(): GeneroDao
    companion object{
        @Volatile
        private var INSTANCE: MainBaseDatos? = null

        fun getDataBase(context: Context): MainBaseDatos {
            synchronized(this){
                var instance = INSTANCE
                if (instance == null){
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        MainBaseDatos::class.java,
                        "peliculas_main_db"
                    ).fallbackToDestructiveMigration()
                        .build()
                    INSTANCE = instance
                }
                return instance
            }
        }
    }
}