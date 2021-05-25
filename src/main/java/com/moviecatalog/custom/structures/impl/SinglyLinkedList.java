package com.moviecatalog.custom.structures.impl;

import javax.management.AttributeNotFoundException;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.moviecatalog.custom.structures.LinkedListInter;
import com.moviecatalog.model.BaseModel;

public class SinglyLinkedList<T extends BaseModel<T>> implements LinkedListInter<T> {

	public class Node {

		public T data;

		public Node next;

		public Node(T data) {

			this.data = data;

			this.next = null;

		}

	}

	private Node head;

	private Node tail;

	private int size;

	public SinglyLinkedList() {

		this.head = this.tail = null;

		this.size = 0;

	}

	public void add(T data) {

		Node newNode = new Node(data);

		if (head == null) {

			head = newNode;

			tail = newNode;

		} else {

			tail.next = newNode;

			tail = newNode;

		}

		size++;
	}

	public T peekFirst() throws Exception {

		if (head == null) {

			throw new Exception("Lista vazia!");

		}

		return head.data;
	}

	public T peekLast() throws Exception {

		if (tail == null) {

			throw new Exception("Lista vazia!");

		}

		return tail.data;
	}

	public T removeLast() throws Exception {

		if (tail == null) {

			throw new Exception("Lista vazia!");

		}

		T removed;

		if (head == tail) {

			removed = head.data;

			head = tail = null;

		} else {

			Node ref = findPreviousNode(tail);

			tail = ref;

			tail.next = null;

			removed = ref.data;
		}

		size--;

		return removed;
	}

	public T removeFirst() throws Exception {

		if (tail == null) {

			throw new Exception("Lista vazia!");

		}

		T removed;

		removed = head.data;

		if (head == tail) {

			head = tail = null;

		} else {

			head = head.next;

		}

		size--;

		return removed;
	}

	private Node findPreviousNode(Node node) {

		Node ref = head;

		while (ref.next != node) {

			ref = ref.next;

		}

		return ref;
	}

	@Override
	public String toString() {

		String result = "";

		Node ref = head;

		while (ref != null) {

			result += "{value: " + ref.data + "},\n ";

			ref = ref.next;

		}

		if (result.equals("")) {

			return "[]";

		}

		result = result.substring(0, result.length() - 3);

		return "[" + result + "]";
	}

	public void sortAsc(String attribute) throws AttributeNotFoundException {

		Node ref = head;
		
		Node index = null;

		if (head != null) {
			
			while (ref != null) {
				
				index = ref.next;

				while (index != null) {

					if (ref.data.compareTo(index.data, attribute) > 0) {
						
						swap(ref, index);
						
					}

					index = index.next;
				}
				
				ref = ref.next;
			}
			
		}

	}
	
	public void sortDesc(String attribute) throws AttributeNotFoundException {

		Node ref = head;
		
		Node index = null;

		if (head != null) {
			
			while (ref != null) {
				
				index = ref.next;

				while (index != null) {

					if (ref.data.compareTo(index.data, attribute) < 0) {
						
						swap(ref, index);
						
					}

					index = index.next;
				}
				
				ref = ref.next;
			}
			
		}

	}

	public void swap(Node a, Node b) {

		T temp = a.data;

		a.data = b.data;

		b.data = temp;

	}

	public Object formatToJSONObject() throws JsonMappingException, JsonProcessingException {

		String result = "";

		Node ref = head;

		while (ref != null) {

			result += ref.data.toJSONString() + ",";

			ref = ref.next;

		}

		if (result.equals("")) {

			return "[]";

		}

		result = result.substring(0, result.length() - 1);

		result = "[" + result + "]";

		Object obj = new ObjectMapper().readValue(result, Object.class);

		return obj;

	}

}
