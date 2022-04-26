# CleverSIDC-ADT
Programmed a customized Abstract Data Type to store & manipulate data and keep balance between memory (space) & runtime complexity thus strategically optimizing performance for add, remove, prev, next & get operations in Java

CleverSIDC adapts to usage and keep the balance between memory and runtime requirements. For instance, if an CleverSIDC contains only a small number of entries (e.g., few hundreds), it might use less memory overhead but slower (sorting) algorithms. On the other hand, if the number of contained entries is large (greater than 1000 or even in the range of tens of thousands of elements), it might have a higher memory requirement but faster (sorting) algorithms. CleverSIDC might be almost constant in size or might grow and/or shrink dynamically. Ideally, operations applicable to a single CleverSIDC entry should be O(1) but never worse than O(n). Operations applicable to a complete CleverSIDC should not exceed O(n2).
You have been asked to design and implement the CleverSIDC ADT, which automatically adapts to the dynamic content that it operates on. In other words, it accepts the size (total number of students, n, identified by their 8 digits SIDC number as a key) as a parameter and uses an appropriate (set of) data structure(s), or other data types, from the one(s) studied in class in order to perform the operations below efficiently1. 

The CleverSIDC must implement the following methods:
• SetSIDCThreshold (Size): where 100 ≤ Size ≤ ~500,000 is an integer number that defines the size of the list. This size is very important as it will determine what data types or data structures will be used (i.e. a Tree, Hash Table, AVL tree, binary tree, sequence, etc.);
• generate(): randomly generates new non-existing key of 8 digits;
• allKeys(CleverSIDC): return all keys in CleverSIDC as a sorted sequence;
• add(CleverSIDC,key,value2): add an entry for the given key and value;
• remove(CleverSIDC,key): remove the entry for the given key;
• getValues(CleverSIDC,key): return the values of the given key;
• nextKey(CleverSIDC,key): return the key for the successor of key;
• prevKey(CleverSIDC,key): return the key for the predecessor of key;
• rangeKey(key1, key2): returns the number of keys that are within the specified range of the two keys key1 and key2.