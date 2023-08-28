package com.example.finance.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Update
import com.example.finance.data.entity.LancamentoEntity
import com.example.finance.data.entity.PerguntaEntity
import com.example.finance.data.entity.PerguntaWithRespostasEntity
import com.example.finance.data.entity.RespostaEntity
import com.example.finance.data.entity.UserEntity
import com.example.finance.data.entity.UserWithLancamentoEntity

@Dao
interface UserDAO {
    @Query("SELECT * FROM user WHERE name = :user")
    fun getUser(user: String): UserEntity

    @Insert
    fun insert(user:UserEntity)

    @Insert
    fun insert(lancamento: LancamentoEntity)

    @Insert
    fun insert(pergunta: PerguntaEntity)

    @Insert
    fun insert(resposta: RespostaEntity)

    @Update
    fun update(lancamento: LancamentoEntity)

    @Delete
    fun delete(lancamento: LancamentoEntity)

    @Transaction
    @Query("SELECT * FROM user WHERE id = :userId")
    fun getLancamentoWithUser(userId: Int): UserWithLancamentoEntity

    @Transaction
    @Query("SELECT * FROM resposta WHERE id = :perguntaId")
    fun getPerguntaWithResposta(perguntaId: Int): PerguntaWithRespostasEntity
}