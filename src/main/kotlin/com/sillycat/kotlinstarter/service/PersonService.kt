package com.sillycat.kotlinstarter.service

import com.sillycat.kotlinstarter.model.Person

interface PersonService {

    fun savePerson(person: Person)

    fun deletePerson(id: String)

    fun saveRelationship(sourceId: String, destId: String)

}