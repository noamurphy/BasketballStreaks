import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * A class to retrieve target information from a file of basketball score data
 */
public class GameFileProcessor {
    private String fileLocation;
    private String gameNumber;
    private String shotType;
    private ArrayList<Player> playerList = new ArrayList<>();

    /**
     * Checks for a valid user input and sets Class variables to user input
     * @param args arguments from the user specifying requested information for retrieval from game file
     * @return a feedback message to the user indicating validity of their input
     */
    public boolean processArgs(String args){
        System.out.println(args);
        Scanner inputScan = new Scanner(args);
        //Get file location
        fileLocation = inputScan.next();
        //Get game(s) of interest
        gameNumber = inputScan.next();
        //Get shotType
        shotType = inputScan.next();
        if (!(shotType.equals("1")|shotType.equals("2")|shotType.equals("3")|shotType.toUpperCase().equals("ANY"))){
            System.out.println("Invalid shot type provided.");
            return false;
        }
        //Get players of interest
        while(inputScan.hasNext()){
            playerList.add(new Player(inputScan.next()));
        }
        return true;
    }

    /**
     * Processes game file data to determine made/miss streaks based on arguments given by the user
     * @param gameNumber the game number for the game of interest
     */
    public void processFile(String gameNumber) {
        try {
            File gameFile = new File(fileLocation + "/game" + (gameNumber) + ".txt");
            Scanner fileScan = new Scanner(gameFile);
            while (fileScan.hasNextLine()) {
                String currentLine = fileScan.nextLine().replace(',', ' ');
                Scanner lineScan = new Scanner(currentLine);
                String name = lineScan.next();
                for (Player player : playerList) {
                    if (name.equals(player.getName())) {
                        lineScan.next();
                        String shotStatus = lineScan.next();
                        if (shotType.toUpperCase().equals("ANY") | lineScan.next().equals(shotType)) {
                            if (shotStatus.equals("miss")) {
                                player.addMiss();
                            } else {
                                player.addMade();
                            }
                        }
                    }
                }
            }
        }
        catch (FileNotFoundException e) {
            System.out.println("File " + fileLocation + "/game" + (gameNumber) + ".txt" + " not found. Check for proper formatting of game number and file path args.");
        }
    }

    /**
     *Outputs results from file processing
     */
    public void printResults() {
        System.out.println("Player Longest_Misses Longest_Makes");
        for (Player player : playerList) {
            System.out.println(player.getName() + " " +  player.getMissStreak() + " " + player.getMadeStreak());
        }
    }

    public String getGameNumber(){
        return gameNumber;
    }
}
