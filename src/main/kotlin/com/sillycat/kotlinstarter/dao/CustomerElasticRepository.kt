package com.sillycat.kotlinstarter.dao

import com.sillycat.kotlinstarter.model.Customer
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository
import org.springframework.stereotype.Repository
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.elasticsearch.annotations.Query

@Repository
interface CustomerElasticRepository : ElasticsearchRepository<Customer, String> {

    fun findByIdAndStatus(id: String, status: String): Customer?

    fun findByLastName(lastName: String, pageable: Pageable): Page<Customer>

    @Query("{\"bool\": {\"must\": [{\"match\":{\"lastName\":\"?0\"}}]}}")
    fun findByLastNameUsingCustomQuery(lastName: String, pageable: Pageable): Page<Customer>

    fun findAllByStatus(status: String, pageable: Pageable): Page<Customer>

}