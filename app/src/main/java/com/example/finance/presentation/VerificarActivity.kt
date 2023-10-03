package com.example.finance.presentation

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.finance.R
import com.example.finance.databinding.ActivityMainBinding
import com.example.finance.databinding.ActivityVerificarBinding

class VerificarActivity : AppCompatActivity() {

    private val binding by lazy{ ActivityVerificarBinding.inflate(layoutInflater)}
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val sharedPreferences = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE)
        binding.tvPergunta.text = ("Você é o " + sharedPreferences.getString("username", "") + "?")

        binding.btSim.setOnClickListener {
            val intent = Intent(this, EscolhaActivity::class.java)

            startActivity(intent)
            finish()
        }

        binding.btNao.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            val editor = sharedPreferences.edit()

            editor.putBoolean("isLoggedIn", false)
            editor.apply()
            finish()
            startActivity(intent)

            finish()
        }
        setContentView(binding.root)
    }
}