package me.opd.gambit.listeners;

import me.opd.gambit.GambitPlugin;
import me.opd.gambit.managers.ChatManager;
import me.opd.gambit.managers.ConfigManager;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;

public class SetupBlockPlace implements Listener {

    private GambitPlugin plugin;
    public SetupBlockPlace(GambitPlugin plugin){
        this.plugin = plugin;
    }

    @EventHandler
    public void onPlayerBlockPlace(BlockPlaceEvent e){
        ConfigManager cm = new ConfigManager(plugin);
        Player p = e.getPlayer();
        Block block = e.getBlock();

        if(!plugin.setup.contains(p)){
            return;
        }

        Location location = block.getLocation();

        switch(block.getType()){
            case EMERALD_BLOCK:
                p.sendMessage(ChatManager.prefix + ChatManager.format("You have set the lobby point."));
                cm.saveLocation(location, "locations.lobby");
                e.setCancelled(true);
                break;
            case RED_BANNER:
                p.sendMessage(ChatManager.prefix + ChatManager.format("You have set the spawn point for the &cred team&7."));
                cm.saveLocation(location, "locations.redTeamSpawn");
                e.setCancelled(true);
                break;
            case BLUE_BANNER:
                p.sendMessage(ChatManager.prefix + ChatManager.format("You have set the spawn point for the &9blue team&7."));
                cm.saveLocation(location, "locations.blueTeamSpawn");
                e.setCancelled(true);
                break;
            case RED_GLAZED_TERRACOTTA:
                p.sendMessage(ChatManager.prefix + ChatManager.format("You have set a mob spawn point for the &cred team&7."));
                //cm.saveLocation(location, "locations.redMobSpawn." + GambitPlugin.redMobSpawnLocations);
                GambitPlugin.redMobSpawnLocations.add(block.getLocation());
                e.setCancelled(true);
                break;
            case BLUE_GLAZED_TERRACOTTA:
                p.sendMessage(ChatManager.prefix + ChatManager.format("You have set a mob spawn point for the &9blue team&7."));
                //cm.saveLocation(location, "locations.blueMobSpawn." + GambitPlugin.blueMobSpawnLocations);
                GambitPlugin.blueMobSpawnLocations.add(block.getLocation());
                e.setCancelled(true);
                break;

            case RED_STAINED_GLASS:
                p.sendMessage(ChatManager.prefix + ChatManager.format("You have set a glass break point for the &cred team&7."));
                GambitPlugin.glassBreakPoints.add(location);
                break;
            case BLUE_STAINED_GLASS:
                p.sendMessage(ChatManager.prefix + ChatManager.format("You have set a glass break point for the &9blue team&7."));
                GambitPlugin.glassBreakPoints.add(location);
                break;
            case ENDER_CHEST:
                p.sendMessage(ChatManager.prefix + ChatManager.format("You have set a crate location&7."));
                cm.saveLocation(e.getBlock().getLocation(), "");
                //TODO add this to be a list situation rather than a single chest
                break;
            default:
                return;
        }
        p.playSound(p, Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 1, 1);
    }
}
