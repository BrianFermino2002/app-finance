package com.example.finance.data.mapper

import com.example.finance.data.entity.UserEntity
import com.example.finance.domain.model.UserDomain

    fun UserDomain.toEntity() = UserEntity(
        id = id,
        name = name,
        salario = salario,
        nivel = nivel,
        idade = idade
    )

    fun UserEntity.toDomain() = UserDomain(
        id = id,
        name = name,
        salario = salario,
        nivel = nivel,
        idade = idade
    )