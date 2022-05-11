package com.sillycat.kotlinstarter

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cache.annotation.EnableCaching

@SpringBootApplication
@EnableCaching
class KotlinstarterApplication

fun main(args: Array<String>) {
	runApplication<KotlinstarterApplication>(*args)
}
