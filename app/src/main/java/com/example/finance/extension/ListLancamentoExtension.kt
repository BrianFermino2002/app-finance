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
        Triple(date.dayOfMonth, date.monthValue, date.year) // Agrupar pelo mês, dia e pelo ano
    }

    val listDataItem = mutableListOf<DataItem>()
    grouping.forEach { mapEntry ->
        val (day, month, year) = mapEntry.key
        val monthName = Month.of(month).getDisplayName(TextStyle.FULL, Locale.getDefault())
        listDataItem.add(DataItem.Header("$day de $monthName $year", day, month, year))
        listDataItem.addAll(
            mapEntry.value.map { lanc ->
                DataItem.LancamentoItem(lanc)
            }
        )
    }

    return listDataItem
}