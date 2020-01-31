
package com.example.android.codelabs.paging

import android.content.Context
import androidx.lifecycle.ViewModelProvider
import com.example.android.codelabs.paging.db.IssueDatabase
import com.example.android.codelabs.paging.ui.ViewModelFactory
import com.firebot.assignment.db.IssueLocalCache
import com.firebot.assignment.service.repository.APIService
import com.firebot.assignment.service.repository.IssueRepository
import java.util.concurrent.Executors

/**
 * Class that handles object creation.
 * Like this, objects can be passed as parameters in the constructors and then replaced for
 * testing, where needed.
 */
object Injection {

    /**
     * Creates an instance of [IssueLocalCache] based on the database DAO.
     */
    private fun provideCache(context: Context): IssueLocalCache {
        val database = IssueDatabase.getInstance(context)
        return IssueLocalCache(database.reposDao(), Executors.newSingleThreadExecutor())
    }

    /**
     * Creates an instance of [IssueRepository] based on the [APIService] and a
     * [IssueLocalCache]
     */
    private fun provideGithubRepository(context: Context): IssueRepository {
        return IssueRepository(APIService.create(), provideCache(context))
    }

    /**
     * Provides the [ViewModelProvider.Factory] that is then used to get a reference to
     * [ViewModel] objects.
     */
    fun provideViewModelFactory(context: Context): ViewModelProvider.Factory {
        return ViewModelFactory(provideGithubRepository(context))
    }
}
