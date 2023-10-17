import java.util.Collection;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public final class Deck {
    private final List<Card> cards;

    public Deck(List<Card> cards) {
        this.cards = cards;
        shuffleDeck();
    }

    public static class EmptyDeckException extends Exception {}

    /**
     * This function does the following:
     * - Checks if cards is empty
     *   - If it is then throw a new EmptyDeckException
     *   - If not then return and remove the first card in cards
     * @return The top card from the deck
     * @throws EmptyDeckException
     * TODO: Implement this
     */

    // to check if the deck is empty or not
    public Card drawCard() throws EmptyDeckException {
        // to throw the exception if the deck is empty
        if (cards== null){
            throw new EmptyDeckException();
        }
        // to remove the top card from the deck
        else {
            cards.remove(0);
        }
        // to return the top card of the deck
        return cards.get(0);
    }

    public void shuffleDeck() {
        Collections.shuffle(this.cards);
    }

    public void addCards(Collection<Card> cards) {
        this.cards.addAll(cards);
        shuffleDeck();
    }
}
