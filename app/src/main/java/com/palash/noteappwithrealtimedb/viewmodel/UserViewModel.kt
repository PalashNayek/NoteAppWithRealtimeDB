package com.palash.noteappwithrealtimedb.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.palash.noteappwithrealtimedb.datamodel.User
import com.palash.noteappwithrealtimedb.repository.UserRepository

class UserViewModel : ViewModel() {

    private val repository : UserRepository = UserRepository().getInstance()
    private val _allUsers = MutableLiveData<List<User>>()
    val allUsers : LiveData<List<User>> = _allUsers
    init {
        repository.loadUsers(_allUsers)
    }

}