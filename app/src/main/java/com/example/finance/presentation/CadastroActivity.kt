package com.example.finance.presentation

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.core.view.isVisible
import com.example.finance.R
import com.example.finance.databinding.ActivityCadastroBinding
import com.example.finance.databinding.ActivityMainBinding
import com.example.finance.presentation.fragments.UserState
import com.example.finance.presentation.fragments.UserViewModel
import kotlinx.coroutines.job

class CadastroActivity : AppCompatActivity() {
    companion object {
        const val EXTRA_USERNAME = "extra_username"
    }

    private val binding by lazy{ ActivityCadastroBinding.inflate(layoutInflater)}

    private val viewModel: UserViewModel by viewModels {
        UserViewModel.Factory()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(binding.root)

        val nome = intent.getStringExtra(EXTRA_USERNAME)
        binding.tvOlaUsuario.setText("Olá, " + nome)

        binding.btFinalizacad.setOnClickListener{
            inserirUsuario(nome?: "")
        }
    }


    fun inserirUsuario(nome: String){
        val salario = binding.etSalario.text.toString().toDouble()
        val idade = binding.etIdade.text.toString().toInt()
        viewModel.insert(nome, salario, idade)
        Toast.makeText(this, "Cadastro Realizado! Faça o Login agora",
            Toast.LENGTH_LONG).show()
        finish()
    }


}