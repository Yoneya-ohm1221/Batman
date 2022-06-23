package com.example.aloanmini.ui.fragment.roomChat

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.aloanmini.ui.models.Chats
import com.example.aloanmini.ui.models.Users
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class RoomChatViewModel :ViewModel() {

    private val repository = RoomChatRepository()
    private var _chats = MutableLiveData<List<Chats>>()
    val chats: LiveData<List<Chats>>
        get() = _chats

    fun addChat(toUid: String, chat: String){
        viewModelScope.launch(Dispatchers.IO) {
            repository.addChat(toUid,chat)
        }
    }

    fun getChats(toUid: String){
        viewModelScope.launch(Dispatchers.IO){
            repository.getData(_chats,toUid)
        }
    }
}