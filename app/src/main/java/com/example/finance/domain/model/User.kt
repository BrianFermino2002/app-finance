package com.example.finance.domain.model

import com.example.finance.data.Nivel

typealias UserDomain = User

data class User (
        val id: Int = 0,
        val name: String,
        val salario: Double,
        val nivel: Nivel
        )