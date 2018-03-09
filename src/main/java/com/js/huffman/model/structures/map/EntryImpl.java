package com.js.huffman.model.structures.map;

import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Entry object of our HashMap.
 * @author Jack
 * @param <K>
 * @param <V>
 */
public class EntryImpl<K extends Object, V extends Object> implements Map.Entry {

    private static final Logger LOG = Logger.getLogger(EntryImpl.class.getName());
    private EntryImpl<K, V> next;
    private K key;
    private V value;

    /**
     * Create a new EntryImpl, representing an entry in a Map.
     * @param key
     * @param value
     */
    public EntryImpl(final K key, V value) {
        this.next = null;
        this.key = key;
        this.value = value;
    }

    /**
     * Return this entry's key.
     * @return key
     */
    @Override
    public K getKey() {
        return this.key;
    }

    /**
     * Return this entry's value
     * @return value
     */
    @Override
    public V getValue() {
        return this.value;
    }

    /**
     * Set this entry's value.
     * @param v
     * @return
     */
    @Override
    public V setValue(Object v) {
        try {
            this.value = (V) v;
        } catch (ClassCastException e) {
            LOG.log(Level.SEVERE, "Set value failed to cast", e);
            return null;
        }
        return this.value;
    }

    /**
     * Returns the next entry chained to this entry.
     * @return next entry.
     */
    public final EntryImpl<K, V> getNext() {
        return next;
    }

    /**
     * Set the next entry chained to this entry.
     * @param next the entry to be set as next.
     */
    public void setNext(EntryImpl<K, V> next) {
        this.next = next;
    }

    /**
     * Check if this entry is the final entry in this bucket.
     * @return true if this entry has next, else false
     */
    public final boolean hasNext() {
        return this.next != null;
    }

}
