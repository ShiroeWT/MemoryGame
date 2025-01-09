package memorygame;

import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;

public class MemoryGameController implements Initializable {

    @FXML
    private Label correctLabel;

    @FXML
    private Label guessesLabel;


    @FXML
    private FlowPane imagesFlowPane;
    
    private ArrayList<MemoryCard> cardsInGame;
    private int numOfGuesses;
    private int numOfMatches;
    private MemoryCard firstCard;
    private MemoryCard secondCard;
    

    @FXML
    void playAgain() {
        firstCard = null; 
        secondCard = null;
        
        DeckOfCard deck = new DeckOfCard();
        deck.shuffle();
        cardsInGame = new ArrayList<>();
        
        for (int i = 0; i < imagesFlowPane.getChildren().size()/2; i++) {
            Card cardDealt = deck.dealTopCard();
            cardsInGame.add(new MemoryCard(cardDealt.getFaceName(), cardDealt.getSuit()));
            cardsInGame.add(new MemoryCard(cardDealt.getFaceName(), cardDealt.getSuit()));  
        }
        Collections.shuffle(cardsInGame);
        System.out.println(cardsInGame);
        flipAllCards();
        numOfGuesses = 0;
        numOfMatches = 0;
        updateLabel();
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        initializeImageView();
        playAgain();
    }
    
    
    //add imageView and set the image into back of the card
    private void initializeImageView(){
        for (int i = 0; i < imagesFlowPane.getChildren().size(); i++) {
            ImageView imageView = (ImageView) imagesFlowPane.getChildren().get(i);
            imageView.setImage(new Image(Card.class.getResourceAsStream("images/back_of_card.png")));
            imageView.setUserData(i);
            
            
            //add mouse click listener
            imageView.setOnMouseClicked(event-> {
                flipCard((int)imageView.getUserData());
            });
        }
    }
    
    private void flipCard(int indexOfCard){
        if(firstCard == null && secondCard == null){
            flipAllCards();
        }
        ImageView imageView = (ImageView) imagesFlowPane.getChildren().get(indexOfCard);
        
        if(firstCard == null){
            firstCard = cardsInGame.get(indexOfCard);
            imageView.setImage(firstCard.getImage());
        }
        else if(secondCard == null){
            numOfGuesses++;
            secondCard = cardsInGame.get(indexOfCard);
            imageView.setImage(secondCard.getImage());
            checkForMatch();
            updateLabel();
        }
        
    }
    
    private void checkForMatch(){
        if(firstCard.isSameCard(secondCard)){
            numOfMatches++;
            firstCard.setMatched(true);
            secondCard.setMatched(true);
            
        }
        firstCard = null;
        secondCard = null;
    }
    
    //flip all unmatched Card
    private void flipAllCards(){
        for (int i = 0; i < cardsInGame.size(); i++) {
            ImageView imageView = (ImageView) imagesFlowPane.getChildren().get(i);
            MemoryCard card = cardsInGame.get(i);
            if(card.isMatched()){
                imageView.setImage(card.getImage());
            }
            else{
                imageView.setImage(card.getBackOfCard());
            }
            
        }
    }
    
    private void updateLabel(){
        guessesLabel.setText(Integer.toString(numOfGuesses));
        correctLabel.setText(Integer.toString(numOfMatches));
    }

}
