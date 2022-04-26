package random;

/**
 * @author Garvit Kataria (40155647) and Shubhang Khattar (40163063)  on 24-11-2021 (MM/DD/YYYY)
 * @project CleverSIDC ADT
 */

import java.util.ArrayList;
import java.util.List;

public class HashMap<K, V> {
	private class HMNode {
		K key;
		V value;

		/**
		 * Paramterized Constructor for HMNode inner class.
		 * @param key
		 * @param value
		 */

		HMNode(K key, V value) {
			this.key = key;
			this.value = value;
		}
	}
	private int size; // n
	private LinkedList<HMNode>[] buckets; // N = buckets.length

	/**
	 * Default Constructor of Hashmap class.
	 * Initialize the default bucket size to 1000.
	 */

	public HashMap() {
		initbuckets(1000);
		size = 0;
	}

	/**
	 * initbuckets method initialize the bucket with empty LinkedList of type HMNode.
	 * The size of the bucket is given as paramter.
	 * @param N
	 */

	private void initbuckets(int N) {
		buckets = new LinkedList[N];
		for (int bi = 0; bi < buckets.length; bi++) {
			buckets[bi] = new LinkedList<HMNode>();
		}
	}

	/**
	 * getBucketIdx returns the HashIndex based on the given key.
	 * @param key
	 * @return
	 */

	public int getBucketIdx(K key) {
		int hc = key.hashCode();
		int bi = Math.abs(hc) % buckets.length;
		return bi;
	}

	/**
	 * Traverse the Linked List at given hashindex in bucket to find the key index.
	 * @param key
	 * @param bidx
	 * @return
	 */

	public int getKeyIdx(K key, int bidx) {
		int idx = 0;
		for(HMNode node: buckets[bidx]) {
			if(key.equals(node.key)) 
				return idx;
			idx++;
		}
		return -1;
	}

	/**
	 * put function inserts or update the given key value pair in the HashMap.
	 * @param key
	 * @param value
	 */

	public void put(K key, V value) {
		int bidx = getBucketIdx(key);
		int kidx = getKeyIdx(key, bidx);
		if(kidx == -1) {
			buckets[bidx].add(new HMNode(key, value));
			size++;
		}
		else {
			buckets[bidx].get(kidx).value = value;  
		}

		double lambda = (size*1.0) / buckets.length;
		if(lambda > 2.0) {
			rehash();
		}
	}

	/**
	 * When load factor is more than 2, rehashing of the hashmap is done by doubling the bucket size.
	 */

	public void rehash() {
		LinkedList<HMNode>[] old = buckets;
		initbuckets(old.length*2);
		size = 0;
		for(LinkedList<HMNode> l: old) {
			for(HMNode node: l) {
				put(node.key, node.value);
			}
		}
	}

	/**
	 * get method returns the value corresponding to the given key.
	 * @param key
	 * @return
	 */

	public V get(K key) {
		int bidx = getBucketIdx(key);
		int kidx = getKeyIdx(key, bidx);
		if(kidx == -1) {
			return null;
		}
		else {
			return buckets[bidx].get(kidx).value;
		}
	}

	/**
	 * containsKey is used to know whether the given key is already present in the hashmap or not.
	 * @param key
	 * @return
	 */

	public boolean containsKey(K key) {
		int bidx = getBucketIdx(key);
		int kidx = getKeyIdx(key, bidx);
		return kidx == -1? false: true;
	}

	/**
	 * remove method remove the key and returns the corresponding value.
	 * @param key
	 * @return
	 */

	public V remove(K key) {
		int bidx = getBucketIdx(key);
		int kidx = getKeyIdx(key, bidx);
		if(kidx == -1) {
			return null;
		}
		else {
			HMNode temp = buckets[bidx].remove(kidx);
			size--;
			return temp.value;
		}

	}

	/**
	 * keyset method returns the list having all the key values present in the hashmap
	 * @return
	 */

	public List<K> keyset() {
		List<K> ll = new ArrayList<>();
		for(LinkedList<HMNode> l: buckets) {
			for(HMNode node: l) {
				ll.add(node.key);
			}
		}
		return ll;
	}

	/**
	 * size method returns the size of the hashmap.
	 * @return
	 */

	public int size() {
		return size;
	}

	/**
	 * display method displays the hashmap key and values in each bucket of hashmap.
	 */

	public void display() {
		System.out.println("Display Begins");
		for (int bi = 0; bi < buckets.length; bi++) {
			System.out.print("Bucket" + bi + " ");
			for (HMNode node : buckets[bi]) {
				System.out.print( node.key + "@" + node.value + " ");
			}
			System.out.println(".");
		}
		System.out.println("Display Ends");
	}
}
