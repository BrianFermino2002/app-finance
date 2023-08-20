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
import androidx.viewbinding.ViewBinding
import com.example.finance.R
import com.example.finance.databinding.HeaderItemBinding
import com.example.finance.databinding.LancamentoItemBinding
import com.example.finance.domain.model.Lancamento
import com.example.finance.domain.model.LancamentoDomain
import com.example.finance.extension.toListOfDataItem
import com.example.finance.presentation.dialog.DialogAtualizarLancamento
import com.example.finance.presentation.dialog.DialogInserirLancamento
import com.example.finance.presentation.fragments.LancamentoViewModel
import com.google.gson.Gson
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.lang.ClassCastException

class LancamentoAdapter(val listenerLancamento: LancamentoListener) :
    androidx.recyclerview.widget.ListAdapter<DataItem, LancamentoAdapter.DataItemViewHolder>(DataItemDiffCallback()) {

    private val ITEM_VIEW_TYPE_HEADER = 0
    private val ITEM_VIEW_TYPE_LANCAMENTOITEM = 1

    val adapterScope = CoroutineScope(Dispatchers.Default)

    override fun getItemViewType(position: Int): Int {
        return when(getItem(position)) {
            is DataItem.Header -> ITEM_VIEW_TYPE_HEADER
            is DataItem.LancamentoItem -> ITEM_VIEW_TYPE_LANCAMENTOITEM
        }
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) :
            DataItemViewHolder {
        return when(viewType) {
            ITEM_VIEW_TYPE_HEADER -> DataItemViewHolder.HeaderViewHolder.from(parent)
            ITEM_VIEW_TYPE_LANCAMENTOITEM -> DataItemViewHolder.LancamentoViewHolder.from(parent)
            else -> throw ClassCastException("ViewType não reconhecido: $viewType")
        }
    }

    override fun onBindViewHolder(holder: DataItemViewHolder, position: Int) {
        return when(holder) {
            is DataItemViewHolder.LancamentoViewHolder -> {
                val item = getItem(position) as DataItem.LancamentoItem
                holder.bind(item.lancamento, listenerLancamento)
            }
            is DataItemViewHolder.HeaderViewHolder -> {
                val item = getItem(position) as DataItem.Header
                holder.bind(item)
            }
        }

    }

    fun addHeadersAndSubmitList(list: List<LancamentoDomain>?) {

        adapterScope.launch {
            val listDataItem = list?.toListOfDataItem()
            withContext(Dispatchers.Main) {
                submitList(listDataItem)
            }
        }

    }

    class DataItemDiffCallback : DiffUtil.ItemCallback<DataItem>() {
        override fun areItemsTheSame(oldItem: DataItem, newItem: DataItem): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: DataItem, newItem: DataItem): Boolean {
            return oldItem == newItem
        }

    }

    sealed class DataItemViewHolder(open val binding: ViewBinding) :
        RecyclerView.ViewHolder(binding.root){

        class LancamentoViewHolder(override val binding: LancamentoItemBinding) :
            DataItemViewHolder(binding) {

            /**
             * Esse método faz a vinculação dos dados com os componentes visuais. É possível fazer
             * tudo manualmente aqui na classe ViewHolder, mas preferi adotar BindingAdapters para
             * deixar a solução mais flexível e fácil de manter.
             */
            fun bind(item: LancamentoDomain, cardListener: LancamentoListener) {
                with(binding) {
                    lancamento = item
                    listener = cardListener
                    executePendingBindings()
                }
            }

            /**
             * Esse companion object permite que o ListAdapter obtenha uma instância
             * do ViewHolder passando apenas o parent.
             */
            companion object {
                fun from(parent: ViewGroup): DataItemViewHolder {
                    val binding: LancamentoItemBinding = LancamentoItemBinding.inflate(
                        LayoutInflater.from(parent.context),
                        parent,
                        false
                    )
                    return LancamentoViewHolder(binding)
                }
            }
        }

        class HeaderViewHolder(override val binding: HeaderItemBinding) :
            DataItemViewHolder(binding) {

            /**
             * Esse método faz a vinculação dos dados com os componentes visuais. É possível fazer
             * tudo manualmente aqui na classe ViewHolder, mas preferi adotar BindingAdapters para
             * deixar a solução mais flexível e fácil de manter.
             */
            fun bind(item: DataItem.Header) {
                with(binding) {
                    itemHeaderTv.text = item.key.toString()
                }
            }

            /**
             * Esse companion object permite que o ListAdapter obtenha uma instância
             * do ViewHolder passando apenas o parent.
             */
            companion object {
                fun from(parent: ViewGroup): DataItemViewHolder {
                    val binding: HeaderItemBinding = HeaderItemBinding.inflate(
                        LayoutInflater.from(parent.context),
                        parent,
                        false
                    )
                    return HeaderViewHolder(binding)
                }
            }
        }
    }

}
