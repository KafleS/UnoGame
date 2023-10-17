import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public final class Skip extends Card {

    public Skip(Color cardColor) {
        super(cardColor);
    }

    /**
     * Skip can skip any player except for the player who played it.
     * This function accomplishes the following:
     * - Prompts the user who they would like to skip with the following message:
     *   "Who would you like to skip? (n)ext or (s)pecific user?"
     * - If the answer is "n" then the next player is skipped
     * - If the answer is "s" then a specific player is skipped
     *   - The user must then be prompted with the following prompt:
     *     "Please choose from the following numbers: $playerNumbers"
     *     where playerNumbers are all the indices of players other than the current player seperated by spaces
     *   - You must loop until they give a valid index, if they fail output the following message:
     *     "$playerNumber is not valid. $playersToChoose"
     *     where playerNumber is the number they input
     *   - If they give an index that is not a number then output the following message and loop again:
     *     "$n not an int, please try again."
     *     where n is the index they input
     * - You must loop until they give you a valid command, if they fail output the following message:
     *   "$answer is not a recognized command, please try again."
     * @param game
     * TODO: Implement this
     */
    @Override
    // to skip the player
    public void doAction(Game game) {
        Scanner in = new Scanner(System.in);
        String temp = game.interact("Who would you like to skip? (n)ext or (s)pecific user?");
        while(!temp.equals("s")&& !temp.equals("n")){
            // to give message if the user input invalid command
            System.out.println(temp +" is not a recognized command, please try again.");
            temp = in.next();
        }
        // to skip the next player if the user input n
        if (temp.equals("n")){
           game.getPlayers().skip(game.getPlayers().findNextIndex(false));
           return;
        }
        // to skip the specific player if the player input s
        if ( temp.equals("s")){
            int skipIndex = -1;
            String input = "";
            System.out.print("Please choose from the following numbers:");
            for(int i = 0; i < game.getNumPlayers(); i++){
                if(i != game.getPlayers().getCurIndex()){
                    System.out.print(" " + i);
                }
            }
            System.out.println();
            boolean cond = true;
            while (cond) {
                // to get the index of the player as per the user command
                try {
                    input = in.next();
                    skipIndex = Integer.parseInt(input);
                    cond = false;
                    if(skipIndex < 0 || skipIndex >= game.getNumPlayers() || skipIndex == game.getPlayers().getCurIndex()){
                        System.out.println(input + " is not valid.");
                        for(int i = 0; i < game.getNumPlayers(); i++){
                            if(i != game.getPlayers().getCurIndex()){
                                System.out.print(" " + i);
                            }
                        }
                        cond = true;
                    }
                }
                // to catch the exception if the user input other than integer
                catch (NumberFormatException e) {
                    System.out.println(input + " not an int, please try again.");
                }
            }
            game.getPlayers().skip(skipIndex);
            return;
        }
    }

    @Override
    public boolean matchValue(Card other) {
        return other instanceof Skip;
    }

    @Override
    public String strRep() {
        return "S";
    }
}
