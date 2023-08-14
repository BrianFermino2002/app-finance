package com.example.finance.domain.model
typealias UserDomain = User

data class User (
        val id: Int = 0,
        val name: String,
        val salario: Double
        )