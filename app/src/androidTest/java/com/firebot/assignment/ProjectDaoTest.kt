package com.firebot.assignment

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.example.android.codelabs.paging.db.ProjectDao
import com.example.android.codelabs.paging.db.ProjectDatabase
import com.firebot.assignment.service.model.BuiltBy
import com.firebot.assignment.service.model.Project
import com.google.samples.apps.sunflower.utilities.getValue
import kotlinx.coroutines.runBlocking
import org.hamcrest.Matchers
import org.junit.*
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class ProjectDaoTest {
    private lateinit var database: ProjectDatabase
    private lateinit var projectDao: ProjectDao

    val creators = listOf(
        BuiltBy("avatar", "href", "username"),
        BuiltBy("avatar", "href", "username"),
        BuiltBy("avatar", "href", "username"))


    private val projectA = Project("a", "avatar", creators, 1,
        "description", 200,"name",10,"url","language",
        "#24242",false,0)
    private val projectB = Project("b", "avatar", creators, 1,
        "description", 200,"name",10,"url","language",
        "#24242",false,0)
    private val projectC = Project("c", "avatar", creators, 1,
        "description", 200,"name",10,"url","language",
        "#24242",false,0)

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Before
    fun createDb() = runBlocking {
        val context = InstrumentationRegistry.getInstrumentation().targetContext
        database = Room.inMemoryDatabaseBuilder(context, ProjectDatabase::class.java).build()
        projectDao = database.reposDao()

        // Insert projects in non-alphabetical order to test that results are sorted by name
        projectDao.insert(listOf(projectB, projectC, projectA))
    }

    @After
    fun closeDb() {
        database.close()
    }

    @Test
    fun testGetProjects() {
        val projectList = getValue(projectDao.allProjects())
        Assert.assertThat(projectList.size, Matchers.equalTo(3))
    }

    @Test
    fun testGetProjectsSortedByName() {
        val projectList = getValue(projectDao.allProjectsByName())

        // Ensure project list is sorted by name
        Assert.assertThat(projectList[0].author, Matchers.equalTo(projectA.author))
        Assert.assertThat(projectList[1].author, Matchers.equalTo(projectB.author))
        Assert.assertThat(projectList[2].author, Matchers.equalTo(projectC.author))
    }

    @Test
    fun testGetProjectsByName() {
        val projectList = getValue(projectDao.projectsByName("a"))
        Assert.assertThat(projectList.size, Matchers.equalTo(1))
        Assert.assertThat(
            getValue(projectDao.projectsByName("b")).size,
            Matchers.equalTo(1)
        )
        Assert.assertThat(
            getValue(projectDao.projectsByName("c")).size,
            Matchers.equalTo(1)
        )

    }

}