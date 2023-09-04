package com.example.finance.presentation

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.core.view.isVisible
import com.example.finance.databinding.ActivityMainBinding
import com.example.finance.presentation.fragments.UserState
import com.example.finance.presentation.fragments.UserViewModel
import com.example.finance.presentation.fragments.homeFragment
import com.google.android.material.textfield.TextInputEditText

class MainActivity : AppCompatActivity() {

    private val viewModel: UserViewModel by viewModels {
        UserViewModel.Factory()
    }

    lateinit var tfnome: TextInputEditText
    private val binding by lazy{ActivityMainBinding.inflate(layoutInflater)}

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ThemeManager.setCustomizedThemes(
            this,
            ThemeStorage.getThemeColor(this)
        )

        tfnome = binding.etNome
        binding.btEnviarNome.setOnClickListener{
            observeStates()
        }

        setContentView(binding.root)
    }


    private fun observeStates(){

        viewModel.getAllUser(tfnome.text.toString()).observe(this){
            when(it){
                is UserState.Success -> {
                    binding.pbLoading.isVisible = false
                    val intent = Intent(this, HomeActivity::class.java)
                    intent.putExtra(HomeActivity.EXTRA_USERID, it.user.id.toString())
                    startActivity(intent)
                    finish()
                }
                is UserState.Error -> {
                    binding.pbLoading.isVisible = false
                    val intent = Intent(this, CadastroActivity::class.java)
                    intent.putExtra(CadastroActivity.EXTRA_USERNAME, tfnome.text.toString())
                    startActivity(intent)
                }
                UserState.Loading -> {
                    binding.pbLoading.isVisible = true
                }

                UserState.Empty -> TODO()
            }
        }
    }

}