package com.example.finance.presentation.adapters

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ListAdapter
import android.widget.Toast
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.setFragmentResultListener
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.finance.R
import com.example.finance.databinding.LancamentoItemBinding
import com.example.finance.domain.model.Lancamento
import com.example.finance.domain.model.LancamentoDomain
import com.example.finance.presentation.dialog.DialogAtualizarLancamento
import com.example.finance.presentation.dialog.DialogInserirLancamento
import com.example.finance.presentation.fragments.LancamentoViewModel
import com.google.gson.Gson

class LancamentoAdapter(private val fragmentManager: FragmentManager,
    private val viewModel: LancamentoViewModel,
    private val listener: OnLancamentoDeleteListener
) : androidx.recyclerview.widget.ListAdapter<LancamentoDomain, LancamentoAdapter.ViewHolder>(DiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = LancamentoItemBinding.inflate(inflater, parent, false)

        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class ViewHolder(
        private val binding: LancamentoItemBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(item: LancamentoDomain) = with(binding) {
            tvData.text = item.dataEfet
            tvValue.text = "R$${item.valor.toString()}"
            val typeIcon =if(item.tipoMov == "Debito") R.drawable.debito_icon else R.drawable.credito_icon
            ivTipo.setImageResource(typeIcon)

            ibEdit.setOnClickListener {
                val lancamentoJson = Gson().toJson(item)
                DialogAtualizarLancamento.show(
                        title = "Lançamento - Atualizar",
                        lancamentoJson = lancamentoJson,
                        fragmentManager = fragmentManager
                    )
            }

            ibDelete.setOnClickListener {
                listener.onLancamentoDelete(item)
            }
        }

    }

}

class DiffCallback : DiffUtil.ItemCallback<LancamentoDomain>() {
    override fun areItemsTheSame(oldItem: LancamentoDomain, newItem: LancamentoDomain) = oldItem == newItem
    override fun areContentsTheSame(oldItem: LancamentoDomain, newItem: LancamentoDomain) =
        oldItem.id == newItem.id
}

interface OnLancamentoDeleteListener {
    fun onLancamentoDelete(lancamento: LancamentoDomain)
}