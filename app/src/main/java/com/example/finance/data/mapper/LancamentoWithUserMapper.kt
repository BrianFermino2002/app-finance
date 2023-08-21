package com.example.finance.data.mapper

import com.example.finance.data.entity.UserWithLancamentoEntity
import com.example.finance.domain.model.LancamentoDomain
import com.example.finance.domain.model.UserWithLancamentoDomain
import kotlinx.coroutines.flow.map

fun UserWithLancamentoEntity.toDomain() = UserWithLancamentoDomain(
    user = user.toDomain(),
    lancamento = lancamento.map{
        LancamentoDomain(
        id =it.id,
        tipoMov = it.tipoMov,
        valor = it.valor,
        idUsuario = it.idUsuario,
        dataEfet = it.dataEfet,
        categoria = it.categoria
)
    }

)