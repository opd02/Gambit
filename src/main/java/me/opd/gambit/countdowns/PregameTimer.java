package me.opd.gambit.countdowns;

import me.opd.gambit.GambitPlugin;
import me.opd.gambit.GameStates;
import me.opd.gambit.managers.ChatManager;
import org.bukkit.*;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

public class PregameTimer {

    private GambitPlugin plugin;
    private World world;

    public PregameTimer(GambitPlugin plugin, World world){
        this.plugin = plugin;
        this.world = world;
    }

    public void startPregameTimer() {

        //System.out.println(GambitPlugin.glassBreakPoints.size());

        for(Location b : GambitPlugin.glassBreakPoints){
            b.getWorld().setType(b, Material.YELLOW_STAINED_GLASS);
        }

        for(Entity e : world.getEntities()){
            if(e.getType()==EntityType.WOLF||e.getType()==EntityType.ZOMBIE||e.getType()==EntityType.PILLAGER||e.getType()==EntityType.RAVAGER||e.getType()==EntityType.WITHER||e.getType()==EntityType.VINDICATOR){
                e.remove();
            }
        }

        new BukkitRunnable() {

            int number = 11;

            @Override
            public void run() {
                if (number > 0) {
                    if (number == 10) {
                        Bukkit.broadcastMessage(ChatManager.prefix + "Game starting in 10 seconds.");
                    }
                    if (number == 5) {
                        Bukkit.broadcastMessage(ChatManager.prefix + "Game starting in 5 seconds.");
                    }
                    if (number == 4) {
                        Bukkit.broadcastMessage(ChatManager.prefix + "Game starting in 4 seconds.");
                    }
                    if (number == 3) {
                        Bukkit.broadcastMessage(ChatManager.prefix + "Game starting in 3 seconds.");
                    }
                    if (number == 2) {
                        Bukkit.broadcastMessage(ChatManager.prefix + "Game starting in 2 seconds.");
                    }
                    if (number == 1) {
                        Bukkit.broadcastMessage(ChatManager.prefix + "Game starting in 1 second.");
                        // DO PREGAME THINGS, SCATTER, KITS, ETC
                    }
                    for (Player p : Bukkit.getOnlinePlayers()){
                        p.playSound(p.getLocation(), Sound.BLOCK_TRIPWIRE_CLICK_ON, 1, 1);
                    }
                    number--;
                } else {
                    Bukkit.broadcastMessage(ChatManager.prefix + "The game has now started!");

                    for(Location b : GambitPlugin.glassBreakPoints){
                        b.getWorld().spawnParticle(Particle.BLOCK_CRACK, b, 10, 10, 10, 10, 10, b.getBlock().getBlockData());
                        b.getWorld().playSound(b,Sound.BLOCK_GLASS_BREAK,1,1);
                        b.getWorld().setType(b, Material.AIR);

                    }

                    plugin.setGameState(GameStates.INGAME);

                    WaveSpawning ws = new WaveSpawning(plugin);
                    ws.spawnWaveSpawning(world);


                    cancel();
                }
            }
        }.runTaskTimer(plugin,20L, 20L);
    }
}
