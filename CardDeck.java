/**
 * card deck class
 * 
 * @author scrum-and-coke
 */
//use a queue or something??
import java.util.*;


public class CardDeck {
	
	private int remainingCards;
	public ArrayList<Card> cards = new ArrayList<Card>();
	public Queue<Card> deck = new LinkedList<Card>();
	
	String[] suits = {"spade", "heart", "diamond", "club"};
	
	
	public Card draw(){
		return deck.poll();
	}
	
	public Queue<Card> getRemainingCards(){
		return deck;
	}
	
	public void discard(Card c){
		deck.add(c);
	}
	
	public int getDeckSize(){
		return deck.size();
	}
	
	public void initialize(){
		remainingCards = 52;
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
