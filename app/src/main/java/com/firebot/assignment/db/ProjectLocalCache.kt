
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
    fun allProjectsByName(): LiveData<List<Project>> {
        return projectDao.allProjectsByName()
    }

    fun allProjectsByStars(): LiveData<List<Project>> {
        return projectDao.projectsByStars()
    }

    fun allProjects(): LiveData<List<Project>> {
        return projectDao.allProjects()
    }

    fun getTime(fetchingFinished: (Long) -> Unit)
    {
        ioExecutor.execute {
            fetchingFinished(projectDao.getTime())
        }
    }

    fun clearAllData()
    {
        ioExecutor.execute {
            Log.d("LocalCache", "deleting")
            projectDao.deleteAllData()
//            deletionFinished()
        }
    }
}
