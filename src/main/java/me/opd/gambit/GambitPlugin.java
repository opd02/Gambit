package me.opd.gambit;

import me.opd.gambit.commands.*;
import me.opd.gambit.listeners.*;
import me.opd.gambit.managers.ConfigManager;
import me.opd.gambit.managers.PlayerManager;
import me.opd.gambit.managers.ScoreManager;
import me.opd.gambit.token.TokenInteractListener;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;

public class GambitPlugin extends JavaPlugin {

    private static GameStates gamestates;
    public static HashMap<UUID, ChatColor> alive = new HashMap<UUID, ChatColor>();
    public static ArrayList<UUID> spectating = new ArrayList<>();
    public static ArrayList<UUID> setup = new ArrayList<>();
    public static ArrayList<Location> glassBreakPoints = new ArrayList<>();
    public static ScoreManager scoreManager;
    public static boolean allowRespawning;
    public static ArrayList<Location> blueMobSpawnLocations = new ArrayList<>();
    public static ArrayList<Location> redMobSpawnLocations = new ArrayList<>();
    public static PlayerManager playerManager;
    public static ArrayList<Entity> frozen = new ArrayList<>();
    public static Location redBossSpawnLocation;
    public static Location blueBossSpawnLocation;

    @Override
    public void onEnable() {
        registerCommands();
        registerEvents();
        setGameState(GameStates.LOBBY);
        GambitPlugin.allowRespawning = true;
        ConfigManager cm = new ConfigManager(this);
        GambitPlugin.glassBreakPoints = cm.getGlassPoints();
        GambitPlugin.redMobSpawnLocations = cm.getSpawningLocations("red");
        GambitPlugin.blueMobSpawnLocations = cm.getSpawningLocations("blue");
        GambitPlugin.scoreManager = new ScoreManager(0, 0, this);
        GambitPlugin.playerManager = new PlayerManager(this);

        GambitPlugin.redBossSpawnLocation = getConfig().getLocation("locations.RedTeamBossSpawn");
        GambitPlugin.blueBossSpawnLocation = getConfig().getLocation("locations.BlueTeamBossSpawn");
    }

    @Override
    public void onDisable() {
        alive.clear();
        spectating.clear();
        setup.clear();

        this.getConfig().set("locations.blueMobSpawnLocations", GambitPlugin.blueMobSpawnLocations);
        this.getConfig().set("locations.redMobSpawnLocations", GambitPlugin.redMobSpawnLocations);

        this.getConfig().set("locations.glassBreakPoints", GambitPlugin.glassBreakPoints);
        this.saveConfig();
        glassBreakPoints.clear();
    }

    private void registerCommands() {
        Bukkit.getServer().getPluginCommand("start").setExecutor(new Start(this));
        Bukkit.getServer().getPluginCommand("setup").setExecutor(new Setup(this));
        Bukkit.getServer().getPluginCommand("gteam").setExecutor(new GTeam(this));
        Bukkit.getServer().getPluginCommand("tokengive").setExecutor(new TokenGive());
        Bukkit.getServer().getPluginCommand("gabort").setExecutor(new GAbort(this));
    }

    private void registerEvents() {
        PluginManager pm = Bukkit.getPluginManager();
        pm.registerEvents(new Join(this), this);
        pm.registerEvents(new OrbEvents(this), this);
        pm.registerEvents(new Respawn(this), this);
        pm.registerEvents(new SetupBlockPlace(this), this);
        pm.registerEvents(new BankingOrbs(this), this);
        pm.registerEvents(new TokenInteractListener(), this);
        pm.registerEvents(new DropPrizeEvent(this), this);
    }

    public static GameStates getGameState() {
        return gamestates;
    }

    public static void setGameState(GameStates gameState) {
        gamestates = gameState;
    }
}
