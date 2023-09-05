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
import com.example.finance.databinding.ActivityPerguntasBinding
import com.example.finance.domain.model.UserDomain
import com.example.finance.presentation.fragments.UserState
import com.example.finance.presentation.fragments.UserViewModel
import com.example.finance.presentation.util.CompanionPerguntasRespostas
import com.example.finance.presentation.util.PerguntaLoad
import com.example.finance.presentation.util.RespostaLoad
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import org.apache.xmlbeans.impl.xb.xsdschema.Attribute.Use

class PerguntasActivity : AppCompatActivity() {
    private val binding by lazy{ ActivityPerguntasBinding.inflate(layoutInflater)}
    private val viewModel: UserViewModel by viewModels {
        UserViewModel.Factory()
    }
    private lateinit var listaDePerguntas: List<PerguntaLoad>
    private lateinit var listaDeRespostas: List<RespostaLoad>
    private lateinit var listaDePerguntasNivel: List<PerguntaLoad>

    private var currentIndex = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ThemeManager.setCustomizedThemes(
            this,
            ThemeStorage.getThemeColor(this)
        )

        listaDePerguntas = CompanionPerguntasRespostas.criarPerguntas()
        listaDeRespostas = CompanionPerguntasRespostas.criarRespostas()

        observeStates(HomeActivity.nome)
        showNextQuestion()
        setContentView(binding.root)
    }

    fun showNextQuestion() {
        if (currentIndex < 10) {
            val pergunta = listaDePerguntas[currentIndex]
            val respostas = listaDeRespostas.filter { it.idPergunta == pergunta.id }

            binding.tvPergunta.text = pergunta.enunciado
            binding.tvAlt01.text = respostas[0].descricao
            binding.tvAlt02.text = respostas[1].descricao
            binding.tvAlt03.text = respostas[2].descricao
            binding.tvAlt04.text = respostas[3].descricao

            currentIndex++
        } else {
            // Todas as perguntas foram exibidas.
            // Você pode fazer algo aqui, como encerrar a atividade ou mostrar um resultado final.
        }
    }

    private fun observeStates(nome: String){
        viewModel.getAllUser(nome)
        viewModel.state.observe(this){
            when(it){
                UserState.Empty -> {
                }
                is UserState.Error ->{
                }
                UserState.Loading -> {
                }
                is UserState.Success -> {
                    binding.tvNivel.text= it.user.nivel.toString()
                    setLevel(it.user)
                }
            }
        }
    }

    fun setLevel(user: UserDomain){
        listaDePerguntasNivel = listaDePerguntas.filter {
            it.nivel == user.nivel
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