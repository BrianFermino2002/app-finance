package com.example.finance.presentation

import android.content.Context
import com.example.finance.R

class ThemeManager {
    companion object {
        fun setCustomizedThemes(context: Context, theme: String?) {
            when (theme) {
                "nada" -> context.setTheme(R.style.Base_Theme_Finance)
                "rosa" -> context.setTheme(R.style.TemaRosa)
                "azul" -> context.setTheme(R.style.TemaAzul)
                "verde" -> context.setTheme(R.style.TemaVerde)
                "laranja" -> context.setTheme(R.style.TemaLaranja)
            }
        }
    }
}