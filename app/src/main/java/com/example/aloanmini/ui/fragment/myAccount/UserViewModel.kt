package com.example.aloanmini.ui.fragment.myAccount

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.aloanmini.ui.models.User
import com.example.aloanmini.ui.utill.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class UserViewModel : ViewModel() {
    private val userRepository = UserRepository()
    private val _user = MutableLiveData<Resource<User>>()
    val user: LiveData<Resource<User>?>
        get() = _user

    init {
        viewModelScope.launch(Dispatchers.IO) {
            _user.postValue(Resource.Loading())
            try {
                val res = userRepository.getUser()
                _user.postValue(Resource.Success(res))
            } catch (e: Exception) {

            }
        }
    }
}