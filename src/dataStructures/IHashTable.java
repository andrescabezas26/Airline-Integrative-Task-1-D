package dataStructures;

public interface IHashTable<K extends Comparable<K>, V> {

    /**
     * Función hash para obtener el índice de la tabla de hash.
     * 
     * @param key la clave del elemento que se quiere agregar, obtener o eliminar
     * @return el índice de la tabla de hash
     */
    int hash(K key);


    /**
   * Agrega un nuevo elemento a la tabla de hash.
   * 
   * @param key   la clave del nuevo elemento a agregar
   * @param value el valor del nuevo elemento a agregar
   */
    public void add(K key, V value);


    /**
   * Elimina un elemento de la tabla de hash a partir de su clave.
   * 
   * @param key la clave del elemento que se quiere eliminar
   */
    public void remove(K key);

    /**
   * Obtiene el valor de un elemento de la tabla de hash a partir de su clave.
   * 
   * @param key la clave del elemento del que se quiere obtener el valor
   * @return el valor del elemento con la clave dada, o null si no se encuentra en
   *         la tabla de hash
   */
    public V getValue(K key);
}