package me.opd.gambit.managers;

import org.bukkit.Location;

import me.opd.gambit.GambitPlugin;

public class ConfigManager {

    private GambitPlugin plugin;

    public ConfigManager (GambitPlugin plugin){
        this.plugin = plugin;
    }

    public void saveLocation(Location location, String configStringName){
        plugin.getConfig().set(configStringName, location);
        plugin.saveConfig();
    }

    public Location locationFromConfig(String string){
        return plugin.getConfig().getLocation(string);
    }
    //Lobby X Y Z
    //RedSpawn X  Y Z
    //BlueSpawn X Y Z
    //RedMobSpawn X Y Z
    //BlueMobSpawn X Y Z
    //RedTeamScore
    //BlueTeamScore
}
