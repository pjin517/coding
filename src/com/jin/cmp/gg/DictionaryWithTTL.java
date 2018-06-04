package com.jin.cmp.gg;

import java.util.HashMap;


/**
 * 设计一个dictionary，存key和value，value有time stamp，可能会expire，如果expire了返回空，
 * follow up是怎么有效删除过期的key value pair
 *
 * @param <K>
 * @param <V>
 */
public class DictionaryWithTTL<K, V> {
    private HashMap<K, DictEntry<V>> dictionary;

    public DictionaryWithTTL() {
        this.dictionary = new HashMap<>();
    }

    public V get(K key) {
        if (dictionary.containsKey(key)) {
            DictEntry<V> entry = dictionary.get(key);
            if (!entry.isExpired())
                return entry.value;
            else
                dictionary.remove(key);
        }
        return null;
    }

    public void put(K key, V value, long ttl) {
        dictionary.put(key, new DictEntry<>(value, ttl));

    }

    public static void main(String[] args) {
        DictionaryWithTTL<Integer, Integer> dictionaryWithTTL = new DictionaryWithTTL();

        dictionaryWithTTL.put(1, 10, 3000);
        dictionaryWithTTL.put(2, 20, 300);
        System.out.println("get(1): " + dictionaryWithTTL.get(1));
        System.out.println("get(2): " + dictionaryWithTTL.get(2));
        System.out.println("Sleeping 1 sec: ");
        try {
            Thread.sleep(1000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("get(1): " + dictionaryWithTTL.get(1));
        System.out.println("get(2): " + dictionaryWithTTL.get(2));
    }
}

class DictEntry<V> {
    V value;
    long createdAt;
    long ttl;

    public DictEntry(V value, long ttl) {
        this.value = value;
        this.ttl = ttl;
        this.createdAt = System.currentTimeMillis();
    }

    public boolean isExpired() {
        return System.currentTimeMillis() > createdAt + ttl;
    }
}
