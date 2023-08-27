package com.example.finance.presentation.fragments

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import com.example.finance.data.CategoriaLancamentoEntity
import com.example.finance.databinding.FragmentAnalisesBinding
import com.example.finance.domain.model.LancamentoDomain
import com.example.finance.presentation.HomeActivity
import kotlinx.coroutines.flow.filter
import org.eazegraph.lib.models.PieModel

class FragmentAnalises: Fragment(){

    private lateinit var binding: FragmentAnalisesBinding
    private val viewModel by activityViewModels<LancamentoViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentAnalisesBinding.inflate(inflater, container, false)
        initLancamentoList()

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        observeLanc()
    }

    fun initLancamentoList(){
        viewLifecycleOwner.lifecycleScope.launchWhenStarted {
            viewModel.lancamentos.collect { lancamentos ->
                gerarGrafico(lancamentos)
            }

        }
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

    fun somarGastosPorCategoria(categoriaDesejada: CategoriaLancamentoEntity, lista: List<LancamentoDomain>): Double {
        return lista
            .filter { it.categoria == categoriaDesejada && it.tipoMov == "Debito" }
            .sumOf { it.valor }
    }

    fun gerarGrafico(lista: List<LancamentoDomain>){
        var valor = somarGastosPorCategoria(CategoriaLancamentoEntity.CASA, lista)
        binding.tvValCasa.setText(valor.toString())
        binding.piechart.clearChart()
        binding.piechart.addPieSlice(

            PieModel(
                "Casa", (valor).toFloat(),
                Color.parseColor("#77371D")
            )
        )
        valor = somarGastosPorCategoria(CategoriaLancamentoEntity.COMIDA, lista)
        binding.tvValComida.setText(valor.toString())
        binding.piechart.addPieSlice(
            PieModel(
                "Comida", (valor).toFloat(),
                Color.parseColor("#BC1515")
            )
        )
        valor = somarGastosPorCategoria(CategoriaLancamentoEntity.LAZER, lista)
        binding.tvValLazer.setText(valor.toString())
        binding.piechart.addPieSlice(
            PieModel(
                "Lazer", (valor).toFloat(),
                Color.parseColor("#751491")
            )
        )
        valor = somarGastosPorCategoria(CategoriaLancamentoEntity.FACULDADE, lista)
        binding.tvValFaculdade.setText(valor.toString())
        binding.piechart.addPieSlice(
            PieModel(
                "Faculdade", (valor).toFloat(),
                Color.parseColor("#CDBD2D")
            )
        )
        valor = somarGastosPorCategoria(CategoriaLancamentoEntity.TRABALHO, lista)
        binding.tvValTrabalho.setText(valor.toString())
        binding.piechart.addPieSlice(
            PieModel(
                "Trabalho", (valor).toFloat(),
                Color.parseColor("#00BCD4")
            )
        )
        valor = somarGastosPorCategoria(CategoriaLancamentoEntity.FAMILIA, lista)
        binding.tvValFamilia.setText(valor.toString())
        binding.piechart.addPieSlice(
            PieModel(
                "Familia", (valor).toFloat(),
                Color.parseColor("#604B16")
            )
        )

        binding.piechart.startAnimation()
        }
}