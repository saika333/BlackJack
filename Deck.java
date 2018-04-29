package blackjack;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Deck{
    
    private List<Card> cardDeck = new ArrayList<Card>();
    
    public Deck(){
        String[] markArray = {"♥", "♠", "◆", "♣"};

        for (String mark : markArray) {
            for (int i =1; i<=13; i++) {
                cardDeck.add(new Card(mark, i));
            }
        }
    }
    
    public Card dealCard(){
    	
        if(cardDeck.size() == 0) {
        	String[] markArray = {"♥", "♠", "◆", "♣"};

            for (String mark : markArray) {
                for (int i =1; i<=13; i++) {
                    cardDeck.add(new Card(mark, i));
                }
            }
        }
        int randomNum = new Random().nextInt(cardDeck.size());
        
        return cardDeck.get(randomNum);
    }
    
    public void deleteCard(final Card card){
        cardDeck.remove(card);
    }

}
