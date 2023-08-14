package com.example.finance.presentation.dialog

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import android.widget.RadioButton
import android.widget.RadioGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.setFragmentResult
import com.example.finance.databinding.FragmentDialogLancamentoBinding
import com.example.finance.domain.model.LancamentoDomain
import com.google.android.material.datepicker.MaterialDatePicker
import org.jetbrains.annotations.Nullable
import java.lang.IllegalStateException
import java.text.SimpleDateFormat
import java.util.Locale

class DialogInserirLancamento: DialogFragment() {
lateinit var binding:FragmentDialogLancamentoBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val titleText = arguments?.getString(TITLE_TEXT)
            ?: throw IllegalArgumentException("Ops, passe o titulo")
        return activity?.let{
            binding =
                FragmentDialogLancamentoBinding.inflate(requireActivity().layoutInflater).apply {
                    tvTitulo.text = titleText
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
                setFragmentResult(
                    FRAGMENT_RESULT, bundleOf(
                        EDIT_TEXT_VALUE to binding.etLancamento.text.toString(),
                        RADIO_BUTTON_VALUE to check.text.toString(),
                        DATA_VALUE to binding.etData.text.toString()
                    )
                )

                }
                .setNegativeButton("Cancelar"){_, _ ->
                    dismiss()
                }.create()
        }?:throw IllegalStateException("Activity cannot be Null")
    }

    companion object {
        const val TITLE_TEXT = "TITLE_TEXT"
        const val FRAGMENT_RESULT = "FRAGMENT_RESULT"
        const val EDIT_TEXT_VALUE = "EDIT_TEXT_VALUE"
        const val RADIO_BUTTON_VALUE = "RADIO_BUTTON_VALUE"
        const val DATA_VALUE = "DATA_VALUE"

        fun show(
            title: String,
            fragmentManager: FragmentManager,
            tag: String = DialogInserirLancamento::class.simpleName.toString()
        ) {
            DialogInserirLancamento().apply {
                arguments = bundleOf(
                    TITLE_TEXT to title,
                )
            }.show(
                fragmentManager,
                tag
            )
        }
    }
}