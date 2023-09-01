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
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch

class PerguntasViewModel (
    private val insertPerguntaUseCase: InsertPerguntaUseCase,
    private val insertRespostaUseCase: InsertRespostaUseCase,
    private val getPerguntasUseCase: GetPerguntasUseCase,
    private val getPerguntaWithRespostasUseCase: GetPerguntaWithRespostasUseCase
): ViewModel() {

    private val _state = MutableSharedFlow<PerguntaState>()
    val state: SharedFlow<PerguntaState> = _state

    init {
        getAllPerguntas()
    }
    private fun getAllPerguntas() = viewModelScope.launch {
        getPerguntasUseCase().flowOn(Dispatchers.Main)
            .onStart {
            _state.emit(PerguntaState.Loading)
        }.catch {
            _state.emit(PerguntaState.Error("Erro"))
        }.collect{perguntas ->
            _state.emit(PerguntaState.Success(perguntas))
        }
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