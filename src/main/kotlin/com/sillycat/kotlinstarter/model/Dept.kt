package com.sillycat.kotlinstarter.model

import org.springframework.data.neo4j.core.schema.Id
import org.springframework.data.neo4j.core.schema.Node
import org.springframework.data.neo4j.core.schema.Relationship


@Node
data class Dept(
    @Id
    val id: String,
    val deptName: String,

    @Relationship(type = "HAS_CHILD")
    val children: MutableSet<Dept> = mutableSetOf(),

    @Relationship(type = "HAS_PARENT")
    val parents:MutableSet<Dept> = mutableSetOf(),

)