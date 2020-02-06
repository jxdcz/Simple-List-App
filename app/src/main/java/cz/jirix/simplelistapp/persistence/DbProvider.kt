package cz.jirix.simplelistapp.persistence

import android.content.Context
import androidx.room.Room

class DbProvider private constructor() {

    companion object {
        fun init(appContext: Context) {
            INSTANCE = Room.databaseBuilder(
                appContext,
                AppDatabase::class.java,
                "users_db"
            ).build()
        }

        lateinit var INSTANCE: AppDatabase
    }
}