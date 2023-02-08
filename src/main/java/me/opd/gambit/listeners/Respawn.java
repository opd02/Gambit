package me.opd.gambit.listeners;

import me.opd.gambit.GambitPlugin;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class Respawn implements Listener {

    private GambitPlugin plugin;
    public Respawn(GambitPlugin plugin){
        this.plugin = plugin;
    }

    @EventHandler
    public void onPlayerRespawn(PlayerRespawnEvent e){
        Player p = e.getPlayer();
        PlayerInventory pi = p.getInventory();
        pi.remove(Material.NETHER_STAR);
        p.sendMessage("§cYou have died and lost all the orbs you were carrying!");
        p.setLevel(0);
        Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(plugin, new Runnable() {
            public void run() {
                p.addPotionEffect(new PotionEffect(PotionEffectType.NIGHT_VISION, 1000000, 2));
            }
        }, 2L);
        return;
    }
}
