package com.example.finance.domain.usecase

import com.example.finance.domain.repository.UserRepository

class GetPerguntasUseCase constructor(
    private val repository: UserRepository
) {
    suspend operator fun invoke() = repository.getPerguntas()
}