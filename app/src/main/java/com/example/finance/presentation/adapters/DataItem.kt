package com.example.finance.presentation.adapters

import java.time.Month
import com.example.finance.domain.model.LancamentoDomain

sealed class DataItem {

    abstract val id: Int


    data class LancamentoItem(val lancamento: LancamentoDomain,
                                override val id: Int = lancamento.id) : DataItem()

    data class Header(val key: String, val month: Int, val year: Int) : DataItem() {
        override val id: Int = year * 100 + month
    }
}