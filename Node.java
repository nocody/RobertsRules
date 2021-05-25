import java.io.Serializable;

public class Node<E> implements Serializable {

	E element;
	
	Node<E> next;
	
	Node<E> prev;
	
	public Node(E element, Node<E> next, Node<E> prev) {
		this.element = element;
		this.next = next;
		this.prev = prev;
	}
	
}