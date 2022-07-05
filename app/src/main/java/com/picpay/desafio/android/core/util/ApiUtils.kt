package com.picpay.desafio.android.core.util

import com.picpay.desafio.android.core.exception.RemoteException
import com.picpay.desafio.android.core.mapper.Mapper
import retrofit2.Response

class ApiUtils {

    companion object {

        /**
         * Performs the treatment on top of the service call and maps the result to the expected entity.
         * @param apiCall: requested service call
         * @param mapper: data mapper class that inherits from Mapper<I, O>
         */
        suspend fun <I, O> safeApiCall(
            apiCall: suspend () -> Response<I>,
            mapper: Mapper<I, O>
        ): O? {
            try {
                val response = apiCall()
                if (response.isSuccessful) {
                    val body = response.body()
                    body?.let {
                        return mapper.mapList(it)
                    }
                }
                return null
            } catch (exception: Exception) {
                throw RemoteException(exception.message ?: Constants.ERROR_HTTP_MESSAGE)
            }
        }
    }
}