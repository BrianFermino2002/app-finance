package com.example.finance.domain.usecase

import com.example.finance.domain.model.PerguntaDomain
import com.example.finance.domain.repository.UserRepository

class InsertPerguntaUseCase constructor(
    private val repository: UserRepository
) {
    suspend operator fun invoke(perguntaDomain: PerguntaDomain) =
        repository.insertPergunta(perguntaDomain)
}