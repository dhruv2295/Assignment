package com.firebot.assignment.service.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.firebot.assignment.db.ProjectLocalCache
import com.firebot.assignment.service.model.Project
import com.firebot.assignment.service.model.ProjectFetchResults

class ProjectRepository(
    private val apiService: APIService,
    private val cache: ProjectLocalCache

) {

    private var lastRequestedPage = 1
    private var TIME_TO_PURGE = 7200000

    private val networkErrors = MutableLiveData<String>()

    // avoid triggering multiple requests in the same time
    private var isRequestInProgress = false

    fun getProjects(): ProjectFetchResults {

        lastRequestedPage = 1
        requestAndSaveData()

        cache.getTime{
            Log.d("LocalCache", "getting time:"+it)
                    if(System.currentTimeMillis() - it > TIME_TO_PURGE)
                        cache.clearAllData()
        }

        return ProjectFetchResults(cache.allProjects(), networkErrors)

    }

    fun requestMore() {
        requestAndSaveData()
    }

    fun getLocalProjects(): LiveData<List<Project>> {
        return cache.allProjects()
    }

    fun getProjectsByName(): LiveData<List<Project>> {
        return cache.allProjectsByName()
    }

    fun getProjectsByStars(): LiveData<List<Project>> {
        return cache.allProjectsByStars()
    }

    private fun requestAndSaveData() {
        if (isRequestInProgress) return

        isRequestInProgress = true
        getProjects(apiService, { repos ->
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
