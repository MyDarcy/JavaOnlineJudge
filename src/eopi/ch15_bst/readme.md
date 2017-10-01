- BSTs offer the ability to search for a key as well as find the min and max elements, look for the successor or predecessor of a search key
(which itself need not be present in the BST), and enumerate the keys in a range in
sorted order.

- There are two BST-based data structures commonly used in Java-TreeSet and TreeMap. 

- TreeSet
    - The iterator returned by **iterate()** traverses keys in **ascending order**. 
    (To iterate over keys in **descending order**, use **descendinglterator()**.)
    - first()/last() yield the **smallest and largest** keys in the tree.
    - lower(12)/higher(3) yield the largest element strictly less than the argument/smallest
     element strictly greater than the argument
    - floor(4.9)/ceiling(5.7) yield the largest element less than or equal to the 
     argument/smallest element greater than or equal to the argument.
    - headSet(10), tailSet(5), subSet(1 , 12) return viewsof theportionof the keys lying 
     in the given range. It'sparticularly important to note that these operations take
      **O(logn)** time, since they are backed by the underlying tree. This implies changes
     to the tree may change the view. It also means that operations like size() have O(n) 
     time complexity.

- The TreeSet and TreeMap constructors permit for an explicit comparator object that
  is used to order keys