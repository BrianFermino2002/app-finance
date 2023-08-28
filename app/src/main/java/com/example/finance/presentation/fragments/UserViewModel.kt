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
import kotlinx.coroutines.launch

class UserViewModel(

    private val getAllUsersUseCase: GetAllUsersUseCase,
    private val insertUserUseCase: InsertUserUseCase
): ViewModel() {

    fun getAllUser(nome: String): LiveData<UserState> = liveData {
        emit(UserState.Loading)
        val state = try {
            val user = getAllUsersUseCase(nome)
            UserState.Success(
                user = user
            )
        } catch (exception: Exception) {
            Log.e("Error", exception.message.toString())
            UserState.Error(exception.message.toString())
        }
        emit(state)
    }


    fun insert(name: String, salario: Double) = viewModelScope.launch {
        insertUserUseCase(UserDomain(name = name, salario = salario, nivel = Nivel.INICIANTE))
    }

    class Factory: ViewModelProvider.Factory{
        override fun <T : ViewModel> create(modelClass: Class<T>,
            extras: CreationExtras
                                            ): T {
            val application = checkNotNull(extras[APPLICATION_KEY])
            val repository = UserRepositoryImpl(application.db.userDAO())
            return UserViewModel(
                getAllUsersUseCase = GetAllUsersUseCase(repository),
                insertUserUseCase = InsertUserUseCase(repository)
            ) as T
        }
    }
}