import java.util.*;

public final class Game {
    private final Scanner io;
    private final UnusIterator<Player> players;
    private final int numPlayers;
    private final Deck deck;
    private final Deque<Card> playArea;

    /**
     * Constructs all the data necessary to run a game.
     * This includes the following:
     * - Create a scanner of System.in and saves it into io
     * - Creates a deck using the createDeck function and saves it into deck.
     * - Creates a list of players with length given by numPlayers
     * - Has each player draw 5 cards
     * - Creates a UnusIterator with the aforementioned player list
     * - Assigns the parameter numPlayers to the instance variable numPlayers
     * - Initializes playArea with a new ArrayDeque
     * @param numPlayers
     * TODO: Implement this
     */
    public Game(int numPlayers) {
        this.io = new Scanner(System.in);
        this.deck = createDeck();
        List<Player> players = new ArrayList<>(numPlayers);
        //to make each player draw 5 cards
        for(int i = 0; i < numPlayers; i++){
            players.add(new Player(Integer.toString(i), this));
            players.get(i).drawCards(5);
        }
        this.players = new UnusIterator<>(players);

        this.numPlayers = numPlayers;
        this.playArea = new ArrayDeque<>();
    }

    /**
     * The main game loop function.
     * Does the following:
     * - Loops until the curPlayer's hand is empty
     *   - When this is the case the curPlayer has won the game.
     *   - It then prints: "$curPlayer won!"
     * - The current player is received from the UnusIterator
     * - The player then takes their turn
     * - The UnusIterator is then moved to the next player
     * TODO: Implement this
     */
    // to get the winner
    public void start() {
        // while loop is implemented until there will be one winner
        while (true){
            players.current().takeTurn();
            if (  players.current().emptyHand() == true){
                System.out.println(players.getCurIndex()+"won!");
                players.current().takeTurn();
                break;
            }
            players.next();
        }
    }

    public String interact(String toUser) {
        System.out.println(toUser);
        return io.nextLine();
    }

    public UnusIterator<Player> getPlayers() {
        return players;
    }

    public int getNumPlayers() {
        return numPlayers;
    }

    public int getNumberOfCardsInPlay() {
        return playArea.size();
    }

    public Deck getDeck() {
        return deck;
    }

    public Card getTopCard() {
        if (playArea.isEmpty()) {
            return new None();
        }

        return playArea.getFirst();
    }

    public void playCard(Card card) {
        playArea.addFirst(card);
    }

    public void shufflePlayAreaIntoDeck() {
        deck.addCards(playArea);
        deck.shuffleDeck();
    }

    /**
     * Creates the standard 108 card Unus deck.
     * The deck contains the following cards:
     * - 19 red cards
     *   - 1 zero
     *   - 2 of every number
     * - 19 blue cards
     *   - 1 zero
     *   - 2 of every number
     * - 19 green cards
     *   - 1 zero
     *   - 2 of every number
     * - 19 yellow cards
     *   - 1 zero
     *   - 2 of every number
     * - 8 skip cards - two of each color
     * - 8 reverse cards - two of each color
     * - 8 draw 2 cards - two of each color
     * - 4 wild cards
     * - 4 wild draw 4 cards
     * @return A standard Unus deck of 108 cards
     * TODO: Implement this
     */
    // to create a deck of 108 cards
    private Deck createDeck() {
        List<Card> cards = new ArrayList<>();
        Card.Color[] colors = Card.Color.values();
        int n = 0;
        // to get the 0 card from each of the colors
        for(int i = 0; i < colors.length -1; i++){
            Numbers Zero = new Numbers(colors[i], n);
            cards.add(Zero);
        }
        
        // to get the 2 cards from 1 to 9 of each color in the deck
        for(int i = 1; i < 10; i++) {
            for(int j = 0; j < Card.Color.values().length-1; j++) {
                Numbers number = new Numbers(colors[j], i);
                cards.add(number);
                cards.add(number);
            }
        }
        
        // to get the 4 wild card in the deck
        Wild wildcard = new Wild(Card.Color.WILD);
        cards.add(wildcard);
        cards.add(wildcard);
        cards.add(wildcard);
        cards.add(wildcard);

       
        // to get the  2 reverse card form each of the color in the deck
        for(int i = 0; i < colors.length -1; i++){
            Reverse reverse = new Reverse(colors[i]);
            cards.add(reverse);
            cards.add(reverse);
        }
        
        // to get wild draw 4 card in the deck
        for(int i = 0; i < 4; i ++){
            DrawN wildDraw4 = new DrawN(colors[colors.length-1], 4);
            cards.add(wildDraw4);}
        
        // to get draw 2 card from each of the color in the deck
        for(int i = 0; i < 2; i ++){
            DrawN wildDraw2 = new DrawN(colors[colors.length-1], 4);
            cards.add(wildDraw2);
            cards.add(wildDraw2);
        }
        
        // to get  2 skips card from each color in the deck
        for(int i = 0; i < colors.length -1; i++){
            Skip skip = new Skip(colors[i]);
            cards.add(skip);
            cards.add(skip);
        }
        return new Deck(cards);
    }
}
