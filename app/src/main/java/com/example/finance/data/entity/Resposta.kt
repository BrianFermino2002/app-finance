package com.example.finance.data.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

typealias RespostaEntity = Resposta
@Entity
data class Resposta(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    @ColumnInfo(name = "descricao") val descricao: String,
    @ColumnInfo(name = "correta") val correta: Boolean,
    @ColumnInfo(name = "idPergunta") val idPergunta: Int
)