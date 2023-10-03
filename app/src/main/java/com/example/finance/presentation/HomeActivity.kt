package com.example.finance.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI.setupWithNavController
import com.example.finance.R
import com.example.finance.databinding.ActivityHomeBinding
import com.example.finance.databinding.ActivityMainBinding
import com.example.finance.presentation.fragments.homeFragment

class HomeActivity : AppCompatActivity() {
    companion object {
        const val EXTRA_USERID = "extra_userid"
        const val EXTRA_USERNAME = "extra_username"
        lateinit var nome: String
        lateinit var id: String
    }

    private val binding by lazy{ActivityHomeBinding.inflate(layoutInflater)}

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        setContentView(binding.root)
        val navController = findNavController(R.id.nav_host_fragment)
        setupWithNavController(binding.bottomNavigation, navController)
    }


}