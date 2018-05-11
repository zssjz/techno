package com.jason.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.DataType;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * redis工具类
 * Created by Jason on 2018/5/2.
 */
@Component
public class RedisUtil {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private RedisTemplate redisTemplate;

    /**
     * 删除一个或多个key
     * @param keys
     */
    public boolean del(String ... keys) {
        try {
            if (keys.length == 1) {
                redisTemplate.delete(keys[0]);
            } else {
                redisTemplate.delete(CollectionUtils.arrayToList(keys));
            }
            return true;
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return false;
        }
    }

    /**
     * 删除多个key
     * @param keys
     * @return
     */
    public boolean del(List<String> keys) {
        try {
            redisTemplate.delete(keys);
            return true;
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return false;
        }
    }

    /**
     * 设置key过期时间，单位秒
     * @param key
     * @param time
     * @return
     */
    public boolean expire(String key, long time) {
        try {
            return redisTemplate.expire(key, time, TimeUnit.SECONDS);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return false;
        }
    }

    /**
     * 设置key过期时间，单位毫秒
     * @param key
     * @param time
     * @return
     */
    public boolean pexpire(String key, long time) {
        try {
            return redisTemplate.expire(key, time, TimeUnit.MILLISECONDS);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return false;
        }
    }

    /**
     * 判断key是否存在
     * @param key
     * @return
     */
    public boolean exists(String key) {
        try {
            return redisTemplate.hasKey(key);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return false;
        }
    }

    /**
     * 获取key的过期时间,单位秒
     * @param key
     * @return
     */
    public long getExpire(String key) {
        return redisTemplate.getExpire(key);
    }

    /**
     * rename
     * @param oldKey
     * @param newKey
     */
    public boolean rename(String oldKey, String newKey) {
        try {
            redisTemplate.rename(oldKey, newKey);
            return true;
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return false;
        }
    }

    /**
     * 返回key的类型
     * @param key
     * @return
     */
    public DataType type(String key) {
        return redisTemplate.type(key);
    }

    /**
     * 写入String
     * @param key
     * @param value
     * @return
     */
    public boolean set(String key, String value) {
        try {
            redisTemplate.opsForValue().set(key, value);
            return true;
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return false;
        }
    }

    /**
     * 读出String
     * @param key
     * @return
     */
    public Object get(String key) {
        try {
            return redisTemplate.opsForValue().get(key);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return null;
        }
    }

    /**
     * 写入String
     * @param key
     * @param value
     * @param time  过期时间，单位秒
     * @return
     */
    public boolean set(String key, String value, long time) {
        try {
            if (time > 0) {
                redisTemplate.opsForValue().set(key, value, time, TimeUnit.SECONDS);
            } else {
                this.set(key, value);
            }
            return true;
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return false;
        }
    }

    /**
     * 递增整数
     * @param key
     * @param step
     * @return
     */
    public long decrBy(String key, long step) {
        return redisTemplate.opsForValue().increment(key, step);
    }

    /**
     * 递增浮点数
     * @param key
     * @param step
     * @return
     */
    public double decrBy(String key, double step) {
        return redisTemplate.opsForValue().increment(key, step);
    }

    /**
     * 向哈希表中添加字段和值
     * @param key
     * @param item
     * @param value
     * @return
     */
    public boolean hSet(String key, String item, String value) {
        try {
            redisTemplate.opsForHash().put(key, item, value);
            return true;
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return false;
        }
    }

    /**
     * 获取存储在哈希表中指定字段的值。
     * @param key
     * @param item
     */
    public Object hGet(String key, String item) {
        try{
            return redisTemplate.opsForHash().get(key, item);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return null;
        }
    }

    /**
     * 获取在哈希表中指定 key 的所有字段和值
     * @param key
     * @return
     */
    public Map<Object, Object> hGetAll(String key) {
        try {
            return redisTemplate.opsForHash().entries(key);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return null;
        }
    }

    /**
     * 获取哈希表字段数量
     * @param key
     * @return
     */
    public long hLen(String key) {
        return redisTemplate.opsForHash().size(key);
    }

    /**
     * 写入哈希表
     * @param key
     * @param values
     * @return
     */
    public boolean hmset(String key, Map<String, String> values) {
        try {
            redisTemplate.opsForHash().putAll(key, values);
            return true;
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return false;
        }
    }

    /**
     * 为哈希表 key 中的指定字段的整数数值加上增量
     * @param key
     * @param item
     * @param step
     * @return
     */
    public long hIncrBy(String key, String item, long step) {
        return redisTemplate.opsForHash().increment(key, item, step);
    }

