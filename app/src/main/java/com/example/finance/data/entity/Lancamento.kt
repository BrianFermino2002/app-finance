package com.example.finance.data.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.LocalDateTime

typealias LancamentoEntity  = Lancamento

@Entity
data class Lancamento(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    @ColumnInfo (name = "tipoMov")val tipoMov: String,
    @ColumnInfo(name = "valor") val valor: Double,
    @ColumnInfo(name = "idUser") val idUsuario: Int,
    @ColumnInfo(name = "dataEfet") val dataEfet: String,
    @ColumnInfo(name = "categoriaLancamento") val categoria: String,
    @ColumnInfo(name= "datainserido") val data: String = LocalDateTime.now().toString()
)