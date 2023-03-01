package me.opd.gambit.countdowns;

import me.opd.gambit.GambitPlugin;
import me.opd.gambit.GameStates;
import org.bukkit.*;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitTask;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class WaveSpawning {

    private GambitPlugin plugin;
    public boolean blueMobSpawning;
    public boolean redMobSpawning;
    private Random random = new Random();
    public static BukkitTask stask = null;
    private int select;

    public ArrayList<Location> redMobSpawnLocations;
    public ArrayList<Location> blueMobSpawnLocations;

    @SuppressWarnings("unchecked")
    public WaveSpawning(GambitPlugin plugin){
        this.plugin = plugin;
        this.blueMobSpawning = true;
        this.redMobSpawning = false;
//TODO get this spawning to work, add glass way of storing locations are entire object lest heap
        this.redMobSpawnLocations = (List<Location>) plugin.getConfig().getList("locations.redMobSpawn").toArray();
        this.blueMobSpawnLocations = (List<Location>) plugin.getConfig().getList("locations.blueMobSpawn").toArray();
    }

    public void spawnWaveSpawning(World world){

        stask = Bukkit.getScheduler().runTaskTimer(plugin, new Runnable() {
            @Override
            public void run() {

                select = random.nextInt(GambitPlugin.blueMobSpawnLocations);

                if(!plugin.getGameState().equals(GameStates.INGAME)){
                    System.out.println(ChatColor.RED + "Mob spawning has ended because the game in not in the INGAME status.");
                    stask.cancel();
                    return;
                }

                if(blueMobSpawning){
                    spawnWave(blueMobSpawnLocations.get(select));
                }
                if(redMobSpawning){
                    spawnWave(redMobSpawnLocations.get(select));
                }

                for (Player p : Bukkit.getOnlinePlayers()){
                    p.playSound(p.getLocation(), Sound.ENTITY_ELDER_GUARDIAN_CURSE, 1, 1);
                }
            }
        }, 0, 900L);

    }

    public void spawnWave(Location location){
        location.getWorld().spawnEntity(location, EntityType.ZOMBIE);
        location.getWorld().spawnEntity(location, EntityType.ZOMBIE);
        location.getWorld().spawnEntity(location, EntityType.ZOMBIE);
        location.getWorld().spawnEntity(location, EntityType.ZOMBIE);
        location.getWorld().spawnEntity(location, EntityType.ZOMBIE);
        location.getWorld().spawnEntity(location, EntityType.PILLAGER);
    }
}
