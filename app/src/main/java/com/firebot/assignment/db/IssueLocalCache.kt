
package com.firebot.assignment.db

import android.util.Log
import androidx.lifecycle.LiveData
import com.example.android.codelabs.paging.db.IssueDao
import com.firebot.assignment.service.model.Issues
import java.util.concurrent.Executor

/**
 * Class that handles the DAO local data source. This ensures that methods are triggered on the
 * correct executor.
 */
class IssueLocalCache(
    private val issueDao: IssueDao,
    private val ioExecutor: Executor
) {

    /**
     * Insert a list of repos in the database, on a background thread.
     */
    fun insert(repos: List<Issues>, insertFinished: () -> Unit) {
        ioExecutor.execute {
            Log.d("LocalCache", "inserting ${repos.size} repos")
            issueDao.insert(repos)
            insertFinished()
        }
    }

    /**
     * Request a LiveData<List<Repo>> from the Dao, based on a repo name. If the name contains
     * multiple words separated by spaces, then we're emulating the GitHub API behavior and allow
     * any characters between the words.
     * @param name repository name
     */
    fun allIssuesByDate(): LiveData<List<Issues>> {
        return issueDao.allIssuesByDate()
    }


    fun allIssues(): LiveData<List<Issues>> {
        return issueDao.allIssues()
    }

    fun getTime(fetchingFinished: (Long) -> Unit)
    {
        ioExecutor.execute {
            fetchingFinished(issueDao.getTime())
        }
    }

    fun clearAllData()
    {
        ioExecutor.execute {
            Log.d("LocalCache", "deleting")
            issueDao.deleteAllData()
//            deletionFinished()
        }
    }
}
