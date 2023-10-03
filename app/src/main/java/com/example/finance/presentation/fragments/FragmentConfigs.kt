package com.example.finance.presentation.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.finance.databinding.FragmentConfigsBinding


class FragmentConfigs: Fragment() {
    private lateinit var binding: FragmentConfigsBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentConfigsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.blueColor.setOnClickListener {

            activity?.recreate()
        }

        binding.pinkColor.setOnClickListener {

            activity?.recreate()
        }

        binding.ibSemTema.setOnClickListener {

            activity?.recreate()
        }

        binding.greenColor.setOnClickListener {

            activity?.recreate()
        }

        binding.orangeColor.setOnClickListener {

            activity?.recreate()
        }
    }
}