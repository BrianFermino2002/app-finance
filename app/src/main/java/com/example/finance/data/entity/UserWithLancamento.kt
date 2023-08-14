package com.example.finance.data.entity

import androidx.room.Embedded
import androidx.room.Relation
import kotlinx.coroutines.flow.Flow

typealias UserWithLancamentoEntity = UserWithLancamento

data class UserWithLancamento (
    @Embedded val user: UserEntity,
    @Relation(
        parentColumn = "id",
        entityColumn = "idUser"
    )
    val lancamento: List<Lancamento>
    )

