package hw2;

import exceptions.IndexException;
import org.junit.Before;
import org.junit.Test;

import java.util.Iterator;

import static org.junit.Assert.*;

public class SparseArrayTest extends ArrayTest {

  @Override
  public Array<Integer> createArray() {
    return new SparseArray<>(LENGTH, INITIAL);
  }

}