package com.chenjx.redwars.utils;

import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

/**
 * 在redis 与spring-data-redis 整合的时候，在使用template 方法其实还是比较的麻烦，编码量有点大
 * 在这里我们将封装template 中的一些常用方法，有使用其他的方法，直接加就可以了
 * 其他人调用就可以是命令式的调用，集群与单机都可以使用
 * 
 * 使用此工具类之前，保证自己的spring xml 配置文件中已经进行了 template 的注册，
 * 关于xml 如何进行整合配置请参考
 * <a href="https://www.cnblogs.com/iscys/p/9612715.html">https://www.cnblogs.com/iscys/p/9612715.html</a>
 * 这一点大家应该明白！
 *     //@Autowired
    private template redisUtils;
    
 * @author cys
 *
 */
@Component//作为组件进行spring的加载
public class RedisOperationUtils {
    
    /**
     * 引入XML 配置的template,这是必须的，我们是将template 进行的封装
     */
    @Autowired
    private RedisTemplate template;
    
    
    /**
     * 实现命令：TTL key，以秒为单位，返回给定 key的剩余生存时间(TTL, time to live)。
     * 
     * @param key
     * @return
     */
    public long ttl(String key) {
        return template.getExpire(key);
    }
    
    /**
     * 实现命令：expire 设置过期时间，单位秒
     * 
     * @param key
     * @return
     */
    public void expire(String key, long timeout) {
        template.expire(key, timeout, TimeUnit.SECONDS);
    }
    
    /**
     * 实现命令：INCR key，增加key一次
     * 
     * @param key
     * @return
     */
    public long incr(String key, long delta) {
        return template.opsForValue().increment(key, delta);
    }

    /**
     * 实现命令：KEYS pattern，查找所有符合给定模式 pattern的 key
     */
    public Set<String> keys(String pattern) {
        return template.keys(pattern);
    }

    /**
     * 实现命令：DEL key，删除一个key
     * 
     * @param key
     */
    public void del(String key) {
        template.delete(key);
    }

    // String（字符串）

    /**
     * 实现命令：SET key value，设置一个key-value（将字符串值 value关联到 key）
     * 
     * @param key
     * @param value
     */
    public void set(String key, String value) {
        template.opsForValue().set(key, value);
    }

    /**
     * 实现命令：SET key value EX seconds，设置key-value和超时时间（秒）
     * 
     * @param key
     * @param value
     * @param timeout
     *            （以秒为单位）
     */
    public void set(String key, String value, long timeout) {
        template.opsForValue().set(key, value, timeout, TimeUnit.SECONDS);
    }

    /**
     * 实现命令：GET key，返回 key所关联的字符串值。
     * 
     * @param key
     * @return value
     */
    public String get(String key) {
        return (String)template.opsForValue().get(key);
    }

    // Hash（哈希表）

    /**
     * 实现命令：HSET key field value，将哈希表 key中的域 field的值设为 value
     * 
     * @param key
     * @param field
     * @param value
     */
    public void hset(String key, String field, Object value) {
        template.opsForHash().put(key, field, value);
    }

    /**
     * 实现命令：HGET key field，返回哈希表 key中给定域 field的值
     * 
     * @param key
     * @param field
     * @return
     */
    public String hget(String key, String field) {
        return (String) template.opsForHash().get(key, field);
    }

    /**
     * 实现命令：HDEL key field [field ...]，删除哈希表 key 中的一个或多个指定域，不存在的域将被忽略。
     * 
     * @param key
     * @param fields
     */
    public void hdel(String key, Object... fields) {
        template.opsForHash().delete(key, fields);
    }

    /**
     * 实现命令：HGETALL key，返回哈希表 key中，所有的域和值。
     * 
     * @param key
     * @return
     */
    public Map<Object, Object> hgetall(String key) {
        return template.opsForHash().entries(key);
    }

    // List（列表）

    /**
     * 实现命令：LPUSH key value，将一个值 value插入到列表 key的表头
     * 
     * @param key
     * @param value
     * @return 执行 LPUSH命令后，列表的长度。
     */
    public long lpush(String key, String value) {
        return template.opsForList().leftPush(key, value);
    }

    /**
     * 实现命令：LPOP key，移除并返回列表 key的头元素。
     * 
     * @param key
     * @return 列表key的头元素。
     */
    public String lpop(String key) {
        return (String)template.opsForList().leftPop(key);
    }

    /**
     * 实现命令：RPUSH key value，将一个值 value插入到列表 key的表尾(最右边)。
     * 
     * @param key
     * @param value
     * @return 执行 LPUSH命令后，列表的长度。
     */
    public long rpush(String key, String value) {
        return template.opsForList().rightPush(key, value);
    }

}