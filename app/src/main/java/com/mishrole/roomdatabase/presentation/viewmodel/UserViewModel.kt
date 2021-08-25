package com.mishrole.roomdatabase.presentation.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.mishrole.roomdatabase.data.database.UserDatabase
import com.mishrole.roomdatabase.data.entity.User
import com.mishrole.roomdatabase.data.repository.UserRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

// Provide data to the UI and survive configuration changes
// Acts as a communication center between the Repository and the UI
class UserViewModel(application: Application): AndroidViewModel(application) {
    // An AndroidViewModel is different from a regular ViewModel, it contains application reference

    val readAllData: LiveData<List<User>>
    private val repository: UserRepository

    // Executed at first when our ViewModel is called
    init {
        // Get database with our application as context
        val userDao = UserDatabase.getDatabase(application).userDao()
        // Initialize repository with our userDao
        repository = UserRepository(userDao)
        // Call our data from repository
        readAllData = repository.readAllData
    }

    fun addUser(user: User) {
        // viewModelScope is a part of Kotlin coroutines
        // Dispatchers.IO to run our code in the worker (background) thread
        // Coroutine is a powerful functionality to avoid to launch database jobs from the main thread ('cause it's a bad practice)
        viewModelScope.launch(Dispatchers.IO) {
            repository.addUser(user)
        }
    }

    fun updateUser(user: User) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.updateUser(user)
        }
    }
}