package com.sillycat.kotlinstarter.config

import java.time.Duration
import org.springframework.cache.CacheManager
import org.springframework.cache.annotation.EnableCaching
import org.springframework.cache.concurrent.ConcurrentMapCacheManager
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Primary
import org.springframework.data.redis.cache.RedisCacheConfiguration
import org.springframework.data.redis.cache.RedisCacheManager
import org.springframework.data.redis.cache.RedisCacheWriter
import org.springframework.data.redis.connection.RedisConnectionFactory
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory
import org.springframework.data.redis.core.RedisTemplate


@Configuration
@EnableCaching
class CacheConfig() {

    @Primary
    fun cacheManager(): CacheManager? {
        return ConcurrentMapCacheManager()
    }

    @Bean
    fun  alternateCacheManager(redisConnectionFactory: RedisConnectionFactory?): CacheManager? {
        val redisCacheWriter = RedisCacheWriter.nonLockingRedisCacheWriter(redisConnectionFactory!!)
        val defaultCacheConfig =
            RedisCacheConfiguration.defaultCacheConfig()
        val redisCacheConfigurationMap: MutableMap<String, RedisCacheConfiguration> = HashMap()
        return RedisCacheManager(redisCacheWriter, defaultCacheConfig, redisCacheConfigurationMap)
    }

}