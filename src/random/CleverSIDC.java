package random;

/**
 * @author Garvit Kataria (40155647) and Shubhang Khattar (40163063)  on 24-11-2021 (MM/DD/YYYY)
 * @project CleverSIDC ADT
 */

import java.util.Random;

/*
 * 1-10^3 - Quick Sort
 * 10^3 - 10^5 - Merge Sort
 * 10^5 - 10^6 - AVL Tree
 * */

public class CleverSIDC {

	public HashMap<String, String> valueMap;
	AVLTree tree;
	String[] listStudents;
	public int size;
	int thresholdSize;

	/**
	 * Parameterized constructor initializes the CleverSIDC ADT with the given size.
	 * @param size
	 */
	
	public CleverSIDC(int size) {
		valueMap = new HashMap<>();
		
		if(size >= 100000) {
			tree = new AVLTree();
			System.out.println("Size is " + size + " in Range (10^5 - 10^6) => AVL Tree");
		}
		else if(size >= 1000 && size < 100000) {
			listStudents = new String[size];
			System.out.println("Size is " + size + " in Range (10^3 - 10^5) => Merge Sort");
		}
		else if(size >= 1 && size < 1000) {
			listStudents = new String[size];
			System.out.println("Size is " + size + " in Range (10^0 - 10^3) => Quick Sort");
		}
		
		this.size = 0;
		SetSIDCThreshold(size);
	}

	/**
	 * sets the CleverSIDC ADT size threshhold.
	 * @param size
	 */

	private void SetSIDCThreshold(int size) {
		this.thresholdSize = size;
	}

	/**
	 *
	 * @return
	 */

	/**
	 * generate method generate a random 8 digit number String
	 * @return
	 */

	public String generate() {
		Random random = new Random();
		String newKey = String.valueOf(random.nextInt(99999999 - 10000000 + 1) + 10000000);
		if(valueMap.containsKey(newKey)) return generate();
		return newKey;
	}

	/**
	 * allKeys emthod returns all the keys in CleverSIDC ADT in sorted manner as per keys.
	 * The sorting algorithm chosen is based on the size of CleverSIDC data type.
	 * @return
	 */
	
	public String[] allKeys() {
		if(this.thresholdSize >= 100000) {
			String[] allKeys = new String[this.size];
			AVLTree.getAllNodesInOrder(tree.root, allKeys); // O(n)
			return allKeys;
		}
		else if(this.thresholdSize >= 1000 && this.thresholdSize < 100000) {
			String[] allKeys = SortingAlgorithms.mergeSort(listStudents, 0, size-1); // O(nlogn)
			return allKeys;
		}
		else if(this.thresholdSize >= 1 && this.thresholdSize < 1000) {
			String[] allKeys = new String[this.size];
			SortingAlgorithms.quickSort(listStudents, 0, size-1); // theta(nlogn)
			for(int i=0; i<size; i++) allKeys[i] = listStudents[i];
			return allKeys;
		}
		return listStudents;
	}

	/**
	 * add method adds key value pair to the CleverSIDC ADT.
	 * @param key
	 * @param value
	 * @return
	 */
	
	public boolean add(String key, String value) {
		if(valueMap.containsKey(key)) return false;
		
		if(this.thresholdSize >= 100000) {
			tree.root = tree.insert(tree.root, key); // O(log(n))
		}
		else if(this.thresholdSize >= 1000 && this.thresholdSize < 100000) {
			listStudents[size] = key; // O(1)
		}
		else if(this.thresholdSize >= 1 && this.thresholdSize < 1000) {
			listStudents[size] = key; // O(1)
		}
		
		valueMap.put(key, value);
		size++;
		return true;
	}

	/**
	 * remove method adds key value pair to the CleverSIDC ADT.
	 * @param key
	 * @return
	 */
	
	public boolean remove(String key) {
		if(!valueMap.containsKey(key)) return false;
		
		if(this.thresholdSize >= 100000) {
			tree.deleteNode(tree.root, key); // O(log(n))
		}
		else if(this.thresholdSize >= 1000 && this.thresholdSize < 100000) {
			removeFromListStudents(key); // O((n)
		}
		else if(this.thresholdSize >= 1 && this.thresholdSize < 1000) {
			removeFromListStudents(key); // O((n)
		}
		valueMap.remove(key);
		size--;
		return true;
	}

	/**
	 * getValues method return the Value to the given corresponding key in the CleverSIDC ADT.
	 * @param key
	 * @return
	 */
	
	public String getValues(String key) {
		return valueMap.get(key); // O(1)
	}

	/**
	 * nextKey method, returns the next key in the CleverSIDC data type.
	 * @param key
	 * @return
	 */
	
	public String nextKey(String key) {
		String[] sortedKeys = this.allKeys();
		for(int i=0; i<size; i++) {
			if(this.compareKeys(key, sortedKeys[i])<0) {
				return sortedKeys[i];
			}
		}
		return null;
	}

	/**
	 * prevKey method, returns the previous key in the CleverSIDC data type.
	 * @param key
	 * @return
	 */

	public String prevKey(String key) {
		String[] sortedKeys = this.allKeys();
		for(int i=0; i<size; i++) {
			if( this.compareKeys(key, sortedKeys[i])<=0 && i>0) {
				return sortedKeys[i-1];
			}
		}
		return null;
	}

	/**
	 * rangeKey returns the number of elements between the given two keys.
	 * @param key1
	 * @param key2
	 * @return
	 */
	
	public int rangeKey(String key1, String key2) {
		String[] sortedKeys = this.allKeys();
		int startIdx = 1;
		int endIdx = 0;
		for(int i=0; i<size; i++) {
			if(this.compareKeys(key1, sortedKeys[i]) >=0) {
				startIdx = i;
			}
		}
		for(int i=0; i<size; i++) {
			if(this.compareKeys(key2, sortedKeys[i]) >= 0) {
				endIdx = i;
			}
		}
		return endIdx - startIdx - 1;
	}

	/**
	 * removeFromListStudents method returns the
	 * @param key
	 */
	
	public void removeFromListStudents(String key) {
		int remIdx = size;
		for(int i=0; i<size; i++) {
			if(key.equals(listStudents[i])) {
				remIdx = i;
				break;
			}
		}
		
		if(remIdx != size) {
			for(int i=remIdx; i<size-1; i++) {
				listStudents[i] = listStudents[i+1];
			}
			listStudents[size-1] = null;
		}
	}

	/**
	 * Comparing two numbers in string format.
	 * @param key
	 * @param key2
	 * @return
	 */
	
 	private int compareKeys(String key, String key2) {
		if(key.equals(key2)) return 0;
		
		if(key.length()==key2.length()) {
			for(int i=0; i<key.length(); i++) {
				if(key.charAt(i) > key2.charAt(i)) return 1;
				else if(key.charAt(i) < key2.charAt(i)) return -1;
			}
		}
		return key.length() > key2.length() ? 1 : -1;
	}
}
