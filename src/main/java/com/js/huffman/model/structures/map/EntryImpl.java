/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.js.huffman.model.structures.map;

import java.util.Map;
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

    public EntryImpl(final K key, V value) {
        this.next = null;
        this.key = key;
        this.value = value;
    }

    @Override
    public K getKey() {
        return this.key;
    }

    @Override
    public V getValue() {
        return this.value;
    }

    @Override
    public V setValue(Object v) {
        try {
            this.value = (V) v;
        } catch (ClassCastException e) {
            //LOG.log(Level.SEVERE, "Set value failed to cast", e);
            return null;
        }
        return this.value;
    }

    public final EntryImpl<K, V> getNext() {
        return next;
    }

    public void setNext(EntryImpl<K, V> next) {
        this.next = next;
    }

    public final boolean hasNext() {
        return this.next != null;
    }

}
