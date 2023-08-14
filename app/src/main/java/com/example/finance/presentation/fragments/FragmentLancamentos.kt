package com.example.finance.presentation.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.setFragmentResultListener
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.finance.databinding.FragmentLancamentosBinding
import com.example.finance.domain.model.Lancamento
import com.example.finance.domain.model.LancamentoDomain
import com.example.finance.presentation.HomeActivity
import com.example.finance.presentation.adapters.LancamentoAdapter
import com.example.finance.presentation.adapters.OnLancamentoDeleteListener
import com.example.finance.presentation.dialog.DialogAtualizarLancamento
import com.google.gson.Gson

class FragmentLancamentos: Fragment(), OnLancamentoDeleteListener {
    private lateinit var binding: FragmentLancamentosBinding
    private val viewModel by activityViewModels<LancamentoViewModel>()
    private val adapter by lazy { LancamentoAdapter(parentFragmentManager, viewModel, this) }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentLancamentosBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observe()
        observeLD()
        setupAdapters()

    }

    private fun setupAdapters(){
        binding.rvLancamentos.adapter =adapter
    }


    private fun observeLD() {
        viewLifecycleOwner.lifecycleScope.launchWhenStarted {
            viewModel.lancamentos.collect { lancamentos ->
                adapter.submitList(lancamentos)
            }
        }
    }
    private fun observe() {

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
                        adapter.submitList(state.lancamento)
                    }
                }
            }
        }
    }
    override fun onLancamentoDelete(lancamento: LancamentoDomain) {
        viewModel.deleteLancamento(lancamento) // Chama a função de exclusão do ViewModel
        observe()
        observeLD()
    }
}