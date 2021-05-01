package com.moviecatalog.custom.structures.impl;

import com.moviecatalog.custom.structures.StackInter;

public class SinglyLinkedStack<T> implements StackInter<T> {
	
	public class Node {
		
		T data;
		
		Node next;

		public Node(T data) {
			
			this.data = data;
			
			this.next = null;
			
		}
		
	}
	
	private Node head;
	
	private Node tail;
	
	public SinglyLinkedStack() {
		
		this.head = this.tail = null;
		
	}

	public void push(T t) {
		
		Node newNode = new Node(t);
		
		if (head == null) {
			
			head = tail = newNode;
			
		} else {
			
			tail.next = newNode;
			
			tail = newNode;
			
		}
	}

	public T pop() throws Exception {
		
		if (tail == null) {
			
			throw new Exception("Lista vazia!");
			
		}
		
		T removed = tail.data;
		
		if (head == tail) {
			
			head = tail = null;
			
		} else {
			
			Node previous = findPreviousNode(tail);
			
			previous.next = null;
			
			tail = previous;
		}
		
		return removed;
	}

	public T peek() throws Exception {
		
		if (tail == null) {
			
			throw new Exception("Lista vazia!");
			
		}
		
		return tail.data;
	}
	
	private Node findPreviousNode(Node node) {
		
		Node ref = head;
		
		while (ref.next != node) {
			
			ref = ref.next;
			
		}
		
		return ref;
	}
	
	public boolean isEmpty() {
		
		return head == null;
		
	}
	
	public String toString() {
		
		String result = "";
		
		Node ref = head;
		
		while (ref != null) {
			
			result += ref.data + ",";
			
			ref = ref.next;
			
		}
		
		if (result.equals("")) {
			
			return "[]";
			
		}
		
		result = result.substring(0, result.length() - 1);
		
		return "[" + result + "]";
	}

}
