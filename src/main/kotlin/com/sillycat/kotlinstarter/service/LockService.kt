package com.sillycat.kotlinstarter.service

import org.springframework.cache.Cache
import java.util.concurrent.TimeUnit

interface LockService {

    fun tryLock(name: String?, key: String, timeout: Long, ttl: Long) : Boolean

    fun unLock(name: String?, key: String)
}