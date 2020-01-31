
package com.example.android.codelabs.paging.db

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.firebot.assignment.service.model.Issues

/**
 * Room data access object for accessing the [Repo] table.
 */
@Dao
interface IssueDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(posts: List<Issues>)


    @Query("SELECT * FROM issues ORDER BY date(createdAt) DESC")
    fun allIssues(): LiveData<List<Issues>>

    @Query("SELECT * FROM issues ORDER BY date(createdAt)")
    fun allIssuesByDate(): LiveData<List<Issues>>

    @Query("DELETE FROM issues")
    fun deleteAllData()

    @Query("SELECT MAX(timeAdded) FROM issues")
    fun getTime(): Long
}
