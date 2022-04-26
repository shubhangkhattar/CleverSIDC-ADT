package testcases;

/**
 * @author Garvit Kataria (40155647) and Shubhang Khattar (40163063)  on 24-11-2021 (MM/DD/YYYY)
 * @project CleverSIDC ADT
 */

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Random;
import java.util.Scanner;

import random.CleverSIDC;

public class Test5Case3 {
	public static void main(String[] args) {
		long startTime = System.currentTimeMillis();
		String values[] = {"Kalia White  11/29/2021", 
				"Cynthia Knapp  06/03/2021", 
				"Rhiannon Albert  07/18/2022", 
				"Athena Sawyer  06/24/2022", 
				"Madaline Frye  05/08/2022", 
				"Martena Sharp  06/01/2022",
				"Harriet Holman  03/01/2021",
				"Kennedy Parks  01/14/2022"};
		Scanner sc = null;
		
		try {
			System.out.println("Reading dataset 5...");
			sc = new Scanner(new FileReader("./Test_Files/Test5_dataset_more_than_10^5"));
		} catch (FileNotFoundException e) {
			System.out.println(e.getMessage());
		}

		CleverSIDC cleverSIDC = new CleverSIDC(110000);
		
		System.out.println("==========");
		System.out.println("add(CleverSIDC,key,value2): add an entry for the given key and value;");
		
		int i = 0;
		while(sc.hasNext()) {
			String key = sc.next();
			Random rand = new Random();
			String value = values[rand.nextInt(8)];
			
			
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
		String value = cleverSIDC.getValues("00007726");
		System.out.println("Value of 00007726: " + value);
		
		System.out.println("==========");
		System.out.println("remove(CleverSIDC,key): remove the entry for the given key.");
		boolean isRemoved = cleverSIDC.remove("00007726");
		System.out.println("Removing key 00007726: " + isRemoved);
		value = cleverSIDC.getValues("00007726");
		System.out.println("Value of 00007726: " + value);
		System.out.println("Total number of keys:" + cleverSIDC.size);
		
		
		System.out.println("==========");
		System.out.println("prevKey(CleverSIDC,key): return the key for the predecessor of key;");
		System.out.println("Prev key of 00009557: " + cleverSIDC.prevKey("00009557"));
		
		System.out.println("==========");
		System.out.println("nextKey(CleverSIDC,key): return the key for the successor of key;");
		System.out.println("Next key of 00009557: " + cleverSIDC.nextKey("00009557"));
		
		System.out.println("==========");
		System.out.println("rangeKey(key1, key2): returns the number of keys that are within the specified range of the two keys (key1 and key2).");
		System.out.println("Number of keys between 00008726 & 00011726: " + cleverSIDC.rangeKey("00008726","00011726"));
		
		sc.close();
		long stopTime = System.currentTimeMillis();
	    long elapsedTime = stopTime - startTime;
	    System.out.println("Code Execution Time (Seconds): " + elapsedTime/1000);
	}
}
