package com.sillycat.kotlinstarter.service

import com.fasterxml.jackson.annotation.JsonTypeInfo
import com.sillycat.kotlinstarter.dao.DeptNeo4jRepository
import com.sillycat.kotlinstarter.model.Dept
import com.sillycat.kotlinstarter.model.Person
import org.neo4j.driver.internal.shaded.reactor.core.publisher.Flux
import org.springframework.stereotype.Service
import org.yaml.snakeyaml.events.Event


@Service
class DeptServiceImpl(val deptNeo4jRepository: DeptNeo4jRepository): DeptService {

    override fun saveDept(dept: Dept) {
        deptNeo4jRepository.save(dept)
    }

    override fun deleteDept(id: String) {
        deptNeo4jRepository.deleteById(id)
    }
}