    /**
     * 为哈希表 key 中的指定字段的浮点数值加上增量(注意精度)
     * @param key
     * @param item
     * @param step
     * @return
     */
    public double hIncrBy(String key, String item, double step) {
        return redisTemplate.opsForHash().increment(key, item, step);
    }

    /**
     * 获取所有哈希表中的字段
     * @param key
     * @return
     */
    public Set hKeys(String key) {
        try {
            return redisTemplate.opsForHash().keys(key);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return null;
        }
    }

    /**
     * 获取哈希表中所有值
     * @param key
     * @return
     */
    public List hVals(String key) {
        try {
            return redisTemplate.opsForHash().values(key);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return null;
        }
    }

    /**
     * 查看哈希表 key 中，指定的字段是否存在。
     * @param key
     * @param item
     * @return
     */
    public boolean hExists(String key, String item) {
        return redisTemplate.opsForHash().hasKey(key, item);
    }

    /**
     * 获取列表长度
     * @param key
     * @return
     */
    public long lLen(String key) {
        return redisTemplate.opsForList().size(key);
    }

    /**
     * 通过索引获取列表中的元素
     * @param key
     * @param index
     * @return
     */
    public Object lIndex(String key, int index) {
        try {
            return redisTemplate.opsForList().index(key,index);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return null;
        }
    }

    /**
     * 移出并获取列表的第一个元素
     * @param key
     * @return
     */
    public Object lPop(String key) {
        try {
            return redisTemplate.opsForList().leftPop(key);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return null;
        }
    }

    /**
     * 移出并获取列表的最后一个元素
     * @param key
     * @return
     */
    public Object rPop(String key) {
        try {
            return redisTemplate.opsForList().rightPop(key);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return null;
        }
    }

    /**
     * 为已存在的列表添加值到头部，并返回当前列表长度
     * @param key
     * @param value
     * @return
     */
    public long lPushX(String key, String value) {
        return redisTemplate.opsForList().leftPush(key, value);
    }

    /**
     * 将一个或多个值插入到列表头部，并返回当前列表长度
     * @param key
     * @param values
     */
    public long lPush(String key, List<String> values) {
        return redisTemplate.opsForList().leftPushAll(key, values);
    }

    /**
     * 将一个或多个值插入到列表尾部，并返回当前列表长度
     * @param key
     * @param values
     * @return
     */
    public long rPush(String key, List<String> values) {
        return redisTemplate.opsForList().rightPushAll(key, values);
    }

    /**
     * 为已存在的列表添加值的末尾,并返回当前列表长度
     * @param key
     * @param value
     * @return
     */
    public long rPushX(String key, String value) {
        return redisTemplate.opsForList().rightPush(key, value);
    }

    /**
     * 获取列表指定范围内的元素
     * @param key
     * @param start
     * @param end
     * @return
     */
    public List lRange(String key, long start, long end) {
        try {
            return redisTemplate.opsForList().range(key, start, end);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return  null;
        }
    }

    /**
     * 根据参数 COUNT 的值，移除列表中与参数 VALUE 相等的元素。
     *  COUNT 的值可以是以下几种：
     *      count > 0 : 从表头开始向表尾搜索，移除与 VALUE 相等的元素，数量为 COUNT 。
     *      count < 0 : 从表尾开始向表头搜索，移除与 VALUE 相等的元素，数量为 COUNT 的绝对值。
     *      count = 0 : 移除表中所有与 VALUE 相等的值
     * @param key
     * @param count
     * @param value
     */
    public boolean lRem(String key, long count, String value) {
        try {
            redisTemplate.opsForList().remove(key, count, value);
            return true;
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return false;
        }
    }

    /**
     * 设置列表下标对应的值
     * @param key
     * @param index
     * @param value
     * @return
     */
    public boolean lSet(String key, long index, String value) {
        try {
            redisTemplate.opsForList().set(key, index, value);
            return true;
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return false;
        }
    }

    /**
     * 保留列表中从start偏移量到end偏移量范围内的所有元素
     * @param key
     * @param start
     * @param end
     * @return
     */
    public boolean lTrim(String key, long start, long end) {
        try {
            redisTemplate.opsForList().trim(key, start, end);
            return true;
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return false;
        }
    }

    /**
     * 向集合添加一个或多个成员
     * @param key
     * @param values
     * @return
     */
    public long sAdd(String key, String ... values) {
        try {
            return redisTemplate.opsForSet().add(key, values);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return 0;
        }
    }

