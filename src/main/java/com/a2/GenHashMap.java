package com.a2;

import java.util.Objects;

/**
 * @param <K> key
 * @param <V> value
 * This class defines a generic hashmap
 * Autor: Daniil Suvorkov
 */
public class GenHashMap<K, V> {
    static final int MAXIMUM_CAPACITY = 1 << 30;

    private static class Node<K, V> implements MapEntry<K, V> {
        public final int hash;
        public final K key;
        public V value;
        public Node<K, V> next;
        public Node<K, V> prev;

        Node(int hash, K key, V value, Node<K, V> next, Node<K, V> prev) {
            this.hash = hash;
            this.key = key;
            this.value = value;
            this.next = next;
            this.prev = prev;
        }

        public final K getKey() {
            return key;
        }

        public final V getValue() {
            return value;
        }

        public final String toString() {
            return key + "=" + value ;
        }


        public final V setValue(V newValue) {
            V oldValue = value;
            value = newValue;
            return oldValue;
        }

        public final boolean equals(Object object) {
            if (object == this) return true;

            return object instanceof MapEntry<?, ?> element
                && Objects.equals(key, element.getKey())
                && Objects.equals(key, element.getValue());
        }
    }

    private Node<K, V>[] table;
    private int size; //current size


    public GenHashMap(int capacity) {
        if (capacity < 0) {
            throw new IllegalArgumentException();
        }
        if (capacity > MAXIMUM_CAPACITY) {
            capacity = MAXIMUM_CAPACITY-1;
        }

        for (Node<K, V> kvNode : table = new Node[capacity + 1]) {
            kvNode = null;
        }
    }

    public GenHashMap() {
    }

    public void setValue(K key, V value) {
        int index = hash(key) & (table.length - 1);
        Node<K, V> node = table[index];
        while (node != null) {
            if (node.key.equals(key)  && value.equals(node.value)) {
                node.setValue(value);
            }
            node = node.next;
        }
    }

    /**
     * This method returns the number of key-value mappings in this map.
     * @return size
     */
    public int size() {
        return size;
    }

    /**
     * This method adds the mapping of the specified key-value pair to this map.
     * If the map previously contained a mapping for the key, the old value is replaced by the new value.
     * @param key
     * @param value
     */
    public V put(K key, V value) {
        int index = hash(key) & (table.length - 1);
        Node<K, V> node = table[index];
        while (node != null) {
            if (node.key.equals(key)) {
                V oldValue = node.value;
                node.value = value;
                return oldValue;
            }
            node = node.next;
        }
        node = new Node<>(hash(key), key, value, null, null);
        node.next = table[index];
        table[index] = node;
        size++;
        return null;
    }

    public V get(K key) {
        Node<K, V> element;
        return (element = getNodeForKey(key)) == null ? null : element.value;
    }

    /**
     * @param key
     * This method removes the mapping for the specified key from this map if present.
     */
    public V delete(K key) {
        if (key == null) {
            throw new NullPointerException();
        }
        removeNode(hash(key), key, null, false);
        return null;
    }

    /**
     * This method is used to get all values
     */
    public String values() {
            StringBuilder sb = new StringBuilder();
            sb.append("Values = {");
            for (int i = 0; i < table.length; i++) {
                Node<K, V> node = table[i];
                while (node != null) {
                    var v = node.value.toString();
                    if (node.next == null) sb.append(v);
                    else sb.append(v + ", ");
                    node = node.next;
                }
            }
            return sb.append("}").toString();
    }

    /**
     * This method is used to get all keys
     */
    public String keyset() {
        StringBuilder sb = new StringBuilder();
        sb.append("Keys = {");
        for (int i = 0; i < table.length; i++) {
            Node<K, V> node = table[i];
            while (node != null) {
                var k = node.key.toString();
                if (node.next == null) sb.append(k);
                else sb.append(k + ", ");
                node = node.next;
            }
        }
        return sb.append("}").toString();
    }

    /**
     * This method is used to get all entries
     */
    public String entrySet() {
        StringBuilder sb = new StringBuilder();
        sb.append("HashMap = {");
        for (int i = 0; i < table.length; i++) {
            Node<K, V> node = table[i];
            while (node != null) {
                var s = node.toString();
                if (node.next == null) sb.append(s);
                else sb.append(s + ", ");
                node = node.next;
            }
        }
        return sb.append("}").toString();
    }

    /**
     * This method is used to clear the map
     */
    public void clear() {
        Node<K, V>[] tab;
        if ((tab = table) != null && size > 0) {
            size = 0;
            for (int i = 0; i < tab.length; i++) {
                tab[i] = null;
            }
        }
    }

    private void add(Node<K, V> node) {
        int index = hash(node.key) & (table.length - 1);
        Node<K, V> current = table[index];
        while (current != null) {
            if (current.key.equals(node.key)) {
                throw new IllegalArgumentException("Key already exists: " + node.key);
            }
            current = current.next;
        }
        node.next = table[index];
        table[index] = node;
        size++;
    }

    private int hash(K key) {
        int hash;
        return (key == null) ? 0 : (hash = key.hashCode()) ^ (hash >>> 16);
    }

    private Node<K, V> getNodeForKey(K key) {
        int index = hash(key) & (table.length - 1); //index for key
        Node<K, V> current = table[index];
        while (current != null) {
            if (current.key == key) {
                return current;
            }
            current = current.next;
        }
        return null;
    }

    /**
     * @param hash hash for key
     * @param key the key
     * @param value the value to match if matchValue, else ignored
     * @param matchValue if true only remove if value is equal
     * @return the node, or null if none
     */
    final Node<K, V> removeNode(int hash, Object key, Object value, boolean matchValue) {
        Node<K, V>[] tab;
        Node<K, V> current;
        int n, index;
        if ((tab = table) != null && (n = tab.length) > 0 &&
            (current = tab[index = (n - 1) & hash]) != null) {
            Node<K, V> node = null, element; // link to be deleted
            K k = current.key;
            V v ;

            if (current.hash == hash && (k = current.key) == key || key != null && key.equals(k)) {
                node = current;
            } else {
                while ((element = current.next) != null) {
                    if (element.hash == hash && (k = element.key) == key || key != null && key.equals(k)) {
                        node = element;
                        break;
                    }
                    current = element;
                }
            }
            if (node != null && ( !matchValue || (v = node.value) == value ||
                (value != null && value.equals(v)))) {
                if (node == current)
                    tab[index] = node.next;
                else
                    current.next = node.next;
                node.next = null;
                size--;
                return node;
            }
        }
        return null;
    }

}