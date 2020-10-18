package solitare;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.Stack;

public class driver 
{
	//make deck to draw from
	private static CardDeck cardDeck = new CardDeck();
	//make tableau
	private static ArrayList<Stack<Card>> tableau = new ArrayList<Stack<Card>>();
	//make foundation
	private static ArrayList<Stack<Card>> foundation = new ArrayList<Stack<Card>>();
	//private static WasteDeck waste = new WasteDeck();
	private static Stack<Card> wd = new Stack<Card>();
	//count cards in foundation
	private static int clubCount = 1;
	private static int diamondCount = 1;
	private static int heartCount = 1;
	private static int spadeCount = 1;



	public static void main(String[] args) throws Exception 
	{
		Scanner scan = new Scanner(System.in);
		System.out.print("Number of games: ");
		int numGames = scan.nextInt();
		scan.close();

		int countWins = 0;
		for(int i = 0; i < numGames; i++)
		{
			System.out.println("***************************\n****** GAME NUMBER "+i+" ******\n***************************\n");
			if (solitaire())
				countWins++;

			cardDeck = new CardDeck();
			tableau = new ArrayList<Stack<Card>>(7);
			foundation = new ArrayList<Stack<Card>>(4);
			wd = new Stack<Card>();

			clubCount = 1;           
			diamondCount = 1;        
			heartCount = 1;          
			spadeCount = 1;

		}

		System.out.println(countWins + " wins");
	}





	public static boolean solitaire() throws Exception
	{
		//initialize deck
		cardDeck.initialize();
		//initialize foundation
		for(int i = 0; i < 4; i++)
		{
			Stack<Card> s = new Stack<Card>();
			foundation.add(s);
		}

		//make tableau 
		//ArrayList of Stack<Card>
		//say a prayer that this works
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

		System.out.println("Number of cards: " + countCards());

		//test here
		System.out.println("Preconditions:");
		print(tableau);
		print(foundation);
		System.out.println("Deck: ");
		cardDeck.printDeck();
		System.out.println();

		//works better if you let it go a few times before adding cards
		for (int i = 0; i < 10; i++)
			playTableauCards();

		for (int i = 0; i < 3; i++)						//num of times ran
		{
			for(int ii = 0; ii < cardDeck.getDeckSize(); ii++)
			{
				Card current = cardDeck.draw();
				if (play(current) == false)
					wd.add(current);

				if(wd.size() != 0)
					if(play(wd.peek()))
					{
						wd.pop();
					}

				for (int iii = 0; iii < 4; iii++)
					playTableauCards();
			}

			for (int i1 = 0; i1 < 16; i1++)
				playTableauCards();

			System.out.println("-------------Resetting the deck");
			
			//reset the deck
			for (int iter = 0; iter < wd.size(); iter++)
			{
				System.out.println(wd.elementAt(iter).toString());
				cardDeck.add(wd.pop());
			}
		}


		//show results
		//Good luck figuring this part out
		System.out.println();
		System.out.println();
		System.out.println();
		System.out.println("Done-----");
		print(tableau);
		print(foundation);
		System.out.println("Deck: ");
		cardDeck.printDeck();
		System.out.println("waste: ");
		for (Card c : wd)
			System.out.println(c);
		System.out.println("Number of cards: " + countCards());

		if(checkIfWon())
		{
			System.out.println("Won");
			return true;
		}
		else
		{
			System.out.println("You lost.");
			return false;
		}

	}





	/**
	 * check if won
	 */
	public static boolean checkIfWon()
	{
		if(foundation.get(0).size() == 13 && foundation.get(1).size() == 13 &&
				foundation.get(2).size() == 13 && foundation.get(3).size() == 13)
		{
			return true;
		}
		else
			return false;
	}






	/**
	 * Plays card if possible or discards
	 * 
	 * @param c card to play
	 */
	private static boolean play(Card c)
	{
		//check if can be added to a foundation
		if(toFoundation(c))
			return true;
		//check if can be added to a stack
		else if(toTableau(c))
			return true;
		else
			return false;
	}






