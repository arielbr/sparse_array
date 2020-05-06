package hw2;

import exceptions.IndexException;
import exceptions.LengthException;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class ListArrayTest extends ArrayTest {

  @Override
  public Array<Integer> createArray() {
    return new ListArray<>(LENGTH, INITIAL);
  }
}
