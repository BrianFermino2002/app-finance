package com.example.finance.presentation

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.finance.R
import com.example.finance.databinding.ActivityExtratoBinding
import com.example.finance.databinding.ActivityPerguntasBinding
import com.example.finance.presentation.adapters.LancamentoAdapter
import com.example.finance.presentation.adapters.LancamentoListener
import com.example.finance.presentation.fragments.LancamentoState
import com.example.finance.presentation.fragments.LancamentoViewModel
import com.example.finance.presentation.fragments.UserViewModel
import java.text.NumberFormat
import java.util.Locale

class ExtratoActivity : AppCompatActivity() {
    private val binding by lazy{ ActivityExtratoBinding.inflate(layoutInflater)}
    private val viewModel: LancamentoViewModel by viewModels {
        LancamentoViewModel.Factory()
    }

    private var id = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        val sharedPreferences = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE)
        id = sharedPreferences.getInt("iduser", -1)
        binding.btnVoltar.setOnClickListener {
            finish()
        }

        observeLanc()

        lifecycleScope.launchWhenStarted {
            viewModel.lancamentos.collect { lancamentos ->
                val adapter = LancamentoAdapter(LancamentoListener( clickListener = { lanc ->

                }))
                binding.rvLancamentos.layoutManager = LinearLayoutManager(this@ExtratoActivity)
                binding.rvLancamentos.adapter = adapter
                adapter.addHeadersAndSubmitList(lancamentos)
            }
        }

        initLancamentoList()
    }

    private fun initLancamentoList() {
        lifecycleScope.launchWhenStarted {
            viewModel.lancamentos.collect { lancamentos ->
                val adapter = LancamentoAdapter(LancamentoListener( clickListener = { lanc ->

                }))
                binding.rvLancamentos.layoutManager = LinearLayoutManager(this@ExtratoActivity)
                binding.rvLancamentos.adapter = adapter
                adapter.addHeadersAndSubmitList(lancamentos)
            }
        }

        lifecycleScope.launchWhenStarted {
            viewModel.carteira.collect{cart ->
                binding.tvValorCarteira.setText(formatarLocal(cart))
            }
        }
    }

    private fun observeLanc() {
        lifecycleScope.launchWhenStarted {
            viewModel.getLancamentos(id).collect { state ->
                when (state) {
                    LancamentoState.Loading -> {
                        // @TODO mostrar loading para o usuario
                    }
                    is LancamentoState.Error -> {

                    }
                    is LancamentoState.Success -> {

                    }
                }
            }
        }
    }

    private fun formatarLocal(valor: Double ): String{
        val currencyFormat = NumberFormat.getCurrencyInstance(Locale.getDefault())
        val formatado = currencyFormat.format(valor)
        return formatado
    }
}