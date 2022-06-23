package com.example.aloanmini.ui.fragment.roomChat

import androidx.recyclerview.widget.DiffUtil
import com.example.aloanmini.ui.models.Chats

class ChatDiffCallback() : DiffUtil.ItemCallback<Chats>() {
    override fun areItemsTheSame(oldItem: Chats, newItem: Chats): Boolean {
        return false
    }

    override fun areContentsTheSame(oldItem: Chats, newItem: Chats): Boolean {
        return false
    }
}