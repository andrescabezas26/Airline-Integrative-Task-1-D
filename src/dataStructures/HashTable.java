package dataStructures;

public class HashTable<K, V> {

  private int sizeTable;
  private Node<K, V>[] table;

  @SuppressWarnings("unchecked")
  public HashTable(int size) {
    sizeTable = size;
    this.table = (Node<K, V>[]) new Node[sizeTable];
  }

  // hash function
  private int hash(K key) {
    return Math.abs(key.hashCode()) % sizeTable;
  }

  public String add(K key, V value) {
    String msj = "";

    int index = hash(key);
    Node<K, V> newNode = new Node<K, V>(key, value);
    // si no hay ninguna colision
    if (table[index] == null) {
      table[index] = newNode;
      msj = "nuevo nodo agrgado";
    } else {
      // si hay colison, se agrega el nodo al final
      Node<K, V> current = table[index];
      while (current.getNext() != null) {
        current = current.getNext();
      }
      current.setNext(newNode);
      msj = "nuevo nodo agrgado";
    }
    return msj;
  }

  public V getValue(K key) {
    int index = hash(key);

    if (table[index] == null) {
      return null;
    } else {
      Node<K, V> current = table[index];
      while (current.getNext() != null) {
        if (current.getKey().equals(key)) {
          return current.getValue();
        }
        current = current.getNext();
      }
      return null;
    }
  }

  public String remove(K key) {
    String msj = "";
    int index = hash(key);

    if (table[index] == null) {
      msj = "no se encontro valor a eliminar";
    } else {
      if (table[index].getKey().equals(key)) {
        table[index] = table[index].getNext();
      } else {
        Node<K, V> current = table[index];
        while (current.getNext() != null && !current.getKey().equals(key)) {
          current = current.getNext();
        }
        if (current.getNext() != null) {
          current.setNext(current.getNext().getNext());
        }
      }
    }
    return msj;
  }
}
