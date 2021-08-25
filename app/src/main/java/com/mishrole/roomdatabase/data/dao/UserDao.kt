package com.mishrole.roomdatabase.data.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.mishrole.roomdatabase.data.entity.User

// Contains the methods used for accessing the database
@Dao
interface UserDao {
    // onConflictStrategy.IGNORE -> Ignore conflicts as: if the user exists
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addUser(user: User)

    @Update
    suspend fun updateUser(user: User)

    @Query("SELECT * FROM user_table ORDER BY id ASC")
    fun readAllData(): LiveData<List<User>>

}