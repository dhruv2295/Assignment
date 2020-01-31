package com.example.android.codelabs.paging.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.firebot.assignment.service.model.Issues

/**
 * Database schema that holds the list of repos.
 */
@Database(
    entities = [Issues::class],
    version = 1,
    exportSchema = false
)
abstract class IssueDatabase : RoomDatabase() {

    abstract fun reposDao(): IssueDao

    companion object {

        @Volatile
        private var INSTANCE: IssueDatabase? = null

        fun getInstance(context: Context): IssueDatabase =
            INSTANCE ?: synchronized(this) {
                INSTANCE
                    ?: buildDatabase(context).also { INSTANCE = it }
            }

        private fun buildDatabase(context: Context) =
            Room.databaseBuilder(
                context.applicationContext,
                IssueDatabase::class.java, "Firebot.db"
            )
                .build()
    }
}
