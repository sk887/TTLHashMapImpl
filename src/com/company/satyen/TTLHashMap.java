package com.company.satyen;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;


public class TTLHashMap<K,V> implements Map<K, V>{

    private final HashMap<K, V> store = new HashMap<>();
    private final HashMap<K, Long> timestamps = new HashMap<>();
    private final long ttl;

    public TTLHashMap(TimeUnit ttlUnit, long ttlValue) {
        this.ttl = ttlUnit.toNanos(ttlValue);
    }
    public V get(Object key) {
        V value = this.store.get(key);

        if (value != null && expired(key, value)) {
            store.remove(key);
            timestamps.remove(key);
            return null;
        } else {
            return value;
        }
    }

    private boolean expired(Object key, V value) {
        return (System.nanoTime() - timestamps.get(key)) > this.ttl;
    }

    public V put(K key, V value) {
        timestamps.put(key, System.nanoTime());
        return store.put(key, value);
    }



}
