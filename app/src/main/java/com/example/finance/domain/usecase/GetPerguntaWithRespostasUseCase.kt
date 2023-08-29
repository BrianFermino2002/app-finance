package com.example.finance.domain.usecase

import com.example.finance.domain.repository.UserRepository

class GetPerguntaWithRespostasUseCase constructor(
    private val repository: UserRepository
) {
    suspend operator fun invoke(idPergunta: Int) =
        repository.getPerguntaWithRespostas(idPergunta)
}