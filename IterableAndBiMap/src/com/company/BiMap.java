package com.company;

import java.util.*;
import java.util.function.BiFunction;
import java.util.function.Supplier;

public class BiMap<K0, K1, V> implements Iterable<K0> {

    final BiFunction<K0, K1, V> valueCreator;
    final Supplier<Map<K1, V>> innerMapCreator;
    final Map<K0, Map<K1, V>> map;

    public BiMap(BiFunction<K0, K1, V> valueCreator,
                 Map<K0, Map<K1, V>> map,
                 Supplier<Map<K1, V>> innerMapCreator) {
        this.valueCreator = valueCreator;
        this.innerMapCreator = innerMapCreator;
        this.map = map;
    }

    public BiMap(BiFunction<K0, K1, V> valueCreator) {
        this(valueCreator, new LinkedHashMap<K0, Map<K1, V>>(), ()->new LinkedHashMap<K1,V>());
    }

    public V get(K0 key0, K1 key1) {
        Objects.requireNonNull(key0);
        Objects.requireNonNull(key1);

        Map<K1, V> inner = this.map.get(key0);

        if (inner == null) {
            inner = this.innerMapCreator.get();
            this.map.put(key0, inner);
        }

        V value = inner.get(key1);

        if (value == null) {
            value = this.valueCreator.apply(key0, key1);
            inner.put(key1, value);
        }

        assert value != null;

        return value;
    }

    public Map<K1, V> get(K0 key0) {
        Objects.requireNonNull(key0);
        Map<K1, V> inner = this.map.get(key0);

        if (inner == null)
            return Collections.emptyMap();

        return Collections.unmodifiableMap(inner);
    }

    @Override
    public Iterator<K0> iterator() {
        return this.map.keySet().iterator();
    }
}
