package com.example.finance.data.entity

import androidx.room.Embedded
import androidx.room.Relation

typealias PerguntaWithRespostasEntity = PerguntaWithRespostas

data class PerguntaWithRespostas (
    @Embedded val pergunta: PerguntaEntity,
    @Relation(
        parentColumn = "id",
        entityColumn = "idPergunta"
    )
    val resposta: List<RespostaEntity>
)