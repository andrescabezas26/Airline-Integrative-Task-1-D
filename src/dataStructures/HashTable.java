package dataStructures;

public class HashTable<K extends Comparable<K>, V> implements IHashTable<K, V> {

  private int sizeTable;
  private Node<K, V>[] table;

  @SuppressWarnings("unchecked")
  public HashTable(int sizeTable) {
    this.sizeTable = sizeTable;
    this.table = (Node<K, V>[]) new Node[sizeTable];
  }

  /**
   * Función hash para obtener el índice de la tabla de hash.
   * 
   * @param key la clave del elemento que se quiere agregar, obtener o eliminar
   * @return el índice de la tabla de hash
   */
  @Override
  public int hash(K key) {
    return Math.abs(key.hashCode()) % sizeTable;
  }

  /**
   * Agrega un nuevo elemento a la tabla de hash.
   * 
   * @param key   la clave del nuevo elemento a agregar
   * @param value el valor del nuevo elemento a agregar
   */
  @Override
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

  /**
   * Obtiene el valor de un elemento de la tabla de hash a partir de su clave.
   * 
   * @param key la clave del elemento del que se quiere obtener el valor
   * @return el valor del elemento con la clave dada, o null si no se encuentra en
   *         la tabla de hash
   */
  @Override
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

  /**
   * Elimina un elemento de la tabla de hash a partir de su clave.
   * 
   * @param key la clave del elemento que se quiere eliminar
   */
  @Override
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
