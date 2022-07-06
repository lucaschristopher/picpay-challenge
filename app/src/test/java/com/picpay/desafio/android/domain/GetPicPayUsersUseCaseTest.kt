package com.picpay.desafio.android.domain

import android.app.Application
import com.picpay.desafio.android.BASE_URL
import com.picpay.desafio.android.configureDataModuleForTest
import com.picpay.desafio.android.configureDomainModuleForTest
import com.picpay.desafio.android.configureTestAppComponent
import com.picpay.desafio.android.domain.model.User
import com.picpay.desafio.android.domain.usecase.GetPicPayUsersUseCase
import io.mockk.MockKAnnotations
import io.mockk.impl.annotations.MockK
import junit.framework.Assert.assertFalse
import junit.framework.Assert.assertTrue
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.*
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.koin.android.ext.koin.androidContext
import org.koin.core.component.inject
import org.koin.core.context.loadKoinModules
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin
import org.koin.test.KoinTest
import kotlin.test.assertNotNull

@RunWith(JUnit4::class)
class GetPicPayUsersUseCaseTest : KoinTest {

    val useCase: GetPicPayUsersUseCase by inject()

    @MockK
    private lateinit var context: Application

    @Before
    fun setup() {
        MockKAnnotations.init(this)

        startKoin {
            androidContext(context)
            loadKoinModules(
                configureDataModuleForTest(BASE_URL) +
                        configureDomainModuleForTest()
            )
        }
    }

    @After
    fun tearDown() {
        stopKoin()
    }

    @Test
    fun `should returns not null result when connecting to repository`() {
        runBlocking {
            val result = useCase()
            print(result.first().size)

            assertFalse(result.first().isEmpty())
            assertTrue(result is Flow<List<User>>)
            assertNotNull(result)
        }
    }
}