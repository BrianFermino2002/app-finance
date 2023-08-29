package com.example.finance.presentation.fragments

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.CreationExtras
import com.example.finance.data.Nivel
import com.example.finance.data.db
import com.example.finance.data.repository.UserRepositoryImpl
import com.example.finance.domain.model.PerguntaDomain
import com.example.finance.domain.model.PerguntaWithRespostasDomain
import com.example.finance.domain.model.RespostaDomain
import com.example.finance.domain.usecase.GetPerguntaWithRespostasUseCase
import com.example.finance.domain.usecase.GetPerguntasUseCase
import com.example.finance.domain.usecase.InsertPerguntaUseCase
import com.example.finance.domain.usecase.InsertRespostaUseCase
import kotlinx.coroutines.launch

class PerguntasViewModel (
    private val insertPerguntaUseCase: InsertPerguntaUseCase,
    private val insertRespostaUseCase: InsertRespostaUseCase,
    private val getPerguntasUseCase: GetPerguntasUseCase,
    private val getPerguntaWithRespostasUseCase: GetPerguntaWithRespostasUseCase
): ViewModel() {
    fun getAllPerguntas(): LiveData<PerguntaState> = liveData {
        emit(PerguntaState.Loading)
        val state = try {
            val perguntas = getPerguntasUseCase()
            PerguntaState.Success(
                pergunta = perguntas
            )
        } catch (exception: Exception) {
            Log.e("Error", exception.message.toString())
            PerguntaState.Error(exception.message.toString())
        }
        emit(state)
    }

    fun getPerguntasWithRespostas(idPergunta: Int): LiveData<PerguntaWithRespostasDomain> = liveData {
        try {
            val allRespostas = getPerguntaWithRespostasUseCase(idPergunta)
            emit(allRespostas)
        } catch (exception: Exception) {
            Log.e("Error", exception.message.toString())
        }
    }
    fun insertPergunta(enunciado: String, nivel: Nivel) = viewModelScope.launch {
        insertPerguntaUseCase(PerguntaDomain(enunciado = enunciado, nivel = nivel))
    }

    fun insertResposta(descricao: String, correta: Boolean, idPergunta: Int) = viewModelScope.launch {
        insertRespostaUseCase(RespostaDomain(descricao = descricao, correta = correta, idPergunta = idPergunta))
    }


    class Factory : ViewModelProvider.Factory {
        @Suppress("UNCHECKED_CAST")
        override fun <T : ViewModel> create(
            modelClass: Class<T>,
            extras: CreationExtras
        ): T {
            val application = checkNotNull(extras[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY])
            val repository = UserRepositoryImpl(application.db.userDAO())
            return PerguntasViewModel(
                insertPerguntaUseCase = InsertPerguntaUseCase(
                    repository
                ),
                insertRespostaUseCase = InsertRespostaUseCase(repository),
                getPerguntasUseCase = GetPerguntasUseCase(repository),
                getPerguntaWithRespostasUseCase = GetPerguntaWithRespostasUseCase(repository)
            ) as T
        }
    }
}