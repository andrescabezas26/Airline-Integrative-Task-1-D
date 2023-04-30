package dataStructures;

public interface IHashTable<K extends Comparable<K>,V>{
    int hash(K key);
    public void add(K key, V value);
    public void remove(K key);
    public V getValue(K key);
}