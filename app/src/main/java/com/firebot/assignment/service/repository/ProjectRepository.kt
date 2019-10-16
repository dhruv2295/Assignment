package com.firebot.assignment.service.repository

import androidx.lifecycle.MutableLiveData
import com.firebot.assignment.db.ProjectLocalCache
import com.firebot.assignment.service.model.ProjectFetchResults

class ProjectRepository(
    private val apiService: APIService,
    private val cache: ProjectLocalCache

) {

    private var lastRequestedPage = 1

    private val networkErrors = MutableLiveData<String>()

    // avoid triggering multiple requests in the same time
    private var isRequestInProgress = false

    // ...
    fun getProjects(): ProjectFetchResults {
        // This isn't an optimal implementation. We'll fix it later.
        lastRequestedPage = 1
        requestAndSaveData()

        val data = cache.allProjects()


        return ProjectFetchResults(data, networkErrors)

    }

    fun requestMore() {
        requestAndSaveData()
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
