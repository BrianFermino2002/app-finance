package com.example.finance.domain.usecase

import com.example.finance.domain.repository.UserRepository

class GetLancamentoWithUserUseCase constructor(
    private val repository: UserRepository
) {
    suspend operator fun invoke(idUser: Int) =
        repository.getLancamentoWithUser(idUser)
}