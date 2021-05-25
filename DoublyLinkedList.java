
import java.io.Serializable;
import java.util.NoSuchElementException;

public class DoublyLinkedList<E> implements Serializable {

	public Node<E> head;
	public Node<E> tail;
	private int size;
	
	public DoublyLinkedList() {
		size = 0;
		
	}
	
	/*
	 * return the size of the linked list
	 */
	public int size() {return size;}
	/*
	 * return if linked list is empty
	 */
	public boolean isEmpty() {return size == 0;}
	/*
	 * add elements at the begining of the linked list
	 */
	public void addFirst(E element) {
		Node<E> tmp = new Node<E>(element, head, null);
		
		if(head != null) {head.prev = tmp;}
		head = tmp;
		if(tail == null) {tail = tmp;}
		size++;
//		System.out.println("adding: " + element);
	}// close add first
	
	/*
	 * add an element at the end of linked list
	 */
	public void addLast(E element) {
		Node<E> tmp = new Node<E>(element, null, tail);
		if( tail != null) {tail.next = tmp;}
		tail = tmp;
		if(head == null) { head = tmp;}
		size++;
//		System.out.println("adding: " + element);
	}//close add last
	
	/*
	 * this method walks forward through the linked list
	 */
	public void iterateForward() {
		//System.out.println("Iterating forward...");
		Node<E> tmp = head;
		while(tmp != null) {
			System.out.println(tmp.element);
			
			tmp = tmp.next;
		}
	}//close move forward
	
	/*
	 * this method walks forward through the linked list
	 */
	public void iterateBackward() {
		System.out.println("Iterating backward...");
		Node<E> tmp = tail;
		while(tmp != null) {
			System.out.println(tmp.element);
			tmp = tmp.prev;
		}
	}//close move backward
	
	/*
	 * method removes element from start of linked list
	 * 
	 */
	public E removeFirst() {
		if(size == 0) {throw new NoSuchElementException();}
		Node<E> tmp = head;
		head = head.next;
		head.prev = null;
		size--;
		System.out.println("Deleted: " + tmp.element);
		return tmp.element;
	}// close remove first
	
	/*
	 * method removes element from end of linked list
	 * 
	 */
	public E removeLast() {
		if(size == 0) {throw new NoSuchElementException();}
		Node<E> tmp = tail;
		tail = tail.prev;
		tail.next = null;
		size--;
		System.out.println("Deleted: " + tmp.element);
		return tmp.element;
	}// close remove last
	
	
}
