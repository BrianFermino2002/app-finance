package com.example.finance.presentation.fragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.finance.databinding.FragmentExtrasBinding

import com.example.finance.presentation.PerguntasActivity


class FragmentExtras: Fragment() {
    private lateinit var binding: FragmentExtrasBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentExtrasBinding.inflate(inflater, container, false)

        binding.ibIrPerguntas.setOnClickListener {
            val intent = Intent(activity, PerguntasActivity::class.java)
            startActivity(intent)
        }
        return binding.root
    }


}