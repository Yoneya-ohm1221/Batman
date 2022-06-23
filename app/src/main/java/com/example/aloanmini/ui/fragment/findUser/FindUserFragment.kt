package com.example.aloanmini.ui.fragment.findUser

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels

import com.example.aloanmini.databinding.FragmentFindUserBinding
import com.example.aloanmini.ui.utill.Resource


class FindUserFragment : Fragment() {
    lateinit var binding: FragmentFindUserBinding
    private val viewModel: FindUserViewModel by viewModels()
    lateinit var adapter: FindUserAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentFindUserBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        adapter = FindUserAdapter()
        binding.recyclerView.adapter = adapter
        binding.back.setOnClickListener {
            requireActivity().onBackPressed()
        }
        setUi()
    }

    private fun setUi() {
        viewModel.loading.observe(viewLifecycleOwner){
            when(it) {
                is Resource.Loading ->{
                    binding.viewFlipper.displayedChild = 1
                }

                is Resource.Success ->{
                    binding.viewFlipper.displayedChild = 0
                }
            }

        }

        viewModel.users.observe(viewLifecycleOwner){
            adapter.submitList(it)
        }
    }


}