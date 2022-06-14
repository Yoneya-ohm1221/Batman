package com.example.aloanmini.ui.fragment.homePager

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.aloanmini.databinding.FragmentHomeBinding
import com.google.android.material.tabs.TabLayoutMediator


class HomeFragment : Fragment() {
    lateinit var binding: FragmentHomeBinding
    lateinit var viewPagerAdapter: ViewPagerAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentHomeBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val tabLayout = binding.tabLayout
        viewPagerAdapter = ViewPagerAdapter(childFragmentManager, lifecycle)
        binding.pager.apply {
            adapter =viewPagerAdapter
        }
        TabLayoutMediator(tabLayout, binding.pager) { tab, position ->
            when (position) {
                0 -> tab.text = "Borrower"
                1 -> tab.text = "Loaner"
                2 -> tab.text = "Finish"
            }
        }.attach()
    }

}