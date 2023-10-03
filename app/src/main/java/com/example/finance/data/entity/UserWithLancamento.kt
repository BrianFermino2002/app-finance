package com.example.finance.data.entity

import androidx.room.Embedded
import androidx.room.Relation

typealias UserWithLancamentoEntity = UserWithLancamento

data class UserWithLancamento (
    @Embedded val user: UserEntity,
    @Relation(
        parentColumn = "id",
        entityColumn = "idUser",
    )
    val lancamento: List<LancamentoEntity>
    )

