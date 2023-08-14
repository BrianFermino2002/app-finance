package com.example.finance.presentation.fragments

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter


class TabAdapter(fragment: Fragment) : FragmentStateAdapter(fragment) {
    override fun getItemCount(): Int {
        return 2
    }

    override fun createFragment(position: Int): Fragment {
      return  when(position){
          0 -> FragmentVisaoGeral()
          1 -> FragmentLancamentos()
          else -> FragmentVisaoGeral()
      }
    }
}