package com.sillycat.kotlinstarter.web

import com.sillycat.kotlinstarter.model.Customer
import com.sillycat.kotlinstarter.model.Message
import com.sillycat.kotlinstarter.service.CustomerService
import org.redisson.api.RLock
import org.redisson.api.RedissonClient
import org.springframework.web.bind.annotation.*
import java.util.concurrent.TimeUnit

@RestController
@RequestMapping("/customers")
class CustomerController(val customerService: CustomerService, val redissonClient: RedissonClient) {

    @GetMapping
    fun index(): Set<Customer> {
        return customerService.loadAllCustomers()
    }

    @PostMapping
    fun post(@RequestBody customer: Customer) {
        val lock: RLock = redissonClient.getLock("LOCK" + customer.id)
        if(lock.tryLock(100, 10, TimeUnit.SECONDS)) {
            try {
                customerService.createCustomer(customer)
            } finally {
                lock.unlock()
            }
        }
    }
}