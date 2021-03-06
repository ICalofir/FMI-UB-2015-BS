package myQueue;

import java.util.*;

public class myQueue<E> implements Iterable<E> {
	LinkedList<E> elems;
	
	public myQueue() {
		elems = new LinkedList<E>();
	}
	
	public Iterator<E> iterator() {
		return new myQueueIterator();
	}
	
	private class myQueueIterator implements Iterator<E> {
		private int current = 0;
		public boolean hasNext() {
			return (current < elems.size());
		}
		
		public E next() {
			return elems.get(current++);
		}
	}
	
	public boolean isEmpty() {
		return !(elems.size() > 0);
	}
	
	public void add(E elem) {
		elems.add(elem);
	}
	
	public E remove() {
		return elems.remove();
	}
}