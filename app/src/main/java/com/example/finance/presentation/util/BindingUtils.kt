package com.example.finance.presentation.util

import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.example.finance.R
import com.example.finance.domain.model.LancamentoDomain
import java.text.NumberFormat
import java.util.Locale

@BindingAdapter("iv_tipo")
fun ImageView.setIcone(item: LancamentoDomain?) {
    setImageResource(
        when(item?.tipoMov){
            "Credito" -> R.drawable.credito_icon
            else -> R.drawable.debito_icon
        }
    )
}

@BindingAdapter("tv_value")
fun TextView.setValor(item: LancamentoDomain?){
    val currencyFormat = NumberFormat.getCurrencyInstance(Locale.getDefault())
    val formatado = currencyFormat.format(item?.valor)
    setText(formatado)
}