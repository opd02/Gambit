package me.opd.gambit;

import me.opd.gambit.commands.GTeam;
import me.opd.gambit.commands.Setup;
import me.opd.gambit.commands.Start;
import me.opd.gambit.listeners.*;
import me.opd.gambit.managers.ScoreManager;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.HashMap;

public class GambitPlugin extends JavaPlugin {

    private GameStates gamestates;
    public HashMap<Player, ChatColor> alive = new HashMap<Player, ChatColor>();
    public ArrayList<Player> spectating = new ArrayList<>();
    //public ArrayList<Player> vanished = new ArrayList<>();
    public ArrayList<Player> setup = new ArrayList<>();
    public static int redMobSpawnLocations = 8;
    public static int blueMobSpawnLocations = 8;
    public static ArrayList<Location> glassBreakPoints = new ArrayList<Location>();
    public ScoreManager scoreManager = new ScoreManager(0, 0, this);
    public static boolean allowRespawning;

    @Override
    public void onEnable() {
        registerCommands();
        registerEvents();
        setGameState(GameStates.LOBBY);
        GambitPlugin.allowRespawning = true;
    }

    @Override
    public void onDisable() {
        this.saveConfig();
        alive.clear();
        spectating.clear();
        setup.clear();
        glassBreakPoints.clear();
    }

    private void registerCommands() {
        Bukkit.getServer().getPluginCommand("start").setExecutor(new Start(this));
        Bukkit.getServer().getPluginCommand("setup").setExecutor(new Setup(this));
        Bukkit.getServer().getPluginCommand("gteam").setExecutor(new GTeam(this));
    }

    private void registerEvents() {
        PluginManager pm = Bukkit.getPluginManager();
        pm.registerEvents(new Join(this), this);
        pm.registerEvents(new OrbEvents(this), this);
        pm.registerEvents(new Respawn(this), this);
        pm.registerEvents(new SetupBlockPlace(this), this);
        pm.registerEvents(new BankingOrbs(this), this);
    }

    public GameStates getGameState(){
        return this.gamestates;
    }

    public void setGameState(GameStates gameState){
        this.gamestates = gameState;
    }
}
