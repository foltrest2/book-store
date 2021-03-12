package dataStructures;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class StackTest {

	private Stack<Integer> s;
	
	public void setupScenary1() {
		s = new Stack<>();
		s.push(1);
		s.push(2);
		s.push(3);
		s.push(4);
	}
	
	public void setupScenary2() {
		s = new Stack<>();
	}
	
	public void setupScenary3() {
		Integer [] testList = {1, 2, 3, 4};
		s = new Stack<>(testList);
	}
	
	@Test
	public void popTest1() {
		setupScenary1();
		int x = s.pop();
		assertEquals(4, x, "Fail test");
		assertEquals(3, s.top(), "Fail test");
		int y = s.pop();
		assertEquals(3, y, "Fail test");
		assertEquals(2, s.top(), "Fail test");
	}
	
	@Test
	public void popTest2() {
		setupScenary3();
		int x = s.pop();
		assertEquals(4, x, "Fail test");
		assertEquals(3, s.top(), "Fail test");
		int y = s.pop();
		assertEquals(3, y, "Fail test");
		assertEquals(2, s.top(), "Fail test");
	}
	
	@Test
	public void sizeTest() {
		setupScenary1();
		assertEquals(4, s.size(), "Fail test");
		s.pop();
		assertEquals(3, s.size(), "Fail test");
	}
	
	@Test
	public void isEmptyTest() {
		setupScenary2();
		assertEquals(true, s.isEmpty(), "Fail test");
	}
}
