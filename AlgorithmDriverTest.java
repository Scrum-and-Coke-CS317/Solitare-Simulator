

import static org.junit.jupiter.api.Assertions.*;

import org.junit.Assert;

import solitare.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class AlgorithmDriverTest {

	AlgorithmDriver testDriver; 

	@BeforeEach
	void setUp() throws Exception {
		testDriver = new AlgorithmDriver();
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@SuppressWarnings("static-access")
	@Test
	void testSetup() throws Exception {
		testDriver.setup();
		//test that there is a deck created containing 24 cards, which is the number left after dealing the rest out
		assertTrue(testDriver.cardDeck.getDeckSize() == 24);
		//test that there is an empty waste pile created
		assertTrue(testDriver.wasteDeck.getDeckSize() == 0);
		//test that there are seven stacks created with the correct number of cards for the tableau piles
		//Stack 1: 1 card, not hidden
		assertTrue(testDriver.tableau.get(0).size() == 1);
		assertTrue(testDriver.tableau.get(0).get(0).getHidden() == false);
		//Stack 2: 2 cards, 1 hidden, 1 not hidden
		assertTrue(testDriver.tableau.get(1).size() == 2);
		assertTrue(testDriver.tableau.get(1).get(0).getHidden() == true &&
				testDriver.tableau.get(1).get(1).getHidden() == false);
		//Stack 3: 3 cards, 2 hidden, 1 not hidden
		assertTrue(testDriver.tableau.get(2).size() == 3);
		assertTrue(testDriver.tableau.get(2).get(0).getHidden() == true &&
				testDriver.tableau.get(2).get(1).getHidden() == true &&
				testDriver.tableau.get(2).get(2).getHidden() == false);
		//Stack 4: 4 cards, 3 hidden, 1 not hidden
		assertTrue(testDriver.tableau.get(3).size() == 4);
		assertTrue(testDriver.tableau.get(3).get(0).getHidden() == true &&
				testDriver.tableau.get(3).get(1).getHidden() == true &&
				testDriver.tableau.get(3).get(2).getHidden() == true &&
				testDriver.tableau.get(3).get(3).getHidden() == false);
		//Stack 5: 5 cards, 4 hidden, 1 not hidden
		assertTrue(testDriver.tableau.get(4).size() == 5);
		assertTrue(testDriver.tableau.get(4).get(0).getHidden() == true &&
				testDriver.tableau.get(4).get(1).getHidden() == true &&
				testDriver.tableau.get(4).get(2).getHidden() == true &&
				testDriver.tableau.get(4).get(3).getHidden() == true &&
				testDriver.tableau.get(4).get(4).getHidden() == false);
		//Stack 6: 6 cards, 5 hidden, 1 not hidden
		assertTrue(testDriver.tableau.get(5).size() == 6);
		assertTrue(testDriver.tableau.get(5).get(0).getHidden() == true &&
				testDriver.tableau.get(5).get(1).getHidden() == true &&
				testDriver.tableau.get(5).get(2).getHidden() == true &&
				testDriver.tableau.get(5).get(3).getHidden() == true &&
				testDriver.tableau.get(5).get(4).getHidden() == true &&
				testDriver.tableau.get(5).get(5).getHidden() == false);
		//Stack 7: 7 cards, 6 hidden, 1 not hidden
		assertTrue(testDriver.tableau.get(6).size() == 7);
		assertTrue(testDriver.tableau.get(6).get(0).getHidden() == true &&
				testDriver.tableau.get(6).get(1).getHidden() == true &&
				testDriver.tableau.get(6).get(2).getHidden() == true &&
				testDriver.tableau.get(6).get(3).getHidden() == true &&
				testDriver.tableau.get(6).get(4).getHidden() == true &&
				testDriver.tableau.get(6).get(5).getHidden() == true &&
				testDriver.tableau.get(6).get(6).getHidden() == false);
		//test that there are 4 empty stacks created for the foundation piles
		assertTrue(testDriver.foundation.size() == 4);
		assertTrue(testDriver.foundation.get(0).size() == 0 &&
				testDriver.foundation.get(1).size() == 0 &&
				testDriver.foundation.get(2).size() == 0 &&
				testDriver.foundation.get(3).size() == 0);
	}

	@SuppressWarnings("static-access")
	@Test
	void testTableauToFoundation() throws Exception {
//		testDriver.setup();
//		testDriver.tableauToFoundation();
//		//check for correct card movement
//		for(int i = 0; i < 4; i++) {
//			if(testDriver.foundation.get(i).size() == 1) {
//				//card in foundation is Ace
//				assertTrue(1 == 1);
//			assertTrue(testDriver.foundation.get(i).get(testDriver.foundation.get(i).size()-1).getValue() == 1);
//			} else if(testDriver.foundation.get(i).size() > 1) {
//				//card on top of stack in foundation is greater than the card before it
//				assertTrue(testDriver.foundation.get(i).get(testDriver.foundation.get(i).size()-2).getValue()+1 ==
//						testDriver.foundation.get(i).get(testDriver.foundation.get(i).size()-1).getValue());
//				//test if all cards in foundation pile are of the same suit
//				for(int j = 0; j < testDriver.foundation.get(i).size()-2; j++) {
//					assertTrue(testDriver.foundation.get(i).get(j).getSuit().equals(testDriver.foundation.get(i).get(j).getSuit()+1));
//				}
//			}
//		}
//		
	}

	//	@Test
	//	void testUnhideTableau() {
	//		fail("Not yet implemented");
	//	}
	//
	//	@Test
	//	void testCheckWaste() {
	//		fail("Not yet implemented");
	//	}
	//
	//	@Test
	//	void testDrawCard() {
	//		fail("Not yet implemented");
	//	}
	//
	//	@Test
	//	void testWasteToFoundation() {
	//		fail("Not yet implemented");
	//	}
	//
	//	@Test
	//	void testWasteToTableau() {
	//		fail("Not yet implemented");
	//	}
	//
	//	@Test
	//	void testCalculateSimStatistics() {
	//		fail("Not yet implemented");
	//	}
	//
	//	@Test
	//	void testPrintResults() {
	//		fail("Not yet implemented");
	//	}

	@Test
	void testToTableau() {
		//known cards that can be added to tableau stacks and tested on
		//8 hearts at index 0
		Card known8Heart = new Card();
		known8Heart.setValue(8);
		known8Heart.setSuit("heart");
		AlgorithmDriver.tableau.get(0).push(known8Heart);
		
		//empty pile at index 3
		while(!AlgorithmDriver.tableau.get(3).empty()) {
			AlgorithmDriver.tableau.get(3).pop();
		}
		
		//4 spades at index 4
		Card known4Spade = new Card();
		known4Spade.setValue(4);
		known4Spade.setSuit("spade");
		AlgorithmDriver.tableau.get(4).push(known4Spade);
		
		//12 diamond at index 6 (last index)
		Card known12Diamond = new Card();
		known12Diamond.setValue(12);
		known12Diamond.setSuit("diamond");
		AlgorithmDriver.tableau.get(6).push(known12Diamond);
		
	}
			
	
	@Test
	void testToFoundation() {
		boolean result = false;
		//set up new cards and tests to see if they are added to foundation. Takes into account differing values of cardCount variables, but 
		//foundation arrayList must have the respective hard coded indexes for the clubs, diamonds, hearts, and spades. also could create compare methods to decouple code
		Card nextClub = new Card();
		nextClub.setSuit("club");
		nextClub.setValue(testDriver.getClubCount());
		result = AlgorithmDriver.toFoundation(nextClub);
		Assert.assertTrue("successfully adds next club to foundation", result);
		Assert.assertTrue("Checks to make sure new club value was added to top of its stack", nextClub.getValue() == 
				(AlgorithmDriver.foundation.get(0).peek()).getValue());
		
		
		Card nextDiamond = new Card();
		nextDiamond.setSuit("diamond");
		nextDiamond.setValue(testDriver.getDiamondCount());
		result = AlgorithmDriver.toFoundation(nextDiamond);
		Assert.assertTrue("successfully adds next diamond to foundation", result);
		Assert.assertTrue("Checks to make sure new diamond value added to top of its stack", nextDiamond.getValue() == 
				(AlgorithmDriver.foundation.get(1).peek()).getValue());
		
		Card nextHeart = new Card();
		nextHeart.setSuit("heart");
		nextHeart.setValue(testDriver.getHeartCount());
		result = AlgorithmDriver.toFoundation(nextHeart);
		Assert.assertTrue("successfully adds next heart to foundation", result);
		Assert.assertTrue("Checks to make sure new heart value added to top of its stack", nextHeart.getValue() == 
				(AlgorithmDriver.foundation.get(2).peek()).getValue());
		
		Card nextSpade = new Card();
		nextSpade.setSuit("spade");
		nextSpade.setValue(testDriver.getSpadeCount());
		result = AlgorithmDriver.toFoundation(nextSpade);
		Assert.assertTrue("successfully adds next spade to foundation", result);
		Assert.assertTrue("Checks to make sure new spade value added to top of its stack", nextSpade.getValue() ==
				(AlgorithmDriver.foundation.get(3).peek()).getValue());
		
		//tests unsuccessfully adding a card of same suit to the Foundation by trying to add the nextSpade card again, after it has already been added
		result = AlgorithmDriver.toFoundation(nextSpade);
		nextSpade.setValue(nextSpade.getValue()-1);
		Assert.assertTrue("unsuccessfully adds card to foundation (already was added)", !result);
		nextSpade.setValue(nextSpade.getValue()+1);
		Assert.assertTrue("maintains that the top spade value card is unchanged", nextSpade.getValue() == (AlgorithmDriver.foundation.get(3).peek().getValue()));
		
		//tests trying to add a different suit on top of a differing foundation
		nextHeart.setValue(testDriver.getClubCount());
		result = AlgorithmDriver.toFoundation(nextHeart);
		Assert.assertTrue("maintains it was unsuccessfully added", !result);
		Assert.assertTrue("checks top club foundation still has same value", nextClub.getValue() == 
				AlgorithmDriver.foundation.get(0).peek().getValue());
		Assert.assertTrue("checks top club foundation still has same suit", nextClub.getSuit().equals
				(AlgorithmDriver.foundation.get(0).peek().getSuit()));
		
		
		
		
		
	}
	
}
