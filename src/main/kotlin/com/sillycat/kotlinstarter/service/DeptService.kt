package com.sillycat.kotlinstarter.service

import com.sillycat.kotlinstarter.model.Dept
import com.sillycat.kotlinstarter.model.Person

interface DeptService {

    fun saveDept(dept: Dept)

    fun deleteDept(id: String)
}