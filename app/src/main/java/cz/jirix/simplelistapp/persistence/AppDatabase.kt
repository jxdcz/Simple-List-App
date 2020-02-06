package cz.jirix.simplelistapp.persistence

import androidx.room.Database
import androidx.room.RoomDatabase
import cz.jirix.simplelistapp.model.User

@Database(entities = [User::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao

}
