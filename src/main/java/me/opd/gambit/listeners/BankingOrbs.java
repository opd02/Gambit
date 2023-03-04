package me.opd.gambit.listeners;

import me.opd.gambit.GambitPlugin;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

public class BankingOrbs implements Listener {

    private GambitPlugin plugin;

    public BankingOrbs(GambitPlugin plugin){
        this.plugin = plugin;
    }

    @EventHandler
    public void onButtonPush(PlayerInteractEvent e){
        Player p = e.getPlayer();
        Block b = e.getClickedBlock();
        if(!(e.getAction().equals(Action.RIGHT_CLICK_BLOCK))){
            return;
        }
        if(!(b.getType()==Material.STONE_BUTTON)){
            return;
        }
        int levels = p.getLevel();
        if(levels==0){
            p.sendMessage(ChatColor.RED + "You have nothing to bank!");
            p.playSound(p.getLocation(), Sound.ENTITY_ZOMBIE_INFECT, 10, (float)1);
            return;
        }

        ChatColor color = plugin.alive.get(p);
        GambitPlugin.scoreManager.increaseScore(color, p.getLevel());
    }
}
