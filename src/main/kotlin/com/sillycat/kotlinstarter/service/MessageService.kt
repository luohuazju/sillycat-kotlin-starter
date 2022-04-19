package com.sillycat.kotlinstarter.service

import com.sillycat.kotlinstarter.dao.MessageRepository
import com.sillycat.kotlinstarter.model.Message
import org.springframework.stereotype.Service

@Service
class MessageService(val db: MessageRepository) {

    fun findMessages(): List<Message> = db.findMessages()

    fun post(message: Message) {
        db.save(message)
    }
}