package com.sillycat.kotlinstarter.service

import com.sillycat.kotlinstarter.dao.PersonNeo4jRepository
import com.sillycat.kotlinstarter.model.Person
import org.springframework.stereotype.Service

@Service
class PersonServiceImpl(
    private val personNeo4jRepository: PersonNeo4jRepository
) : PersonService {
    override fun savePerson(person: Person) {
        personNeo4jRepository.save(person)
    }

    override fun deletePerson(id: String) {
       personNeo4jRepository.deleteById(id)
    }

    override fun saveRelationship(sourceId: String, destId: String) {
        val source = personNeo4jRepository.findById(sourceId).get()
        val dest = personNeo4jRepository.findById(destId).get()
        source.familyMembers.add(dest)
        personNeo4jRepository.save(source)
    }


}