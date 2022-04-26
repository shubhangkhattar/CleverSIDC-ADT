package random;

/**
 * @author Garvit Kataria (40155647) and Shubhang Khattar (40163063)  on 24-11-2021 (MM/DD/YYYY)
 * @project CleverSIDC ADT
 */

import java.util.Iterator;

public class LinkedList<K> implements Iterable<K>{
	public static class Node<K> {
		K data;
		Node<K> next;
	}
	Node<K> head;
	Node<K> tail;
	int size;

	/**
	 * add method adds the given val to the linked list.
	 * @param val
	 */

	void add(K val) {
		Node<K> temp = new Node<>();
		temp.data = val;
		temp.next = null;

		if (size == 0) {
			head = tail = temp;
		} else {
			tail.next = temp;
			tail = temp;
		}

		size++;
	}

	/**
	 * returns the size of the linked list.
	 * @return
	 */

	public int size() {
		return size;
	}

	/**
	 * displays method display the linked list values.
	 */

	public void display() {
		for (Node<K> temp = head; temp != null; temp = temp.next) {
			System.out.print(temp.data + " ");
		}
		System.out.println();
	}

	/**
	 * Remove method removes the first limit in the linked list.
	 * @return
	 */

	public K removeFirst() {
		if (size == 0) {
			System.out.println("List is empty");
		} else if (size == 1) {
			K removeNode = head.data;
			head = tail = null;
			size = 0;
			return removeNode;
		} else {
			K removeNode = head.data;
			head = head.next;
			size--;
			return removeNode;
		}
		return null;
	}

	/**
	 * getFirst method returns the first node of the linked list.
	 * @return
	 */

	public K getFirst() {
		if (size == 0) {
			System.out.println("List is empty");
			return null;
		} else {
			return head.data;
		}
	}

	/**
	 * getLast method returns the last method in the linked list.
	 * @return
	 */

	public K getLast() {
		if (size == 0) {
			System.out.println("List is empty");
			return null;
		} else {
			return tail.data;
		}
	}

	/**
	 * Returns the given idx'th element from the Linked List.
	 * @param idx
	 * @return
	 */

	public K get(int idx) {
		if (size == 0) {
			System.out.println("List is empty");
			return null;
		} else if (idx < 0 || idx >= size) {
			System.out.println("Invalid arguments");
			return null;
		} else {
			Node<K> temp = head;
			for (int i = 0; i < idx; i++) {
				temp = temp.next;
			}
			return temp.data;
		}
	}

	/**
	 * addFirst method adds the element at the beginning of the list.
	 * @param val
	 */

	public void addFirst(K val) {
		Node<K> temp = new Node<K>();
		temp.data = val;
		temp.next = head;
		head = temp;

		if (size == 0) {
			tail = temp;
		}

		size++;
	}

	/**
	 * add the element at n'th position in linked list.
	 * @param idx
	 * @param val
	 */

	public void add(int idx, K val) {
		if (idx < 0 || idx > size) {
			System.out.println("Invalid arguments");
		} else if (idx == 0) {
			addFirst(val);
		} else if (idx == size) {
			add(val);
		} else {
			Node<K> node = new Node<K>();
			node.data = val;

			Node<K> temp = head;
			for (int i = 0; i < idx - 1; i++) {
				temp = temp.next;
			}
			node.next = temp.next;

			temp.next = node;
			size++;
		}
	}

	/**
	 * Remove the last element from the linked list
	 * @return
	 */

	public K removeLast() {
		if (size == 0) {
			System.out.println("List is empty");
		} else if (size == 1) {
			K removeNode = head.data;
			head = tail = null;
			size = 0;
			return removeNode;
		} else {
			Node<K> temp = head;
			for (int i = 0; i < size - 2; i++) {
				temp = temp.next;
			}
			K removeNode = tail.data;
			tail = temp;
			tail.next = null;
			size--;
			return removeNode;
		}
		return null;
	}

	/**
	 * remove method removes the n'th element from the linked list.
	 * @param idx
	 * @return
	 */

	public K remove(int idx) {
		if (idx < 0 || idx >= size) {
			System.out.println("Invalid arguments");
		} else if (idx == 0) {
			return removeFirst();
		} else if (idx == size - 1) {
			return removeLast();
		} else {
			Node<K> temp = head;
			for (int i = 0; i < idx - 1; i++) {
				temp = temp.next;
			}
			K removeNode = temp.next.data;
			temp.next = temp.next.next;
			size--;
			return removeNode;
		}
		return null;
	}

	/**
	 * Overridden Iterator method of interface Iterable.
	 * Returning the iterator object.
	 * @return
	 */

	public Iterator<K> iterator()
	{
		return new ListIterator<K>(this);
	}

}

class ListIterator<T> implements Iterator<T> {

	LinkedList.Node<T> current;

	// initialize pointer to head of the list for iteration
	public ListIterator(LinkedList<T> list)
	{
		current = list.head;
	}

	// returns false if next element does not exist
	public boolean hasNext()
	{
		return current != null;
	}

	// return current data and update pointer
	public T next()
	{
		T data = current.data;
		current = current.next;
		return data;
	}
}
