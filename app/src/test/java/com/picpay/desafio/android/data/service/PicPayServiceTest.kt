package com.picpay.desafio.android.data.service

import com.google.gson.GsonBuilder
import com.picpay.desafio.android.data.service.PicPayService
import com.picpay.desafio.android.data.util.OkHttpClientFactory
import com.picpay.desafio.android.data.util.PicPayApiFactory
import com.picpay.desafio.android.data.util.RetrofitFactory
import dev.thiagosouto.butler.file.readFile
import junit.framework.Assert.assertEquals
import junit.framework.Assert.assertNotNull
import kotlinx.coroutines.runBlocking
import okhttp3.mockwebserver.Dispatcher
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import okhttp3.mockwebserver.RecordedRequest
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import retrofit2.HttpException
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

private const val json_response = "picpay_users_response_success.json"

@RunWith(JUnit4::class)
class PicPayServiceTest {

    private lateinit var mockWebServer: MockWebServer
    private lateinit var retrofit: Retrofit
    private lateinit var service: PicPayService

    @Before
    fun createService() {
        mockWebServer = MockWebServer()
        retrofit = createRetrofit()
        service = createServiceFactory()
    }

    @After
    fun stopService() {
        mockWebServer.shutdown()
    }

    // region Configurations
    private fun createRetrofit() = RetrofitFactory.build(
        url = mockWebServer.url("/").toString(),
        client = OkHttpClientFactory.build(),
        factory = createAdapterFactory(),
    )

    private fun createAdapterFactory() = GsonConverterFactory.create(GsonBuilder().create())

    private fun createServiceFactory() = PicPayApiFactory.build(
        retrofit = retrofit,
        PicPayService::class.java
    )

    private fun retrieveDispatcher() = object : Dispatcher() {
        override fun dispatch(request: RecordedRequest): MockResponse {
            return MockResponse()
                .setResponseCode(404)
                .setBody("[]")
                .throttleBody(1024, 3, TimeUnit.SECONDS)
        }
    }
    // endregion

    // region Tests
    @Test
    fun `should hit the expected endpoint`() = runBlocking {
        mockWebServer.enqueue(MockResponse().setBody("[]"))
        service.getUsers()
        val request = mockWebServer.takeRequest()
        assertEquals(request.path, "/users")
    }

    @Test
    fun `should hit the expected endpoint and bring a valid return`() {
        runBlocking {
            val response = readFile(json_response)
            mockWebServer.enqueue(MockResponse().setBody(response))
            val result = service.getUsers()
            val request = mockWebServer.takeRequest()
            assertEquals(request.path, "/users")
            assertNotNull(result)
        }
    }

    @Test
    fun `should return a http exception`() = runBlocking {
        try {
            mockWebServer.dispatcher = retrieveDispatcher()
            service.getUsers()
            val request = mockWebServer.takeRequest()
            assertEquals(request.failure, null)
        } catch (exception: Throwable) {
            assertEquals(exception.javaClass, HttpException::class.java)
        }
    }
    //endregion
}