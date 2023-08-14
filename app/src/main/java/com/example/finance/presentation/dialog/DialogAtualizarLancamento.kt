package com.example.finance.presentation.dialog

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import android.widget.RadioButton
import android.widget.RadioGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.setFragmentResult
import androidx.fragment.app.setFragmentResultListener
import com.example.finance.databinding.FragmentDialogLancamentoBinding
import com.example.finance.domain.model.LancamentoDomain
import com.example.finance.presentation.fragments.LancamentoViewModel
import com.google.android.material.datepicker.MaterialDatePicker
import com.google.gson.Gson
import org.jetbrains.annotations.Nullable
import java.lang.IllegalStateException
import java.text.SimpleDateFormat
import java.util.Locale

class DialogAtualizarLancamento: DialogFragment() {
    lateinit var binding:FragmentDialogLancamentoBinding
    private val viewModel by activityViewModels<LancamentoViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val titleText = arguments?.getString(TITLE_TEXT)
            ?: throw IllegalArgumentException("Ops, passe o titulo")

        val lancamentoJsn = arguments?.getString(LANCAMENTO_ITEM) ?: throw IllegalArgumentException(
            "Ops, passe o objeto"
        )

        val lancamento = Gson().fromJson(lancamentoJsn, LancamentoDomain::class.java)
        return activity?.let{
            binding =
                FragmentDialogLancamentoBinding.inflate(requireActivity().layoutInflater).apply {
                    tvTitulo.text = titleText
                    etLancamento.setText(lancamento.valor.toString())
                    etData.setText(lancamento.dataEfet)
                    if(lancamento.tipoMov.compareTo("Credito") == 0){
                        rbCredito.isChecked = true
                    } else{
                        rbDebito.isChecked = true
                    }
                }

            binding.etData.isEnabled = false
            binding.btData.setOnClickListener {

                val datePicker =
                    MaterialDatePicker.Builder.datePicker()
                        .setTitleText("Seleione a data")
                        .setInputMode(MaterialDatePicker.INPUT_MODE_TEXT)
                        .build()
                datePicker.show(parentFragmentManager, "tag")

                datePicker.addOnPositiveButtonClickListener {
                    var data: String = SimpleDateFormat("dd/MM/yyy", Locale.getDefault()).format(it)
                    binding.etData.setText(data)
                }
            }

            var check: RadioButton = binding.rbCredito
            binding.rgTipolanc.setOnCheckedChangeListener { group, i ->
                if(binding.rbCredito.isChecked){
                    check = binding.rbCredito
                } else{
                    check = binding.rbDebito
                }
            }
            AlertDialog.Builder(it)
                .setView(binding.root)
                .setPositiveButton("Confirmar"){_, _ ->
                    viewModel.updateLancamento(
                        id = lancamento.id,
                        tipomov = check.text.toString(),
                        valor = binding.etLancamento.text.toString().toDouble(),
                        dataEfet = binding.etData.text.toString(),
                        idUsuario = lancamento.idUsuario
                    )

                }
                .setNegativeButton("Cancelar"){_, _ ->
                    dismiss()
                }.create()
        }?:throw IllegalStateException("Activity cannot be Null")
    }

    companion object {
        const val TITLE_TEXT = "TITLE_TEXT"
        const val LANCAMENTO_ITEM = "LANCAMENTO_ITEM"
        fun show(
            title: String,
            lancamentoJson: String,
            fragmentManager: FragmentManager,
            tag: String = DialogAtualizarLancamento::class.simpleName.toString()
        ) {
            DialogAtualizarLancamento().apply {
                arguments = bundleOf(
                    TITLE_TEXT to title,
                    LANCAMENTO_ITEM to lancamentoJson
                )
            }.show(
                fragmentManager,
                tag
            )
        }
    }
}