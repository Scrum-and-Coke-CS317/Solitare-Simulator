package solitare;

import java.util.ArrayList;
import java.util.Queue;
/**
 * card deck class
 * 
 * @author scrum-and-coke
 */
//use a queue or something??
import java.util.Stack;


public class WasteDeck 
{
	
	public Stack<Card> deck = new Stack<Card>();
	
	/**
	 * 
	 * @return card from top of Waste Pile
	 * @throws Exception if there are no cards in Waste
	 */
	public Card draw()
	{
		if (deck.isEmpty())
			return null;
		return deck.pop();
	}
	 
	public void discard(Card c)
	{
		deck.add(c);
	}
	
	public int getDeckSize()
	{
		return deck.size();
	}
	
	public CardDeck reset()
	{
		CardDeck cd = new CardDeck();
		
		ArrayList<Card> temp = new ArrayList<Card>();
		for(Card c:deck)	//reverse order for queue, right?
		{
			temp.add(c);
		}
		for(int i = temp.size(); i > 0; i--)
		{
			cd.add(temp.get(i));
		}
		
		return cd;
	}	
	
	public void clear()
	{
		deck.clear();
	}
	
	/**
	 * Used for testing purposes
	 */
	public Stack<Card> getRemainingCards(){
		return deck;
	}
	
	public void printDeck() 
	{
		for (Card card:deck) 
		{
			System.out.println("Suit: "+card.getSuit() + " Number: "+card.getValue());
		}
	}
	
}