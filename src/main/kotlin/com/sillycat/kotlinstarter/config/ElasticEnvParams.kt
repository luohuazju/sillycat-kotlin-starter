package com.sillycat.kotlinstarter.config

class ElasticEnv(private val name: String) {
    fun get(): String = System.getenv(name) ?: throw RuntimeException("Please set environment variable $name")
}

object ElasticEnvParams {
    val ELASTIC_HOST = ElasticEnv("ELASTIC_HOST")
    val ELASTIC_PORT = ElasticEnv("ELASTIC_PORT")
    val ELASTIC_USERNAME = ElasticEnv("ELASTIC_USERNAME")
    val ELASTIC_PASSWORD = ElasticEnv("ELASTIC_PASSWORD")
}