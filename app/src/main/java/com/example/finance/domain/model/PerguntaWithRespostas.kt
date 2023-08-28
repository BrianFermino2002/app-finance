package com.example.finance.domain.model

typealias PerguntaWithRespostasDomain = PerguntaWithRespostas
data class PerguntaWithRespostas (
    val pergunta: PerguntaDomain,
    val respostas: List<RespostaDomain>
)