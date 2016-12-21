package homework5;
import java.util.ArrayList;

public class Table {
	
private final int MAXPLAYER = 4;
		private Deck tDeck;
		private Player[] players;
		private Dealer dealer;
		private int[] pos_betArray;//存放每個玩家在一局下的注
		
		Table (int nDeck){
			tDeck = new Deck(nDeck);
			players = new Player[MAXPLAYER];
			pos_betArray = new int[MAXPLAYER];
		}
		
		public void set_player(int pos, Player p){
			players[pos] = p;
		}
		
		public Player[] get_player(){
			return players;
		}
		
		public void set_dealer(Dealer d){
			dealer = d;
		}
		
		public Card get_face_up_card_of_dealer(){
			return dealer.getOneRoundCard().get(1);
		}

		private void ask_each_player_about_bets(){
		    for(int i=0; i<players.length; i++){
				players[i].say_hello();
				pos_betArray[i]= players[i].make_bet();
			}
		}
		
		private void distribute_cards_to_dealer_and_players(){
			
			for (Player p : players){
				ArrayList<Card> pL = new ArrayList<Card>();
				pL.add(tDeck.getOneCard(true));
				pL.add(tDeck.getOneCard(true));
				p.setOneRoundCard(pL);	
			}
			ArrayList<Card> pL2 = new ArrayList<Card>();  //pL
			pL2.add(tDeck.getOneCard(false));
			pL2.add(tDeck.getOneCard(true));
			dealer.setOneRoundCard(pL2);	
			
	        System.out.println("Dealer's face up card is ");
	        dealer.getOneRoundCard().get(1).printCard();
		}
		
		private void ask_each_player_about_hits(){	
			for (Player p : players){
				do {
					if(p.hit_me(this)){
						p.getOneRoundCard().add(tDeck.getOneCard(true));
						System.out.print("Hit! ");
						System.out.println(p.get_name()+"'s Cards now:");
						for(Card c : p.getOneRoundCard()){
							c.printCard();
						}		
					}	
					else{
						System.out.println(p.get_name()+", Pass hit!");	
						for(Card c : p.getOneRoundCard()){
							c.printCard();
						}
					}			
				} while(p.hit_me(this));
			}
		}

		private void ask_dealer_about_hits(){
			while(dealer.hit_me(this)){
				dealer.getOneRoundCard().add(tDeck.getOneCard(true));
			}
			System.out.println("Dealer's hit is over!");
		}
		
		private void calculate_chips(){
			System.out.println("Dealer's card value is "+ dealer.getTotalValue() +",Cards:");
			for (Card c: dealer.getOneRoundCard()){
				c.printCard();
			}
			int index = 0;
			for(Player p : players){ //for players
				System.out.print(p.get_name() +" card value is "+ p.getTotalValue());

				if(dealer.getTotalValue() > 21){
					System.out.println(",Get " + pos_betArray[index] + " Chips, the Chips now is:\r"
							+ (p.get_current_chips() + pos_betArray[index])); 
				}
				else{
					if(dealer.getTotalValue() > p.getTotalValue() || p.getTotalValue() > 21){	
						System.out.println(",Loss " + pos_betArray[index] + " Chips, the Chips now is:\r"
							+ (p.get_current_chips() - pos_betArray[index]));
					}
					else if(dealer.getTotalValue() < p.getTotalValue() && p.getTotalValue()<=21){
						System.out.println(",Get " + pos_betArray[index] + " Chips, the Chips now is:\r"
							+ (p.get_current_chips() + pos_betArray[index])); 
					}
					else {
						System.out.println(",chips have no change! The Chips now is: \r"
							+ p.get_current_chips());
					}
				}
				index++;
			}
		}

		public int[] get_palyers_bet(){
			return pos_betArray;
		}
		
		public void play(){
			ask_each_player_about_bets();
			distribute_cards_to_dealer_and_players();
			ask_each_player_about_hits();
			ask_dealer_about_hits();
			calculate_chips();
		}

}
