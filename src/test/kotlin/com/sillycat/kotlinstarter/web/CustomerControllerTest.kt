package com.sillycat.kotlinstarter.web

import com.sillycat.kotlinstarter.service.CustomerService
import com.sillycat.kotlinstarter.service.LockService
import io.mockk.every
import io.mockk.impl.annotations.MockK
import com.ninjasquad.springmockk.MockkBean
import io.mockk.mockk
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.test.context.junit.jupiter.SpringExtension
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status
import org.springframework.test.web.servlet.result.MockMvcResultHandlers.print
import io.mockk.verify
import org.junit.jupiter.api.Assertions.assertEquals
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.TestConfiguration
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.context.annotation.Bean
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc

@ExtendWith(SpringExtension::class)
@WebMvcTest(CustomerController::class)
internal class CustomerControllerTest {

    @MockkBean
    private lateinit var customerService: CustomerService

    @MockkBean
    private lateinit var lockService: LockService

    @Autowired
    private lateinit var controller: CustomerController

    @Test
    fun loadAll() {
        every { customerService.loadAllCustomers() } returns mockk()
        val response = controller.index()
        verify(atLeast = 1) { customerService.loadAllCustomers() }
        assertEquals(true, response != null)
    }

}