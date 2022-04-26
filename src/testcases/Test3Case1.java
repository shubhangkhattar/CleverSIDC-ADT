package testcases;

/**
 * @author Garvit Kataria (40155647) and Shubhang Khattar (40163063)  on 24-11-2021 (MM/DD/YYYY)
 * @project CleverSIDC ADT
 */


import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

import random.CleverSIDC;

public class Test3Case1 {
	public static void main(String[] args) {
		long startTime = System.currentTimeMillis();
		Scanner sc = null;
		
		try {
			System.out.println("Reading dataset 3...");
			sc = new Scanner(new FileReader("./Test_Files/Test3_dataset_less_than_10^3"));
		} catch (FileNotFoundException e) {
			System.out.println(e.getMessage());
		}

		CleverSIDC cleverSIDC = new CleverSIDC(500);
		
		System.out.println("==========");
		System.out.println("add(CleverSIDC,key,value2): add an entry for the given key and value;");
		
		int i = 0;
		while(sc.hasNext()) {
			String key = sc.next();
			String value = sc.nextLine().strip();
			
			
			boolean isAdded = cleverSIDC.add(key, value);
			System.out.println(i + ". Key:" + key + " Value: " + value + " isAdded: " + isAdded);
			i++;
		}

		System.out.println("==========");
		System.out.println("allKeys(CleverSIDC): All keys in CleverSIDC as a sorted sequence.");
		String[] allKeys = cleverSIDC.allKeys();
		System.out.println("Total number of keys:" + allKeys.length);
		System.out.println("First 10 keys are: ");
		for(i=0; i<allKeys.length && i<10; i++) System.out.println(allKeys[i]);
		
		System.out.println("==========");
		System.out.println("generate(): randomly generates new non-existing key of 8 digits.");
		String sidc = cleverSIDC.generate();
		System.out.println("SIDC: "+ sidc);
		
		System.out.println("==========");
		System.out.println("getValues(CleverSIDC,key): return the values of the given key.");
		String value = cleverSIDC.getValues("01122277");
		System.out.println("Value of 01122277: " + value);
		
		System.out.println("==========");
		System.out.println("remove(CleverSIDC,key): remove the entry for the given key.");
		boolean isRemoved = cleverSIDC.remove("01122277");
		System.out.println("Removing key 01122277: " + isRemoved);
		value = cleverSIDC.getValues("01122277");
		System.out.println("Value of 01122277: " + value);
		System.out.println("Total number of keys:" + cleverSIDC.size);
		
		
		System.out.println("==========");
		System.out.println("prevKey(CleverSIDC,key): return the key for the predecessor of key;");
		System.out.println("Prev key of 02011291: " + cleverSIDC.prevKey("02011291"));
		
		System.out.println("==========");
		System.out.println("nextKey(CleverSIDC,key): return the key for the successor of key;");
		System.out.println("Next key of 02011291: " + cleverSIDC.nextKey("02011291"));
		
		System.out.println("==========");
		System.out.println("rangeKey(key1, key2): returns the number of keys that are within the specified range of the two keys (key1 and key2).");
		System.out.println("Number of keys between 01720675 & 02247111: " + cleverSIDC.rangeKey("01720675","02247111"));
		
		sc.close();
		long stopTime = System.currentTimeMillis();
	    long elapsedTime = stopTime - startTime;
	    System.out.println("Code Execution Time (Seconds): " + elapsedTime/1000);
	}
}
