package com.example.finance.presentation.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.finance.databinding.FragmentLancamentosBinding
import com.example.finance.presentation.HomeActivity
import com.example.finance.presentation.adapters.LancamentoAdapter
import com.example.finance.presentation.adapters.LancamentoListener
import com.google.gson.Gson

class FragmentLancamentos: Fragment(){
    private lateinit var binding: FragmentLancamentosBinding
    private val viewModel by activityViewModels<LancamentoViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentLancamentosBinding.inflate(inflater, container, false)
        initLancamentoList()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        /*observeLanc()*/
    }

    private fun initLancamentoList() {
        viewLifecycleOwner.lifecycleScope.launchWhenStarted {
            viewModel.lancamentos.collect { lancamentos ->
                val adapter = LancamentoAdapter(LancamentoListener( clickListener = {lanc ->
                    viewModel.deleteLancamento(lanc)
                }))
                binding.rvLancamentos.layoutManager = LinearLayoutManager(context)
                binding.rvLancamentos.adapter = adapter
                adapter.addHeadersAndSubmitList(lancamentos)
            }

        }

    }

    /*private fun observeLanc() {

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
    }*/

}

