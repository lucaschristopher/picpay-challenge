package com.picpay.desafio.android

import org.junit.Test

import org.junit.Assert.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Test
    fun addition_isCorrect() {
        assertEquals(4, 2 + 2)
    }
}
/*
* package com.picpay.invoice.data.repository

import com.picpay.android.koffeetools.dispatcher.dispatcherWithMocks
import com.picpay.android.koffeetools.dispatcher.mock
import com.picpay.android.koffeetools.dispatcher.response
import com.picpay.android.koffeetools.dispatcher.with

private const val GET_MONTH_LIST_SUCCESS_RESPONSE = "get_month_list_success_response.json"
private const val GET_INVOICE_BY_ID_SUCCESS_RESPONSE = "get_invoice_by_id_success_response.json"
private const val GET_INVOICE_SUMMARY_BY_ID_SUCCESS_RESPONSE = "get_invoice_summary_by_id_success_response.json"
private const val INVOICE_MONTHS_API_ENDPOINT = "/credit/v2/invoices"
private const val INVOICE_BY_ID_API_ENDPOINT = "/credit/invoice/info/62a0f00a6a7e9f2b9271ab0e"
private const val INVOICE_SUMMARY_BY_ID_API_ENDPOINT = "/credit/invoice/summary/601beb0cbac6d34124fcad65"

val getMonthListSuccessResponse = dispatcherWithMocks {
    mock { INVOICE_MONTHS_API_ENDPOINT with response(GET_MONTH_LIST_SUCCESS_RESPONSE) }
}

val getInvoicesByIdSuccessResponse = dispatcherWithMocks {
    mock { INVOICE_BY_ID_API_ENDPOINT with response(GET_INVOICE_BY_ID_SUCCESS_RESPONSE) }
}

val getInvoicesSummaryByIdSuccessResponse = dispatcherWithMocks {
    mock { INVOICE_SUMMARY_BY_ID_API_ENDPOINT with response(GET_INVOICE_SUMMARY_BY_ID_SUCCESS_RESPONSE) }
}
*
* */

/*
package com.picpay.android.koffeetools.dispatcher

import com.picpay.android.koffeetools.dispatcher.matcher.HeaderMatcher
import com.picpay.android.koffeetools.dispatcher.matcher.HttpMethod
import com.picpay.android.koffeetools.dispatcher.matcher.HttpMethodMatcher
import com.picpay.android.koffeetools.dispatcher.matcher.RequestFieldMatcher
import com.picpay.android.koffeetools.dispatcher.matcher.RequestMatcher
import com.picpay.android.koffeetools.dispatcher.mockresponse.DefaultResponseBuilder
import com.picpay.android.koffeetools.dispatcher.mockresponse.ResponseBuilder
import com.picpay.android.koffeetools.dispatcher.mockresponse.StringBodyResponseBuilder
import okhttp3.mockwebserver.MockWebServer
import java.net.HttpURLConnection

// Dispatcher
fun dispatcherWithMocks(
    defaultResponse: ResponseBuilder = DefaultResponseBuilder(responseCode = HTTP_ERROR_CODE),
    inOrder: Boolean = false,
    mocks: MutableList<MockRequest>.() -> Unit
): MockDispatcher {
    val requests = mutableListOf<MockRequest>().apply(mocks)
    return MockDispatcher(requests, defaultResponse, inOrder)
}

infix fun MockDispatcher.startOn(mockWebServer: MockWebServer) {
    startOn(mockWebServer)
}

// Request
infix fun MutableList<MockRequest>.mock(request: () -> MockRequest) {
    add(request.invoke())
}

infix fun String.with(response: ResponseBuilder): MockRequest {
    val matcher = RequestMatcher(this)
    return MockRequest(matcher, response)
}

infix fun RequestMatcher.with(response: ResponseBuilder): MockRequest {
    return MockRequest(this, response)
}

// Matcher
fun request(path: String, block: (RequestMatcher.() -> Unit)? = null): RequestMatcher {
    val matcher = RequestMatcher(path)
    block?.let {
        matcher.apply(it)
    }

    return matcher
}

fun RequestMatcher.withHttpMethod(method: HttpMethod) {
    addMatcher(HttpMethodMatcher(method))
}

fun RequestMatcher.withHeader(key: String, value: String) {
    val header = Pair(key, value)
    addMatcher(HeaderMatcher(header))
}

fun RequestMatcher.withMatcher(block: () -> RequestFieldMatcher) {
    addMatcher(block())
}

// Response
typealias response = DefaultResponseBuilder

fun responseFromBody(
    responseBody: String,
    responseCode: Int = HttpURLConnection.HTTP_OK
): ResponseBuilder {
    return StringBodyResponseBuilder(responseBody, responseCode)
}
* */

