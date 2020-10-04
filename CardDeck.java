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
	public ArrayList<Card> drawnCards = new ArrayList<Card>();
	
	String[] suits = {"spade", "heart", "diamond", "club"};
	
	
	public void shuffle(){
		
	}
	
	public void draw(){
		remainingCards--;
		int randomCard = random.nextInt(cards.size());
		cards.get(randomCard);
	}
	
	public ArrayList<Card> getRemainingCards(){
		return cards;
	}
	
	public void discard(Card c){
		//send to back
	}
	
	public void initialize(){
		remainingCards = 52;
		for (String suit:suits) {
			for (int i = 1 ; i < 14 ; i++) {
				Card c = new Card(i, suit);
				cards.add(c);
			}
		}

		
		
	}
	
	public void printDeck() {
		for (Card card:cards) {
			System.out.println("Suit: "+card.getSuit() + " Number: "+card.getValue());
		}
	}
}
