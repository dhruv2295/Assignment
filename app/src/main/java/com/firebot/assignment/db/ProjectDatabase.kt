package com.example.android.codelabs.paging.db

import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import android.content.Context
import com.firebot.assignment.service.model.Project

/**
 * Database schema that holds the list of repos.
 */
@Database(
        entities = [Project::class],
        version = 1,
        exportSchema = false
)
abstract class ProjectDatabase : RoomDatabase() {

    abstract fun reposDao(): ProjectDao

    companion object {

        @Volatile
        private var INSTANCE: ProjectDatabase? = null

        fun getInstance(context: Context): ProjectDatabase =
                INSTANCE ?: synchronized(this) {
                    INSTANCE
                            ?: buildDatabase(context).also { INSTANCE = it }
                }

        private fun buildDatabase(context: Context) =
                Room.databaseBuilder(context.applicationContext,
                        ProjectDatabase::class.java, "Firebot.db")
                        .build()
    }
}
