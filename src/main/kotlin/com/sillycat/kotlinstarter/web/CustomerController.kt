package com.sillycat.kotlinstarter.web

import com.sillycat.kotlinstarter.model.Customer
import com.sillycat.kotlinstarter.model.Message
import com.sillycat.kotlinstarter.service.CustomerService
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/customers")
class CustomerController(val customerService: CustomerService) {

    @GetMapping
    fun index(): Set<Customer> {
        return customerService.loadAllCustomers()
    }

    @PostMapping
    fun post(@RequestBody customer: Customer) {
        customerService.createCustomer(customer)
    }
}