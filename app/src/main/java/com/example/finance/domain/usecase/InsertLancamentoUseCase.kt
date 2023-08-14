package com.example.finance.domain.usecase

import com.example.finance.domain.model.LancamentoDomain
import com.example.finance.domain.repository.UserRepository

class InsertLancamentoUseCase constructor(
    private val repository:UserRepository
) {
    suspend operator fun invoke(lancamentoDomain: LancamentoDomain) =
    repository.insertLancamento(lancamentoDomain)
}