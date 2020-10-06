import java.util.ArrayList;
import java.util.Stack;

public class driver 
{
 public static void main(String[] args) throws Exception 
 	{
	 //make deck to draw from
		CardDeck cardDeck = new CardDeck();
		cardDeck.initialize();
		
		
		//make tableau
		ArrayList<Stack<Card>> tableau = new ArrayList<Stack<Card>>(7);
		//say a prayer that this works
		for(int i = 0; i < 7; i++)
		{
			Stack<Card> s = new Stack<Card>();
			tableau.add(s);
			for (int ii = 0; ii < i+1; ii++)
			{
				Card c = cardDeck.draw();
				if (ii != i)
					c.hide();
				tableau.get(i).add(c);
			}
		}
		//an attempt to print what's in the tableau
		//if this works, that's proof God exists
		for(int i = 0; i < 7; i++)
		{
			Stack<Card> s = tableau.get(i);
			System.out.println("Row " + i + ": ");
			for (Card c : s)
			{
				System.out.println(c.toString());
			}
			System.out.print('\n' + "------");
		}
 	}
}
