package me.opd.gambit.listeners;

import me.opd.gambit.GameStates;
import me.opd.gambit.managers.ConfigManager;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import me.opd.gambit.GambitPlugin;
import me.opd.gambit.managers.ChatManager;
import me.opd.gambit.managers.PlayerManager;

public class Join implements Listener {

    private GambitPlugin plugin;
    private ConfigManager cm;

    public Join(GambitPlugin plugin){

        this.plugin = plugin;
        this.cm = new ConfigManager(plugin);
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent e){

        Player p = e.getPlayer();
        e.setJoinMessage("");

        if(plugin.getGameState()== GameStates.LOBBY){
            p.sendMessage(ChatManager.prefix + ChatManager.format("Welcome to Gambit! Type &r/gteam join <blue/red>&7 to join a team!"));
            p.getInventory().clear();
            p.getEquipment().clear();
            p.teleport(cm.locationFromConfig("locations.lobby"));
            //new PlayerManager(plugin).handle(p);
        }
    }
}
