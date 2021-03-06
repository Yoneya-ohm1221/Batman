package com.example.aloanmini.ui.fragment.homePager

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.aloanmini.R
import com.example.aloanmini.databinding.FragmentMyDueBinding
import com.google.firebase.database.FirebaseDatabase


class MyDueFragment : Fragment() {
    lateinit var binding: FragmentMyDueBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentMyDueBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.floatingActionButton.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_loanFragment)
        }

        binding.text.setOnClickListener {
            val database = FirebaseDatabase.getInstance()
            val myRef = database.getReference("message")

            myRef.setValue("Hello, World!")
        }

    }

}