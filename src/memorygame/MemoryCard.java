package memorygame;

/**
 *
 * @author Shiroe
 */
public class MemoryCard extends Card{
    private boolean matched;

    public MemoryCard(String faceName, String suit) {
        super(faceName, suit);
        this.matched = false;
    }

    public boolean isMatched() {
        return matched;
    }

    public void setMatched(boolean matched) {
        this.matched = matched;
    }
    
    public boolean isSameCard(MemoryCard otherCard){
        return(this.getSuit().equals(otherCard.getSuit()) && 
               this.getFaceName().equals(otherCard.getFaceName()));
    }
    
}
