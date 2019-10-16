package com.firebot.assignment.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.firebot.assignment.service.model.Project
import com.firebot.assignment.service.model.ProjectFetchResults
import com.firebot.assignment.service.repository.ProjectRepository

class MainViewModel(private val repository: ProjectRepository) : ViewModel() {

    companion object {
        private const val VISIBLE_THRESHOLD = 5
    }


    private val queryLiveData = MutableLiveData<String>()
    private val repoResult: LiveData<ProjectFetchResults> = Transformations.map(queryLiveData) {
        repository.getProjects()
    }

    val repos: LiveData<List<Project>> = Transformations.switchMap(repoResult) { it.data }
    val networkErrors: LiveData<String> = Transformations.switchMap(repoResult) { it.networkErrors }

    /**
     * Search a repository based on a query string.
     */
    fun fetchProjects() {
        queryLiveData.postValue("");
    }

    fun listScrolled(visibleItemCount: Int, lastVisibleItemPosition: Int, totalItemCount: Int) {
        if (visibleItemCount + lastVisibleItemPosition + VISIBLE_THRESHOLD >= totalItemCount) {
            repository.requestMore()
        }
    }
}
