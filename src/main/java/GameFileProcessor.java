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
     * Attempts to load a file into a Scanner
     */
    private boolean loadFile(String game){
        System.out.println("Loading file...");
        try {
            File gameFile = new File(fileLocation + "/game" + game + ".txt");
            Scanner fileScan = new Scanner(gameFile);
            System.out.println("File loaded successfully.");
        }
        catch(FileNotFoundException e) {
            System.out.println("File " + fileLocation + "/game" + game + ".txt" + " not found.");
            return false;
        }
        return true;
    }

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
        if (!(shotType.equals("1")|shotType.equals("2")|shotType.equals("3"))){
            System.out.println("Invalid shot type provided.");
            return false;
        }
        //Get players of interest
        while(inputScan.hasNext()){
            playerList.add(new Player(inputScan.next()));
        }
        System.out.println("Valid arguments provided.");
        return true;
    }
    public boolean processFile(){
        boolean proceed = true;
        if(gameNumber.toUpperCase().equals("ALL")){
            for(int i=1;(i<=82)&(proceed);i++){
                loadFile(Integer.toString(i));
            }
        }
        else {
            proceed = loadFile(gameNumber);
        }
        return proceed;
    }
}
