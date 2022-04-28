package com.sillycat.kotlinstarter.model

import com.fasterxml.jackson.annotation.JsonProperty
import org.springframework.data.annotation.Id
import org.springframework.data.elasticsearch.annotations.Document

@Document(indexName = "customer")
data class Customer(
    @Id
    @JsonProperty("id")
    val id: String? = null,

    @JsonProperty("firstName")
    val firstName: String,

    @JsonProperty("lastName")
    val lastName: String,

    @JsonProperty("status")
    var status: String? = STATUS_ACTIVE,

    @JsonProperty("tags")
    val tags: Set<String>,
)
