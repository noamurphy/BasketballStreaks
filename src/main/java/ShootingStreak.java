/**
 * A simple console output
 * driver program to analyze
 * the specified data supplied
 * through the program arguments
 *
 * Complete the main method below
 *
 * Do not add any additional methods to this class.
 *
 */

public class ShootingStreak {

    /**
     * Analyze a player's shooting streaks and
     * output to System.out the longest streak of missed shots followed
     * by a space followed by the longest streak of made shots for each player
     * in the order that the player names are provided
     *
     * The first output from this method MUST be an echo of the program arguments
     * for example if this program is run with arguments: src/main/resources/games 1 3 K.Lowry the first line of output
     * should be:
     * src/main/resources/games 1 3 K.Lowry N.Powell
     *
     * If any supplied player either had no misses or no makes in the
     * provided game then the output should be 0 for that value.
     *
     * @param args the following order must be adhered to in the args:
     *             first: the path to the location of the game files
     *             second: the game number example: 1 (or all for all games)
     *             third: the shot type, example: 3
     *             forth (and beyond): the player names separated by spaces, example: K.Lowry
     *                                 you may assume no player names contain spaces
     */
    public static void main(String [] args ) {
        if(args.length < 4) {
            System.out.println("Please provide at least 4 program arguments:\n" +
                    "gamesPath gameNumber shotType player1 player2 ...");
        }
        GameFileProcessor gfp = new GameFileProcessor();
        String argsString = "";
        for (String s: args) {
            argsString = argsString + " " + s;
        }
        if (gfp.processArgs(argsString)){
            System.out.println("processArgs complete");
        }
        if (gfp.processFile()){
            System.out.println("processFile complete");
        }
    }
}
