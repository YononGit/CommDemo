package com.yonon.demo.cache;

/**
 * Created by jr-jiangyinghan on 2017-8-31.
 */


import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author wenchao.ren
 *         2015/1/5.
 */
public class CacheContext<T> {

    private Map<String, T> cache = new ConcurrentHashMap<String, T>();

    public T get(String key) {
        return cache.get(key);
    }

    public void addOrUpdateCache(String key, T value) {
        cache.put(key, value);
    }

    // ���� key ��ɾ�������е�һ����¼
    public void evictCache(String key) {
        if (cache.containsKey(key)) {
            cache.remove(key);
        }
    }

    // ��ջ����е����м�¼
    public void evictCache() {
        cache.clear();
    }

}