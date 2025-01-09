package memorygame;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 *
 * @author Shiroe
 */
public class DeckOfCard {
    private ArrayList<Card> deck;

    public DeckOfCard() {
        this.deck = new ArrayList<>();
        List<String> suits = Card.getValidSuit();
        List<String> faceNames = Card.getValidFaceName();
        
        for (String suit : suits) {
            for(String faceName : faceNames){
                deck.add(new Card(faceName, suit));
            }
        }     
    }
    
    
    //method for shuffling the deck
    public void shuffle(){
        Collections.shuffle(deck);
    }
    
    //method for dealing card from the top
    public Card dealTopCard(){
        if(deck.size() > 0){
            return deck.remove(0);
        }
        else{
            return null;
        }
    }
    
    //method to get how many card left in the deck
    public int getNumOfCard(){
        return deck.size();
    }
    
}
