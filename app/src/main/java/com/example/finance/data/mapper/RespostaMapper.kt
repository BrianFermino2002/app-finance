package com.example.finance.data.mapper

import com.example.finance.data.entity.RespostaEntity
import com.example.finance.domain.model.RespostaDomain

fun RespostaDomain.toEntity() = RespostaEntity(
    id = id,
    descricao = descricao,
    correta = correta,
    idPergunta = idPergunta
)

fun RespostaEntity.toDomain() = RespostaDomain(
    id = id,
    descricao = descricao,
    correta = correta,
    idPergunta = idPergunta
)