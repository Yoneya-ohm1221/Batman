package com.example.aloanmini.ui.fragment.chat

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.aloanmini.R
import com.example.aloanmini.databinding.FragmentChatBinding
import com.google.firebase.database.FirebaseDatabase


class ChatFragment : Fragment() {
    lateinit var binding: FragmentChatBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentChatBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.imageView.setOnClickListener {
            findNavController().navigate(R.id.action_chatFragment2_to_findUserFragment)
        }
    }

}