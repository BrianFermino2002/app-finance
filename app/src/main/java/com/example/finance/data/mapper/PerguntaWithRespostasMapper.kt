package com.example.finance.data.mapper

import com.example.finance.data.entity.PerguntaWithRespostasEntity
import com.example.finance.domain.model.PerguntaWithRespostasDomain

fun PerguntaWithRespostasEntity.toDomain() = PerguntaWithRespostasDomain(
    pergunta = pergunta.toDomain(),
    respostas = resposta.map{
        it.toDomain()
    }
)