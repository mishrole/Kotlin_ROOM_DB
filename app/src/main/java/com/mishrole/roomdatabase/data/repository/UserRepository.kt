package com.mishrole.roomdatabase.data.repository

import androidx.lifecycle.LiveData
import com.mishrole.roomdatabase.data.dao.UserDao
import com.mishrole.roomdatabase.data.entity.User

// Abstracts access to multiple data source
// It's not part of the Architecture Components libraries, but is a suggested best practice for code separation and architecture
class UserRepository(private val userDao: UserDao) {
    val readAllData: LiveData<List<User>> = userDao.readAllData()

    suspend fun addUser(user: User) {
        userDao.addUser(user)
    }


}