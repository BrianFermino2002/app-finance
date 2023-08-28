package com.example.finance.data.mapper

import com.example.finance.data.entity.UserWithLancamentoEntity
import com.example.finance.domain.model.LancamentoDomain
import com.example.finance.domain.model.UserWithLancamentoDomain
import kotlinx.coroutines.flow.map

fun UserWithLancamentoEntity.toDomain() = UserWithLancamentoDomain(
    user = user.toDomain(),
    lancamento = lancamento.map{
        it.toDomain()
    }

)