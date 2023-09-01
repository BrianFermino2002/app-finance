package com.example.finance.presentation

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.core.view.isVisible
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.example.finance.R
import com.example.finance.data.Nivel
import com.example.finance.databinding.ActivityMainBinding
import com.example.finance.databinding.ActivityPerguntasBinding
import com.example.finance.presentation.fragments.PerguntaState
import com.example.finance.presentation.fragments.PerguntasViewModel
import com.example.finance.presentation.fragments.UserState
import com.example.finance.presentation.fragments.UserViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class PerguntasActivity : AppCompatActivity() {
    private val binding by lazy{ ActivityPerguntasBinding.inflate(layoutInflater)}
    private val viewModel: PerguntasViewModel by viewModels {
        PerguntasViewModel.Factory()
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ThemeManager.setCustomizedThemes(
            this,
            ThemeStorage.getThemeColor(this)
        )
        observeStates()
        setContentView(binding.root)
    }

    private fun observeStates(){

        viewModel.state.observe(this){
            when(it){
                is PerguntaState.Error -> viewModel.insertPergunta("a", Nivel.INICIANTE)
                PerguntaState.Loading -> TODO()
                is PerguntaState.Success -> binding.tvTest.setText(it.pergunta.get(0).enunciado)
            }
        }
    }

    fun <T> Flow<T>.observe(owner: LifecycleOwner, observe: (T) -> Unit) {
        owner.lifecycleScope.launch {
            owner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                this@observe.collect(observe)
            }
        }
    }
}