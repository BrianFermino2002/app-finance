package com.example.finance.presentation.adapters


import com.example.finance.domain.model.LancamentoDomain

class LancamentoListener(
        val apagarListener: (lancamento: LancamentoDomain) -> Unit,
    ) {


        fun onDelete(lancamento: LancamentoDomain)  {
            apagarListener(lancamento)
        }
    }