/*
* package com.picpay.invoice.data.repository

import androidx.test.filters.MediumTest
import app.cash.turbine.test
import com.picpay.android.koffeetools.dispatcher.startOn
import com.picpay.android.koffeetools.localtestrule.LocalRxRule
import com.picpay.data.remote.mockprovider.retrofit.createRetrofit
import com.picpay.invoice.data.api.InvoiceService
import com.picpay.invoice.data.datasource.InvoiceRemoteDataSourceImpl
import com.picpay.invoice.data.repository.InvoiceStub.getInvoice
import com.picpay.invoice.data.repository.InvoiceStub.getInvoiceID
import com.picpay.invoice.data.repository.InvoiceStub.getInvoiceSummary
import com.picpay.invoice.domain.repository.InvoiceRepository
import kotlinx.coroutines.runBlocking
import okhttp3.OkHttpClient
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Rule
import org.junit.Test
import retrofit2.Retrofit
import java.util.concurrent.TimeUnit
import kotlin.test.assertEquals
import kotlin.time.ExperimentalTime

private const val ONE_MILLI_TIMEOUT = 1L

@MediumTest
@ExperimentalTime
internal class InvoiceRepositoryImplIntegrationTest {
    private val mockWebServer = MockWebServer()
    private val baseUrl = mockWebServer.url("/").toString()
    private val retrofit = createRetrofit(baseUrl)
    private val repository = createRepository(retrofit)

    @get:Rule
    val localRxRule = LocalRxRule()

    @After
    fun tearDown() {
        mockWebServer.shutdown()
    }

    @Test
    fun `getInvoiceMonthList Should return correct content When service returns with success`() = runBlocking {
        // Given
        val expectedResult = InvoiceStub.getInvoiceMonthList()
        getMonthListSuccessResponse.startOn(mockWebServer)

        // When
        val result = repository.getInvoiceMonthList()

        // Then
        result.test {
            assertEquals(expectedResult, expectItem())
            expectComplete()
        }
    }

    @Test
    fun `getInvoiceMonthList should return a exception`() = runBlocking {
        // Given
        val customRetrofit = createRetrofit(baseUrl, setupTimeout())
        val repository = createRepository(customRetrofit)
        getMonthListSuccessResponse.startOn(mockWebServer)

        // When
        val response = repository.getInvoiceMonthList()

        // Then
        response.test {
            expectError()
        }
    }

    @Test
    fun `getInvoiceById Should return correct content When service returns with success`() = runBlocking {
        // Given
        val expectedResult = getInvoice()
        getInvoicesByIdSuccessResponse.startOn(mockWebServer)

        // When
        val result = repository.getInvoiceById("62a0f00a6a7e9f2b9271ab0e")

        // Then
        result.test {
            assertEquals(expectedResult, expectItem())
            expectComplete()
        }
    }

    @Test
    fun `getInvoiceById should return CardNoConnectionException`() = runBlocking {
        // Given
        val customRetrofit = createRetrofit(baseUrl, setupTimeout())
        val repository = createRepository(customRetrofit)
        val invoiceId = getInvoice().id
        getMonthListSuccessResponse.startOn(mockWebServer)

        // When
        val response = repository.getInvoiceById(invoiceId)

        // Then
        response.test {
            expectError()
        }
    }

    @Test
    fun `getInvoiceSummaryById Should return correct content When service returns with success`() = runBlocking {
        // Given
        val expectedResult = getInvoiceSummary()
        getInvoicesSummaryByIdSuccessResponse.startOn(mockWebServer)

        // When
        val result = repository.getInvoiceSummaryById(getInvoiceID())

        // Then
        result.test {
            assertEquals(expectedResult, expectItem())
            expectComplete()
        }
    }

    @Test
    fun `getInvoiceSummaryById should return CardNoConnectionException`() = runBlocking {
        // Given
        val customRetrofit = createRetrofit(baseUrl, setupTimeout())
        val repository = createRepository(customRetrofit)
        getInvoicesSummaryByIdSuccessResponse.startOn(mockWebServer)

        // When
        val response = repository.getInvoiceSummaryById(getInvoiceID())

        // Then
        response.test {
            expectError()
        }
    }

    private fun setupTimeout(): OkHttpClient {
        return OkHttpClient.Builder()
            .connectTimeout(ONE_MILLI_TIMEOUT, TimeUnit.MILLISECONDS)
            .readTimeout(ONE_MILLI_TIMEOUT, TimeUnit.MILLISECONDS)
            .build()
    }

    private fun createRepository(retrofit: Retrofit): InvoiceRepository {
        val service = retrofit.create(InvoiceService::class.java)
        val dataSource = InvoiceRemoteDataSourceImpl(service)
        return InvoiceRepositoryImpl(dataSource)
    }
}
*
* */