package solitare;

/**
 * card deck class
 * 
 * @author scrum-and-coke
 */
//use a queue or something??
import java.util.*;


public class CardDeck {
	
	public ArrayList<Card> cards = new ArrayList<Card>();
	public Queue<Card> deck = new LinkedList<Card>();
	
	String[] suits = {"spade", "heart", "diamond", "club"};
	
	
	public Card draw() throws Exception
	{
		if (deck.isEmpty())
			throw new Exception("deck isEmpty with 0 cards -- is it initialized?");
		return deck.poll();
	}
	
	public void add(Card c)
	{
		deck.add(c);
	}
	
	public void discard(Card c){
		deck.add(c);
	}
	
	public int getDeckSize(){
		return deck.size();
	}
	
	public void initialize(){
		deck.clear();
		for (String suit:suits) {
			for (int i = 1 ; i < 14 ; i++) {
				Card c = new Card(i, suit);
				cards.add(c);
			}
		}
		Random r = new Random();
		for (int i = 0 ; i < 52 ; i++)
		{
			int randomInt = r.nextInt(cards.size());	
			Card randomCard = cards.get(randomInt);
			deck.add(randomCard);
			cards.remove(randomInt);
		}
	}
	
	public void printDeck() {
		for (Card card:deck) {
			System.out.println("Suit: "+card.getSuit() + " Number: "+card.getValue());
		}
	}
	

}