	/**
	 * try to add card to tableau
	 * 
	 * @param c card
	 * @return true or false if works
	 */
	private static boolean toTableau(Card c)
	{
		for(int i = 0; i < tableau.size(); i++)
		{ 			
			if (tableau.get(i).isEmpty() && c.getValue() == 13)
			{
				tableau.get(i).add(c);
				return true;
			}
			else if(tableau.get(i).isEmpty() && c.getValue() != 13)
			{
				return false;
			}
			else if (tableau.get(i).peek().getValue() - 1 == c.getValue()
					&&  !tableau.get(i).peek().getColor().equals(c.getColor())) //I hope this works
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
	private static boolean toFoundation(Card c)
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





	/**
	 * try to play cards down on tableau and unhide card under
	 * this method is a terrible mess and I hate it
	 * 
	 */
	private static void playTableauCards()
	{
		//for each of the 7 stacks in tableau
		for(int i = 0 ; i < tableau.size(); i++)
		{
			//make sure it's not empty
			if (! tableau.get(i).isEmpty())
			{
				Card c = tableau.get(i).peek();
				int count = tableau.get(i).size();

				//find highest card in stack that's face up
				for(int faceUp = tableau.get(i).size() - 1; 0 < count; faceUp--)
				{
					c = tableau.get(i).elementAt(faceUp);
					if(c.getHidden() == true)
					{
						break;
					}
					count--;
				} 					

				//go back down the cards to try moving them
				while(count < tableau.get(i).size())
				{
					c = tableau.get(i).elementAt(count);
					int playable = isPlayable(c);
					if(playable > -1)
					{
						Stack<Card> toMove = new Stack<Card>();
						//remove cards from current place
						while(count < tableau.get(i).size())
						{
							toMove.add(tableau.get(i).pop());
						}
						if(!tableau.get(i).isEmpty())
							tableau.get(i).peek().setHidden(false);
						//put in new place
						while(!toMove.isEmpty())
						{
							tableau.get(playable).add(toMove.pop());
						}
					}
					else if(playable == -2)
					{
						tableau.get(i).pop();
						if(!tableau.get(i).isEmpty())
							tableau.get(i).peek().setHidden(false);
					}
					else
						count++;
				}

			}
		}
	}






	/**
	 * Helper method to determine if a card is playable somewhere in tableau
	 * 
	 * @return
	 */
	private static int isPlayable(Card c)
	{
		Card temp;
		for(int i = 0; i < tableau.size(); i++)
		{

			if(toFoundation(c))
			{
				return -2;
			}
			else if(!tableau.get(i).isEmpty() && c.getValue() != 13)
			{
				temp = tableau.get(i).peek();
				if(! temp.getColor().equals(c.getColor()) && temp.getValue()-1 == c.getValue())
				{
					//System.out.println(c.toString() + " is playable from tableau");
					return i;
				}
			}
			else if(tableau.get(i).isEmpty() && c.getValue() == 13)
			{
				//System.out.println(c.toString() + " is playable from tableau");
				return i;
			}
		}
		return -1; //-1 if can't be played
	}






	/**
	 * Print big things
	 * 
	 * @param list tableau
	 */
	private static void print(ArrayList<Stack<Card>> list)
	{
		//an attempt to print what's in the tableau
		//if this works, that's proof God exists
		for(int i = 0; i < list.size(); i++)
		{
			Stack<Card> s = list.get(i);
			System.out.println("Row " + i + ": ");
			for (Card c : s)
			{
				System.out.println(c.toString());
			}
			System.out.println("------" + '\n');
		}
	}

	
	/**
	 * Get the number of cards to test
	 * 
	 * @return num cards
	 */
	private static int countCards()
	{
		int count = 0;
		for(int i = 0; i < tableau.size(); i++)
		{
			for(Card c : tableau.get(i))
				count++;
		}
		for(int i = 0; i < foundation.size(); i++)
		{
			for(Card c : foundation.get(i))
				count++;
		}

		return count + cardDeck.getDeckSize() + wd.size();

	}
}


