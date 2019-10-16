
package com.example.android.codelabs.paging

import android.content.Context
import androidx.lifecycle.ViewModelProvider
import com.example.android.codelabs.paging.db.ProjectDatabase
import com.example.android.codelabs.paging.ui.ViewModelFactory
import com.firebot.assignment.db.ProjectLocalCache
import com.firebot.assignment.service.repository.APIService
import com.firebot.assignment.service.repository.ProjectRepository
import java.util.concurrent.Executors

/**
 * Class that handles object creation.
 * Like this, objects can be passed as parameters in the constructors and then replaced for
 * testing, where needed.
 */
object Injection {

    /**
     * Creates an instance of [ProjectLocalCache] based on the database DAO.
     */
    private fun provideCache(context: Context): ProjectLocalCache {
        val database = ProjectDatabase.getInstance(context)
        return ProjectLocalCache(database.reposDao(), Executors.newSingleThreadExecutor())
    }

    /**
     * Creates an instance of [ProjectRepository] based on the [APIService] and a
     * [ProjectLocalCache]
     */
    private fun provideGithubRepository(context: Context): ProjectRepository {
        return ProjectRepository(APIService.create(), provideCache(context))
    }

    /**
     * Provides the [ViewModelProvider.Factory] that is then used to get a reference to
     * [ViewModel] objects.
     */
    fun provideViewModelFactory(context: Context): ViewModelProvider.Factory {
        return ViewModelFactory(provideGithubRepository(context))
    }
}
