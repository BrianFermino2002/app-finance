package com.example.finance.domain.usecase

import com.example.finance.domain.model.LancamentoDomain
import com.example.finance.domain.repository.UserRepository

class UpdateLancamentoUseCase constructor(
    private val repository: UserRepository
) {
    suspend operator fun invoke(lancamento: LancamentoDomain) =
        repository.updateLancamento(lancamento)
}