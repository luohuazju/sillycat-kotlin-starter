package com.sillycat.kotlinstarter.service

import com.sillycat.kotlinstarter.dao.CustomerElasticRepository
import com.sillycat.kotlinstarter.model.Customer
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate
import org.springframework.stereotype.Service


interface CustomerService{

    fun createCustomer(customer: Customer): Customer

    fun loadAllCustomers(): Set<Customer>
}