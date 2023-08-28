package com.example.finance.domain.model

typealias RespostaDomain = Resposta

data class Resposta(
    val id: Int = 0,
    val descricao: String,
    val correta: Boolean,
    val idPergunta: Int
)