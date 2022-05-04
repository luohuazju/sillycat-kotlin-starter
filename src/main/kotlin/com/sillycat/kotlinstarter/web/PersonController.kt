package com.sillycat.kotlinstarter.web

import com.sillycat.kotlinstarter.model.Message
import com.sillycat.kotlinstarter.model.Person
import com.sillycat.kotlinstarter.service.PersonService
import org.redisson.api.RLock
import org.redisson.api.RedissonClient
import org.springframework.web.bind.annotation.*
import java.util.concurrent.TimeUnit

@RestController
@RequestMapping("/persons")
class PersonController(val personService: PersonService, val redissonClient: RedissonClient) {

    @PostMapping
    fun post(@RequestBody person: Person) {
        val lock: RLock = redissonClient.getLock("LOCK" + person.id)
        if(lock.tryLock(100, 10, TimeUnit.SECONDS)) {
            try {
                personService.savePerson(person)
            } finally {
                lock.unlock()
            }
        }
    }

}