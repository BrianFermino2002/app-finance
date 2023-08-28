package com.example.finance.data.mapper

import com.example.finance.data.CategoriaLancamentoEntity
import com.example.finance.data.entity.LancamentoEntity
import com.example.finance.domain.model.LancamentoDomain

fun LancamentoDomain.toEntity() = LancamentoEntity(
    id = id,
    tipoMov = tipoMov,
    valor = valor,
    idUsuario = idUsuario,
    dataEfet = dataEfet,
    categoria = categoria
)

fun LancamentoEntity.toDomain() = LancamentoDomain(
    id = id,
    tipoMov = tipoMov,
    valor = valor,
    idUsuario = idUsuario,
    dataEfet = dataEfet,
    categoria = categoria
)