package com.sillycat.kotlinstarter.model

import org.springframework.data.neo4j.core.schema.Id
import org.springframework.data.neo4j.core.schema.Node
import org.springframework.data.neo4j.core.schema.Relationship


const val STATUS_ACTIVE = "ACTIVE"
const val STATUS_DELETED = "DELETED"

@Node
data class Person(
    @Id
    val id: String?,
    val status: String = STATUS_ACTIVE,
    val name: String,
    val code: String,

    @Relationship(type = "HAS_CHILD")
    val familyMembers: MutableSet<Person> = mutableSetOf()
)
