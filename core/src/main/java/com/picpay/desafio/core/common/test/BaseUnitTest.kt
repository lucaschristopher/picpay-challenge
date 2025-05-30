package com.picpay.desafio.core.common.test

import io.mockk.MockKAnnotations
import io.mockk.clearAllMocks
import io.mockk.unmockkAll
import org.junit.After
import org.junit.Before

abstract class BaseUnitTest {

    @Before
    fun onBeforeTest() {
        MockKAnnotations.init()
    }

    @After
    fun onAfterTest() {
        unmockkAll()
        clearAllMocks()
    }
}