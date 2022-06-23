package com.example.aloanmini.ui.fragment.findUser

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.aloanmini.R
import com.example.aloanmini.databinding.CardUsersBinding
import com.example.aloanmini.ui.models.Users

class FindUserAdapter : ListAdapter<Users, FindUserAdapter.ViewHolder>(DiffCallback()) {

    class ViewHolder(val binding: CardUsersBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(CardUsersBinding.inflate(LayoutInflater.from(parent.context)))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val data = getItem(position)
        holder.binding.txtdispalyname.text = data.name
        holder.binding.txtdisplayemail.text = data.email

        holder.itemView.setOnClickListener{
            it.findNavController().navigate(FindUserFragmentDirections.actionFindUserFragmentToRoomChatFragment(data.uid,data.name))
        }
    }

    class DiffCallback : DiffUtil.ItemCallback<Users>() {
        override fun areItemsTheSame(oldItem: Users, newItem: Users): Boolean {
            return oldItem.uid == newItem.uid
        }

        override fun areContentsTheSame(oldItem: Users, newItem: Users): Boolean {
            return oldItem == newItem
        }
    }
}