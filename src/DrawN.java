public class DrawN extends Card {
    private final int n;

    public DrawN(Color cardColor, int n) {
        super(cardColor);
        this.n = n;
    }
    public int getN() {
        return n;
    }

    /**
     * Makes the next player draw n cards
     * @param game Current game state
     * TODO: Implement this
     */
    @Override
    // to make the next player dran the n numbers of the card
    public void doAction(Game game) {
        game.getPlayers().peekNext().drawCards(n);
    }

    /**
     * Checks if other has the same value as this
     * @param other Other card to match against
     * @return true if other is an instanceof DrawN and our n equals their n, false otherwise
     * TODO: Implement this
     */
    @Override
    // to check if the card in the play area is same as the card in players hand
    public boolean matchValue(Card other) {
        if(other instanceof DrawN && n == (((DrawN)other).getN())){
            return true;
        }
        return false;
    }

    @Override
    public String strRep() {
        return "D+" + n;
    }
}
