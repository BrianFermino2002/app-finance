package com.example.finance.domain.model


import com.example.finance.data.Nivel

typealias PerguntaDomain = Pergunta
data class Pergunta (
    val id: Int = 0,
    val enunciado: String,
    val nivel: Nivel
)