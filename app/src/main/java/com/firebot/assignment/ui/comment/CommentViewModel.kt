package com.firebot.assignment.ui.comment

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.firebot.assignment.service.model.Comment
import com.firebot.assignment.service.model.CommentFetchResults
import com.firebot.assignment.service.repository.CommentRepository

class CommentViewModel(private val repository: CommentRepository) : ViewModel() {

    companion object {
        private const val VISIBLE_THRESHOLD = 5
    }


    private val queryLiveData = MutableLiveData<String>()
    private val repoResult: LiveData<CommentFetchResults> = Transformations.map(queryLiveData) {
        repository.getComments(it)
    }

    val repos: LiveData<List<Comment>> = Transformations.switchMap(repoResult) { it.data }
    val networkErrors: LiveData<String> = Transformations.switchMap(repoResult) { it.networkErrors }

    /**
     * Search a repository based on a query string.
     */
    fun fetchComments(id: String) {
        queryLiveData.postValue(id)
    }

}
