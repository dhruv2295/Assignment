package com.firebot.assignment.service.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.firebot.assignment.db.IssueLocalCache
import com.firebot.assignment.service.model.IssueFetchResults
import com.firebot.assignment.service.model.Issues

class IssueRepository(
    private val apiService: APIService,
    private val cache: IssueLocalCache

) {

    private var lastRequestedPage = 1
    private var TIME_TO_PURGE = 86400000 //24 hours

    private val networkErrors = MutableLiveData<String>()

    // avoid triggering multiple requests in the same time
    private var isRequestInProgress = false

    fun getIssues(): IssueFetchResults {

        lastRequestedPage = 1

        cache.getTime {
            Log.d("LocalCache", "getting time:" + it)
            if (System.currentTimeMillis() - it > TIME_TO_PURGE)
            {
                cache.clearAllData()
                requestAndSaveData()
            }

        }

        return IssueFetchResults(cache.allIssues(), networkErrors)

    }

    fun requestMore() {
        requestAndSaveData()
    }

    fun getLocalIssues(): LiveData<List<Issues>> {
        return cache.allIssues()
    }

    fun getIssuesByDate(): LiveData<List<Issues>> {
        return cache.allIssuesByDate()
    }


    private fun requestAndSaveData() {
        if (isRequestInProgress) return

        isRequestInProgress = true
        getIssues(apiService, { repos ->
            cache.insert(repos) {
                lastRequestedPage++
                isRequestInProgress = false
            }
        }, { error ->
            networkErrors.postValue(error)
            isRequestInProgress = false
        })
    }

    companion object {
        private const val NETWORK_PAGE_SIZE = 50
    }
}
