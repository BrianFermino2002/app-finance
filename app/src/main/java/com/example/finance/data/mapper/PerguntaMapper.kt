package com.example.finance.data.mapper

import com.example.finance.data.entity.PerguntaEntity
import com.example.finance.domain.model.PerguntaDomain

fun PerguntaDomain.toEntity() = PerguntaEntity(
    id = id,
    enunciado = enunciado,
    nivel = nivel
)

fun PerguntaEntity.toDomain() = PerguntaDomain(
    id = id,
    enunciado = enunciado,
    nivel = nivel
)