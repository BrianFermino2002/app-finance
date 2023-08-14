package com.example.finance.domain.model

import kotlinx.coroutines.flow.Flow

typealias UserWithLancamentoDomain = UserWithLancamento

data class UserWithLancamento(
    val user: UserDomain,
    val lancamento: List<LancamentoDomain>
)