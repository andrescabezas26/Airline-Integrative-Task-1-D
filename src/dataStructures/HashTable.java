package dataStructures;

public class HashTable<K, V> {

  private int sizeTable;
  private Node<K, V>[] table;

  @SuppressWarnings("unchecked")
  public HashTable(int sizeTable) {
    this.sizeTable = sizeTable;
    this.table = (Node<K, V>[]) new Node[sizeTable];
  }

  // hash function
  private int hash(K key) {
    return Math.abs(key.hashCode()) % sizeTable;
  }

  public int hashString(K key) {
    int hashValue = 0;
    for (int i = 0; i < ((String) key).length(); i++) {
      hashValue = (hashValue * 31 + ((String) key).charAt(i)) % sizeTable;
    }
    return hashValue;
  }

  public void add(K key, V value) {
    int index = hash(key);

    Node<K, V> newNode = new Node<K, V>(key, value);
    // si no hay ninguna colision
    if (table[index] == null) {
      table[index] = newNode;

    } else {
      // si hay colison, se agrega el nodo al final
      Node<K, V> current = table[index];
      while (current.getNext() != null) {
        current = current.getNext();
      }
      current.setNext(newNode);

    }
  }

  public V getValue(K key) {
    int index = hash(key);

    if (table[index] == null) {
      return null;
    } else {
      Node<K, V> current = table[index];
      while (current != null) {
        if (current.getKey().equals(key)) {
          return current.getValue();
        }
        current = current.getNext();
      }
      return null;
    }
  }

  public void remove(K key) {
    int index = hash(key);

    if (table[index] != null) {
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

  }

  /**
   * @return int return the sizeTable
   */
  public int getSizeTable() {
    return sizeTable;
  }

  /**
   * @param sizeTable the sizeTable to set
   */
  public void setSizeTable(int sizeTable) {
    this.sizeTable = sizeTable;
  }

  /**
   * @return Node<K, V>[] return the table
   */
  public Node<K, V>[] getTable() {
    return table;
  }

  /**
   * @param table the table to set
   */
  public void setTable(Node<K, V>[] table) {
    this.table = table;
  }

}
