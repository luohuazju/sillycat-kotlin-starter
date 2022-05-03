import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
	id("org.springframework.boot") version "2.6.6"
	id("io.spring.dependency-management") version "1.0.11.RELEASE"
	kotlin("jvm") version "1.6.10"
	kotlin("plugin.spring") version "1.6.10"
	id("org.openapi.generator") version "5.3.0"
}

group = "com.sillycat"
version = "0.0.1-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_1_8

repositories {
	mavenCentral()
}

dependencies {
	implementation("org.springframework.boot:spring-boot-starter-data-jdbc")
	implementation("org.springframework.boot:spring-boot-starter-web")
	implementation("org.springframework.boot:spring-boot-starter-data-neo4j")
	implementation("org.springframework.data:spring-data-elasticsearch")
	implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
	implementation("org.redisson:redisson:3.17.1")
	implementation("org.jetbrains.kotlin:kotlin-reflect")
	implementation("org.jetbrains.kotlin:kotlin-stdlib")
	implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
	implementation("javax.validation:validation-api:2.0.1.Final")
	implementation("org.springdoc:springdoc-openapi-data-rest:1.6.7")
	implementation("org.springdoc:springdoc-openapi-ui:1.6.7")
	implementation("io.github.microutils:kotlin-logging-jvm:2.1.21")
	implementation("org.springdoc:springdoc-openapi-kotlin:1.6.7")
	runtimeOnly("com.h2database:h2")
	testImplementation("org.springframework.boot:spring-boot-starter-test")
}

val oasPackage = "com.sillycat"
val oasGenOutputDir = project.layout.buildDirectory.dir("generated-oas")

sourceSets {
	val main by getting
	main.java.srcDir("${oasGenOutputDir.get()}/src/main/kotlin")
}

tasks.withType<KotlinCompile> {
	kotlinOptions {
		freeCompilerArgs = listOf("-Xjsr305=strict")
		jvmTarget = "1.8"
	}
}

