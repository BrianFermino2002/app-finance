package com.example.finance.presentation.fragments

import com.example.finance.domain.model.LancamentoDomain

sealed interface LancamentoState{
    object Loading: LancamentoState
    data class Success(val lancamento: List<LancamentoDomain>) : LancamentoState
    data class Error(val message: String): LancamentoState
}