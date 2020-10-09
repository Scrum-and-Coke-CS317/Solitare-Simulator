package solitare;

/**
 * card deck class
 * 
 * @author scrum-and-coke
 */
//use a queue or something??
import java.util.*;


public class WasteDeck {
	
	public ArrayList<Card> cards = new ArrayList<Card>();
	public Queue<Card> deck = new LinkedList<Card>();
	
	String[] suits = {"spade", "heart", "diamond", "club"};
	
	/**
	 * 
	 * @return card from top of Waste Pile
	 * @throws Exception if there are no cards in Waste
	 */
	public Card draw() throws Exception
	{
		if (deck.isEmpty())
			throw new Exception("No cards in waste");
		return deck.poll();
	}
	
	public Queue<Card> getRemainingCards(){
		return deck;
	}
	
	public int getDeckSize(){
		return deck.size();
	}
	
	public void initialize(){
		deck.clear();
	}
	
	public void printDeck() {
		for (Card card:deck) {
			System.out.println("Suit: "+card.getSuit() + " Number: "+card.getValue());
		}
	}
	
}