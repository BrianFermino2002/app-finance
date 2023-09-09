package com.example.finance.presentation

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
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
    private lateinit var user: UserDomain

    private val viewModel: UserViewModel by viewModels {
        UserViewModel.Factory()
    }
    private lateinit var listaDePerguntas: List<PerguntaLoad>
    private lateinit var listaDeRespostas: List<RespostaLoad>
    private lateinit var listaDePerguntasNivel: List<PerguntaLoad>
    private lateinit var respostaCerta: List<RespostaLoad>

    var originalCardViewColor: Int = 0
    private var currentIndex = 0
    private var pontuacao = 0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ThemeManager.setCustomizedThemes(
            this,
            ThemeStorage.getThemeColor(this)
        )
        originalCardViewColor = binding.cvAlt01.cardBackgroundColor.defaultColor


        binding.btnProxima.isVisible = false
        listaDePerguntas = CompanionPerguntasRespostas.criarPerguntas()
        listaDeRespostas = CompanionPerguntasRespostas.criarRespostas()

        observeStates(HomeActivity.nome)
        setContentView(binding.root)

        binding.cvAlt01.setOnClickListener {
            binding.btnProxima.isVisible = true
            if(respostaCerta[0].descricao.equals(binding.tvAlt01.text)){
                binding.cvAlt01.setCardBackgroundColor(getColor(R.color.verde_claro))
                desabilitaBotoes(binding)
                atualizaPonto()
            } else{
                desabilitaBotoes(binding)
                binding.cvAlt01.setCardBackgroundColor(getColor(R.color.pie_graph_comida))
            }
        }

        binding.cvAlt02.setOnClickListener {
            binding.btnProxima.isVisible = true
            if (respostaCerta[0].descricao.equals(binding.tvAlt02.text)) {
                binding.cvAlt02.setCardBackgroundColor(getColor(R.color.verde_claro))
                desabilitaBotoes(binding)
                atualizaPonto()
            } else{
                desabilitaBotoes(binding)
                binding.cvAlt02.setCardBackgroundColor(getColor(R.color.pie_graph_comida))
            }
        }

        binding.cvAlt03.setOnClickListener {
            binding.btnProxima.isVisible = true
            if (respostaCerta[0].descricao.equals(binding.tvAlt03.text)) {
                binding.cvAlt03.setCardBackgroundColor(getColor(R.color.verde_claro))
                desabilitaBotoes(binding)
                atualizaPonto()
            } else{
                desabilitaBotoes(binding)
                binding.cvAlt03.setCardBackgroundColor(getColor(R.color.pie_graph_comida))
            }
        }

        binding.cvAlt04.setOnClickListener {
            binding.btnProxima.isVisible = true
            if (respostaCerta[0].descricao.equals(binding.tvAlt04.text)) {
                binding.cvAlt04.setCardBackgroundColor(getColor(R.color.verde_claro))
                desabilitaBotoes(binding)
                atualizaPonto()
            } else{
                binding.cvAlt04.setCardBackgroundColor(getColor(R.color.pie_graph_comida))
                desabilitaBotoes(binding)
            }
        }

        binding.btnProxima.setOnClickListener {
            binding.btnProxima.isVisible = false
            habilitaBotoes(binding)
            binding.cvAlt01.setCardBackgroundColor(originalCardViewColor)
            binding.cvAlt02.setCardBackgroundColor(originalCardViewColor)
            binding.cvAlt03.setCardBackgroundColor(originalCardViewColor)
            binding.cvAlt04.setCardBackgroundColor(originalCardViewColor)
            showNextQuestion()
        }
    }

    fun desabilitaBotoes(binding: ActivityPerguntasBinding){
        binding.cvAlt01.isClickable = false
        binding.cvAlt02.isClickable = false
        binding.cvAlt03.isClickable = false
        binding.cvAlt04.isClickable = false
    }

    fun habilitaBotoes(binding: ActivityPerguntasBinding){
        binding.cvAlt01.isClickable = true
        binding.cvAlt02.isClickable = true
        binding.cvAlt03.isClickable = true
        binding.cvAlt04.isClickable = true
    }

    fun showNextQuestion() {
        if (currentIndex < 10) {
            val pergunta = listaDePerguntasNivel[currentIndex]
            val respostas = listaDeRespostas.filter { it.idPergunta == pergunta.id }
            respostaCerta  = listaDeRespostas.filter {it.idPergunta == pergunta.id && it.correta}

            binding.tvPergunta.text = pergunta.enunciado
            binding.tvAlt01.text = respostas[0].descricao
            binding.tvAlt02.text = respostas[1].descricao
            binding.tvAlt03.text = respostas[2].descricao
            binding.tvAlt04.text = respostas[3].descricao

            currentIndex++
        } else {
            if(pontuacao >= 7){
                val nivel = when(user.nivel){
                    Nivel.INICIANTE -> Nivel.APRENDIZ
                    Nivel.APRENDIZ -> Nivel.AVANCADO
                    Nivel.AVANCADO -> Nivel.INCRIVEL
                    else -> Nivel.INICIANTE
                }
                viewModel.updateUsuario(
                    id = user.id,
                    nome = user.name,
                    salario = user.salario,
                    nivel = nivel
                )
                currentIndex = 0
                pontuacao = 0
            }
            currentIndex = 0
            pontuacao = 0
        }
    }

    fun atualizaPonto(){
        pontuacao++
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
                    user = it.user
                    binding.tvNivel.text= it.user.nivel.toString()
                    setLevel(user)
                    showNextQuestion()
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