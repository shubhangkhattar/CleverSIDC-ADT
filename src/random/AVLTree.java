package random;

/**
 * @author Garvit Kataria (40155647) and Shubhang Khattar (40163063)  on 24-11-2021 (MM/DD/YYYY)
 * @project CleverSIDC ADT
 */



class Node {
	String key;
	int height;
	Node left, right;

	/**
	 * Node Class parameterized Constructor.
	 * @param key StudentIDentificationCode Number String.
	 */

	Node(String key) {
		this.key = key;
		this.height = 1;
		this.left = null;
		this.right = null;
	}
}

class AVLTree
{
	Node root;

	/**
	 * height method returns the height of the AVL Node.
	 * @param node
	 * @return
	 */

	int height(Node node) {
		if (node == null) return 0;
		return node.height;
	}

	/**
	 * max method returns max of two given integers.
	 * @param a
	 * @param b
	 * @return
	 */

	int max(int a, int b) {
		return (a > b) ? a : b;
	}

	/**
	 * rightRotate method Rotate the provided note in right direction.
	 * @param y
	 * @return
	 */

	Node rightRotate(Node y) {
		Node x = y.left;
		Node T2 = x.right;

		x.right = y;
		y.left = T2;

		y.height = max(height(y.left), height(y.right)) + 1;
		x.height = max(height(x.left), height(x.right)) + 1;

		return x;
	}

	/**
	 * leftRotate method Rotate the provided note in left direction.
	 * @param x
	 * @return
	 */

	Node leftRotate(Node x)
	{
		Node y = x.right;
		Node T2 = y.left;

		y.left = x;
		x.right = T2;

		x.height = max(height(x.left), height(x.right)) + 1;
		y.height = max(height(y.left), height(y.right)) + 1;

		return y;
	}

	/**
	 * getBalance method returns the balance factor of the given node.
	 * @param node
	 * @return	balance factor
	 */

	int getBalance(Node node)
	{
		if (node == null) return 0;
		return height(node.left) - height(node.right);
	}

	/**
	 * insert method methInsert the given node with given value in the AVL tree.
	 * @param node
	 * @param key
	 * @return
	 */

	Node insert(Node node, String key)
	{
		if (node == null)
			return (new Node(key));

		if (compareKeys(key, node.key) < 0) node.left = insert(node.left, key);
		else if (compareKeys(key, node.key) > 0) node.right = insert(node.right, key);
		else return node;

		node.height = 1 + max(height(node.left), height(node.right));


		int balance = getBalance(node);

		// Node becomes unbalanced => 4 cases 
		//Left Left Case
		if (balance > 1 && compareKeys(key, node.left.key) < 0) {
			return rightRotate(node);
		}
		
		// Right Right Case
		if (balance < -1 && compareKeys(key, node.right.key) > 0) {
			return leftRotate(node);
		}
		
		// Left Right Case
		if (balance > 1 && compareKeys(key, node.left.key) > 0) {
			node.left = leftRotate(node.left);
			return rightRotate(node);
		}

		// Right Left Case
		if (balance < -1 && compareKeys(key, node.right.key) < 0) {
			node.right = rightRotate(node.right);
			return leftRotate(node);
		}

		return node;
	}

	/**
	 * minValueNode method Returns the minimum value children node of given node.
	 * @param node
	 * @return
	 */

	Node minValueNode(Node node)
	{
		Node curr = node;
		while (curr.left != null) curr = curr.left;
		return curr;
	}

	/**
	 * deleteNode method Delete the Given node from the AVL tree.
	 * @param node
	 * @param key
	 * @return
	 */

	Node deleteNode(Node node, String key)
    {
        if (node == null) return node; 
        
        if (compareKeys(key, node.key) < 0) {
        	node.left = deleteNode(node.left, key);
        }
        else if (compareKeys(key, node.key) > 0) {
        	node.right = deleteNode(node.right, key);
        }
        else {
            if ((node.left == null) || (node.right == null))
            {
                Node temp = null;
                if (temp == node.left) temp = node.right;
                else temp = node.left;
 
                if (temp == null) {
                    temp = node;
                    node = null;
                }
                else {
                	node = temp; 
                }
            }
            else
            {
                Node temp = minValueNode(node.right);
                node.key = temp.key;
                node.right = deleteNode(node.right, temp.key);
            }
        }
 
        if (node == null)
            return node;
 
        node.height = max(height(node.left), height(node.right)) + 1;
 
        int balance = getBalance(node);
 
        // Node becomes unbalanced => 4 cases 
        // Left Left Case
        if (balance > 1 && getBalance(node.left) >= 0)
            return rightRotate(node);
 
        // Left Right Case
        if (balance > 1 && getBalance(node.left) < 0)
        {
        	node.left = leftRotate(node.left);
            return rightRotate(node);
        }
 
        // Right Right Case
        if (balance < -1 && getBalance(node.right) <= 0)
            return leftRotate(node);
 
        // Right Left Case
        if (balance < -1 && getBalance(node.right) > 0)
        {
        	node.right = rightRotate(node.right);
            return leftRotate(node);
        }
 
        return node;
    }

	/**
	 * compareKeys method Compare the two given keys in String representing numbers.
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
 	
 	static int idx;

	/**
	 * getAllNodesInOrder method populates the allKeys array with all the key values in given AVL tree.
	 * @param node
	 * @param allKeys
	 */

 	public static void getAllNodesInOrder(Node node, String[] allKeys) {
 		idx = 0;
		AVLTree.getAllNodesInOrderHelper(node, allKeys);
 	}

	/**
	 * Recursive helper function for getAllNodesInOrder.
	 * @param node
	 * @param allKeys
	 */
 	
	public static void getAllNodesInOrderHelper(Node node, String[] allKeys) {
		if (node != null) {
			getAllNodesInOrderHelper(node.left, allKeys);
			allKeys[idx++] = node.key;
			getAllNodesInOrderHelper(node.right, allKeys);
		}
	}

}