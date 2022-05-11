package com.sillycat.kotlinstarter.service.impl

import com.sillycat.kotlinstarter.service.LockService
import org.springframework.cache.CacheManager
import org.springframework.stereotype.Service
import java.util.concurrent.TimeUnit

@Service(value="lockService")
class LockServiceImpl(private val cacheManager: CacheManager): LockService  {

    override fun tryLock(name: String?, key: String, timeout: Long, ttl: Long): Boolean {
        val cacheName = name ?: "DEFAULT"
        val cache = cacheManager.getCache(cacheName)
        if (cache != null) {
            var total : Long = 0
            do {
                val now = System.currentTimeMillis()
                val newTimestamp = now + ttl + 1
                val oldTimestamp = cache.putIfAbsent(key, newTimestamp)
                if (oldTimestamp == null ||
                    ( now > oldTimestamp.get().toString().toLong() && cache.putIfAbsent(
                        key,
                        newTimestamp
                    ) != null)
                ) {
                    //get lock, go with logic
                    return true
                } else {
                    TimeUnit.MILLISECONDS.sleep(200)
                    total += ttl
                }
            } while (total < timeout)
        }
        return false
    }

    override fun unLock(name: String?, key: String) {
        val cacheName = name ?: "DEFAULT"
        cacheManager.getCache(cacheName)?.evict(key)
    }


}