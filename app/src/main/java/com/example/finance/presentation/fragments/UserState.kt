package com.example.finance.presentation.fragments

import com.example.finance.domain.model.UserDomain

sealed interface UserState{
    object Loading: UserState
    object Empty: UserState
    data class Success(val user: UserDomain) : UserState
    data class Error(val message: String): UserState
}