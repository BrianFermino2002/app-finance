package com.example.finance.data.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.finance.data.Nivel

typealias UserEntity = User

@Entity
data class User (
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "salario") val salario: Double,
    @ColumnInfo(name = "idade") val idade: Int,
    @ColumnInfo(name = "nivel") val nivel: Nivel
        )