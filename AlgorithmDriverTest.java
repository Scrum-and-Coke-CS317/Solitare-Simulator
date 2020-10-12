

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

	@Test
	void testTableauToFoundation() {
		//clear tableau and foundation so this method has independent tableau and foundation setup
		for(int i = 0; i < AlgorithmDriver.tableau.size(); i++) {
			while(!AlgorithmDriver.tableau.get(i).empty()) {
				AlgorithmDriver.tableau.get(i).pop();
			}
		}

		for(int i = 0; i < AlgorithmDriver.foundation.size(); i++) {
			while(!AlgorithmDriver.foundation.get(i).empty()) {
				AlgorithmDriver.foundation.get(i).pop();
			}
		}

		//set up to have foundation with index 0 and 1 empty (clubs and diamonds, index 2 (hearts) with 3 of hearts on top, and index 3 (spades) with 10 of spades on top
		Card heart3  = new Card(3, "heart");
		Card spade10 = new Card(10, "spade");
		AlgorithmDriver.foundation.get(2).push(heart3);
		AlgorithmDriver.foundation.get(3).push(spade10);

		//setup to have tableau with club1 index 0, diamond1 on club2 index 2, heart4 index 3, spade11 index 5
		Card club1 = new Card(1, "club");
		Card diamond1 = new Card(1, "diamond");
		Card club2 = new Card(2, "club");
		Card heart4 = new Card(4, "heart");
		Card spade11 = new Card(11, "spade");

		AlgorithmDriver.tableau.get(0).push(club1);
		AlgorithmDriver.tableau.get(1).push(club2);
		AlgorithmDriver.tableau.get(1).push(diamond1);
		AlgorithmDriver.tableau.get(3).push(heart4);
		AlgorithmDriver.tableau.get(6).push(spade11);

		//tests begin. must check method return value, suit and value of top foundation card, make sure card is removed from its position in tableau 
		//move club 1 to foundation
		AlgorithmDriver.tableauToFoundation(); 
		Assert.assertTrue("show club foundation is initially empty", AlgorithmDriver.foundation.get(0).empty());
		Assert.assertTrue("show foundation for clubs is no longer empty", !AlgorithmDriver.foundation.get(0).empty());
		Assert.assertTrue("make sure club1 suit is on top of club foundation", club1.getSuit().equals(AlgorithmDriver.foundation.get(0).peek().getSuit()));
		Assert.assertTrue("make sure club1 value is on top of club foundation", club1.getValue() == (AlgorithmDriver.foundation.get(0).peek().getValue()));
		Assert.assertTrue("club1 removed from the tableau", AlgorithmDriver.tableau.get(0).empty());

		//move diamond1 to foundation
		AlgorithmDriver.tableauToFoundation();
		Assert.assertTrue("make sure diamond1 suit is on top of diamond foundation", diamond1.getSuit().equals(AlgorithmDriver.foundation.get(1).peek().getSuit()));
		Assert.assertTrue("make sure diamond1 value is on top of diamond foundation", diamond1.getValue() == (AlgorithmDriver.foundation.get(1).peek().getValue()));
		Assert.assertTrue("diamond1 place in tableau no longer has equal suit", !diamond1.getSuit().equals(AlgorithmDriver.tableau.get(1).peek().getSuit()));
		Assert.assertTrue("diamond1 place in tableau no longer has equal value", diamond1.getValue() != AlgorithmDriver.tableau.get(1).peek().getValue());

		//move club2 to foundation
		AlgorithmDriver.tableauToFoundation();
		Assert.assertTrue("make sure club2 suit is on top of club foundation", club2.getSuit().equals(AlgorithmDriver.foundation.get(0).peek().getSuit()));
		Assert.assertTrue("make sure club2 value is on top of club foundation", club2.getValue() == (AlgorithmDriver.foundation.get(0).peek().getValue()));
		Assert.assertTrue("club2 place in tableau is now empty", AlgorithmDriver.tableau.get(1).empty());

		//move heart4 to foundation
		AlgorithmDriver.tableauToFoundation();
		Assert.assertTrue("make sure heart4 suit is on top of heart foundation", heart4.getSuit().equals(AlgorithmDriver.foundation.get(2).peek().getSuit()));
		Assert.assertTrue("make sure heart4 value is on top of heart foundation", heart4.getValue() == (AlgorithmDriver.foundation.get(2).peek().getValue()));
		Assert.assertTrue("heart4 place in tableau is now empty", AlgorithmDriver.tableau.get(3).empty());

		//move spade 11 to foundation
		AlgorithmDriver.tableauToFoundation();
		Assert.assertTrue("make sure spade11 suit is on top of spade foundation", spade11.getSuit().equals(AlgorithmDriver.foundation.get(3).peek().getSuit()));
		Assert.assertTrue("make sure spade11 value is on top of spade foundation", spade11.getValue() == (AlgorithmDriver.foundation.get(3).peek().getValue()));
		Assert.assertTrue("spade11 place in tableau is now empty", AlgorithmDriver.tableau.get(6).empty());

		//try to move to foundation after all is empty. tableau should still be empty. Check foundation top cards to make sure they have same suit and value as before.
		AlgorithmDriver.tableauToFoundation();
		for (int i = 0; i < AlgorithmDriver.tableau.size(); i++) {
			Assert.assertTrue("tableau is now filled with empty piles", AlgorithmDriver.tableau.get(i).empty());
		}
		Assert.assertTrue("make sure diamond1 suit is still on top of diamond foundation", diamond1.getSuit().equals(AlgorithmDriver.foundation.get(1).peek().getSuit()));
		Assert.assertTrue("make sure diamond1 value is still on top of diamond foundation", diamond1.getValue() == (AlgorithmDriver.foundation.get(1).peek().getValue()));

		Assert.assertTrue("make sure club2 suit is still on top of club foundation", club2.getSuit().equals(AlgorithmDriver.foundation.get(0).peek().getSuit()));
		Assert.assertTrue("make sure club2 value is still on top of club foundation", club2.getValue() == (AlgorithmDriver.foundation.get(0).peek().getValue()));

		Assert.assertTrue("make sure heart4 suit is still on top of heart foundation", heart4.getSuit().equals(AlgorithmDriver.foundation.get(2).peek().getSuit()));
		Assert.assertTrue("make sure heart4 value is still on top of heart foundation", heart4.getValue() == (AlgorithmDriver.foundation.get(2).peek().getValue()));

		Assert.assertTrue("make sure spade11 suit is still on top of spade foundation", spade11.getSuit().equals(AlgorithmDriver.foundation.get(3).peek().getSuit()));
		Assert.assertTrue("make sure spade11 value is still on top of spade foundation", spade11.getValue() == (AlgorithmDriver.foundation.get(3).peek().getValue()));

		//End Result: should move all cards from this new setup to respective foundations. clubs should have c1 and c2, diamonds should have d1, hearts should have h4 and h3, and 
		//spades should have s11 and s10.
	}

	@Test
	void testUnhideTableau() {
		//fail("Not yet implemented");
	}

	@Test
	void testCheckWaste() {
		//fail("Not yet implemented");
	}

	@Test
	void testDrawCard() {
		//fail("Not yet implemented");
	}

	@Test
	void testWasteToFoundation() {
		//fail("Not yet implemented");
	}

	@Test
	void testWasteToTableau() {
		//fail("Not yet implemented");
	}

	@Test
	void testCalculateSimStatistics() {
		//fail("Not yet implemented");
	}

	@Test
	void testPrintResults() {
		//fail("Not yet implemented");
	}

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

		//4 spades at index 4 (middle deck)
		Card known4Spade = new Card();
		known4Spade.setValue(4);
		known4Spade.setSuit("spade");
		AlgorithmDriver.tableau.get(4).push(known4Spade);

		//12 diamond at index 6 (last index)
		Card known12Diamond = new Card();
		known12Diamond.setValue(12);
		known12Diamond.setSuit("diamond");
		AlgorithmDriver.tableau.get(6).push(known12Diamond);

		//fill remaining stacks with known8Heart to make sure no test card is added too early
		AlgorithmDriver.tableau.get(1).push(known8Heart);
		AlgorithmDriver.tableau.get(2).push(known8Heart);
		AlgorithmDriver.tableau.get(5).push(known8Heart);

		//testing begins
		Card king = new Card();
		king.setValue(13);
		king.setSuit("heart");
		boolean result = AlgorithmDriver.toTableau(king);
		Assert.assertTrue("King placed in empty spot", result);
		Assert.assertTrue("check that king is in specified position", AlgorithmDriver.tableau.get(3).peek().getValue() == king.getValue());
		AlgorithmDriver.tableau.get(3).pop(); //remove card

		result = AlgorithmDriver.toTableau(known8Heart);
		Assert.assertTrue("check if non king is placed in empty space", !result);
		Assert.assertTrue("Empty space is still empty", AlgorithmDriver.tableau.get(3).empty());

		Card spade7 = new Card(7, "spade");
		result = AlgorithmDriver.toTableau(spade7);
		Assert.assertTrue("successful add card to first index", result);
		Assert.assertTrue("spade7 was successfully added", AlgorithmDriver.tableau.get(0).peek().getValue() == spade7.getValue());

		result = AlgorithmDriver.toTableau(known8Heart);
		Assert.assertTrue("try to add card with same suit and value as other cards", !result);

		Card spade6 = new Card(6, "spade");
		result = AlgorithmDriver.toTableau(spade6);
		Assert.assertTrue("add correct value but wrong suit for index 0", !result);
		Assert.assertTrue("value at index 0 is still 7", AlgorithmDriver.tableau.get(0).peek().getValue() == spade7.getValue());

		Card spade11 = new Card(11, "spade");
		result = AlgorithmDriver.toTableau(spade11);
		Assert.assertTrue("card added to last tableau index", result);
		Assert.assertTrue("check to see it was actually added", spade11.getValue() == AlgorithmDriver.tableau.get(6).peek().getValue());
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
