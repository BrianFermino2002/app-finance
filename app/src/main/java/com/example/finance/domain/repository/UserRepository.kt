package com.example.finance.domain.repository

import com.example.finance.domain.model.LancamentoDomain
import com.example.finance.domain.model.PerguntaDomain
import com.example.finance.domain.model.PerguntaWithRespostasDomain
import com.example.finance.domain.model.RespostaDomain
import com.example.finance.domain.model.UserDomain
import com.example.finance.domain.model.UserWithLancamentoDomain
import kotlinx.coroutines.flow.Flow

interface UserRepository {
    suspend fun getUser(nome: String): UserDomain
    suspend fun insert(user: UserDomain)
    suspend fun insertLancamento(lancamento: LancamentoDomain)
    suspend fun insertPergunta(pergunta: PerguntaDomain)
    suspend fun insertResposta(resposta: RespostaDomain)
    suspend fun updateLancamento(lancamento: LancamentoDomain)
    suspend fun deleteLancamento(lancamento: LancamentoDomain)
    suspend fun getLancamentoWithUser(userId: Int): UserWithLancamentoDomain
    suspend fun getPerguntaWithRespostas(perguntaId: Int): PerguntaWithRespostasDomain
}