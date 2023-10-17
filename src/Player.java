import java.util.ArrayList;
import java.util.Collections;
import java.util.InputMismatchException;

public class Player {
    private final String name;
    private final Game game;
    private final Hand hand;

    public Player(String name, Game game) {
        this.name = name;
        this.game = game;
        this.hand = new Hand(new ArrayList<>());
    }

    /**
     * This function does the following:
     * - Attempts to draw num number of cards
     * - If a EmptyDeckException is caught then the play area
     *   must be shuffled into the deck. Note this a function of game class
     * - Adds each drawn card to hand
     * @param num Number of cards to be drawn
     * TODO: Implement this
     */
    // to draw the certain number of the card from the deck
    public void drawCards(int num) {
        boolean condition = true;
        int i = 0;
        while(condition){
            try {
                while (i< num) {
                    hand.addCard(game.getDeck().drawCard());
                    i++;
                }
                condition = false;
            }
            // to catch the exception if the deck is empty and to shuffle the play area card into the deck
            catch (Deck.EmptyDeckException e){
                game.shufflePlayAreaIntoDeck();
            }
        }

    }

    /**
     * Performs IO to figure out what moves the user
     * wants to make. It does this as follows:
     * - Loops until the user has successfully played a card
     * - Prints out "Play area:\n"
     * - Prints out the top card
     * - Checks to see if the hand has any matches against the top card
     *   - If it does not then print: "Your hand had no matches, a card was drawn."
     *   - Then draw 1 card
     * - Then prints "Hand:\n"
     * - Then prints out the hand
     * - If the hand still has no matches then print: "Your hand still has no matches your turn is being passed"
     *   and ends the turn
     * - Otherwise it asks the user: "Which card would you like to play?" using the game::interact function
     * - The code loops until the user successfully answers this question, the three criteria are:
     *   - A valid int, if not print:
     *     "$cardNumStr is not a valid integer, please try again."
     *     where cardNumStr is the user input
     *   - A valid match, if not print:
     *     "Card $cardNumStr cannot currently be played, please try again."
     *     where cardNumStr is the user input
     *   - A valid index, if not print:
     *     "$cardNumStr is not a valid index, please try again."
     * TODO: Implement this
     */
    // to play the card in the playing area
    public void takeTurn() {
        System.out.println("Play area: \n");
        System.out.println(game.getTopCard());
        for (int i = 0; i < hand.numCardsRemaining(); i++) {
            // if the player does not have the valid card to play , playing drawing the top card from the deck
            if (!hand.noMatches(game.getTopCard())){
                System.out.println("Your hand had no matches, a card was drawn.");
                drawCards(1);
                if (!hand.noMatches(game.getTopCard())) {
                    System.out.println("Hand: \n");
                    System.out.println(hand);
                    System.out.println("Your hand still has no matches your turn is being passed");
                    return;
                }
            } else {
                boolean condition = true;
                System.out.println("Hand: \n");
                System.out.println(hand);
                String input = game.interact("Which card would you like to play?");
                while(condition){
                    // to try to play card from the player
                    try {
                        int index = Integer.parseInt(input);
                        if ( index<0 || index >= hand.numCardsRemaining()){
                            input = game.interact(index+" is not a valid index, please try again");
                        }
                        else{
                            hand.playCard(game, index);
                            return;
                        }
                    }
                    // to provide message if the player inputs the non valid integer
                    catch (InputMismatchException im) {
                        input = game.interact(input + " is not a valid integer, please try again.");
                    }
                    // to provide the information  if the player tries to play that cannot be played
                    catch (Card.CannotPlayCardException e){
                        input = game.interact("Card "+ input +" cannot currently be played, please try again.");
                    }
                }
            }
        }
    }

    public boolean emptyHand() {
        return hand.numCardsRemaining() == 0;
    }

    @Override
    public String toString() {
        return name;
    }
}
