package com.example.finance.presentation

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.core.view.isVisible
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
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
        ThemeManager.setCustomizedThemes(
            this,
            ThemeStorage.getThemeColor(this)
        )

        tfnome = binding.etNome
        binding.btEnviarNome.setOnClickListener{
            observeStates(tfnome.text.toString())
        }

        setContentView(binding.root)
    }


    private fun observeStates(nome: String){
        viewModel.getAllUser(nome)
        viewModel.state.observe(this){
            when(it){
                UserState.Empty -> {
                    binding.pbLoading.isVisible = false
                    val intent = Intent(this, CadastroActivity::class.java)
                    intent.putExtra(CadastroActivity.EXTRA_USERNAME, tfnome.text.toString())
                    startActivity(intent)
                }
                is UserState.Error ->{
                    binding.pbLoading.isVisible = false
                    val intent = Intent(this, CadastroActivity::class.java)
                    intent.putExtra(CadastroActivity.EXTRA_USERNAME, tfnome.text.toString())
                    startActivity(intent)
                }
                UserState.Loading -> binding.pbLoading.isVisible = true
                is UserState.Success -> {
                    binding.pbLoading.isVisible = false
                    val intent = Intent(this, HomeActivity::class.java)
                    intent.putExtra(HomeActivity.EXTRA_USERID, it.user.id.toString())
                    intent.putExtra(HomeActivity.EXTRA_USERNAME, it.user.name)
                    startActivity(intent)
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