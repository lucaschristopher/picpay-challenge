package com.picpay.desafio.android.data.util

import kotlinx.datetime.Clock

class CacheUtils {

    companion object {
        private var lastRequisition: Long? = null

        /**
         * Indicates in-memory data cache timeout until next request
         */
        private const val CACHE_SECONDS_TIME_LIMIT = 5

        private fun getCurrentHour() = Clock.System.now().epochSeconds

        /**
         * Tells if data should be retrieved from cache
         */
        fun shouldGetDataInCache(): Boolean {
            val value = if (lastRequisition == null) {
                false
            } else {
                (getCurrentHour() - lastRequisition!!) < CACHE_SECONDS_TIME_LIMIT
            }

            lastRequisition = getCurrentHour()

            return value
        }
    }
}