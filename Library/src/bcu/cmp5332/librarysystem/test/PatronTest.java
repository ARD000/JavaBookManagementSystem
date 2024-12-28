package bcu.cmp5332.librarysystem.test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import bcu.cmp5332.librarysystem.model.Patron;

class PatronTest {

	@Test
	void testPatronConstructor() {
		Patron patron = new Patron(1,"Will","077777777","will@");
		assertEquals(1,patron.getId());
		assertEquals("Will",patron.getName());
		assertEquals("077777777",patron.getPhone());
		assertEquals("will@",patron.getEmail());
		
	}

	@Test
	void testGetId() {
		Patron patron = new Patron(1,"Will","077777777","will@");
		assertEquals(1,patron.getId());
		
	}

	@Test
	void testSetId() {
		Patron patron = new Patron(1,"Will","077777777","will@");
		patron.setId(0);
		assertEquals(0,patron.getId());
		
	}

	@Test
	void testGetName() {
		Patron patron = new Patron(1,"Will","077777777","will@");
		assertEquals("Will",patron.getName());
	}

	@Test
	void testSetName() {
		Patron patron = new Patron(1,"Will","077777777","will@");
		patron.setName("Bobby");
		assertEquals("Bobby",patron.getName());
		
	}

	@Test
	void testGetPhone() {
		Patron patron = new Patron(1,"Will","077777777","will@");
		assertEquals("077777777",patron.getPhone());
	}

	@Test
	void testSetPhone() {
		Patron patron = new Patron(1,"Will","077777777","will@");
		patron.setPhone("000000000");
		assertEquals("000000000",patron.getPhone());
		
	}

	@Test
	void testGetBooks() {
		fail("Not yet implemented");
	}

	@Test
	void testGetEmail() {
		fail("Not yet implemented");
	}

	@Test
	void testSetEmail() {
		fail("Not yet implemented");
	}

	@Test
	void testBorrowBook() {
		fail("Not yet implemented");
	}

	@Test
	void testRenewBook() {
		fail("Not yet implemented");
	}

	@Test
	void testReturnBook() {
		fail("Not yet implemented");
	}

	@Test
	void testAddBook() {
		fail("Not yet implemented");
	}

}
