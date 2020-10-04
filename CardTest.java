import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * 
 */

/**
 * @author zeemh
 *
 */
class CardTest {
	Card test;
	/**
	 * @throws java.lang.Exception
	 */
	@BeforeEach
	void setUp() throws Exception {
		
		test = new Card(1, "heart");
	}



	/**
	 * Test method for {@link Card#Card()}.
	 */
	@Test
	void testCard() {
		Card ace = new Card();
		assertTrue(ace.getValue() == 0 && ace.getSuit() == "");
	}

	/**
	 * Test method for {@link Card#Card(int, java.lang.String)}.
	 */
	@Test
	void testCardIntString() {
		Card threeOfSpades = new Card(3, "spade");
		assertTrue(threeOfSpades.getValue() == 3 && threeOfSpades.getSuit() == "spade");
	}

	/**
	 * Test method for {@link Card#getValue()}.
	 */
	@Test
	void testGetValue() {
		assertTrue(test.getValue() == 1);
	}

	/**
	 * Test method for {@link Card#getSuit()}.
	 */
	@Test
	void testGetSuit() {
		assertTrue(test.getSuit().equals("heart"));
	}

	/**
	 * Test method for {@link Card#setValue(int)}.
	 */
	@Test
	void testSetValue() {
		test.setValue(4);
		assertTrue(test.getValue() == 4);
	}

	/**
	 * Test method for {@link Card#setSuit(java.lang.String)}.
	 */
	@Test
	void testSetSuit() {
		test.setSuit("club");
		assertTrue(test.getSuit().equals("club"));
	}

	/**
	 * Test method for {@link Card#getColor()}.
	 */
	@Test
	void testGetColor() {
		assertTrue(test.getColor().equals("red"));
	}

}
