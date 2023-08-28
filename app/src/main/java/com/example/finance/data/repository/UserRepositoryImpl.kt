package com.example.finance.data.repository

import com.example.finance.data.dao.UserDAO
import com.example.finance.data.entity.Lancamento
import com.example.finance.data.mapper.toDomain
import com.example.finance.data.mapper.toEntity
import com.example.finance.domain.model.LancamentoDomain
import com.example.finance.domain.model.PerguntaDomain
import com.example.finance.domain.model.PerguntaWithRespostasDomain
import com.example.finance.domain.model.RespostaDomain
import com.example.finance.domain.model.UserDomain
import com.example.finance.domain.model.UserWithLancamentoDomain
import com.example.finance.domain.repository.UserRepository

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext

class UserRepositoryImpl(private val dao: UserDAO): UserRepository {

    override suspend fun getUser(nome: String): UserDomain  = withContext(Dispatchers.IO){
        dao.getUser(nome).toDomain()
    }

    override suspend fun insert(user: UserDomain) = withContext(Dispatchers.IO){
        dao.insert(user.toEntity())
    }

    override suspend fun insertLancamento(lancamento: LancamentoDomain) =
        withContext(Dispatchers.IO) {
            dao.insert(
                Lancamento(
                    id = lancamento.id,
                    tipoMov = lancamento.tipoMov,
                    valor = lancamento.valor,
                    idUsuario = lancamento.idUsuario,
                    dataEfet = lancamento.dataEfet,
                    categoria = lancamento.categoria
                )
            )
        }

    override suspend fun insertPergunta(pergunta: PerguntaDomain) = withContext(Dispatchers.IO){
        dao.insert(pergunta.toEntity())
    }

    override suspend fun insertResposta(resposta: RespostaDomain) = withContext(Dispatchers.IO){
        dao.insert(resposta.toEntity())
    }

    override suspend fun updateLancamento(lancamento: LancamentoDomain) = withContext(Dispatchers.IO){
        dao.update(lancamento.toEntity())
    }

    override suspend fun deleteLancamento(lancamento: LancamentoDomain) = withContext(Dispatchers.IO){
        dao.delete(lancamento.toEntity())
    }

    override suspend fun getLancamentoWithUser(userId: Int) : UserWithLancamentoDomain =
        withContext(Dispatchers.IO){
            dao.getLancamentoWithUser(userId).toDomain()
        }

    override suspend fun getPerguntaWithRespostas(perguntaId: Int): PerguntaWithRespostasDomain =
        withContext(Dispatchers.IO){
        dao.getPerguntaWithResposta(perguntaId).toDomain()
    }
}