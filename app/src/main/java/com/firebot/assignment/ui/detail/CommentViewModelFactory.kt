
package com.example.android.codelabs.paging.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.firebot.assignment.service.repository.CommentRepository
import com.firebot.assignment.service.repository.IssueRepository
import com.firebot.assignment.ui.detail.CommentViewModel
import com.firebot.assignment.ui.main.MainViewModel

/**
 * Factory for ViewModels
 */
class CommentViewModelFactory(private val repository: CommentRepository) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(CommentViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return CommentViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
