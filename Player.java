package homework5;
import java.util.ArrayList;
//import homework5.Card;
import java.util.Random;

public class Player extends Person{
       private String name;
       private int chips;
       private int bet = 0;
       //private ArrayList<Card> oneRoundCard;
       
       public Player(String name, int chips) {
    	   this.name = name;
    	   this.chips = chips;
       }
       
       public String get_name() {
    	   return name;
       }
       
       public int make_bet() {
    	   if (chips > 0) {
    		   bet = 1;
    		   //chips -= bet;
    	   }
    	   else {
    		   bet = 0;
    		   System.out.print("come on.. .There is no money anymore");
    	   }  
    	   return bet;
       }
       
       /*public void setOneRoundCard(ArrayList<Card> cards) {
    	   oneRoundCard = cards;
       }*/
       
       public boolean hit_me(Table table){
    	       boolean yesOrNo = false;;
    		   if (getTotalValue() <= 16){
    			  yesOrNo = true;
    		   }
    	   return yesOrNo;
       }
       
       /*public int getTotalValue() {
    	   int value = 0;
    	   for (Card element : oneRoundCard) {  //super
    		   if (element.getRank() == 11 || element.getRank() == 12 || element.getRank() == 13) {
    			   value += 10;
    		   } 
    		   else if (element.getRank() == 1) {
	    		   if(value >= 15) {
	    			   value += 1;
	    		   }
	    		   else {
	    			   value += 11;
	    		   }
    		   }
    		   else  {
    			   value += element.getRank();
    		   }
    	   }
    	   return value;
       }*/
       
       public int get_current_chips() {
    	   return chips;
       }
       
       public void increase_chips(int diff){
    	   chips += diff;
       }
       public void say_hello() {
    	   System.out.println("Hello, I am " + name + ".");
    	   System.out.println("I have " + chips + " chips.");
       }
       
}
