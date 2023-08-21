package com.example.finance.presentation.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import com.example.finance.databinding.FragmentVisaogeralBinding
import com.example.finance.presentation.HomeActivity
import kotlinx.coroutines.flow.collect
import java.text.NumberFormat
import java.util.Locale

class FragmentVisaoGeral: Fragment() {
    private lateinit var binding: FragmentVisaogeralBinding
    private val viewModel by activityViewModels<LancamentoViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentVisaogeralBinding.inflate(inflater, container, false)
        initInfos()
        return binding.root
    }

    private fun initInfos(){
        viewLifecycleOwner.lifecycleScope.launchWhenStarted {
            viewModel.somaDebito.collect{somaDeb ->
                binding.tvDeb.setText(formatarLocal(somaDeb))
            }
        }

        viewLifecycleOwner.lifecycleScope.launchWhenStarted {
            viewModel.somaCredito.collect{somaCred ->
                binding.tvCred.setText(formatarLocal(somaCred))
            }
        }
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observeLanc()
    }


    private fun observeLanc() {

        lifecycleScope.launchWhenStarted {
            viewModel.getLancamentos(HomeActivity.id.toInt()).collect { state ->
                when (state) {
                    LancamentoState.Loading -> {
                        // @TODO mostrar loading para o usuario
                    }
                    is LancamentoState.Error -> {
                        // @TODO Mostrar error parar o usuario
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