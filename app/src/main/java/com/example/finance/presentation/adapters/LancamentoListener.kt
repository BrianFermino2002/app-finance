package com.example.finance.presentation.adapters


import com.example.finance.domain.model.LancamentoDomain

class LancamentoListener(
        val editListener: (lancamento: LancamentoDomain) -> Unit,
        val apagarListener: (lancamento: LancamentoDomain) -> Unit,
    ) {

        fun onEdit(lancamento: LancamentoDomain) {
            editListener(lancamento)
        }

        fun onDelete(lancamento: LancamentoDomain)  {
            apagarListener(lancamento)
        }
    }
