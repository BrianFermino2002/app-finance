package com.example.finance.presentation.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.finance.databinding.FragmentConfigsBinding
import com.example.finance.presentation.ThemeManager
import com.example.finance.presentation.ThemeStorage

class FragmentConfigs: Fragment() {
    private lateinit var binding: FragmentConfigsBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        ThemeManager.setCustomizedThemes(
            requireContext(),
            ThemeStorage.getThemeColor(requireContext())
        )
        binding = FragmentConfigsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.blueColor.setOnClickListener {
            ThemeStorage.setThemeColor(requireContext().applicationContext, "azul")
            ThemeManager.setCustomizedThemes(requireContext().applicationContext, "azul")
            activity?.recreate()
        }

        binding.pinkColor.setOnClickListener {
            ThemeStorage.setThemeColor(requireContext().applicationContext, "rosa")
            ThemeManager.setCustomizedThemes(requireContext().applicationContext, "rosa")
            activity?.recreate()
        }

        binding.ibSemTema.setOnClickListener {
            ThemeStorage.setThemeColor(requireContext().applicationContext, "nada")
            ThemeManager.setCustomizedThemes(requireContext().applicationContext, "nada")
            activity?.recreate()
        }

        binding.greenColor.setOnClickListener {
            ThemeStorage.setThemeColor(requireContext().applicationContext, "verde")
            ThemeManager.setCustomizedThemes(requireContext().applicationContext, "verde")
            activity?.recreate()
        }

        binding.orangeColor.setOnClickListener {
            ThemeStorage.setThemeColor(requireContext().applicationContext, "laranja")
            ThemeManager.setCustomizedThemes(requireContext().applicationContext, "laranja")
            activity?.recreate()
        }
    }
}