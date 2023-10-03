package com.example.finance.domain.model

import androidx.room.ColumnInfo
import java.time.LocalDateTime

typealias LancamentoDomain = Lancamento


class Lancamento (
    val id: Int = 0,
    val tipoMov: String,
    val valor: Double,
    val idUsuario: Int,
    val dataEfet: String,
    val categoria: String,
    val data: String = LocalDateTime.now().toString()
        )