package com.sillycat.kotlinstarter.service.impl

import com.sillycat.kotlinstarter.dao.DeptNeo4jRepository
import com.sillycat.kotlinstarter.model.Dept
import com.sillycat.kotlinstarter.service.DeptService
import org.springframework.stereotype.Service


@Service
class DeptServiceImpl(val deptNeo4jRepository: DeptNeo4jRepository): DeptService {

    override fun saveDept(dept: Dept) {
        deptNeo4jRepository.save(dept)
    }

    override fun deleteDept(id: String) {
        deptNeo4jRepository.deleteById(id)
    }
}