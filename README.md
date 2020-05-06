# Discussion

### When would a `SparseArray` be useful and why?

A sparse array is useful when there are an enormous amount of data but largely the same value. 
Then it helps accelerate extracting element by index ('.get()') with better time complexitiy and
also saves memory space for better processing speed and for other programs. It does so by not saving
the entire array as one linked list or one array, but only tracks 1) length of array so exceptions will be handled, 2) one linked
list saving the position, data, and next node of each value-modified node, 3) the value 
of most elements, so the user can still access and modify elements by index and index + value as if
using a normal array.


### Why is it that `SparseArray` must implement `iterator()`? Also, why is it beneficial for `SparseArray` to have an iterator?
  
The SparseArray class must implement the iterator because the interface of this class, Array, extends Iterable, and thus its
classes must implement iterator(), which is a function of the Iterable interface. It is beneficial because outside of the SparseArray
class, the users can use .next() to access the element with incremented index, just like ListArray.