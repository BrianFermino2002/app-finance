package com.example.finance.data.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.finance.data.Nivel

typealias PerguntaEntity = Pergunta
@Entity
data class Pergunta (
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    @ColumnInfo(name = "enunciado") val enunciado: String,
    @ColumnInfo(name = "nivel") val nivel: Nivel
)