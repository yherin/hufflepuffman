package com.js.huffman.model.structures.map;

import java.util.Collection;
import java.util.Map;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Jack
 * @param <K> key used by this map
 * @param <V> value stored by this key
 */
public final class HuffmanHashMap<K extends Object, V extends Object> implements Map<K, V> {

    private static final Logger LOG = Logger.getLogger(HuffmanHashMap.class.getName());

    EntryImpl[] buckets;
    final int initialCapacity;
    int capacity;
    final double loadFactor = 0.75f;
    long count;

    public HuffmanHashMap() {
        this.initialCapacity = 4096;
        this.capacity = initialCapacity;
        this.buckets = new EntryImpl[initialCapacity];
        this.count = 0L;
    }

    public HuffmanHashMap(final int initialCapacity) {
        this.initialCapacity = initialCapacity;
        this.capacity = initialCapacity;
        this.buckets = new EntryImpl[initialCapacity];
        this.count = 0L;
    }

    private void handlePossibleResize() {
        if (doResize()) {
            LOG.log(Level.INFO, "Resizing the HashMap. Count: {0} | Capacity: " + this.buckets.length, this.count);
            final int newSize = Math.min(Integer.MAX_VALUE - 1, this.buckets.length * 2);
            EntryImpl[] bigger = new EntryImpl[newSize];
            for (int i = 0; i < this.buckets.length; i++) {
                final EntryImpl entry = buckets[i];
                if (entry != null) {
                    final int hash = hash((K) entry.getKey());
                    final EntryImpl possibleExistingEntry = bigger[hash];
                    if (possibleExistingEntry != null) {
                        entry.setNext(possibleExistingEntry);
                        bigger[hash] = entry;
                    } else {
                        bigger[hash] = entry;
                    }
                }
            }
            this.buckets = bigger;
        }
        this.capacity = this.buckets.length;
    }

    private boolean doResize() {
        return ((float) this.count / (float) this.buckets.length >= this.loadFactor);
    }

    private void chainRehash(final EntryImpl entry, final EntryImpl[] buckets, final EntryImpl[] bigger) {
        if (!entry.hasNext()) {
            final int hash = hash((K) entry.getKey());
            EntryImpl possibleExistingEntry = bigger[hash];
            if (possibleExistingEntry != null) {
                entry.setNext(possibleExistingEntry);
                bigger[hash] = entry;
            } else {
                bigger[hash] = entry;
            }
        }
    }

    @Override
    public int size() {
        if (this.count > Integer.MAX_VALUE) {
            return Integer.MAX_VALUE;
        } else {
            return (int) count;
        }

    }

    @Override
    public boolean isEmpty() {
        return this.count == 0L;
    }

    @Override
    public boolean containsKey(final Object o) {
        Object result = get(o);
        return result != null;

    }

    @Override
    public boolean containsValue(Object o) {
        throw new UnsupportedOperationException("Not supported.");
    }

    @Override
    public V get(Object o) {
        try {
            K k = (K) o;
            final int hash = hash(k);
            final EntryImpl entry = buckets[hash];
            if (entry == null) {
                return null;
            }
            if (entry.getKey().equals(k)) {
                return (V) entry.getValue();
            } else {
                final V val = chainGet(entry, k);
                if (val == null) {
                    return null;
                } else {
                    return val;

                }
            }

        } catch (ClassCastException e) {
            LOG.log(Level.SEVERE, null, e);
            return null;
        }
    }

    @Override
    public V put(K k, V v) {
        handlePossibleResize();
        final int hash = hash(k);
        EntryImpl entry = this.buckets[hash];
        if (entry == null) {
            this.buckets[hash] = new EntryImpl(k, v);
            this.count++;
            return null;
        } else {
            final V val = chainPut(entry, k, v);
            if (val == null) {
                return null;
            } else {
                return val;

            }
        }
    }

    @Override
    public V remove(Object o) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.

    }

    @Override
    public void putAll(Map<? extends K, ? extends V> map) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void clear() {
        this.buckets = new EntryImpl[initialCapacity];
    }

    


    @Override
    public Collection<V> values() {
        throw new UnsupportedOperationException("Not supported.");
    }

    @Override
    public Set<Entry<K, V>> entrySet() {
        throw new UnsupportedOperationException("Not supported.");
    }

    private int hash(K key) {
        final int keyHash;
        if (key == null) {
            keyHash = 0;
        } else {
            keyHash = key.hashCode();
        }
        final int hash = keyHash % this.initialCapacity;
        return hash;
    }

    private V chainPut(final EntryImpl entry, final K key, final V newValue) {
        K existingKey = (K) entry.getKey();
        if (existingKey.equals(key)) {
            final V oldValue = (V) entry.getValue();
            entry.setValue(newValue);
            return oldValue;
        } else {
            if (entry.hasNext()) {
                return chainPut(entry.getNext(), key, newValue);
            } else {
                EntryImpl newEntry = new EntryImpl(key, newValue);
                count++;
                entry.setNext(newEntry);
                return null;
            }
        }
    }

    private V chainGet(final EntryImpl entry, final K key) {
        K k2 = (K) entry.getKey();
        if (key.equals(k2)) {
            return (V) entry.getValue();
        } else if (entry.hasNext()) {
            return chainGet(entry.getNext(), key);
        }
        return null;
    }

    @Override
    public Set<K> keySet() {
        throw new UnsupportedOperationException("Not supported. Use #keyArray instead."); //To change body of generated methods, choose Tools | Templates.
    }
    
    public final EntryImpl[] keyArray() {
        LOG.log(Level.WARNING, "EXPENSIVE OPERATION - keyArray()");
        final int amountOfKeys = Math.min(Integer.MAX_VALUE, (int) count);
        int keyIndex = 0;
        final EntryImpl[] keys = new EntryImpl[amountOfKeys];
        for (int i = 0; i < buckets.length; i++) {
            final EntryImpl entry = buckets[i];
            if (entry != null){
                keys[keyIndex] = entry;
                keyIndex++;
            }
        }
        return keys;
    }

}
