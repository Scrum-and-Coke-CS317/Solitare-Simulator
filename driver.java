import java.util.ArrayList;
import java.util.Stack;

public class driver 
{
	//make deck to draw from
	private static CardDeck cardDeck = new CardDeck();
	//make tableau
	private static ArrayList<Stack<Card>> tableau = new ArrayList<Stack<Card>>(7);
	//make foundation
	private static ArrayList<Stack<Card>> foundation = new ArrayList<Stack<Card>>(4);
	//count cards in foundation
	private static int clubCount = 1;
	private static int diamondCount = 1;
	private static int heartCount = 1;
	private static int spadeCount = 1;
	
	
 public static void main(String[] args) throws Exception 
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
		
		//test here
		System.out.println("Preconditions:");
		print(tableau);
		print(foundation);
		
		for (int i = 0; i < 30; i++) 						//test with 30 cards
		{
			//current card from deck and play
			Card current = cardDeck.draw();
			
			playTableauCards();							//<---- this breaks things 1/2 the time for some reason
			
			if (play(current, false) == false)
				cardDeck.discard(current);
			
					
		}
		
		//show results
		//Good luck figuring this part out
		System.out.println();
		System.out.println();
		System.out.println();
		System.out.println("Done-----");
		print(tableau);
		print(foundation);
		
 	}
 
 	/**
 	 * Plays card if possible or discards
 	 * 
 	 * @param c card to play
 	 */
 	private static boolean play(Card c, boolean fromTableau)
 	{
 		if (fromTableau)
 			System.out.println("   Play Card from Tableau: " + c.toString());
 		else
 			System.out.println("   Play Card: " + c.toString());
 		
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
 		for(int i = 0; i < tableau.size(); i++)
		{
 			//make sure it's not empty
 			if (! tableau.get(i).isEmpty())
	 			{
 					Card c = tableau.get(i).peek();
 					int count = tableau.get(i).size();
 					
 					//find highest card in stack that's face up
 					while (c.getHidden() == false)
 					{
 						count--;
 					} 					
 					
 					//go back down the cards to try moving them
 					while(count < tableau.get(i).size())
 					{
 						int playable = isPlayable(c);
 						//if the card can be played
 						if(playable != -1)
 						{
 							ArrayList<Card> toMove = new ArrayList<Card>();
 							//move cards to tableau.get(playable)
 							//remove cards from current place
 							while(count < tableau.get(i).size())
 							{
 								toMove.add(tableau.get(i).pop());
 							}
 							tableau.get(i).peek().setHidden(false);
 							//put in new place
 							for(int ii = 0; ii < toMove.size(); ii++)
 							{
 								tableau.get(playable).add(toMove.get(ii));
 							}
 						}
 						else
 							count++;
 					}
 					
 					//Card c = tableau.get(i).peek();
	 				if(play(c,true))
	 				{
	 					tableau.get(i).pop();
	 					if (! tableau.get(i).isEmpty())
	 						tableau.get(i).lastElement().setHidden(false);
	 						//tableau.get(i).peek().setHidden(false);
	 				}
	 			}
 			//to test
 			//print(tableau);
			//print(foundation);
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
 			temp = tableau.get(i).peek();
 			if(! temp.getColor().equals(c.getColor()) && temp.getValue()-1 == c.getValue())
 			{
 				System.out.println(c.toString() + "is playable from tableau");
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
}
