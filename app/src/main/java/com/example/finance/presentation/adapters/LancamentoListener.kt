package com.example.finance.presentation.adapters


import com.example.finance.domain.model.LancamentoDomain

class LancamentoListener(
        val clickListener: (lancamento: LancamentoDomain) -> Unit,
    ) {


        fun onClick(lancamento: LancamentoDomain)  {
            clickListener(lancamento)
        }
    }
