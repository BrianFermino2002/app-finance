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

        binding.piechart.addPieSlice(

            PieModel(
                "Casa", (10).toFloat(),
                Color.parseColor("#77371D")
            )
        )
        binding.piechart.addPieSlice(
            PieModel(
                "Comida", (10).toFloat(),
                Color.parseColor("#BC1515")
            )
        )
        binding.piechart.addPieSlice(
            PieModel(
                "Lazer", (30).toFloat(),
                Color.parseColor("#751491")
            )
        )
        binding.piechart.addPieSlice(
            PieModel(
                "Faculdade", (20).toFloat(),
                Color.parseColor("#CDBD2D")
            )
        )
        binding.piechart.addPieSlice(
            PieModel(
                "Trabalho", (10).toFloat(),
                Color.parseColor("#00BCD4")
            )
        )
        binding.piechart.addPieSlice(
            PieModel(
                "Familia", (20).toFloat(),
                Color.parseColor("#604B16")
            )
        )
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
            .filter { it.categoria == categoriaDesejada }
            .sumOf { it.valor }
    }

    fun gerarGrafico(lista: List<LancamentoDomain>){
        /*var valor = somarGastosPorCategoria(CategoriaLancamentoEntity.CASA, lista)
        binding.tvResCasa.setText(valor.toString())
        binding.piechart.addPieSlice(

            PieModel(
                "Casa", (valor).coerceIn(0.0, 1.0).toFloat(),
                Color.parseColor("#77371D")
            )
        )
        binding.piechart.addPieSlice(
            PieModel(
                "Comida", (somarGastosPorCategoria(CategoriaLancamentoEntity.COMIDA, lista)).coerceIn(0.0, 1.0).toFloat(),
                Color.parseColor("#BC1515")
            )
        )
        binding.piechart.addPieSlice(
            PieModel(
                "Lazer", (somarGastosPorCategoria(CategoriaLancamentoEntity.LAZER, lista)).coerceIn(0.0, 1.0).toFloat(),
                Color.parseColor("#751491")
            )
        )
        binding.piechart.addPieSlice(
            PieModel(
                "Faculdade", (somarGastosPorCategoria(CategoriaLancamentoEntity.FACULDADE, lista)).coerceIn(0.0, 1.0).toFloat(),
                Color.parseColor("#CDBD2D")
            )
        )
        binding.piechart.addPieSlice(
            PieModel(
                "Trabalho", (somarGastosPorCategoria(CategoriaLancamentoEntity.TRABALHO, lista)).coerceIn(0.0, 1.0).toFloat(),
                Color.parseColor("#00BCD4")
            )
        )
        binding.piechart.addPieSlice(
            PieModel(
                "Familia", (somarGastosPorCategoria(CategoriaLancamentoEntity.FAMILIA, lista)).coerceIn(0.0, 1.0).toFloat(),
                Color.parseColor("#604B16")
            )
        )

        binding.piechart.startAnimation()*/
    }
}