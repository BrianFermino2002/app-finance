package com.example.finance.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Update
import com.example.finance.data.entity.Lancamento
import com.example.finance.data.entity.UserEntity
import com.example.finance.data.entity.UserWithLancamentoEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface UserDAO {
    @Query("SELECT * FROM user WHERE name = :user")
    fun getUser(user: String): UserEntity

    @Insert
    fun insert(user:UserEntity)

    @Insert
    fun insert(lancamento: Lancamento)

    @Update
    fun update(lancamento: Lancamento)

    @Delete
    fun delete(lancamento: Lancamento)

    @Transaction
    @Query("SELECT * FROM user WHERE id = :userId")
    fun getLancamentoWithUser(userId: Int): UserWithLancamentoEntity
}