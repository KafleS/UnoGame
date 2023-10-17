public final class Reverse extends Card {
    public Reverse(Color cardColor) {
        super(cardColor);
    }

    /**
     * This function calls the reverse function
     * on the UnusIterator
     * @param game
     * TODO: Implement this
     */
    @Override
    // to reverse the direction of the game
    public void doAction(Game game) {
        game.getPlayers().reverse();
    }

    @Override
    public boolean matchValue(Card other) {
        return other instanceof Reverse;
    }

    @Override
    public String strRep() {
        return "Rev";
    }
}
