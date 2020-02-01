package com.firebot.assignment.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.firebot.assignment.service.model.IssueFetchResults
import com.firebot.assignment.service.model.Issues
import com.firebot.assignment.service.repository.IssueRepository

class IssuesViewModel(private val repository: IssueRepository) : ViewModel() {

    companion object {
        private const val VISIBLE_THRESHOLD = 5
    }


    private val queryLiveData = MutableLiveData<String>()
    private val repoResult: LiveData<IssueFetchResults> = Transformations.map(queryLiveData) {
        repository.getIssues()
    }

    val repos: LiveData<List<Issues>> = Transformations.switchMap(repoResult) { it.data }
    val networkErrors: LiveData<String> = Transformations.switchMap(repoResult) { it.networkErrors }

    /**
     * Search a repository based on a query string.
     */
    fun fetchIssues() {
        queryLiveData.postValue("")
    }

    fun forceFetchIssues() {
        repository.requestMore()
    }

    fun fetchLocalIssues() : LiveData<List<Issues>>
    {
        return repository.getLocalIssues()
    }

    fun fetchIssuesByDate() : LiveData<List<Issues>>{
        return repository.getIssuesByDate()
    }



    fun listScrolled(visibleItemCount: Int, lastVisibleItemPosition: Int, totalItemCount: Int) {
        if (visibleItemCount + lastVisibleItemPosition + VISIBLE_THRESHOLD >= totalItemCount) {
            repository.requestMore()
        }
    }

}
