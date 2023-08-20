package com.example.finance.extension

import androidx.lifecycle.LiveData
import androidx.lifecycle.map
import com.example.finance.domain.model.LancamentoDomain
import com.example.finance.presentation.adapters.DataItem
import java.time.LocalDate
import java.time.Month
import java.time.format.DateTimeFormatter
import java.time.format.TextStyle
import java.util.Locale

fun LiveData<List<LancamentoDomain>>.sortedByDate(): LiveData<List<LancamentoDomain>> =
        this.map { list ->
            list.sortedBy { lanc ->
                lanc.dataEfet }
        }

fun List<LancamentoDomain>.toListOfDataItem(): List<DataItem> {

    val grouping = this.groupBy { lancamento ->
        val date = LocalDate.parse(lancamento.dataEfet, DateTimeFormatter.ofPattern("dd/MM/yyyy"))
        date.monthValue to date.year // Agrupar pelo mês e pelo ano
    }

    val listDataItem = mutableListOf<DataItem>()
    grouping.forEach { mapEntry ->
        val (month, year) = mapEntry.key
        val monthName = Month.of(month).getDisplayName(TextStyle.FULL, Locale.getDefault())
        listDataItem.add(DataItem.Header("$monthName $year", month, year))
        listDataItem.addAll(
            mapEntry.value.map { lanc ->
                DataItem.LancamentoItem(lanc)
            }
        )
    }

    return listDataItem
}
