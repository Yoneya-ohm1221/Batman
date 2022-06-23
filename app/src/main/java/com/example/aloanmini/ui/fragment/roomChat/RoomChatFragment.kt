package com.example.aloanmini.ui.fragment.roomChat

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.example.aloanmini.databinding.FragmentRoomChatBinding
import com.example.aloanmini.ui.models.Chats


class RoomChatFragment : Fragment(){
    lateinit var binding: FragmentRoomChatBinding
    private val args: RoomChatFragmentArgs by navArgs()
    private val viewModel: RoomChatViewModel by viewModels()
    lateinit var adapter: RoomChatAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentRoomChatBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val toUid = args.uid
        viewModel.getChats(toUid)
        adapter = RoomChatAdapter()
        binding.recyclerView2.adapter = adapter
        binding.txtdisplayname1.text = args.displayName

        binding.btnsend.setOnClickListener {
            val message = binding.editchat.text
            viewModel.addChat(toUid, message.toString())
            binding.editchat.setText("")
        }

        viewModel.chats.observe(viewLifecycleOwner) {
            adapter.submitList(it)
             binding.recyclerView2.scrollToPosition(it.size - 1);
        }

        binding.imageView2.setOnClickListener {
            requireActivity().onBackPressed()
        }
    }

}