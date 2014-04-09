package aufgabe3;

import java.util.concurrent.atomic.AtomicReference;

// TODO: Implement lock-free stack
public class LockFreeStack<T> implements Stack<T> {
	
	public class Node<T> {
		public final T item;
		public Node<T> next;
		
		public Node(T item) {
            this.item = item;
        }
		
		public void setNext(Node<T> next) {
			this.next = next;
		}
	}
	
	
  AtomicReference<Node<T>> top = new AtomicReference<>();
  
  public void push(T value) {
    Node<T> newNode = new Node<>(value);
    Node<T> current;
    do {
    	current = top.get();
    	newNode.setNext(current);
    } while (!top.compareAndSet(current, newNode));
  }
  
  public T pop() {
    Node<T> oldTop;
    Node<T> newTop;
    do {
    	oldTop = top.get();
    	newTop = oldTop.next;
    	} while (!top.compareAndSet(oldTop, newTop));
    	return oldTop.item;
    }
}
