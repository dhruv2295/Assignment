
package com.example.android.codelabs.paging.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.firebot.assignment.service.model.Project

/**
 * Room data access object for accessing the [Repo] table.
 */
@Dao
interface ProjectDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(posts: List<Project>)

    // Do a similar query as the search API:
    // Look for repos that contain the query string in the name or in the description
    // and order those results descending, by the number of stars and then by name
    @Query("SELECT * FROM projects WHERE (name LIKE :queryString) OR (description LIKE " +
            ":queryString) ORDER BY stars DESC, name ASC")
    fun projectsByName(queryString: String): LiveData<List<Project>>

    @Query("SELECT * FROM projects")
    fun allProjects(): LiveData<List<Project>>

}
