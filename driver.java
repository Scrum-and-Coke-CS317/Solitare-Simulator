public class driver 
{
 public static void main(String[] args) throws Exception 
 {
	CardDeck carddeck = new CardDeck();
	carddeck.initialize();
	System.out.print(carddeck.draw().getSuit());


	
	
 }
}
