package com.jonasrosendo.desafiousemobile.presentation.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.jonasrosendo.desafiousemobile.presentation.db.mappers.UserEntity

@Database(entities = [UserEntity::class], version = 1, exportSchema = false)
abstract class UserDatabase : RoomDatabase() {

    abstract fun userDao(): UserDao

    companion object {

        @Volatile
        private var instance: UserDatabase? = null

        fun getDatabase(context: Context): UserDatabase {
            if (instance != null) {
                return instance as UserDatabase
            }

            synchronized(this) {
                instance = Room
                    .databaseBuilder(
                        context.applicationContext,
                        UserDatabase::class.java,
                        "use_mobile.db"
                    ).build()

                return instance as UserDatabase
            }
        }
    }
}