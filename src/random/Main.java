package random;

/**
 * @author Garvit Kataria (40155647) and Shubhang Khattar (40163063)  on 24-11-2021 (MM/DD/YYYY)
 * @project CleverSIDC ADT
 */

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Scanner sc = null;
		
		try {
			sc = new Scanner(new FileReader("./NASTA_test_files/NASTA_test_file1.txt"));
		} catch (FileNotFoundException e) {
			System.out.println(e.getMessage());
		}

		CleverSIDC cleverSIDC = new CleverSIDC(500000);
		
		int i = 0;
		while(sc.hasNext() && i<100) {
			String key = sc.nextLine();
			System.out.println(key + " " + i);
			i++;
			cleverSIDC.add(key, "val: " + key);
		}
		System.out.println("printAvl: ");
//		cleverSIDC.printAvl();
		System.out.println();
//		System.out.println(cleverSIDC.allKeys());
		String[] temp = cleverSIDC.allKeys();
		for(String st: temp) System.out.println(st);
		System.out.println(cleverSIDC.allKeys().length);
		System.out.println(cleverSIDC.valueMap.keyset().size());
		
		System.out.println(cleverSIDC.getValues("15485764"));
		System.out.println(cleverSIDC.nextKey("15485764"));
		System.out.println(cleverSIDC.prevKey("15485764"));
		System.out.println(cleverSIDC.rangeKey("15485764","33218161"));
//		cleverSIDC.remove("00332885");
		
//		temp = cleverSIDC.allKeys();
//		for(String st: temp) System.out.println(st);
//		System.out.println(cleverSIDC.allKeys().length);
//		System.out.println(cleverSIDC.valueMap.keySet().size());
		sc.close();
	}

}

/*
 
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
	public static void getAllNodesInOrder(Node node, List<String> allKeys)
	{
		if (node != null)
		{
			
			getAllNodesInOrder(node.left, allKeys);
			allKeys.add(node.key);
			getAllNodesInOrder(node.right, allKeys);
		}
	}
	
 */
