package com.firebot.assignment.service.repository

import androidx.lifecycle.MutableLiveData
import com.firebot.assignment.service.model.Comment
import com.firebot.assignment.service.model.CommentFetchResults

class CommentRepository(
    private val apiService: APIService
) {

    private var lastRequestedPage = 1

    private val networkErrors = MutableLiveData<String>()
    private val commentsData = MutableLiveData<List<Comment>>()

    // avoid triggering multiple requests in the same time
    private var isRequestInProgress = false

    fun getComments(id: String): CommentFetchResults {

        lastRequestedPage = 1
        requestAndSaveData(id)

        return CommentFetchResults(commentsData, networkErrors)

    }

    fun requestMore(id: String) {
        requestAndSaveData(id)
    }


    private fun requestAndSaveData(id: String) {
        if (isRequestInProgress) return

        isRequestInProgress = true
        getComments(id, apiService, { repos ->
            commentsData.postValue(repos)
            lastRequestedPage++
            isRequestInProgress = false

        }, { error ->
            networkErrors.postValue(error)
            isRequestInProgress = false
        })
    }

    companion object {
        private const val NETWORK_PAGE_SIZE = 50
    }
}
