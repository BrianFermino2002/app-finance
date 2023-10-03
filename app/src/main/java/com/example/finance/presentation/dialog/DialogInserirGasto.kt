package com.example.finance.presentation.dialog

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import androidx.core.os.bundleOf
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.setFragmentResult
import com.example.finance.databinding.FragmentDialogAddgastoBinding
import com.google.android.material.datepicker.MaterialDatePicker
import java.lang.IllegalStateException
import java.text.SimpleDateFormat
import java.util.Locale

class DialogInserirGasto: DialogFragment() {
    lateinit var binding:FragmentDialogAddgastoBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val titleText = arguments?.getString(TITLE_TEXT)
            ?: throw IllegalArgumentException("Ops, passe o titulo")
        return activity?.let{
            binding =
                FragmentDialogAddgastoBinding.inflate(requireActivity().layoutInflater).apply {
                    tvTitulo.text = titleText
                }

            binding.etData.setOnClickListener {
                val datePicker =
                    MaterialDatePicker.Builder.datePicker()
                        .setTitleText("Seleione a data")
                        .setInputMode(MaterialDatePicker.INPUT_MODE_CALENDAR)
                        .build()
                datePicker.show(parentFragmentManager, "tag")

                datePicker.addOnPositiveButtonClickListener {
                    val data: String = SimpleDateFormat("dd/MM/yyy", Locale.getDefault()).format(it)
                    binding.etData.setText(data)
                }

                binding.btAdd.setOnClickListener {
                    setFragmentResult(
                        FRAGMENT_RESULT, bundleOf(
                            EDIT_TEXT_VALUE to binding.etLancamento.text.toString(),
                            DATA_VALUE to binding.etData.text.toString(),
                            EDIT_TEXT_DESCRIPTION to binding.etDescricao.text.toString()
                        )
                    )
                    dismiss()
                }
            }

            AlertDialog.Builder(it)
                .setView(binding.root)
                .create()
        }?:throw IllegalStateException("Activity cannot be Null")
    }

    companion object {
        const val TITLE_TEXT = "TITLE_TEXT"
        const val FRAGMENT_RESULT = "FRAGMENT_RESULT"
        const val EDIT_TEXT_VALUE = "EDIT_TEXT_VALUE"
        const val EDIT_TEXT_DESCRIPTION = "EDIT_TEXT_DESCRIPTION"
        const val DATA_VALUE = "DATA_VALUE"

        fun show(
            title: String,
            fragmentManager: FragmentManager,
            tag: String = DialogInserirGasto::class.simpleName.toString()
        ) {
            DialogInserirGasto().apply {
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