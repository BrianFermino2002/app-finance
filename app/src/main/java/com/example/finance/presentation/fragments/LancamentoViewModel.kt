package com.example.finance.presentation.fragments

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.CreationExtras
import com.example.finance.data.db
import com.example.finance.data.repository.UserRepositoryImpl
import com.example.finance.domain.model.LancamentoDomain
import com.example.finance.domain.usecase.DeleteLancamentoUseCase
import com.example.finance.domain.usecase.GetLancamentoWithUserUseCase
import com.example.finance.domain.usecase.InsertLancamentoUseCase
import com.example.finance.domain.usecase.UpdateLancamentoUseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch

class LancamentoViewModel(
    private val insertLancamentoUseCase: InsertLancamentoUseCase,
    private val getLancamentoWithUserUseCase: GetLancamentoWithUserUseCase,
    private val updateLancamentoUseCase: UpdateLancamentoUseCase,
    private val deleteLancamentoUseCase: DeleteLancamentoUseCase
): ViewModel() {

    private val _lancamentos = MutableStateFlow<List<LancamentoDomain>>(emptyList())
    val lancamentos: StateFlow<List<LancamentoDomain>> get() = _lancamentos

    private val _somaCredito = MutableStateFlow(0.0)
    val somaCredito: StateFlow<Double> get() = _somaCredito

    private val _somaDebito = MutableStateFlow(0.0)
    val somaDebito: StateFlow<Double> get() = _somaDebito

    fun getLancamentos(idUsuario: Int): Flow<LancamentoState> = flow {
        emit(LancamentoState.Loading)
        try {
            val allLancamentos = getLancamentoWithUserUseCase(idUsuario)
            emit(LancamentoState.Success(lancamento = allLancamentos.lancamento))
        } catch (exception: Exception) {
            Log.e("Error", exception.message.toString())
            emit(LancamentoState.Error(exception.message.toString()))
        }
        soma(idUsuario)
    }

     fun insertLancamento(
        tipomov: String,
        valor: Double,
        idUsuario: Int,
        dataEfet: String
    ) = viewModelScope.launch {
        insertLancamentoUseCase(
            LancamentoDomain(
                tipoMov = tipomov,
                valor = valor,
                idUsuario = idUsuario,
                dataEfet =  dataEfet
            )
        )
// Obtenha a nova lista de lançamentos após a inserção
         val newLancamentos = getLancamentoWithUserUseCase(idUsuario).lancamento
         // Atualize o valor do MutableStateFlow com a nova lista de lançamentos
         _lancamentos.value = newLancamentos

         soma(idUsuario)
    }


    fun updateLancamento(
        tipomov: String,
        valor: Double,
        idUsuario: Int,
        dataEfet: String,
        id: Int) = viewModelScope.launch {
       updateLancamentoUseCase(
           LancamentoDomain(
           tipoMov = tipomov,
           valor = valor,
           idUsuario = idUsuario,
           dataEfet = dataEfet,
           id = id
          )
       )

        // Obtenha a nova lista de lançamentos após a inserção
        val newLancamentos = getLancamentoWithUserUseCase(idUsuario).lancamento
        // Atualize o valor do MutableStateFlow com a nova lista de lançamentos
        _lancamentos.value = newLancamentos

        soma(idUsuario)
    }

    fun deleteLancamento(lancamento: LancamentoDomain) = viewModelScope.launch {
        deleteLancamentoUseCase(lancamento)

        // Obtenha a nova lista de lançamentos após a inserção
        val newLancamentos = getLancamentoWithUserUseCase(lancamento.idUsuario).lancamento
        // Atualize o valor do MutableStateFlow com a nova lista de lançamentos
        _lancamentos.value = newLancamentos

        soma(lancamento.idUsuario)
    }

    fun soma(id: Int) = viewModelScope.launch {
        var somaCred = 0.0
        var somaDeb = 0.0
        val lancs = getLancamentoWithUserUseCase(id).lancamento


        for (lan in lancs){
            if(lan.tipoMov.compareTo("Credito") == 0){
                somaCred += lan.valor
                _somaCredito.value = somaCred
            }else {
                somaDeb += lan.valor
                _somaDebito.value = somaDeb
            }
        }
        if(somaCred == 0.0){
            _somaCredito.value = 0.0
        }else if(somaDeb ==0.0){
            _somaDebito.value = 0.0
        }
    }


    class Factory : ViewModelProvider.Factory {
        @Suppress("UNCHECKED_CAST")
        override fun <T : ViewModel> create(
            modelClass: Class<T>,
            extras: CreationExtras
        ): T {
            val application = checkNotNull(extras[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY])
            val repository = UserRepositoryImpl(application.db.userDAO())
            return LancamentoViewModel(
                insertLancamentoUseCase = InsertLancamentoUseCase(
                    repository
                ),
                getLancamentoWithUserUseCase = GetLancamentoWithUserUseCase(repository),
                updateLancamentoUseCase = UpdateLancamentoUseCase(repository),
                deleteLancamentoUseCase = DeleteLancamentoUseCase(repository)
            ) as T
        }
    }


}