package me.opd.gambit.managers;

import me.opd.gambit.GambitPlugin;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Sound;

public class ScoreManager {

    private int redScore;
    private int blueScore;
    private GambitPlugin plugin;
    private int scoreToWin;
    public int blueBossLevel;
    public int redBossLevel;

    //Boss level 0 is starting, no boss out
    //Boss level 1 is first boss is out
    //Boss level 2 is fighting round 2
    //Boss level 3 is second boss
    //Boss level 4 is third fighting round
    //Boss level 5 is third boss WITHER
    //Boss level 6 is all done and just fighting for tokens are their portal is open

    public ScoreManager(int redStart, int blueStart, GambitPlugin plugin) {
        this.plugin = plugin;
        this.redScore = redStart;
        this.blueScore = blueStart;
        this.scoreToWin = 60;
        blueBossLevel = 0;
        redBossLevel = 0;
    }

    public void setScoreToWin(int scoreToWin) {
        this.scoreToWin = scoreToWin;
    }

    public int getBlueScore() {
        return this.blueScore;
    }

    public int getRedScore() {
        return this.redScore;
    }

    public void resetAllScores() {
        this.redScore = 0;
        this.blueScore = 0;
        this.blueBossLevel = 0;
        this.redBossLevel = 0;
    }

    public void increaseScore(ChatColor color, int amount) {
        if (color.equals(ChatColor.RED)) {
            this.redScore += amount;
            Bukkit.getServer().broadcastMessage(ChatManager.prefix + ChatColor.RED + "Red team now has " + this.redScore + " orbs in the bank!");
        } else if (color.equals(ChatColor.BLUE)) {
            this.blueScore += amount;
            Bukkit.getServer().broadcastMessage(ChatManager.prefix + ChatColor.BLUE + "Blue team now has " + this.blueScore + " orbs in the bank!");
        }
        runScoreChecks(color);
    }

    public void runScoreChecks(ChatColor color) {

        if (color.equals(ChatColor.RED) && this.redScore >= scoreToWin) {
            GambitPlugin.allowRespawning = false;
            this.redBossLevel = 5;
            Bukkit.getServer().broadcastMessage(ChatManager.prefix + " Red team has reached the final death match!");
            Bukkit.getServer().broadcastMessage(ChatManager.prefix + " Respawning has been disabled for all players.");
            PlayerManager.playerSoundForPlayers(Sound.ENTITY_ENDER_DRAGON_GROWL,0.1f);

        } else if (color.equals(ChatColor.BLUE) && this.blueScore >= scoreToWin) {
            GambitPlugin.allowRespawning = false;
            this.redBossLevel = 5;
            Bukkit.getServer().broadcastMessage(ChatManager.prefix + " Blue team has reached the final death match!");
            Bukkit.getServer().broadcastMessage(ChatManager.prefix + " Respawning has been disabled for all players.");
            PlayerManager.playerSoundForPlayers(Sound.ENTITY_ENDER_DRAGON_GROWL,0.1f);

        } else {
            checkIfBoss(color);
        }
    }

    public void checkIfBoss(ChatColor color) {

    }
}
