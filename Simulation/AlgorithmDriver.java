package Simulation;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.Stack;

public class AlgorithmDriver {

	//Initialize
	//make deck to draw from
	static CardDeck cardDeck = new CardDeck();
	//make a waste to discard to
	static WasteDeck wasteDeck = new WasteDeck();
	//make tableau
	static ArrayList<Stack<Card>> tableau = new ArrayList<Stack<Card>>(7);
	//make foundation
	static ArrayList<Stack<Card>> foundation = new ArrayList<Stack<Card>>(4);

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
					tableauToFoundation(); //step 1: sends to step 2
				} else if (gameStep == 2) { 
					unhideTableau(); //step 2: sends to step 3
				} else if (gameStep == 3) {
					checkWaste(); //step 3: sends to step 4 if it doesn't have cards, step 5 if it does
				} else if (gameStep == 4) {
					drawCard(); //step 4: sends to step 5, if empty and waste empty send to step 1
				} else if (gameStep == 5) {
					wasteToFoundation(); //step 5: sends to step 6
				} else if (gameStep == 6) {
					wasteToTableau(); //step 6: sends to step 1
				}
			}
			gameEnd = false;
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
//		for(int i = 0; i < 7; i++) { //loop tableaus
//			if(tableau.get(i).size() != 0) {
//				for(int j = 0; j < 4; j++) { //loop foundations
//					if(foundation.get(j).get(foundation.get(j).size()-1).getSuit().equals(
//							tableau.get(i).get(tableau.get(i).size()-1).getSuit()) &&
//							foundation.get(j).get(foundation.get(j).size()-1).getValue()+1 ==
//							tableau.get(i).get(tableau.get(i).size()-1).getValue()) {
//						//move card from tableau to foundation
//						foundation.get(j).add(tableau.get(i).get(tableau.get(i).size()-1));
//						tableau.get(i).remove(tableau.get(i).size()-1);
//					}
//				}
//			}
//		}
	return 2;
}

	/**
	 * 
	 * @return  
	 */
	public static int unhideTableau() {
		return 3;
		// TODO Auto-generated method stub

	}

	/**
	 * 
	 * @return 
	 */
	public static int checkWaste() {
		return 4;
		// TODO Auto-generated method stub

	}

	/**
	 * 
	 * @return  
	 */
	public static int drawCard() {
		return 5;
		// TODO Auto-generated method stub

	}

	/**
	 * 
	 * @return 
	 */
	public static int wasteToFoundation() {
		return 6;
		// TODO Auto-generated method stub

	}

	/**
	 * 
	 * @return 
	 */
	public static int wasteToTableau() {
		return 1;
		// TODO Auto-generated method stub

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





}
