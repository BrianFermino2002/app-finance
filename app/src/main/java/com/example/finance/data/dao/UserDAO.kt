package com.example.finance.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Update
import com.example.finance.data.entity.LancamentoEntity
import com.example.finance.data.entity.UserEntity
import com.example.finance.data.entity.UserWithLancamentoEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface UserDAO {
    @Query("SELECT * FROM user WHERE name = :user")
    fun getUser(user: String): Flow<UserEntity>

    @Insert
    fun insert(user:UserEntity)

    @Insert
    fun insert(lancamento: LancamentoEntity)

    @Update
    fun update(user: UserEntity)
    @Update
    fun update(lancamento: LancamentoEntity)

    @Delete
    fun delete(lancamento: LancamentoEntity)

    @Transaction
    @Query("SELECT * FROM user WHERE id = :userId")
    fun getLancamentoWithUser(userId: Int): UserWithLancamentoEntity

}