package com.example.practicaroom.database.entities

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "TblGenero")
data class GeneroEntity(
    @PrimaryKey(autoGenerate = true)
    val idGenero: Int,

    @ColumnInfo(name = "nombre")
    val nombre: String,

    @ColumnInfo(name = "activo")
    val activo: Boolean
):Parcelable
