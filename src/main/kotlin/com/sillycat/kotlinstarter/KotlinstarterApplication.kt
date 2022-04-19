package com.sillycat.kotlinstarter

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class KotlinstarterApplication

fun main(args: Array<String>) {
	runApplication<KotlinstarterApplication>(*args)
}
