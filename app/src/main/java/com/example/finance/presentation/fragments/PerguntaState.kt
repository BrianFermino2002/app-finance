package com.example.finance.presentation.fragments

import com.example.finance.domain.model.PerguntaDomain

sealed interface PerguntaState {
    object Loading: PerguntaState
    data class Success(val pergunta: List<PerguntaDomain>) : PerguntaState
    data class Error(val message: String): PerguntaState
}