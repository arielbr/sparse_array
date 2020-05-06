package hw2;

import exceptions.IndexException;
import exceptions.LengthException;
import java.util.Iterator;
import java.util.NoSuchElementException;


/**
 * Array implementation for a sparse array, which has mostly the same elements,
 * and a few modified elements. The user can just use objects from this class
 * outside as if it is a regular array
 * This is an implementation of the Array interface.
 *
 * @param <T> Element type.
 */
public class SparseArray<T> implements Array<T> {

  private Node<T> list;
  private int length;
  private T mostValue;

  /**
   * An array that is meant to be filled primarily with a default value
   * that is not going to change - with the benefit of that default
   * value not being stored numerous times as opposed to once.
   *
   * @param length       The number of indexes the array should have.
   * @param defaultValue The default value for the array.
   */
  public SparseArray(int length, T defaultValue) {
    if (length <= 0) {
      throw new LengthException();
    }
    this.length = length;
    this.mostValue = defaultValue;
    this.list = new Node(-1, null);
  }

  /**
   * return the length of an array.
   *
   * @return the length of array.
   */
  @Override
  public int length() {
    return this.length;
  }

  /**
   * get an element at an index.
   *
   * @param i Index to read value at.
   * @return the data (type T) at the index position.
   * @throws IndexException if index is negative or larger than length.
   */
  @Override
  public T get(int i) throws IndexException {
    if (i < 0 || i >= this.length) {
      throw new IndexException();
    }
    // if enters the if loop, showing value of the pos is not modified
    if (lookUp(i) == null) {
      return mostValue;
    }
    // reaching here, value of position modified
    return lookUp(i).data;
  }

  /**
   * attempt to find if the modified node of a specific index.
   *
   * @param i the index of position.
   * @return the node with that index.
   */
  private Node<T> lookUp(int i) {
    Node<T> current = list;
    while (current != null) {
      if (current.position == i) {
        return current;
      }
      current = current.next;
    }
    return null;
  }

  /**
   * insert a node into the front of the linked list if need to.
   *
   * @param newNode the node to be inserted.
   */
  private void insert(Node<T> newNode) {
    Node<T> current = list;
    // currently no modified value
    if (list == null) {
      list = newNode;
      return;
    } else if (list.position == -1) {
      list = newNode;
      return;
    }
    // add to the front node always
    newNode.next = list;
    list = newNode;
  }

  /**
   * put a data 't' at a given position i.
   *
   * @param i Index to write value at.
   * @param t Value to write at index.
   * @throws IndexException throw if index negative or too large.
   */
  @Override
  public void put(int i, T t) throws IndexException {
    if (i < 0 || i >= this.length) {
      throw new IndexException();
    }
    Node<T> target = lookUp(i);

    if (target != null) {
      // change a modified number back to most value
      if (t == mostValue) {
        delete(i);
        return;
      }
      // change the modified number to another value
      target.data = t;
      return;
    }
    if (t == mostValue) {
      return;
    }
    // add a node
    Node<T> newNode = new Node<T>(i, t);
    insert(newNode);
  }

  /**
   * delete a node at a given position.
   *
   * @param i the index of the node to be deleted
   */
  private void delete(int i) {
    // delete first node
    if (list.position == i) {
      list = list.next;
      return;
    }
    // delete middle node
    Node<T> current = list;
    // later parts of the linked list
    while (current.next != null) {
      if (current.next.position == i) {
        current.next = current.next.next;
      }
    }
    return;
  }

  /**
   * An iterator to traverse the array from front to back.
   * @return the SparseArrayIterator created.
   */
  @Override
  public Iterator<T> iterator() {
    return new SparseArrayIterator<T>();
  }

  /**
   * iterator class for the SparseArray class.
   * @param <T> the element type.
   */
  private class SparseArrayIterator<T> implements Iterator<T> {

    private int current;

    // iterator for the SparseArrayIterator class
    SparseArrayIterator() {
      current = 0;
    }

    /**
     * check if at the end of the linked list.
     *
     * @return true/false of if at end.
     */
    @Override
    public boolean hasNext() {
      return (current < length);
    }

    /**
     * traversing to the next node.
     *
     * @return the data at current node before going to the next.
     * @throws NoSuchElementException thrown if at end of list.
     */
    @Override
    public T next() throws NoSuchElementException {
      if (!hasNext()) {
        throw new NoSuchElementException();
      }
      T data = (T) get(current);
      current++;
      return data;
    }

    /**
     * remove a node.
     */
    @Override
    public void remove() {
      throw new UnsupportedOperationException();
    }
  }

  /**
   * an inner class to save each modified element in the array,
   * storing data, position, and next node of each node.
   *
   * @param <T> element type.
   */
  private static class Node<T> {
    private T data;
    private int position;
    private Node<T> next;

    /**
     * constructor of Node.
     *
     * @param position position of the node.
     * @param data the data of the node.
     */
    Node(int position, T data) {
      this.data = data;
      this.position = position;
      this.next = null;
    }

  }

}
