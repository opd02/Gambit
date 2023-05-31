package me.opd.gambit.countdowns;

import me.opd.gambit.GambitPlugin;
import me.opd.gambit.GameStates;
import me.opd.gambit.managers.ScoreManager;
import org.bukkit.*;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitTask;

import java.util.Random;

public class WaveSpawning {

    private GambitPlugin plugin;
    public static boolean blueMobSpawning;
    public static boolean redMobSpawning;
    private Random random = new Random();
    public static BukkitTask stask = null;
    private int select;
    private ScoreManager scoreManager;

    public WaveSpawning(GambitPlugin plugin){
        this.plugin = plugin;
        blueMobSpawning = true;
        redMobSpawning = true;
        this.scoreManager = new ScoreManager(0, 0, plugin);
    }

    public void spawnWaveSpawning(World world){

        stask = Bukkit.getScheduler().runTaskTimer(plugin, new Runnable() {
            @Override
            public void run() {

                select = random.nextInt(GambitPlugin.blueMobSpawnLocations.size());

                if(!GambitPlugin.getGameState().equals(GameStates.INGAME)){
                    Bukkit.getServer().broadcastMessage(ChatColor.RED + "Mob spawning has ended because the game in not in the INGAME status.");
                    stask.cancel();
                    return;
                }

                if(blueMobSpawning){
                    spawnWave(GambitPlugin.blueMobSpawnLocations.get(select));
                    for (Player p : Bukkit.getOnlinePlayers()){
                        if(GambitPlugin.playerManager.getTeam(p)==ChatColor.BLUE){
                            p.playSound(p.getLocation(), Sound.ENTITY_ELDER_GUARDIAN_CURSE, 1, 1);
                        }
                    }
                }
                if(redMobSpawning){
                    spawnWave(GambitPlugin.redMobSpawnLocations.get(select));
                    for (Player p : Bukkit.getOnlinePlayers()){
                        if(GambitPlugin.playerManager.getTeam(p)==ChatColor.RED){
                            p.playSound(p.getLocation(), Sound.ENTITY_ELDER_GUARDIAN_CURSE, 1, 1);
                        }
                    }
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
