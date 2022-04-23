package com.sillycat.kotlinstarter.web

import com.sillycat.kotlinstarter.model.Message
import com.sillycat.kotlinstarter.model.Person
import com.sillycat.kotlinstarter.service.PersonService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class PersonController(val personService: PersonService) {

    @PostMapping
    fun post(@RequestBody person: Person) {
        personService.savePerson(person)
    }

}