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
	
 public static void main(String[] args) throws Exception 
 	{
	 	//put in the cards
		cardDeck.initialize();
		
		//initialize foundation
		for(int i = 0; i < 4; i++)
		{
			Stack<Card> s = new Stack<Card>();
			foundation.add(s);
		}
		
		//say a prayer that this works
		for(int i = 0; i < 7; i++)
		{
			Stack<Card> s = new Stack<Card>();
			tableau.add(s);
			for (int ii = 0; ii < i+1; ii++)
			{
				Card c = cardDeck.draw();
				if (ii != i)
					c.setHidden(true);
				tableau.get(i).add(c);
			}
		}
		print(tableau);
		print(foundation);
		
		
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
 	 */
 	public void toFoundation(Card c)
 	{
 		if (c.getSuit().equals("club"))
 			foundation.get(0).add(c);
 		else if (c.getSuit().equals("diamond"))
 			foundation.get(1).add(c);
 		else if (c.getSuit().equals("heart"))
 			foundation.get(2).add(c);
 		else if (c.getSuit().equals("spade"))
 			foundation.get(3).add(c);
 		else
 			System.out.println("Error adding to foundation");
 	}
 	
 	public static void print(ArrayList<Stack<Card>> list)
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
