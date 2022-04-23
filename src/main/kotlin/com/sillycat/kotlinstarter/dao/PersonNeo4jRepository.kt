package com.sillycat.kotlinstarter.dao

import com.sillycat.kotlinstarter.model.Person
import org.springframework.data.neo4j.repository.Neo4jRepository

interface PersonNeo4jRepository : Neo4jRepository<Person, String> {
}