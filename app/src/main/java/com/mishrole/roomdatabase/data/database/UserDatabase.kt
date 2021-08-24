package com.mishrole.roomdatabase.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.mishrole.roomdatabase.data.dao.UserDao
import com.mishrole.roomdatabase.data.entity.User

// Contains the database holder and serves as the main access point for the underlying connection to you app's persisted, relational data
// ExportSchema true is a good practice, it allows us to keep version history of our schema in our codebase
// With Ctrl + Q we can view a modal with documentation
@Database(entities = [User::class], version = 1, exportSchema = false)
abstract class UserDatabase: RoomDatabase() {

    abstract fun userDao(): UserDao

    // Visible to other classes
    companion object {

        // Always use the same Instance for ROOM Database
        // Multiple instances === poor performance
        // Context Instance (Singleton)
        @Volatile
        private var INSTANCE: UserDatabase? = null

        fun getDatabase(context: Context): UserDatabase {
            val tempInstance = INSTANCE

            if(tempInstance !== null) {
                return tempInstance
            }

            // Create a new instance
            synchronized(this) {

                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    UserDatabase::class.java,
                    "user_database"
                ).build()

                INSTANCE = instance

                return instance
            }
        }



    }

}