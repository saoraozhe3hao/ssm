package com.hogen.service;

import com.hogen.controller.MyController;
import com.hogen.frontParam.FrontParam;
import com.hogen.sqlResult.City;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.RedisSentinelConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import com.hogen.repository.CityMapper;

@Service
public class CityServiceImpl {
    static Logger logger = LogManager.getLogger(CityServiceImpl.class.getName());

    @Autowired
    private CityMapper cityMapper = null;

    @Transactional(propagation = Propagation.REQUIRED,isolation=Isolation.READ_COMMITTED)
    @CacheEvict(cacheNames="city",key="'city_'+#id")
    public City getCity(long id){
        logger.error("error级别");
        logger.info("info级别");
        logger.debug("debug级别");
        logger.warn("warn级别");
        logger.fatal("fatal级别");
        logger.log(Level.TRACE, "trace级别");
        return cityMapper.getCity(1);
    }

    @Bean(name="redisCacheManager")
    public RedisCacheManager cacheManager(RedisConnectionFactory connectionFactory) {
        return RedisCacheManager.create(connectionFactory);
    }
}
