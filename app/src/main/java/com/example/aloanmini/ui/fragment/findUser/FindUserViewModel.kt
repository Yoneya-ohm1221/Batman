package com.example.aloanmini.ui.fragment.findUser

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.aloanmini.ui.models.Users
import com.example.aloanmini.ui.utill.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class FindUserViewModel() : ViewModel() {
    private val repository = FindUserRepository()

    private var _user = MutableLiveData<List<Users>>()
    val users: LiveData<List<Users>>
        get() = _user

    private var _loading = MutableLiveData<Resource<String>>()
    val loading : LiveData<Resource<String>>
        get() = _loading
    init {
        _loading.postValue(Resource.Loading())
        viewModelScope.launch(Dispatchers.IO) {
            repository.getData(_user)
            _loading.postValue(Resource.Success("success"))
        }
    }
}