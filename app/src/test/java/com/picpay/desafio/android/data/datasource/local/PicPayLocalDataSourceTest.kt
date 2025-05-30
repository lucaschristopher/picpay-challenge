package com.picpay.desafio.android.data.datasource.local

import com.picpay.desafio.android.mock.userEntityList
import com.picpay.desafio.core.common.test.BaseUnitTest
import com.picpay.desafio.core.data.database.dao.UserDao
import io.mockk.Runs
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.just
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
import kotlin.test.assertEquals

class PicPayLocalDataSourceTest : BaseUnitTest() {

    private val dao = mockk<UserDao>()
    private lateinit var dataSource: PicPayLocalDataSource

    @Before
    fun setup() {
        dataSource = PicPayLocalDataSourceImpl(dao)
    }

    @Test
    fun `getUsers returns mapped list from DAO`() = runTest {
        // Given
        val users = userEntityList
        coEvery { dao.getAll() } returns users

        // When
        val result = dataSource.getUsers()

        // Then
        assertEquals(users, result)
        coVerify(exactly = 1) { dao.getAll() }
    }

    @Test
    fun `insertUsers calls DAO with mapped entities`() = runTest {
        // Given
        val users = userEntityList
        coEvery { dao.insertAll(any()) } just Runs

        // When
        dataSource.insertUsers(users)

        // Then
        coVerify { dao.insertAll(users) }
    }
}