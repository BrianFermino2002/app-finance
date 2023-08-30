package com.example.finance.presentation

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.core.view.isVisible
import com.example.finance.R
import com.example.finance.databinding.ActivityMainBinding
import com.example.finance.databinding.ActivityPerguntasBinding
import com.example.finance.presentation.fragments.PerguntaState
import com.example.finance.presentation.fragments.PerguntasViewModel
import com.example.finance.presentation.fragments.UserState
import com.example.finance.presentation.fragments.UserViewModel

class PerguntasActivity : AppCompatActivity() {
    private val binding by lazy{ ActivityPerguntasBinding.inflate(layoutInflater)}
    private val viewModel: PerguntasViewModel by viewModels {
        PerguntasViewModel.Factory()
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        observeStates()
        setContentView(binding.root)
    }

    private fun observeStates(){

        viewModel.getAllPerguntas().observe(this){
            when(it){
                is PerguntaState.Success -> {

                }
                is PerguntaState.Error -> {

                }
                PerguntaState.Loading -> {
                }

            }
        }
    }
}