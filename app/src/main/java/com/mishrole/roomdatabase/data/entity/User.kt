package com.mishrole.roomdatabase.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

// Represents a table within the database
@Entity(tableName = "user_table")
data class User (
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val firstname: String,
    val lastName: String,
    val age: Int
)