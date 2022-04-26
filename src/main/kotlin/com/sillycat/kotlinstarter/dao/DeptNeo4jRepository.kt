package com.sillycat.kotlinstarter.dao

import com.sillycat.kotlinstarter.model.Dept
import org.springframework.data.neo4j.repository.Neo4jRepository

//import org.springframework.data.neo4j.repository.ReactiveNeo4jRepository

interface DeptNeo4jRepository : Neo4jRepository<Dept, String> {

}