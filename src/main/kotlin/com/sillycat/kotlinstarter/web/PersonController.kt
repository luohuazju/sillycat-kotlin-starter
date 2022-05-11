package com.sillycat.kotlinstarter.web

import com.sillycat.kotlinstarter.model.Message
import com.sillycat.kotlinstarter.model.Person
import com.sillycat.kotlinstarter.service.PersonService
import org.springframework.web.bind.annotation.*
import java.util.concurrent.TimeUnit

@RestController
@RequestMapping("/persons")
class PersonController(val personService: PersonService) {

    @PostMapping
    fun post(@RequestBody person: Person) {
        personService.savePerson(person)
    }

}