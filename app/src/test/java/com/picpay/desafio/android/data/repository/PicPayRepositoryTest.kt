package com.picpay.desafio.android.data.repository

import com.picpay.desafio.android.data.datasource.local.PicPayLocalDataSource
import com.picpay.desafio.android.data.datasource.remote.PicPayRemoteDataSource
import com.picpay.desafio.android.data.util.CacheUtils
import io.mockk.*
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test

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

    private fun setupRepository() = PicPayRepositoryImpl(
        remoteDataSource = remoteDataSource,
        localDataSource = localDataSource,
        dispatcher = testDispatcher
    )

    //region Tests
    @Test
    fun `getUsers in RemoteDataSource should be called when getAllUsers in Repository when called`() =
        runTest(testDispatcher) {
            // give (prepare)
            val repository = setupRepository()

            // when
            mockkObject(CacheUtils)
            every { CacheUtils.shouldGetDataInCache() } returns false
            repository.getUsers()

            // then
            coVerify { remoteDataSource.getUsers() }
        }

    //endregion
}