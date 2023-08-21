package com.example.finance.domain.model

import com.example.finance.data.CategoriaLancamentoEntity

typealias LancamentoDomain = Lancamento


class Lancamento (
    val id: Int = 0,
    val tipoMov: String,
    val valor: Double,
    val idUsuario: Int,
    val dataEfet: String,
    val categoria: CategoriaLancamentoEntity
        )