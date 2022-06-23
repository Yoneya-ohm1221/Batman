package com.example.aloanmini.ui.fragment.roomChat

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.aloanmini.databinding.CardChatReceiverBinding
import com.example.aloanmini.databinding.CardChatSenderBinding
import com.example.aloanmini.ui.models.Chats
import com.example.aloanmini.ui.utill.ChatEnum
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser

class RoomChatAdapter : ListAdapter<Chats, RecyclerView.ViewHolder>(ChatDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            ChatEnum.RECEIVER.type -> ReceiverViewHolder(CardChatReceiverBinding.inflate(LayoutInflater.from(parent.context)))
            ChatEnum.SENDER.type -> SenderViewHolder(CardChatSenderBinding.inflate(LayoutInflater.from(parent.context)))
            else -> throw NullPointerException("View holder for type $viewType not found")
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

        when (getItemViewType(position)) {
            ChatEnum.SENDER.type -> {
                val data = getItem(position)
                (holder as SenderViewHolder)
                holder.binding.txtsender.text = data.message
                holder.binding.txtdate.text = data.dateTime
            }
            ChatEnum.RECEIVER.type -> {
                val data = getItem(position)
                (holder as ReceiverViewHolder)
                holder.binding.txtreceiver.text = data.message
                holder.binding.txtdate.text = data.dateTime
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        val user: FirebaseUser = FirebaseAuth.getInstance().currentUser!!
        return when (getItem(position).fromUid) {
            user.uid -> ChatEnum.SENDER.type
            else -> ChatEnum.RECEIVER.type
        }
    }

    override fun onViewRecycled(holder: RecyclerView.ViewHolder) {
        super.onViewRecycled(holder)
    }

    class ReceiverViewHolder(val binding: CardChatReceiverBinding) : RecyclerView.ViewHolder(binding.root)

    class SenderViewHolder(val binding: CardChatSenderBinding) : RecyclerView.ViewHolder(binding.root)
}