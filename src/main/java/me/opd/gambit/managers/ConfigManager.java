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


    public ArrayList<Location> getGlassPoints(){
        ArrayList<Location> points = new ArrayList<Location>();
        for(Object loc : plugin.getConfig().getList("locations.glassBreakPoints").toArray()){
            Location location = (Location) loc;
            points.add(location);
        }
        return points;
    }

    public ArrayList<Location> getSpawningLocations(String string){
        ArrayList<Location> points = new ArrayList<Location>();
        for(Object loc : plugin.getConfig().getList("locations." + string + "MobSpawnLocations").toArray()){
            Location location = (Location) loc;
            points.add(location);
        }
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
