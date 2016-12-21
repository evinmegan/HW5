package homework5;
import java.util.ArrayList;
import java.util.Random;

public class Deck {
	private ArrayList<Card> cards;
	private ArrayList<Card> usedCard;
	private ArrayList<Card> openCard;
	public int nUsed;
	
	public Deck(int nDeck){
		cards = new ArrayList<Card>();
		usedCard = new ArrayList<Card>();
		openCard = new ArrayList<Card>();

		for(int x = 1; x < nDeck+1 ;x++){
			for (Card.Suit a : Card.Suit.values()){
			   for (int b = 1; b < 14; b++){
				   Card cd = new Card(a, b);
				   cards.add(cd);
			   }
		    }
		}
		shuffle();
	}	 
	public void printDeck(){		
        for (Card object : cards){
        	object.printCard();
        }
	}
	public ArrayList<Card> getAllCards(){
		return cards;
	}
	public void shuffle(){
		
		for (int index = 0; index < usedCard.size(); index++) {
			cards.add(usedCard.get(index));
		}
		for (int index = 0; index < cards.size(); index++) {
			int rnd = new Random().nextInt(cards.size());
			Card tarCard = cards.get(rnd);
			cards.set(rnd, cards.get(index));
			cards.set(index, tarCard);
		}
		usedCard.clear();
		openCard.clear();
		nUsed = 0;
	}
	public Card getOneCard(boolean isOpened){
		
		Card oCard;
		if (cards.isEmpty()) {
			shuffle();
		}
		nUsed += 1;
		oCard = cards.get(0);
		cards.remove(0);
		usedCard.add(oCard);
		
		if (isOpened) {
			openCard.add(oCard);
		}
		return oCard;
	}
	public ArrayList<Card> getOpenedCard(){
		return openCard;
	}
	
}
