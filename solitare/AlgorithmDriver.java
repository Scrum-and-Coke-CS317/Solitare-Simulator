package solitare;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.Stack;

public class AlgorithmDriver {

	//Initialize
	//make deck to draw from
	public static CardDeck cardDeck = new CardDeck();
	//make a waste to discard to
	public static WasteDeck wasteDeck = new WasteDeck();
	//make tableau
	public static ArrayList<Stack<Card>> tableau = new ArrayList<Stack<Card>>(7);
	//make foundation
	public static ArrayList<Stack<Card>> foundation = new ArrayList<Stack<Card>>(4);

	//toFoundation counting variables
	private static int clubCount = 1;
	private static int diamondCount = 1;
	private static int heartCount = 1;
	private static int spadeCount = 1;

	//individual game ending conditions
	//end individual games
	private static boolean gameEnd = false;
	//increments when deck is empty and algorithm is stuck //also can get stuck when deck is empty
	private static int stuckDeckCounter = 0; //TODO Increment to three when stuck to switch gameEnd to true

	/**
	 * 
	 * Runs the algorithm for the solitaire simulation
	 * 
	 * @param args
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception 
	{
		Scanner in = new Scanner(System.in);
		System.out.println("input number of games to simulate");
		int numGames = in.nextInt();

		setup();

		//loops through multiple games
		int gameStep = 1;
		for(int i = 0; i < numGames; i++) {
			//each game ends when stuckDeckCounter increments to 3 or all cards in the tableau are not hidden
			while(gameEnd == false) {
				if (gameStep == 1) {
					gameStep = tableauToFoundation(); //step 1: sends to step 2
				} else if (gameStep == 2) { 
					gameStep = unhideTableau(); //step 2: sends to step 3
				} else if (gameStep == 3) {
					gameStep = checkWaste(); //step 3: sends to step 4 if it doesn't have cards, step 5 if it does
				} else if (gameStep == 4) {
					gameStep = drawCard(); //step 4: sends to step 5, if empty and waste empty send to step 1
				} else if (gameStep == 5) {
					gameStep = wasteToFoundation(); //step 5: sends to step 6
				} else if (gameStep == 6) {
					gameStep = wasteToTableau(); //step 6: sends to step 1
				}
			}
			//reset for new game
			gameEnd = false;
			stuckDeckCounter = 0;
			clubCount = 1;
			diamondCount = 1;
			heartCount = 1;
			spadeCount = 1;
		}

		calculateSimStatistics();
		printResults();

		in.close();
	}

	/**
	 * Sets up the game for play, initializes the deck, waste, tableau and foundation 
	 * #Done
	 * 
	 * @throws Exception 
	 */
	public static void setup() throws Exception {
		//initialize deck
		cardDeck.initialize();
		//initialize waste
		wasteDeck.initialize();
		//initialize foundation
		for(int i = 0; i < 4; i++)
		{
			Stack<Card> s = new Stack<Card>();
			foundation.add(s);
		}

		//make tableau 
		//ArrayList of Stack<Card>
		for(int i = 0; i < 7; i++)
		{
			Stack<Card> s = new Stack<Card>();
			tableau.add(s);
			//number of cards per stack
			for (int ii = 0; ii < i+1; ii++)
			{
				Card c = cardDeck.draw();
				if (ii != i)
					c.setHidden(true); //only last card shows
				tableau.get(i).add(c);
			}
		}
	}

	/**
	 * check the first card of each tableau stack for playable cards on the foundation and play them
	 * 
	 * @return  
	 */
	public static int tableauToFoundation() {

		//if successful reset stuckDeckCounter
		return 2;
	}

	/**
	 * 
	 * @return  
	 */
	public static int unhideTableau() {
		
		return 3;
	}

	/**
	 * 
	 * @return 
	 */
	public static int checkWaste() {
		if(wasteDeck.getDeckSize() == 0) { //waste is empty
			return 5;
		}
		//waste isn't empty
		return 4;
	}

	/**
	 * 
	 * @return  
	 * @throws Exception 
	 */
	public static int drawCard() throws Exception {
		if(cardDeck.getDeckSize() == 0 && wasteDeck.getDeckSize() == 0) { //if both piles empty
			stuckDeckCounter++;//add to loss count
			return 1;
		}
		else if(cardDeck.getDeckSize() == 0 && wasteDeck.getDeckSize() != 0) { //if draw deck is empty
			stuckDeckCounter++;//add to loss count
			//take waste pile and loop it into the cardDeck
			for(int i = 0; i < wasteDeck.getDeckSize(); i++) {
				cardDeck.discard(wasteDeck.draw());
			}
		}
		//move card to top of waste pile
		Card c = cardDeck.draw();
		wasteDeck.discard(c);		
		return 5;		
	}

	/**
	 * 
	 * @return 
	 */
	public static int wasteToFoundation() {
		
		//if successful reset stuckDeckCounter to 0
		return 6;
	}

	/**
	 * 
	 * @return 
	 */
	public static int wasteToTableau() {
		
		//if successful reset stuckDeckCounter to 0 and return to 1
		return 1;
		//if not successful return to draw card
		
	}

	/**
	 * 
	 */
	public static void calculateSimStatistics() {
		// TODO Auto-generated method stub

	}

	/**
	 * 
	 */
	public static void printResults() {
		// TODO Auto-generated method stub

	}

	///////////////////////////////////////////Helper Methods/////////////////////////////////////////////////////

	/**
	 * try to add card to tableau
	 * 
	 * @param c card
	 * @return true or false if works
	 */
	public static boolean toTableau(Card c)
	{
		Card above;
		for(int i = 0; i < tableau.size(); i++)
		{
			above = tableau.get(i).peek(); //this might break things if it's empty lol

			if (tableau.get(i).isEmpty() && c.getValue() == 13)
			{
				tableau.get(i).add(c);
				return true;
			}
			else if (above.getValue() - 1 == c.getValue()  &&  !above.getColor().equals(c.getColor())) //I hope this works
			{
				tableau.get(i).add(c);
				return true;
			}

		}

		return false;
	}

	/**
	 * adds card to foundation
	 * 
	 * 0 clubs
	 * 1 diamonds
	 * 2 hearts
	 * 3 spades
	 * 
	 * if this gives a bunch of problems, make sure this card value > previous card value
	 * 
	 * @param c card
	 * @return true or false if works
	 */
	public static boolean toFoundation(Card c)
	{
		if (c.getSuit().equals("club") && clubCount == c.getValue())
		{
			foundation.get(0).add(c);
			clubCount++;
			return true;
		}
		else if (c.getSuit().equals("diamond") && diamondCount == c.getValue())
		{
			foundation.get(1).add(c);
			diamondCount++;
			return true;
		}
		else if (c.getSuit().equals("heart") && heartCount == c.getValue())
		{
			foundation.get(2).add(c);
			heartCount++;
			return true;
		}
		else if (c.getSuit().equals("spade") && spadeCount == c.getValue())
		{
			foundation.get(3).add(c);
			spadeCount++;
			return true;
		}
		else
		{
			return false;
		}
	}
	
	public int getClubCount() {return clubCount;}
	public int getDiamondCount() {return diamondCount;}
	public int getHeartCount() {return heartCount;}
	public int getSpadeCount() {return spadeCount;}
	
	
}
