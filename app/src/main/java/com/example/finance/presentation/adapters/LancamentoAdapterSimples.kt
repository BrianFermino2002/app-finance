package com.example.finance.presentation.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.finance.R
import com.example.finance.databinding.LancamentoSimplificadoBinding
import com.example.finance.domain.model.LancamentoDomain
import java.text.NumberFormat
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.Locale

class LancamentoAdapterSimples: androidx.recyclerview.widget.ListAdapter<LancamentoDomain, LancamentoAdapterSimples.ViewHolder>(DiffCallback()) {
    var click: (LancamentoDomain) -> Unit = {}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = LancamentoSimplificadoBinding.inflate(inflater, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class ViewHolder(
        private val binding: LancamentoSimplificadoBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(item: LancamentoDomain) {
            when(item.tipoMov){
                "Debito" -> binding.ivTipoLanc.setImageResource(R.drawable.minus_square_1)
                "Credito" -> binding.ivTipoLanc.setImageResource(R.drawable.plus_square__1__1)
            }
            binding.tvCategoria.text = item.categoria
            val formatoOriginal = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
            val data = formatoOriginal.parse(item.dataEfet)

            val formatoDesejado = SimpleDateFormat("dd/MM", Locale.getDefault())
            val resultado = formatoDesejado.format(data)
            binding.tvData.text = resultado
            binding.tvValor.text = formatarLocal(item.valor)
            binding.root.setOnClickListener{
                click(item)
            }
        }
    }
    private fun formatarLocal(valor: Double ): String{
        val currencyFormat = NumberFormat.getCurrencyInstance(Locale.getDefault())
        val formatado = currencyFormat.format(valor)
        return formatado
    }
}

class DiffCallback : DiffUtil.ItemCallback<LancamentoDomain>() {
    override fun areItemsTheSame(oldItem: LancamentoDomain, newItem: LancamentoDomain) = oldItem == newItem
    override fun areContentsTheSame(oldItem: LancamentoDomain, newItem: LancamentoDomain) =
        oldItem.id == newItem.id
}