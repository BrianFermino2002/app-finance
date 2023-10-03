package com.example.finance.presentation.fragments

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.setFragmentResultListener
import androidx.lifecycle.lifecycleScope
import com.example.finance.data.toLocalDateTime
import com.example.finance.databinding.FragmentHomeBinding
import com.example.finance.presentation.ExtratoActivity
import com.example.finance.presentation.adapters.LancamentoAdapterSimples
import com.example.finance.presentation.dialog.DialogInserirGasto
import com.example.finance.presentation.dialog.DialogInserirLancamento
import java.text.NumberFormat
import java.util.Locale

class homeFragment: Fragment() {

    private lateinit var binding: FragmentHomeBinding
    private lateinit var sharedPreferences: SharedPreferences
    private lateinit var nome: String
    var iduser = -1

    private val viewModelLancamento by activityViewModels<LancamentoViewModel> {
        LancamentoViewModel.Factory()
    }
    private val adapter by lazy { LancamentoAdapterSimples() }


    override fun onCreateView(
        inflater: LayoutInflater,
        group: ViewGroup?,
        saved: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(inflater, group, false)

        sharedPreferences = requireContext().getSharedPreferences("MyPrefs", Context.MODE_PRIVATE)
        nome = sharedPreferences.getString("username", "")?: ""
        iduser = sharedPreferences.getInt("iduser", -1)
        binding.tvOlaUsuario.text = "Olá, ${nome}"



        binding.clGanho.setOnClickListener {
            showDialogGanho()

            setFragmentResultListener(DialogInserirLancamento.FRAGMENT_RESULT){requestKey, bundle ->
                val valor = bundle.getString(DialogInserirLancamento.EDIT_TEXT_VALUE)?:""
                val data = bundle.getString(DialogInserirLancamento.DATA_VALUE)?:""
                val descricao = bundle.getString(DialogInserirLancamento.EDIT_TEXT_DESCRIPTION)?:""
                viewModelLancamento.insertLancamento(
                    tipomov = "Credito",
                    valor = valor.toDouble(),
                    idUsuario = iduser,
                    dataEfet = data,
                    categoria = descricao
                )
            }
        }

        binding.clGasto.setOnClickListener {
            showDialogGasto()

            setFragmentResultListener(DialogInserirGasto.FRAGMENT_RESULT){requestKey, bundle ->
                val valor = bundle.getString(DialogInserirGasto.EDIT_TEXT_VALUE)?:""
                val data = bundle.getString(DialogInserirGasto.DATA_VALUE)?:""
                val descricao = bundle.getString(DialogInserirGasto.EDIT_TEXT_DESCRIPTION)?:""
                viewModelLancamento.insertLancamento(
                    tipomov = "Debito",
                    valor = valor.toDouble(),
                    idUsuario = iduser,
                    dataEfet = data,
                    categoria = descricao
                )
            }
        }

        binding.btnExtrato.setOnClickListener {
            val intent = Intent(requireContext(), ExtratoActivity::class.java)
            startActivity(intent)
        }

        observeLanc()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observe()
        setupAdapter()

    }


    private fun setupAdapter() {
        binding.rvLancamentos.adapter = adapter
    }

    private fun showDialogGanho(){
        DialogInserirLancamento.show(
            title = "Adicionar Ganho",
            fragmentManager = parentFragmentManager
        )
    }

    private fun showDialogGasto(){
        DialogInserirGasto.show(
            title = "Adicionar Gasto",
            fragmentManager = parentFragmentManager
        )
    }

    private fun observeLanc() {
        viewLifecycleOwner.lifecycleScope.launchWhenStarted {
            viewModelLancamento.lancamentos.collect { lancamentos ->


            }
        }

        viewLifecycleOwner.lifecycleScope.launchWhenStarted {
            viewModelLancamento.somaDebito.collect{somaDeb ->
                binding.tvValorGasto.setText(formatarLocal(somaDeb))
            }
        }

        viewLifecycleOwner.lifecycleScope.launchWhenStarted {
            viewModelLancamento.somaCredito.collect{somaCred ->
                binding.tvValorGanho.setText(formatarLocal(somaCred))
            }
        }

        viewLifecycleOwner.lifecycleScope.launchWhenStarted {
            viewModelLancamento.carteira.collect{cart ->
                binding.tvValorCarteira.setText(formatarLocal(cart))
            }
        }

        viewLifecycleOwner.lifecycleScope.launchWhenStarted {
            viewModelLancamento.lancamentos.collect{
                adapter.submitList(it.sortedByDescending {it.data.toLocalDateTime()}.take(4))
            }
        }
    }
    private fun observe() {

            lifecycleScope.launchWhenStarted {
                viewModelLancamento.getLancamentos(iduser).collect { state ->
                    when (state) {
                        LancamentoState.Loading -> {
                            // @TODO mostrar loading para o usuario
                        }
                        is LancamentoState.Error -> {
                            // @TODO Mostrar error parar o usuario
                        }
                        is LancamentoState.Success -> {
                        }

                        else -> {}
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


