package com.example.finance.presentation.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.viewModels
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.setFragmentResultListener
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.viewpager2.widget.ViewPager2
import com.example.finance.R
import com.example.finance.data.CategoriaLancamentoEntity
import com.example.finance.databinding.FragmentHomeBinding
import com.example.finance.domain.model.LancamentoDomain
import com.example.finance.presentation.HomeActivity
import com.example.finance.presentation.dialog.DialogAtualizarLancamento
import com.example.finance.presentation.dialog.DialogInserirLancamento
import com.google.android.material.tabs.TabLayout
import com.google.gson.Gson

class homeFragment: Fragment() {

    private lateinit var binding: FragmentHomeBinding
    private lateinit var viewPager2: ViewPager2

    private val viewModelLancamento by activityViewModels<LancamentoViewModel> {
        LancamentoViewModel.Factory()
    }


    override fun onCreateView(
        inflater: LayoutInflater,
        group: ViewGroup?,
        saved: Bundle?
    ): View {

        binding = FragmentHomeBinding.inflate(inflater, group, false)
        viewPager2 = binding.viewPager
        setupTabs()


        binding.fabInserirlancamento.setOnClickListener{
            showDialog()
        }

        setFragmentResultListener(DialogInserirLancamento.FRAGMENT_RESULT){requestKey, bundle ->
            val tipoMov = bundle.getString(DialogInserirLancamento.RADIO_BUTTON_VALUE)?: ""
            val valor = bundle.getString(DialogInserirLancamento.EDIT_TEXT_VALUE)?:""
            val data = bundle.getString(DialogInserirLancamento.DATA_VALUE)?:""
            val categoria = when(bundle.getString(DialogInserirLancamento.CATEGORIA_VALUE)?: ""){
                "CASA" -> CategoriaLancamentoEntity.CASA
                "TRABALHO" -> CategoriaLancamentoEntity.TRABALHO
                "COMIDA" -> CategoriaLancamentoEntity.COMIDA
                "LAZER" -> CategoriaLancamentoEntity.LAZER
                "FACULDADE" -> CategoriaLancamentoEntity.FACULDADE
                "FAMILIA" -> CategoriaLancamentoEntity.FAMILIA
                else -> CategoriaLancamentoEntity.CASA
            }
            viewModelLancamento.insertLancamento(
                tipomov = tipoMov,
                valor = valor.toDouble(),
                idUsuario = HomeActivity.id.toInt(),
                dataEfet = data,
                categoria = categoria
            )
        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observeLanc()
    }


    fun setupTabs(){
        val tabsAdapter = TabAdapter(this)
        viewPager2.adapter = tabsAdapter

        binding.tabLayout.addOnTabSelectedListener(object: TabLayout.OnTabSelectedListener{
            override fun onTabSelected(tab: TabLayout.Tab?) {
                tab?.let{
                    viewPager2.currentItem = it.position
                }
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {

            }

            override fun onTabReselected(tab: TabLayout.Tab?) {

            }

        })
        viewPager2.registerOnPageChangeCallback(object: ViewPager2.OnPageChangeCallback(){
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                binding.tabLayout.getTabAt(position)?.select()
            }
        })

    }

    private fun showDialog(){
        DialogInserirLancamento.show(
            title = "Lançamento",
            fragmentManager = parentFragmentManager
        )
    }

    private fun observeLanc() {
        viewLifecycleOwner.lifecycleScope.launchWhenStarted {
            viewModelLancamento.lancamentos.collect { lancamentos ->
            }
        }
    }


}


