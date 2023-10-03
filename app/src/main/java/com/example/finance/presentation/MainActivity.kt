package com.example.finance.presentation

import android.content.Context
import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.core.view.isVisible
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.example.finance.R
import com.example.finance.databinding.ActivityMainBinding
import com.example.finance.presentation.fragments.UserState
import com.example.finance.presentation.fragments.UserViewModel
import com.example.finance.presentation.fragments.homeFragment
import com.google.android.material.textfield.TextInputEditText
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    companion object {
        const val EXTRA_USERNAME = "extra_username"
    }

    private val viewModel: UserViewModel by viewModels {
        UserViewModel.Factory()
    }

    lateinit var tfnome: TextInputEditText
    private val binding by lazy{ActivityMainBinding.inflate(layoutInflater)}

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        tfnome = binding.etNome
        binding.btEnviarNome.setOnClickListener{
            observeStates(tfnome.text.toString())
        }

        checkSession()
        setContentView(binding.root)
    }

    private fun checkSession() {
        val sharedPreferences = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE)
        val isLoggedIn = sharedPreferences.getBoolean("isLoggedIn", false)

        if (isLoggedIn) {
            // O usuário está logado, redirecione para a tela principal
            val intent = Intent(this, VerificarActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
    private fun observeStates(nome: String){
        viewModel.getAllUser(nome)
        viewModel.state.observe(this){
            when(it){
                UserState.Empty -> {
                    val intent = Intent(this, CadastroActivity::class.java)
                    intent.putExtra(CadastroActivity.EXTRA_USERNAME, tfnome.text.toString())
                    startActivity(intent)
                }
                is UserState.Error ->{
                    val intent = Intent(this, CadastroActivity::class.java)
                    intent.putExtra(CadastroActivity.EXTRA_USERNAME, tfnome.text.toString())
                    startActivity(intent)
                }
                UserState.Loading -> {}
                is UserState.Success -> {
                    val intent = Intent(this, HomeActivity::class.java)

                    startActivity(intent)

                    val sharedPreferences = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE)
                    val editor = sharedPreferences.edit()
                    editor.putString("username", it.user.name)
                    editor.putInt("iduser", it.user.id)
                    editor.putBoolean("isLoggedIn", true)
                    editor.apply()

                    finish()
                }
            }
        }
    }

    fun <T> Flow<T>.observe(owner: LifecycleOwner, observe: (T) -> Unit) {
        owner.lifecycleScope.launch {
            owner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                this@observe.collect(observe)
            }
        }
    }

}