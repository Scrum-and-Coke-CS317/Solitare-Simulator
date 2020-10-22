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
	
	public void loseInitialize() {
		deck.clear();
		//add all the cards to an arrayList
		for (String suit:suits)
		{
			for (int i = 1 ; i < 14 ; i++)
			{
				Card c = new Card(i, suit);
				deck.add(c);
				System.out.println("" + c.getValue() + c.getSuit());
			}
		}
	}
	
	public void winInitialize() {
		deck.clear();
		//add all the cards to an arrayList
		for (String suit:suits)
		{
			if (!suit.equals("club") && !suit.equals("spade")) {
				for (int i = 13 ; i > 1 ; i--) //adds all but the Aces, Spades, and Clubs
				{
					Card c = new Card(i, suit);
					deck.add(c);
				}
			}
		}
		//Hardcode time
		deck.add(new Card(7, "club"));
		deck.add(new Card(13, "club"));
		deck.add(new Card(5, "spade"));
		deck.add(new Card(9, "spade"));
		deck.add(new Card(12, "spade"));
		deck.add(new Card(1, "heart"));
		deck.add(new Card(1, "diamond"));
		deck.add(new Card(6, "club"));
		deck.add(new Card(12, "club"));
		deck.add(new Card(4, "spade"));
		deck.add(new Card(8, "spade"));
		deck.add(new Card(11, "spade"));
		deck.add(new Card(13, "spade"));
		deck.add(new Card(5, "club"));
		deck.add(new Card(11, "club"));
		deck.add(new Card(3, "spade"));	
		deck.add(new Card(7, "spade"));
		deck.add(new Card(10, "spade"));
		deck.add(new Card(4, "club"));
		deck.add(new Card(10, "club"));
		deck.add(new Card(2, "spade"));
		deck.add(new Card(6, "spade"));
		deck.add(new Card(3, "club"));
		deck.add(new Card(9, "club"));
		deck.add(new Card(1, "spade"));
		deck.add(new Card(2, "club"));
		deck.add(new Card(8, "club"));
		deck.add(new Card(1, "club"));
		
		for (Card card: deck) {
			System.out.println("" + card.getValue() + card.getSuit());
		}
	}

	/**
	 * This draws a card from the top of the deck and removes it.
	 * @return the card removed
	 * @throws Exception
	 */
	public Card draw()
	{
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
}
