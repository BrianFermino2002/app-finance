package com.example.finance.domain.usecase

import com.example.finance.domain.model.RespostaDomain
import com.example.finance.domain.repository.UserRepository

class InsertRespostaUseCase constructor(
    private val repository: UserRepository
) {
    suspend operator fun invoke(respostaDomain: RespostaDomain) =
        repository.insertResposta(respostaDomain)
}