package hw2;

import exceptions.IndexException;
import exceptions.LengthException;
import org.junit.Before;
import org.junit.Test;

import java.util.Iterator;
import java.util.NoSuchElementException;

import static org.junit.Assert.*;

@SuppressWarnings("CheckStyle")
public abstract class ArrayTest {

  protected static final int LENGTH = 10;
  protected static final int INITIAL = 7;
  private Array<Integer> array;

  public abstract Array<Integer> createArray();

  @Before
  public void setup() {
    array = createArray();
  }

  @Test
  public void testConstructor() {
    assertEquals(LENGTH, array.length());
  }

  @Test
  public void testPut() {
    array.put(0, 20);
    assertEquals(20, array.get(0).intValue());
  }

  @Test(expected = IndexException.class)
  public void testPutThrowsException() {
    array.put(-1, 30);
  }

  // new defined tests below

  @Test
  public void testMorePut() {
    array.put(0, 20);
    assertEquals(20, array.get(0).intValue());
    array.put(0, INITIAL);
    assertEquals(INITIAL, array.get(0).intValue());
    array.put(0, INITIAL);
    assertEquals(INITIAL, array.get(0).intValue());
    array.put(2, 10);
    array.put(3, 15);
    assertEquals(10, array.get(2).intValue());
    assertEquals(15, array.get(3).intValue());
  }

  @Test
  public void getWorksAfterPut() {
    array.put(0, 3);
    assertEquals(3, array.get(0).intValue());
    array.put(LENGTH - 1, 10);
    assertEquals(10, array.get(LENGTH - 1).intValue());
    array.put(LENGTH/2, INITIAL*2);
    assertEquals(INITIAL*2, array.get(LENGTH/2).intValue());
  }

  @Test (expected = LengthException.class)
  public void constructorThrowsExceptionWithNegativeIndex () {
    array = new ListArray<Integer>(-1, 0);
  }

  @Test (expected = IndexException.class)
  public void getThrowsExceptionWithNegativeIndex() {
    this.array.get(-1);
  }

  @Test (expected = IndexException.class)
  public void getThrowsExceptionWithIndexTooLarge() {
    this.array.get(LENGTH + 1);
  }

  @Test (expected = IndexException.class)
  public void putThrowsExceptionWithIndexTooLarge() {
    this.array.put(LENGTH, 0);
  }

  @Test
  public void lengthWorks() {
      assertEquals(LENGTH, this.array.length());
  }

  @Test
  public void iteratorWorks() {
    array.put(0, 5);
    array.put(LENGTH -1, 20);
    Iterator<Integer> it = array.iterator();
    assertTrue(it.hasNext());
    assertEquals(5, it.next().intValue());
    assertEquals(INITIAL, it.next().intValue());
    assertEquals(INITIAL, it.next().intValue());
    assertEquals(INITIAL, it.next().intValue());
    assertEquals(INITIAL, it.next().intValue());
    assertEquals(INITIAL, it.next().intValue());
    assertEquals(INITIAL, it.next().intValue());
    assertEquals(INITIAL, it.next().intValue());
    assertEquals(INITIAL, it.next().intValue());
    assertEquals(20, it.next().intValue());
    assertFalse(it.hasNext());
  }

  @Test (expected = NoSuchElementException.class)
  public void TestIteratorGoesOutOfLinkedList() {
    Iterator<Integer> it = array.iterator();
    for (int i = 0; i < array.length(); i++) {
      it.next();
    }
    it.next();
  }

}
