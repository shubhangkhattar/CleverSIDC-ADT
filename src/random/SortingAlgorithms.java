package random;

/**
 * @author Garvit Kataria (40155647) and Shubhang Khattar (40163063)  on 24-11-2021 (MM/DD/YYYY)
 * @project CleverSIDC ADT
 */

public class SortingAlgorithms {

	/**
	 * Implementation of mergeSort method.
	 * @param arr
	 * @param lo
	 * @param hi
	 * @return
	 */

	public static String[] mergeSort(String[] arr, int lo, int hi) {
		if(lo==hi) {
			String l[] = new String[1];
			l[0] = arr[lo];
			return l;
		}
		int m = lo+((hi-lo)/2);
		String[] a = mergeSort(arr, lo, m);
		String[] b = mergeSort(arr, m+1, hi);
		return mergeTwoSortedArrays(a,b);
	}

	/**
	 * Merge two arrays. Used in merge sort.
	 * @param a
	 * @param b
	 * @return
	 */

	public static String[] mergeTwoSortedArrays(String[] a, String[] b){
		int i = 0, j =0, k = 0;
		String[] ans = new String[a.length + b.length];
		while(i < a.length && j < b.length){
			if(compareKeys(a[i], b[j]) <= 0){
				ans[k] = a[i];
				i++;
				k++;
			}else{
				ans[k] = b[j];
				j++;
				k++;
			}
		}

		while(i < a.length){
			ans[k] = a[i];
			k++;
			i++;
		}

		while(j < b.length){
			ans[k] = b[j];
			k++;
			j++;
		}

		return ans;
	}

	/**
	 * print the contens of the given array.
	 * @param arr
	 */

	public static void print(String[] arr) {
		for (int i = 0; i < arr.length; i++) {
			System.out.print(arr[i] + " ");
		}
		System.out.println();
	}

	/**
	 * Comparing two numbers in string format.
	 * @param key
	 * @param key2
	 * @return
	 */

	private static int compareKeys(String key, String key2) {
		if(key.equals(key2)) return 0;

		if(key.length()==key2.length()) {
			for(int i=0; i<key.length(); i++) {
				if(key.charAt(i) > key2.charAt(i)) return 1;
				else if(key.charAt(i) < key2.charAt(i)) return -1;
			}
		}
		return key.length() > key2.length() ? 1 : -1;
	}

	/**
	 * Implementation of quickSort sortting algorithm.
	 * @param arr
	 * @param lo
	 * @param hi
	 */
	
	public static void quickSort(String[] arr, int lo, int hi) {
		if(lo > hi) return;
		int pidx = partition(arr, arr[hi], lo, hi);
		quickSort(arr, lo, pidx-1);
		quickSort(arr, pidx+1, hi);
	}

	/**
	 * Used to create partition in quick sort.
	 * @param arr
	 * @param pivot
	 * @param lo
	 * @param hi
	 * @return
	 */

	public static int partition(String[] arr, String pivot, int lo, int hi) {
		int i = lo, j = lo;
		while (i <= hi) {
			if (compareKeys(arr[i], pivot) <= 0) {
				swap(arr, i, j);
				i++;
				j++;
			} else {
				i++;
			}
		}
		return (j - 1);
	}

	/**
	 * Helper method to swap two indexes.
	 * @param arr
	 * @param i
	 * @param j
	 */

	public static void swap(String[] arr, int i, int j) {
		String temp = arr[i];
		arr[i] = arr[j];
		arr[j] = temp;
	}
}
