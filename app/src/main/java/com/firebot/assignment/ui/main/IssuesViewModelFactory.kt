
package com.example.android.codelabs.paging.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.firebot.assignment.service.repository.IssueRepository
import com.firebot.assignment.ui.main.IssuesViewModel

/**
 * Factory for ViewModels
 */
class IssuesViewModelFactory(private val repository: IssueRepository) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(IssuesViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return IssuesViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
