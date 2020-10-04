import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class CardDeckTest {
	CardDeck testDeck;
	Card testCard;
	
	@BeforeEach
	void setUp() throws Exception {
		testDeck = new CardDeck();
		testDeck.initialize();
	}

	@Test
	void testDraw() {
		for (int i = 0 ; i < 52 ; i++) {
			assertTrue(testDeck.getDeckSize() == 52-i);	
			testCard = testDeck.draw();
			assertTrue( ( testCard.getValue() == 1 || testCard.getValue() == 2 || testCard.getValue() == 3 || 
					testCard.getValue() == 4 || testCard.getValue() == 5 || testCard.getValue() == 6 || testCard.getValue() == 7 ||
					 testCard.getValue() == 8 || testCard.getValue() == 9 || testCard.getValue() == 10 || testCard.getValue() == 11 ||
					 testCard.getValue() == 12 || testCard.getValue() == 13) );
			assertTrue( ( testCard.getSuit() == "spade" || testCard.getSuit() == "club" || testCard.getSuit() == "heart" || 
					testCard.getSuit() == "diamond" ) );
		}
	}

	@Test
	void testGetRemainingCards() {
		//doesn't contain drawn cards
		testCard = testDeck.draw();
		assertFalse(testDeck.getRemainingCards().contains(testCard));
		//doesn't contain null
		assertFalse(testDeck.getRemainingCards().contains(null));
	}

	@Test
	void testDiscard() {
		//discarded card goes back into deck
		testCard = testDeck.draw();
		assertFalse(testDeck.getRemainingCards().contains(testCard));
		testDeck.discard(testCard);
		assertTrue(testDeck.getRemainingCards().contains(testCard));
	}

	@Test
	void testGetDeckSize() {
		assertTrue(testDeck.getDeckSize() == 52);	
		testCard = testDeck.draw();
		assertTrue(testDeck.getDeckSize() == 51);	
	}

	@Test
	void testInitialize() {
		testDeck.initialize();
		assertTrue(testDeck.getDeckSize() == 52);	
		testDeck.initialize();
		assertTrue(testDeck.getDeckSize() == 52);	
	}

	@Test
	void testPrintDeck() {
		//n/a
	}

}
