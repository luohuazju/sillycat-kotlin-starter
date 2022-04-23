package com.sillycat.kotlinstarter.web

import com.sillycat.kotlinstarter.model.Message
import com.sillycat.kotlinstarter.service.MessageService
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/messages")
class MessageController(val service: MessageService) {

    @GetMapping
    fun index(): List<Message> = service.findMessages()

    @PostMapping
    fun post(@RequestBody message: Message) {
        service.post(message)
    }
}