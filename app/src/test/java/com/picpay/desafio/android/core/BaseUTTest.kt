package com.picpay.desafio.android.core

import dev.thiagosouto.butler.file.readFile
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Before
import org.koin.core.context.stopKoin
import org.koin.test.KoinTest
import java.io.File

abstract class BaseUTTest : KoinTest {

    /**
     * For MockWebServer instance
     */
    private lateinit var mMockServerInstance: MockWebServer

    @Before
    open fun setUp() {
        startMockServer()
    }

    /**
     * Helps to read input file returns the respective data in mocked call
     */
    fun mockNetworkResponseWithFileContent(fileName: String, responseCode: Int) =
        mMockServerInstance.enqueue(
            MockResponse()
                .setResponseCode(responseCode)
                .setBody(readFile(fileName))
        )

    /**
     * Reads input file and converts to json
     */
    fun getJson(path: String): String {
        val uri = javaClass.classLoader!!.getResource(path)
        val file = File(uri.path)
        return String(file.readBytes())
    }

    /**
     * Start Mockwebserver
     */
    private fun startMockServer() {
        mMockServerInstance = MockWebServer()
        mMockServerInstance.start()
    }

    /**
     * Set Mockwebserver url
     */
    fun getMockWebServerUrl() = mMockServerInstance.url("/").toString()

    /**
     * Stop Mockwebserver
     */
    private fun stopMockServer() {
        mMockServerInstance.shutdown()
    }

    @After
    open fun tearDown() {
        stopMockServer()
        stopKoin()
    }
}