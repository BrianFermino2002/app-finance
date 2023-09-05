package com.example.finance.presentation.fragments

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.CreationExtras
import com.example.finance.data.Nivel
import com.example.finance.data.db
import com.example.finance.data.repository.UserRepositoryImpl
import com.example.finance.domain.model.UserDomain
import com.example.finance.domain.usecase.GetAllUsersUseCase
import com.example.finance.domain.usecase.InsertUserUseCase
import com.example.finance.domain.usecase.UpdateUsuarioUseCase
import com.example.finance.presentation.HomeActivity
import com.example.finance.presentation.MainActivity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch

class UserViewModel(

    private val getAllUsersUseCase: GetAllUsersUseCase,
    private val insertUserUseCase: InsertUserUseCase,
    private val updateUserUseCase: UpdateUsuarioUseCase

): ViewModel() {
    private val _state = MutableSharedFlow<UserState>()
    val state: SharedFlow<UserState> = _state

    init {
        getAllUser(MainActivity.EXTRA_USERNAME)
    }
    fun getAllUser(nome: String)=  viewModelScope.launch {
        if (nome == "extra_username") {
            // Se for igual, não faça nada e retorne
            return@launch
        }
        Log.d("Debug", "Nome passado para getAllUser: $nome")
        getAllUsersUseCase(nome)
            .flowOn(Dispatchers.Main)
            .onStart {
                _state.emit(UserState.Loading)
            }.catch{
                _state.emit(UserState.Error("Error"))
            }.collect{user ->
                if(user.name.isEmpty()){
                    _state.emit(UserState.Empty)
                } else{
                    _state.emit(UserState.Success(user))
                }
            }
    }


    fun updateUsuario(
        id: Int,
        nome: String,
        salario: Double,
        nivel: Nivel
    ) = viewModelScope.launch{
        updateUserUseCase(
            UserDomain(
                id = id,
                salario = salario,
                name = nome,
                nivel = nivel
            )
        )
        getAllUser(nome)
    }

    fun insert(name: String, salario: Double) = viewModelScope.launch {
        insertUserUseCase(UserDomain(name = name, salario = salario, nivel = Nivel.INICIANTE))
    }

    class Factory(): ViewModelProvider.Factory{
        override fun <T : ViewModel> create(modelClass: Class<T>,
            extras: CreationExtras
                                            ): T {
            val application = checkNotNull(extras[APPLICATION_KEY])
            val repository = UserRepositoryImpl(application.db.userDAO())
            return UserViewModel(
                getAllUsersUseCase = GetAllUsersUseCase(repository),
                insertUserUseCase = InsertUserUseCase(repository),
                updateUserUseCase = UpdateUsuarioUseCase(repository)
            ) as T
        }
    }
}