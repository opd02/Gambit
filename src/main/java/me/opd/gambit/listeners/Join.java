package me.opd.gambit.listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import me.opd.gambit.GambitPlugin;
import me.opd.gambit.managers.ChatManager;
import me.opd.gambit.managers.PlayerManager;

public class Join implements Listener {

    private GambitPlugin plugin;
    public Join(GambitPlugin plugin){
        this.plugin = plugin;
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent e){
        Player p = e.getPlayer();

        e.setJoinMessage("");
        p.sendMessage(ChatManager.prefix + ChatManager.format("Welcome to Gambit! Type &r/gteam join <blue/red>&7 to join a team!"));
        new PlayerManager(plugin).handle(p);

    }
}
