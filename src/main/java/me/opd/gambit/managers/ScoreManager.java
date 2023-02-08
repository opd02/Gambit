package me.opd.gambit.managers;

import me.opd.gambit.GambitPlugin;
import org.bukkit.Color;

public class ScoreManager {

    private int redScore;
    private int blueScore;
    private GambitPlugin plugin;
    private int scoreToWin;

    public ScoreManager(int redStart, int blueStart, GambitPlugin plugin){
        this.plugin = plugin;
        this.redScore = redStart;
        this.blueScore = blueStart;
        this.scoreToWin = 60;
    }

    public int getBlueScore(){
        return this.blueScore;
    }

    public int getRedScore(){
        return this.redScore;
    }

    public void resetAllScores(){
        this.redScore = 0;
        this.blueScore = 0;
    }

    public void increaseScore(Color color, int amount){
        if(color.equals(Color.RED)){
            this.redScore += amount;
        }else if(color.equals(Color.BLUE)){
            this.blueScore += amount;
        }
        checkIfWon(color);
    }

    public void checkIfWon(Color color){
        if(color.equals(Color.RED) && this.redScore >= scoreToWin){
            //TODO Add portal  opening and respawn denying
        }else if(color.equals(Color.BLUE) && this.blueScore >= scoreToWin){
            //TODO add portal as well and respawn blocking
        }
    }
}
