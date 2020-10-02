/**
 * card class
 * hello
 * test
 * @author scrum-and-coke
 */
public class Card
{
	//suit of card as string - all lowercase for consistency 
	private String suit;
	//vlaue of card (Jack 11, Queen 12, King 13)
	private int value;
	
	/**
	 * Make null card
	 */
	public card()
	{
		this.suit = "";
		this.value = 0;
	}
	
	/**
	 * Make card with attributes
	 * 
	 * @param i value of vard
	 * @param s suit converted to lowercase to make things easy
	 */
	public card(int i, String s)
	{
		this.suit = s.toLowerCase();
		this.value = i;
	}
	
	/**
	 * get value of card
	 * 
	 * @return value of card
	 */
	public int getValue()
	{
		return this.value;
	}
	
	/**
	 * get suit of card
	 * 
	 * @return card suit
	 */
	public String getSuit()
	{
		return this.suit;
	}

	/**
	 * set card value
	 * 
	 * @param i sets value of card
	 */
	public void setValue(int i)
	{
		this.value = i;
	}
	
	/**
	 * set suit of card
	 * 
	 * @param s set the suit of card
	 */
	public void setSuit(String s)
	{
		this.suit = s;
	}
	
	/**
	 * Gets color of card.  Uses all lowercase
	 * 
	 * @return card color
	 */
	public String getColor()
	{
		if (this.suit.equals("diamond") || this.suit.equals("heart") )
			return "red";
		else if (this.suit.equals("club") || this.suit.equals("spade"))
			return "black";
		else
			return "error";
	}
	
}