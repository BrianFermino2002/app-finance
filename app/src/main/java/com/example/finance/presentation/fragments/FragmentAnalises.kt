package com.example.finance.presentation.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.finance.databinding.FragmentAnalisesBinding

class FragmentAnalises: Fragment(){

    private lateinit var binding: FragmentAnalisesBinding
    private val viewModel by activityViewModels<LancamentoViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentAnalisesBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

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