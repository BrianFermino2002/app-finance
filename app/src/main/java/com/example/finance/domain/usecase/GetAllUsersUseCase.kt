package com.example.finance.domain.usecase

import com.example.finance.domain.repository.UserRepository

class GetAllUsersUseCase constructor(
    private val repository: UserRepository
) {
    suspend operator fun invoke(nome: String) = repository.getUser(nome)
}