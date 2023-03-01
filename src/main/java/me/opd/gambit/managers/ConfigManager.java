package me.opd.gambit.managers;

import org.bukkit.Location;

import me.opd.gambit.GambitPlugin;

import java.util.ArrayList;

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

    public void saveGlassBreakPoints(){
        int i = 0;
        for(Location loc : GambitPlugin.glassBreakPoints){
            plugin.getConfig().set("locations.glassBreakPoints." + i,loc);
            i++;
        }
        plugin.saveConfig();
    }

    public ArrayList<Location> getGlassPoints(){
        ArrayList<Location> points = new ArrayList<Location>();
        //TODO add this
        return points;
    }
    //Lobby X Y Z
    //RedSpawn X  Y Z
    //BlueSpawn X Y Z
    //RedMobSpawn X Y Z
    //BlueMobSpawn X Y Z
    //RedTeamScore
    //BlueTeamScore
}
