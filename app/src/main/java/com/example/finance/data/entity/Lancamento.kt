package com.example.finance.data.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

typealias LancamentoEntity  = Lancamento

@Entity
data class Lancamento(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    @ColumnInfo (name = "tipoMov")val tipoMov: String,
    @ColumnInfo(name = "valor") val valor: Double,
    @ColumnInfo(name = "idUser") val idUsuario: Int,
    @ColumnInfo(name = "dataEfet") val dataEfet: String
)