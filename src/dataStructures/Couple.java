package dataStructures;

public class Couple<K extends Comparable<K>, T> {

    private K key;
    private T object;

    public Couple(K key, T object) {
        this.key = key;
        this.object = object;
    }

    /**
     * @return K return the key
     */
    public K getKey() {
        return key;
    }

    /**
     * @param key the key to set
     */
    public void setKey(K key) {
        this.key = key;
    }

    /**
     * @return T return the object
     */
    public T getObject() {
        return object;
    }

    /**
     * @param object the object to set
     */
    public void setObject(T object) {
        this.object = object;
    }

}
