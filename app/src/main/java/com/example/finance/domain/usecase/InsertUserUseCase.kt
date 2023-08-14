package com.example.finance.domain.usecase

import com.example.finance.domain.model.UserDomain
import com.example.finance.domain.repository.UserRepository

class InsertUserUseCase constructor(
    private val repository: UserRepository
){
    suspend operator fun invoke(user:UserDomain) = repository.insert(user)
}