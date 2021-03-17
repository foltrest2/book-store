package dataStructures;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import exceptions.EmptyQueueException;

class QueueTest {

	Queue<Integer> q;
	
	public void setupScenary1() {
		q = new Queue<>();
		q.enqueue(1);
		q.enqueue(2);
		q.enqueue(3);
		q.enqueue(4);
	}
	
	public void setupScenary2() {
		Integer [] testList = {1, 2, 3, 4};
		q = new Queue<>(testList);
	}
	
	@Test
	public void enqueueTest1() {
		setupScenary1();
		assertEquals(1, q.peek(), "Fail test");
	}
	
	@Test
	public void enqueueTest2() {
		setupScenary2();
		assertEquals(1, q.peek(), "Fail test");
	}
	
	@Test
	public void dequeueTest1() {
		setupScenary1();
		try {
			q.dequeue();
			assertEquals(2, q.peek(),"Fail test");
		} catch (EmptyQueueException e) {
			e.printStackTrace();
		}
		try {
			q.dequeue();
			assertEquals(3, q.peek(), "Fail test");
		} catch (EmptyQueueException e) {
			e.printStackTrace();
		}
		try {
			q.dequeue();
			assertEquals(4, q.peek(), "Fail test");
		} catch (EmptyQueueException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void dequeueTest2() {
		setupScenary1();
		try {
			q.dequeue();
			assertEquals(2, q.peek(),"Fail test");
		} catch (EmptyQueueException e) {
			e.printStackTrace();
		}
		try {
			q.dequeue();
			assertEquals(3, q.peek(), "Fail test");
		} catch (EmptyQueueException e) {
			e.printStackTrace();
		}
		try {
			q.dequeue();
			assertEquals(4, q.peek(), "Fail test");
		} catch (EmptyQueueException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void sizeTest() {
		setupScenary1();
		assertEquals(4, q.size(), "Fail test");
	}

}
