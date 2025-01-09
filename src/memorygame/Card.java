package memorygame;

import java.util.Arrays;
import java.util.List;
import javafx.scene.image.Image;

/**
 *
 * @author Shiroe
 */
public class Card {
    private String faceName;
    private String suit;
    
    
   public Card(String faceName, String suit){
       setFaceName(faceName);
       setSuit(suit);
   }
   
   
    // valid faceName just "2", "3", "4", "5", "6", "7", "8", "9", "10", "jack", "queen", "king", "ace"
    public static List<String> getValidFaceName(){
        return Arrays.asList("2", "3", "4", "5", "6", "7", "8", "9", "10", "jack", "queen", "king", "ace");
    }

    //Valid suit just "hearth", "diamond", "club", "ace"
    public static List<String> getValidSuit(){
        return Arrays.asList("hearts", "diamonds", "clubs", "spades");
    }
    
    
    //setter
    public void setFaceName(String faceName) {
        faceName = faceName.toLowerCase();
        if(getValidFaceName().contains(faceName)){
            this.faceName = faceName;
        }
        else{
            throw new IllegalArgumentException(faceName + " is invalid, face must contain " + getValidFaceName());
        }
    }

    public void setSuit(String suit) {
        suit = suit.toLowerCase();
        if(getValidSuit().contains(suit)){
            this.suit = suit;
        }
        else{
            throw new IllegalArgumentException(suit + " is invalid, suit must contain " + getValidSuit());
        }
        
    }
    

    //getter
    public String getFaceName() {
        return faceName;
    }

    public String getSuit() {
        return suit;
    }
    
    public String getColor(){
        if(suit.equals("hearth") || suit.equals("diamond")){
            return "red";
        }
        else{
            return "black";
        }
    }
    
    public int getValue(){
        return getValidFaceName().indexOf(faceName)+2;
    }
    
    @Override
    public String toString(){
        return faceName + " of " + suit;
    }
    
    
    //method to return the image that represent the card
    public Image getImage(){
        String pathName = "images/"+faceName+"_of_"+suit+".png";
        return new Image(Card.class.getResourceAsStream(pathName));
    }
    
    public Image getBackOfCard(){
        return new Image(Card.class.getResourceAsStream("images/back_of_card.png"));
    }
    
}
