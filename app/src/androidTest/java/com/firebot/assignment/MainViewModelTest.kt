package com.firebot.assignment

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.platform.app.InstrumentationRegistry
import com.example.android.codelabs.paging.db.IssueDatabase
import com.firebot.assignment.db.IssueLocalCache
import com.firebot.assignment.service.repository.APIService
import com.firebot.assignment.service.repository.IssueRepository
import com.firebot.assignment.ui.main.MainViewModel
import com.google.samples.apps.sunflower.utilities.getValue
import org.junit.*
import java.util.concurrent.Executors

class MainViewModelTest {

    private lateinit var appDatabase: IssueDatabase
    private lateinit var viewModel: MainViewModel

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Before
    fun setUp() {
        val context = InstrumentationRegistry.getInstrumentation().targetContext
        appDatabase = Room.inMemoryDatabaseBuilder(context, IssueDatabase::class.java).build()

        val projectRepo = IssueRepository(
            APIService.create(),
            IssueLocalCache(
                IssueDatabase.getInstance(context).reposDao(),
                Executors.newSingleThreadExecutor()
            )
        )

        viewModel = MainViewModel(projectRepo)
    }

    @After
    fun tearDown() {
        appDatabase.close()
    }

    @Test
    @Throws(InterruptedException::class)
    fun testDefaultValues() {
        Assert.assertTrue(getValue(viewModel.fetchLocalProjects()).isNotEmpty())
    }
}