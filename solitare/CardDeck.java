package solitare;

//use a queue or something??
import java.util.*;

/**
 * card deck class
 * 
 * @author scrum-and-coke
 */
public class CardDeck {
	
	//this is the deck that is used to hold cards
	public Queue<Card> deck = new LinkedList<Card>();
	//This is used when shuffling cards in the initialization method
	public ArrayList<Card> cards = new ArrayList<Card>();
	
	String[] suits = {"spade", "heart", "diamond", "club"};
	
	/**
	 * After constructing an empty CardDeck, this method adds 52 cards randomly
	 * 
	 */
	public void initialize(){
		deck.clear();
		for (String suit:suits)
		{
			for (int i = 1 ; i < 14 ; i++)
			{
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
	
	/**
	 * This draws a card from the top of the deck and removes it.
	 * @return the card removed
	 * @throws Exception
	 */
	public Card draw() throws Exception
	{
		if (deck.isEmpty())
			throw new Exception("deck isEmpty with 0 cards -- is it initialized?");
		return deck.poll();
	}
	
	/**
	 * This adds a card given to the bottom of the deck
	 * @param c
	 */
	public void add(Card c)
	{
		deck.add(c);
	}
	
	/**
	 * This adds a card given to the bottom of the deck
	 * @param c
	 */
	public void discard(Card c){
		deck.add(c);
	}
	
	/**
	 * This returns the size of the deck
	 * @return
	 */
	public int getDeckSize(){
		return deck.size();
	}
	
	/**
	 * This returns the remaining cards in the deck
	 */
	public Queue<Card> getRemainingCards(){
		return deck;
	}
	
	/**
	 * This is a printed off version of all the cards in the deck
	 */
	public void printDeck() {
		for (Card card:deck) {
			System.out.println(card.toString());
		}
	}
	
	public void chechDupes(int i)
	{
		for(Card c : deck)
		{
			for(Card in : deck)
			{
				if(in.equals(c))
				{
					System.out.println("There's dupes in here");
					System.out.println("Line: " + i);
					//throw new Exception("Duplicate Cards");
				}
			}
		}
	}


}
