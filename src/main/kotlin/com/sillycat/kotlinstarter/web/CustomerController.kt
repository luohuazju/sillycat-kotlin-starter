package com.sillycat.kotlinstarter.web

import com.google.common.util.concurrent.Striped
import com.sillycat.kotlinstarter.model.Customer
import com.sillycat.kotlinstarter.model.Message
import com.sillycat.kotlinstarter.service.CustomerService
import org.redisson.api.RLock
import org.redisson.api.RedissonClient
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.web.bind.annotation.*
import java.util.concurrent.ConcurrentHashMap
import java.util.concurrent.TimeUnit
import java.util.concurrent.locks.ReentrantLock
import java.util.concurrent.locks.Lock

@RestController
@RequestMapping("/customers")
class CustomerController(val customerService: CustomerService, val redissonClient: RedissonClient) {

    private val jdkLock: Lock = ReentrantLock()
    private val concurrentLocks: ConcurrentHashMap<String, Lock> = ConcurrentHashMap()
    private val stripedLocks: Striped<Lock> = Striped.lock(20)

    @GetMapping
    fun index(): Set<Customer> {
        return customerService.loadAllCustomers()
    }

    @PostMapping("/jdklock")
    fun postWithJDKLock(@RequestBody customer: Customer) {
        if (jdkLock.tryLock(100, TimeUnit.SECONDS)){
            try {
                customerService.createCustomer(customer)
            }finally {
                jdkLock.unlock()
            }
        }
    }

    @PostMapping("/concurrentlock")
    fun postWithConcurrentLock(@RequestBody customer: Customer) {
        val key = "LOCK" + customer.id
        val concurrentLock: Lock = concurrentLocks.computeIfAbsent(key) { _ -> ReentrantLock() }
        if (concurrentLock.tryLock(100, TimeUnit.SECONDS)) {
            try {
                customerService.createCustomer(customer)
            }finally {
                concurrentLock.unlock()
            }
        }
    }

    @PostMapping("/guavalock")
    fun postWithGuavaLock(@RequestBody customer: Customer) {
        val key = "LOCK" + customer.id
        val guavaLock: Lock = stripedLocks.get(key)
        if (guavaLock.tryLock(100, TimeUnit.SECONDS)) {
            try {
                customerService.createCustomer(customer)
            } finally {
                guavaLock.unlock()
            }
        }
    }


    @PostMapping("/redislock")
    fun postWithRedisLock(@RequestBody customer: Customer) {
        val redisLock: RLock = redissonClient.getLock("LOCK" + customer.id)
        if(redisLock.tryLock(100, 10, TimeUnit.SECONDS)) {
            try {
                customerService.createCustomer(customer)
            } finally {
                redisLock.unlock()
            }
        }
    }

    @PostMapping("/free")
    fun postWithoutLock(@RequestBody customer: Customer) {
        customerService.createCustomer(customer)
    }
}