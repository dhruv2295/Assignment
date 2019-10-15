
package com.firebot.assignment.db

import android.util.Log
import androidx.lifecycle.LiveData
import com.example.android.codelabs.paging.db.ProjectDao
import com.firebot.assignment.service.model.Project
import java.util.concurrent.Executor

/**
 * Class that handles the DAO local data source. This ensures that methods are triggered on the
 * correct executor.
 */
class ProjectLocalCache(
    private val projectDao: ProjectDao,
    private val ioExecutor: Executor
) {

    /**
     * Insert a list of repos in the database, on a background thread.
     */
    fun insert(repos: List<Project>, insertFinished: () -> Unit) {
        ioExecutor.execute {
            Log.d("LocalCache", "inserting ${repos.size} repos")
            projectDao.insert(repos)
            insertFinished()
        }
    }

    /**
     * Request a LiveData<List<Repo>> from the Dao, based on a repo name. If the name contains
     * multiple words separated by spaces, then we're emulating the GitHub API behavior and allow
     * any characters between the words.
     * @param name repository name
     */
    fun projectsByName(name: String): LiveData<List<Project>> {
        // appending '%' so we can allow other characters to be before and after the query string
        val query = "%${name.replace(' ', '%')}%"
        return projectDao.projectsByName(query)
    }

    fun allProjects(): LiveData<List<Project>> {
        return projectDao.allProjects()
    }
}