    /**
     * 获取集合的成员数
     * @param key
     * @return
     */
    public long sCard(String key) {
        return redisTemplate.opsForSet().size(key);
    }

    /**
     * 判断 member 元素是否是集合 key 的成员
     * @param key
     * @param member
     * @return
     */
    public boolean sIsMember(String key, String member) {
        return redisTemplate.opsForSet().isMember(key, member);
    }

    /**
     * 返回集合中的所有成员
     * @param key
     * @return
     */
    public Set sMembers(String key) {
        try {
            return redisTemplate.opsForSet().members(key);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return null;
        }
    }

    /**
     * 移除并返回集合中的一个随机元素
     * @param key
     * @return
     */
    public Object sPop(String key) {
        try {
            return redisTemplate.opsForSet().pop(key);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return  null;
        }
    }

    /**
     * 移除集合中一个或多个成员,并返回被移除元素的数量
     * @param key
     * @param members
     * @return
     */
    public long sRem(String key, String ... members) {
        return redisTemplate.opsForSet().remove(key, members);
    }

    /**
     * 从集合中随机返回count个元素
     * @param key
     * @param count
     * @return
     */
    public Set sRandMember(String key, long count) {
        if (count < 0) {
            throw new RuntimeException("It can not smaller than zero");
        }
        try {
            return redisTemplate.opsForSet().distinctRandomMembers(key, count);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return null;
        }
    }

    /**
     * 新增一个有序集合
     * @param key
     * @param value
     * @return
     */
    public long zAdd(String key, Set<ZSetOperations.TypedTuple<Object>> value) {
        return redisTemplate.opsForZSet().add(key, value);
    }

    /**
     * 将带有给定分值的成员添加到有序集合里
     * @param key
     * @param score
     * @param value
     * @return
     */
    public boolean zAdd(String key, String value, long score) {
        return redisTemplate.opsForZSet().add(key, value, score);
    }

    /**
     * 从有序集合里移除给定的成员，并返回被移除成员的数量
     * @param key
     * @param members
     * @return
     */
    public long zRem(String key, String ... members) {
        return redisTemplate.opsForZSet().remove(key, members);
    }

    /**
     * 移除有序集合中排名介于start和end之间的所有成员
     * @param key
     * @param start
     * @param end
     * @return
     */
    public long zRemRangeByRank(String key, long start, long end) {
        return redisTemplate.opsForZSet().removeRange(key, start, end);
    }

    /**
     * 移除有序集合中分值介于min和max之间的所有成员
     * @param key
     * @param min
     * @param max
     * @return
     */
    public long zRemRangeByScore(String key, double min, double max) {
        return redisTemplate.opsForZSet().removeRangeByScore(key, min, max);
    }

    /**
     * 返回有序集合包含成员的数量
     * @param key
     * @return
     */
    public long zCard(String key) {
        return redisTemplate.opsForZSet().zCard(key);
    }

    /**
     * 返回分值介于 min 和 max 之间的成员数量
     * @param key
     * @param min
     * @param max
     * @return
     */
    public long zCount(String key, double min, double max) {
        return redisTemplate.opsForZSet().count(key, min, max);
    }

    /**
     * 返回成员member在有序集合中的排名,其中有序集成员按分数值递增(从小到大)顺序排列
     * @param key
     * @param member
     * @return
     */
    public long zRank(String key, String member) {
        return redisTemplate.opsForZSet().rank(key, member);
    }

    /**
     * 返回成员member的分值
     * @param key
     * @param member
     * @return
     */
    public double zScore(String key, String member) {
        return redisTemplate.opsForZSet().score(key, member);
    }

    /**
     * 返回有序集合中排名介于start和end之间的成员,其中有序集成员按分数值递增(从小到大)顺序排列
     * @param key
     * @param start
     * @param end
     * @return
     */
    public Set zRange(String key, long start, long end) {
        try {
            return redisTemplate.opsForZSet().range(key, start, end);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return null;
        }
    }

    /**
     * 返回有序集合中分值介于min和max之间的所有成员,其中有序集成员按分数值递增(从小到大)顺序排列
     * @param key
     * @param min
     * @param max
     * @return
     */
    public Set zRangeByScore(String key, double min, double max) {
        try {
            return redisTemplate.opsForZSet().rangeByScore(key, min, max);
        } catch (Exception e) {
            return null;
        }
    }

}
