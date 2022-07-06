package com.picpay.desafio.android.data.repository

import com.picpay.desafio.android.data.datasource.local.PicPayLocalDataSource
import com.picpay.desafio.android.data.datasource.remote.PicPayRemoteDataSource
import com.picpay.desafio.android.data.util.CacheUtils
import com.picpay.desafio.android.domain.util.DomainMapper
import com.picpay.desafio.android.util.Utils
import io.mockk.*
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
import kotlin.test.assertEquals

@ExperimentalCoroutinesApi
class PicPayRepositoryTest {

    //region Mockk Properties
    @MockK(relaxed = true)
    private lateinit var localDataSource: PicPayLocalDataSource

    @MockK(relaxed = true)
    private lateinit var remoteDataSource: PicPayRemoteDataSource

    private val testDispatcher = UnconfinedTestDispatcher()
    //endregion

    @Before
    fun setup() {
        MockKAnnotations.init(this)
    }

    private fun setupRepository(
        spyOnIt: Boolean = false
    ) = PicPayRepositoryImpl(
        remoteDataSource = remoteDataSource,
        localDataSource = localDataSource,
        dispatcher = testDispatcher
    ).let {
        if (spyOnIt) {
            spyk(it)
        } else {
            it
        }
    }

    //region Tests
    @Test
    fun `getUsers in RemoteDataSource should be called when getAllUsers in Repository when called`() =
        runTest(testDispatcher) {
            // give (prepare)
            val repository = setupRepository()
            val mockedList = Utils.generateListUserResponse()
            val localDataFlow = flowOf(mockedList)
            val domainFlow = flowOf(DomainMapper().mapList(mockedList))

            // when
            mockkObject(CacheUtils)
            every { CacheUtils.shouldGetDataInCache() } returns false
            coEvery { remoteDataSource.getUsers() } returns localDataFlow
            val response = repository.getUsers()

            // then
            assertEquals(domainFlow.first(), response.first())
        }

    @Test
    fun `getUsers in LocalDataSource should be called when getAllUsers in Repository when called`() =
        runTest(testDispatcher) {
            // give (prepare)
            val repository = setupRepository()
            val users = Utils.generateListUser()
            val usersFlow = flowOf(users)

            // when
            mockkObject(CacheUtils)
            every { CacheUtils.shouldGetDataInCache() } returns true
            coEvery { localDataSource.getUsers() } returns users
            val response = repository.getUsers()

            // then
            assertEquals(usersFlow.first(), response.first())
        }
    //endregion
}