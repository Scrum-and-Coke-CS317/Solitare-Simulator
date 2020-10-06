/**
 * card class
 * 
 * @author scrum-and-coke
 */
public class Card
{
	//suit of card as string - all lowercase for consistency 
	private String suit;
	//value of card (Jack 11, Queen 12, King 13)
	private int value;
	//face up or face down
	private boolean hidden;
	
	/**
	 * Make null card
	 */
	public Card()
	{
		this.suit = "";
		this.value = 0;
		this.hidden = false;
	}
	
	/**
	 * Make card with attributes
	 * 
	 * @param i value of Card
	 * @param s suit converted to lowercase to make things easy
	 */
	public Card(int i, String s)
	{
		this.suit = s.toLowerCase();
		this.value = i;
	}
	
	/**
	 * get value of card
	 * @param  
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
			return "error!";
	}
	
	/**
	 * put face down or up
	 * 
	 * @param b new value
	 */
	public void setHidden(boolean b)
	{
		this.hidden = b;
	}
	
	/**
	 * check if card is face down
	 * 
	 * @return hidden status
	 */
	public boolean getHidden()
	{
		return this.hidden;
	}
	
	/**
	 * toString to print card
	 * 
	 * @return value and suit of card
	 */
	public String toString()
	{
		String s = "";
		
		//put dash in front if card face down
		if (this.hidden == true)
			s += "-";	
		
		if (this.value == 1)
			s += "A " + this.suit;
		else if (this.value == 11)
			s += "J " + this.suit;
		else if (this.value == 12)
			s += "Q " + this.suit;
		else if (this.value == 13)
			s += "K " + this.suit;
		else
			s += this.value + " " + this.suit;
		
		return s;
	}
	
}