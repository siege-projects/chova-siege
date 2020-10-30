package com.oxford.springboot.redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * Redis工具类
 *
 * @author Chova
 * @date 2020/10/17
 */
@Component
public class Redis {

    @Autowired
    private RedisTemplate<Object, Object> redisTemplate;

    /**
     * 设置一个Redis对象
     *
     * @param key      Redis的key
     * @param value    对象的值value
     * @param timeout  过期时间
     * @param timeUnit 定义时间的单位
     */
    public void addKey(String key, Object value, long timeout, TimeUnit timeUnit) {
        this.redisTemplate.opsForValue().set(key, value, timeout, timeUnit);
    }

    /**
     * 设置一个Redis对象
     *
     * @param key   Redis的key
     * @param value 对象的值value
     */
    public void set(String key, Object value) {
        try {
            redisTemplate.opsForValue().set(key, value);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    /**
     * 设置一个Redis对象
     *
     * @param key     Redis的key
     * @param value   对象的值value
     * @param timeout 过期时间
     */
    public void set(String key, Object value, long timeout) {
        try {
            if (timeout > 0) {
                redisTemplate.opsForValue().set(key, value, timeout, TimeUnit.SECONDS);
            } else {
                redisTemplate.opsForValue().set(key, value);
            }
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    /**
     * 设置缓存过期时间
     *
     * @param key     需要设置的Redis的key
     * @param timeout 缓存过期时间，单位为秒s
     */
    public void expire(String key, long timeout) {
        if (timeout > 0) {
            redisTemplate.expire(key, timeout, TimeUnit.SECONDS);
        } else {
            throw new IllegalArgumentException("Time cannot be a negative number");
        }
    }

    /**
     * 根据Redis的key获取缓存过期时间
     *
     * @param key Redis的key
     * @return long 缓存过期时间，单位为秒s
     */
    public long getExpire(String key) {
        return redisTemplate.getExpire(key, TimeUnit.SECONDS);
    }

    /**
     * 根据Redis的key获取对象的值
     *
     * @param key Redis的key
     * @return Object 对象的值
     */
    public Object getValue(String key) {
        return this.redisTemplate.opsForValue().get(key);
    }

    public Object get(String key) {
        return key == null ? null : redisTemplate.opsForValue().get(key);
    }

    /**
     * 判断Redis的key是否存在
     *
     * @param key Redis的key
     * @return boolean Redis的key是否存在
     */
    public boolean hasKey(String key) {
        try {
            return redisTemplate.hasKey(key);
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * 根据Redis的key删除Redis缓存
     *
     * @param keys Redis的key
     */
    public void delete(String... keys) {
        if (null != keys && keys.length > 0) {
            if (keys.length == 1) {
                redisTemplate.delete(keys);
            } else {
                redisTemplate.delete(CollectionUtils.arrayToList(keys));
            }
        }
    }

    /**
     * Redis的key生成器
     *
     * @return String 生成的key
     */
    public String keyGenerator() {
        return (String) this.redisTemplate.randomKey();
    }

    /**
     * Redis的key递增
     *
     * @param key         Redis的key
     * @param increaseNum 递增的数
     * @return long 递增后的Redis的key
     */
    public long increment(String key, long increaseNum) {
        if (increaseNum >= 0) {
            return redisTemplate.opsForValue().increment(key, increaseNum);
        } else {
            throw new IllegalArgumentException("Increment cannot be a negative number");
        }
    }

    /**
     * Redis的key递减
     *
     * @param key         Redis的key
     * @param decreaseNum 递减的数
     * @return long 递减后的Redis的key
     */
    public long decrement(String key, long decreaseNum) {
        if (decreaseNum >= 0) {
            return redisTemplate.opsForValue().decrement(key, decreaseNum);
        } else {
            throw new IllegalArgumentException("Increment cannot be a negative number");
        }
    }

    /**
     * 设置指定key,hashKey与对应的value值的Hash对象
     *
     * @param key     需要设置的Hash对象的key
     * @param hashKey 需要设置的Hash对象的key
     * @param value   hash对象
     * @return boolean 是否成功设置Hash对象
     */
    public boolean setHash(Object key, Object hashKey, Object value) {
        try {
            redisTemplate.opsForHash().put(key, hashKey, value);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * 根据Hash对象的key和hashKey获取对应的value
     *
     * @param key     Hash对象的key
     * @param hashKey Hash对象值的key
     * @return Object 对应的value
     */
    public Object getHash(Object key, Object hashKey) {
        return redisTemplate.opsForHash().get(key, hashKey);
    }

    /**
     * 根据Hash对象的key和hashKey删除对应的value
     *
     * @param key      Hash对象的key
     * @param hashKeys Hash对象值的key
     */
    public void deleteHash(Object key, Object... hashKeys) {
        redisTemplate.opsForHash().delete(key, hashKeys);
    }

    /**
     * 判断Hash对象中是否存在key和hashKey对应的value
     *
     * @param key     Hash对象的key
     * @param hashKey Hash对象值的key
     * @return boolean 是否存在key和hashKey对应的value
     */
    public boolean hasHash(Object key, Object hashKey) {
        return redisTemplate.opsForHash().hasKey(key, hashKey);
    }

    /**
     * 设置指定key与对应的Map键值对的Hash对象
     *
     * @param key 需要设置的Hash对象的key
     * @param map HashMap对象
     * @return boolean 是否成功设置Hash对象
     */
    public boolean setHashMap(Object key, Map<Object, Object> map) {
        try {
            redisTemplate.opsForHash().putAll(key, map);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * 设置指定的key与对应的Map键值对的Hash对象，并指定过期时间
     *
     * @param key     需要设置的Hash对象的key
     * @param map     需要设置的Hash对象key对应的Map键值对象
     * @param timeout 需要设置的Hash对象的过期时间
     * @return boolean 是否成功设置Hash对象
     */
    public boolean setHashMap(Object key, Map<Object, Object> map, long timeout) {
        try {
            redisTemplate.opsForHash().putAll(key, map);
            if (timeout > 0) {
                redisTemplate.expire(key, timeout, TimeUnit.SECONDS);
            }
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * 根据Hash对象的hashKey获取对应的Map键值对
     *
     * @param hashKey Hash对象的hashKey
     * @return Map<Object, Object> hashKey对应的Map键值对
     */
    public Map<Object, Object> getHashMap(Object hashKey) {
        return redisTemplate.opsForHash().entries(hashKey);
    }

    /**
     * Hash对象的key和hashKey对应的值递增
     *
     * @param key       Hash对象的key
     * @param hashKey   Hash对象的hashKey
     * @param increment 递增的值
     * @return double 递增后的值
     */
    public double hashIncrement(Object key, Object hashKey, double increment) {
        return redisTemplate.opsForHash().increment(key, hashKey, increment);
    }

    /**
     * Hash对象的key和hashKey对应的值递减
     *
     * @param key       Hash对象的key
     * @param hashKey   Hash对象的hashKey
     * @param decrement 递减的值
     * @return double 递减后的值
     */
    public double hasDecrement(Object key, Object hashKey, double decrement) {
        return redisTemplate.opsForHash().increment(key, hashKey, -decrement);
    }

    /**
     * 设置指定key和value的Set对象
     *
     * @param key    需要设置的Set对象的key
     * @param values Set对象
     * @return long 设置成功的个数
     */
    public long setSet(Object key, Object... values) {
        try {
            return redisTemplate.opsForSet().add(key, values);
        } catch (Exception e) {
            return 0;
        }
    }

    /**
     * 设置指定key和value的Set对象并指定过期时间
     *
     * @param key     需要设置的Set对象的key
     * @param timeout 过期时间
     * @param values  Set对象
     * @return long 设置成功的个数
     */
    public long setSet(Object key, long timeout, Object... values) {
        try {
            long count = redisTemplate.opsForSet().add(key, values);
            if (timeout > 0) {
                redisTemplate.expire(key, timeout, TimeUnit.SECONDS);
            }
            return count;
        } catch (Exception e) {
            return 0;
        }
    }

    /**
     * 根据key获取Set值
     *
     * @param key Set对应的key
     * @return Set<Object> key对象的Set值
     */
    public Set<Object> getSet(Object key) {
        try {
            return redisTemplate.opsForSet().members(key);
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * 获取Set对象值的长度
     *
     * @param key Set对象的key
     * @return long Set值对象的长度
     */
    public long sizeSet(Object key) {
        try {
            return redisTemplate.opsForSet().size(key);
        } catch (Exception e) {
            return 0;
        }
    }

    /**
     * 判断指定的key对应Set中是否存在指定的value
     *
     * @param key   指定的key值
     * @param value 指定的value值
     * @return boolean Set中是否存在指定的值
     */
    public boolean hasSet(Object key, Object value) {
        try {
            return redisTemplate.opsForSet().isMember(key, value);
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * 移除指定key和value的Set对象
     *
     * @param key    Set对象的key
     * @param values Set对象的values
     * @return long 成功移除的个数
     */
    public long removeSet(Object key, Object... values) {
        try {
            return redisTemplate.opsForSet().remove(key, values);
        } catch (Exception e) {
            return 0;
        }
    }

    /**
     * 设置指定key和value的List对象
     *
     * @param key   List对象的key
     * @param value List对象
     * @return long 设置成功的个数
     */
    public long setList(Object key, Object value) {
        try {
            return redisTemplate.opsForList().rightPush(key, value);
        } catch (Exception e) {
            return 0;
        }
    }

    /**
     * 设置指定key和value的List的对象并指定过期时间
     *
     * @param key     List对象的key
     * @param value   List对象
     * @param timeout 过期时间
     * @return long 设置成功的个数
     */
    public long setList(Object key, Object value, long timeout) {
        try {
            long count = redisTemplate.opsForList().rightPush(key, value);
            if (timeout > 0) {
                redisTemplate.expire(key, timeout, TimeUnit.SECONDS);
            }
            return count;
        } catch (Exception e) {
            return 0;
        }
    }

    /**
     * 设置指定的key和value的List对象
     *
     * @param key   List对象的key
     * @param value List对象
     * @return long 设置成功的个数
     */
    public long setList(Object key, List<Object> value) {
        try {
            return redisTemplate.opsForList().rightPushAll(key, value);
        } catch (Exception e) {
            return 0;
        }
    }

    /**
     * 设置指定的key和value的List对象并指定过期时间
     *
     * @param key     List对象的key
     * @param value   List对象
     * @param timeout 过期时间
     * @return long 设置成功的个数
     */
    public long setList(Object key, List<Object> value, long timeout) {
        try {
            long count = redisTemplate.opsForList().rightPushAll(key, value);
            if (timeout > 0) {
                redisTemplate.expire(key, timeout, TimeUnit.SECONDS);
            }
            return count;
        } catch (Exception e) {
            return 0;
        }
    }

    /**
     * 获取指定范围内的List对象
     *
     * @param key   List对象的key
     * @param start 开始位置
     * @param end   结束位置
     * @return List<Object> 指定范围的List对象
     */
    public List<Object> getList(Object key, long start, long end) {
        try {
            return redisTemplate.opsForList().range(key, start, end);
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * 根据List对象的key和List对象的索引index获取List对象中的值
     *
     * @param key   List对象的key
     * @param index List对象的索引
     * @return Object List对象中的值
     */
    public Object index(Object key, long index) {
        try {
            return redisTemplate.opsForList().index(key, index);
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * 根据List对象的key和List对象中的索引index设置List中的值
     *
     * @param key   List对象的key
     * @param index List对象中的索引
     * @param value List中的值
     * @return boolean 是否更新成功
     */
    public boolean updateList(Object key, long index, Object value) {
        try {
            redisTemplate.opsForList().set(key, index, value);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * 获取指定key的List对象的长度
     *
     * @param key List对象的key
     * @return long List对象的长度
     */
    public long sizeList(Object key) {
        try {
            return redisTemplate.opsForList().size(key);
        } catch (Exception e) {
            return 0;
        }
    }

    /**
     * 移除指定的key的List对象中的值为value开始向后count个的等于value的值
     *
     * @param key   List对象的key
     * @param count 第一个等于value的值开始向后的个数
     * @param value List对象中的值
     * @return long 移除的个数
     */
    public long removeList(Object key, long count, Object value) {
        try {
            return redisTemplate.opsForList().remove(key, count, value);
        } catch (Exception e) {
            return 0;
        }
    }

}
