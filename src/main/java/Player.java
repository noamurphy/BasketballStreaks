/**
 *Class for tracking miss and made shot streaks of a player
 */
public class Player {
    private String name;
    private int missStreak, madeStreak, missCount, madeCount;
    Player(String name){
        this.name = name;
    }

    /**
     * Adds a made shot to counter, resets miss counter, and sets made streak if applicable
     */
    public void addMade(){
        madeCount++;
        missCount = 0;
        if (madeCount > madeStreak){
            madeStreak = madeCount;
        }
    }

    /**
     * Adds a miss to counter, resets made counter, and sets miss streak if applicable
     */
    public void addMiss(){
        missCount++;
        madeCount = 0;
        if (missCount > missStreak){
            missStreak = missCount;
        }
    }
}
