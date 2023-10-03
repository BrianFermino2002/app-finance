package com.example.finance.presentation

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.finance.R
import com.example.finance.databinding.ActivityEscolhaBinding
import com.example.finance.databinding.ActivityVerificarBinding

class EscolhaActivity : AppCompatActivity() {

    private val binding by lazy{ ActivityEscolhaBinding.inflate(layoutInflater)}
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val sharedPreferences = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE)

        binding.tvEscolha.text = "Olá ${sharedPreferences.getString("username", "")}, o que gostaria de fazer?"
        setContentView(binding.root)

        binding.btMinhasFinancas.setOnClickListener {
            val intent = Intent(this, HomeActivity::class.java)

            startActivity(intent)
            finish()
        }
    }
}