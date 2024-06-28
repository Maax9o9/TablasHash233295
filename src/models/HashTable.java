package models;

import java.util.LinkedList;

public class HashTable<K, V> {
    private LinkedList<HashNode<K, V>>[] chainArray;
    private int M = 11;
    private int size;

    public HashTable() {
        chainArray = new LinkedList[M];
        for (int i = 0; i < M; i++) {
            chainArray[i] = new LinkedList<>();
        }
    }

    private int hashFunction1(K key) {
        int hashCode = key.hashCode();
        return Math.abs(hashCode % M);
    }

    private int hashFunction2(K key) {
        int hashCode = key.hashCode();
        return Math.abs((hashCode / M) % M);
    }

    public void put(K key, V value, int hashFunction) {
        int bucketIndex = (hashFunction == 1) ? hashFunction1(key) : hashFunction2(key);

        HashNode<K, V> newNode = new HashNode<>(key, value);
        LinkedList<HashNode<K, V>> chain = chainArray[bucketIndex];
        for (HashNode<K, V> node : chain) {
            if (node.key.equals(key)) {
                node.value = value;
                return;
            }
        }
        chain.add(newNode);
        size++;
    }

    public V get(K key, int hashFunction) {
        int bucketIndex = (hashFunction == 1) ? hashFunction1(key) : hashFunction2(key);

        LinkedList<HashNode<K, V>> chain = chainArray[bucketIndex];
        for (HashNode<K, V> node : chain) {
            if (node.key.equals(key)) {
                return node.value;
            }
        }
        return null;
    }

    public int getSize() {
        return size;
    }
}