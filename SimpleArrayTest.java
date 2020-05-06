package hw2;

import exceptions.IndexException;
import exceptions.LengthException;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class SimpleArrayTest extends ArrayTest {

  @Override
  public Array<Integer> createArray() {
    return new SimpleArray<>(LENGTH, INITIAL);
  }

}
