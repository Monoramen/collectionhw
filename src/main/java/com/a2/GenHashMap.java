package com.a2;

import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.function.Function;

/**
 * @param <K> key
 * @param <V> value
 * This class defines a generic hashmap
 * Autor: Daniil Suvorkov
 */
public class GenHashMap<K, V> {
    static final int MAXIMUM_CAPACITY = 1 << 24;
    private static final int DEFAULT_CAPACITY = 16;


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

        @Override
        public final K getKey() {
            return key;
        }

        @Override
        public final V getValue() {
            return value;
        }

        @Override
        public final String toString() {
            return Objects.toString(key) + "=" + Objects.toString(value);
        }


        @Override
        public final V setValue(V newValue) {
            V oldValue = value;
            value = newValue;
            return oldValue;
        }

        @Override
        public final boolean equals(Object object) {
            if (this == object) return true;
            if (!(object instanceof Node<?, ?> otherNode)) return false;
            return Objects.equals(key, otherNode.key) && Objects.equals(value, otherNode.value);
        }

        @Override
        public final int hashCode() {
            return Objects.hash(key, value);
        }
    }

    private Node<K, V>[] table;
    private int size;

    public GenHashMap(int capacity) {
        if (capacity < 0) {
            throw new IllegalArgumentException();
        }
        if (capacity > MAXIMUM_CAPACITY) {
            capacity = MAXIMUM_CAPACITY;
        }
        else if (capacity == 0) {
            capacity = DEFAULT_CAPACITY;
        }

        Node<K, V>[] kvNode  = new Node[capacity];
        table =  kvNode;
    }

    public GenHashMap() {
        int capacity = DEFAULT_CAPACITY;
        Node<K, V>[] kvNode  = new Node[capacity];
        table = kvNode;
    }

    /**
     * This method returns the number of key-value mappings in this map.
     * @return size
     */
    public int size() {
        return size;
    }

    private boolean checkKeyByNull(K key) {
        if (key == null) {
            System.out.println("Key cannot be null");
            return true;
        }
        return false;
    }
    /**
     * This method adds the mapping of the specified key-value pair to this map.
     * If the map previously contained a mapping for the key, the old value is replaced by the new value.
     * @param key
     * @param value
     */
    public V put(K key, V value) {
        if (!checkKeyByNull(key)){
            return  (V) null;
        }
        int index = hash(key) & (table.length - 1);
        Node<K, V> node = table[index];
        while (node != null) {
            if (node.equals(key) ) {
                return  node.setValue(value);
            }
            node = node.next;
        }
        node = new Node<>(hash(key), key, value, null, null);
        node.next = table[index];
        table[index] = node;
        size++;
        return (V) null;
    }

    /**
     * This method returns the value to which the specified key is mapped.
     * @param key
     * @return value of the key or throws exception if not found
     */
    public V get(K key) throws NullPointerException {
        if (!checkKeyByNull(key)){
            return null;
        }
        int index = hash(key) & (table.length - 1);
        Node<K, V> current = table[index];

        while (current != null) {
            if (current.getKey().equals(key)) {
                return current.getValue();
            }
            current = current.next;
        }
        throw new NoSuchElementException("Key " + key + " not found");
    }

    /**
     * This method removes the mapping for the specified key from this map if present.
     * It checks if the key exists or out of bounds
     * @param key
     */
    public V delete(K key) {
        if (!checkKeyByNull(key)){
            return null;
        }
        Node<K, V> node = removeNode(hash(key), key, null, false);
            if (node == null) {
                System.out.println("Key does not exist: " + key);
            }
            return node == null ? null : node.value;
    }

    private void appendNodes(StringBuilder sb, Function<Node<K, V>, String> toStringFunction) {
        boolean first = true;
        for (int i = 0; i < table.length; i++) {
            Node<K, V> node = table[i];
            while (node != null) {
                if (!first) {
                    sb.append(", ");
                }
                sb.append(toStringFunction.apply(node));
                first = false;
                node = node.next;
            }
        }
    }

    /**
     * This method is used to get all values
     */
    public String values() {
        StringBuilder sb = new StringBuilder();
        sb.append("Values = {");
        appendNodes(sb, node -> node.value.toString());
        return sb.append("}").toString();
    }

    /**
     * This method is used to get all keys
     */
    public String keyset() {
        StringBuilder sb = new StringBuilder();
        sb.append("Keys = {");
        appendNodes(sb, node -> node.key.toString());
        return sb.append("}").toString();
    }

    /**
     * This method is used to get all entries
     */
    public String entrySet() {
        if(size == 0) return "HashMap = {}";
        StringBuilder sb = new StringBuilder();
        sb.append("HashMap = {");
        appendNodes(sb, Node::toString);
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

    private int hash(K key) {
        return (key == null) ? 0 : (key.hashCode() ^ (key.hashCode() >>> 16));
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