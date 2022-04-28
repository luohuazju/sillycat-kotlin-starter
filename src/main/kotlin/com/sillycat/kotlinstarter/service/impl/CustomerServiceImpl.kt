package com.sillycat.kotlinstarter.service.impl

import com.sillycat.kotlinstarter.dao.CustomerElasticRepository
import com.sillycat.kotlinstarter.model.Customer
import com.sillycat.kotlinstarter.model.STATUS_ACTIVE
import com.sillycat.kotlinstarter.service.CustomerService
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate
import org.springframework.stereotype.Service

@Service(value="customerService")
class CustomerServiceImpl(
    private val customerElasticRepository: CustomerElasticRepository,
    val elasticsearchRestTemplate: ElasticsearchRestTemplate
): CustomerService {

    override fun createCustomer(customer: Customer): Customer {
        customerElasticRepository.save(customer)
        return customer
    }

    override fun loadAllCustomers(): Set<Customer> {
        val results : Page<Customer> = customerElasticRepository.findAllByStatus(STATUS_ACTIVE, PageRequest.of(0, 10))
        return results.toSet()
    }
}