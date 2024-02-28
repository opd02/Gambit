package me.opd.gambit.listeners;

import me.opd.gambit.GambitPlugin;
import me.opd.gambit.prizes.FreezeBomb;
import me.opd.gambit.prizes.InkBomb;
import me.opd.gambit.prizes.TeamSpeedBoost;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.inventory.ItemStack;

public class DropPrizeEvent implements Listener {
    private GambitPlugin plugin;

    public DropPrizeEvent(GambitPlugin plugin){
        this.plugin = plugin;
    }

    @EventHandler
    public void onPlayerDropItem(PlayerDropItemEvent e){
        Player p = e.getPlayer();
        ItemStack item = e.getItemDrop().getItemStack();

        switch(item.getType()){
            case SUGAR:
                TeamSpeedBoost.TriggerTeamSpeedBoost(p);
                Bukkit.getServer().broadcastMessage(p.getName() + "" + ChatColor.GOLD + " has used a team speed boost!");
                e.getItemDrop().remove();
            case INK_SAC:
                InkBomb.TriggerInkBomb(p);
                Bukkit.getServer().broadcastMessage(p.getName() + "" + ChatColor.GOLD + " has used an ink bomb!");
                e.getItemDrop().remove();
            case ICE:
                FreezeBomb.TriggerFreezeBomb(p, plugin);
                Bukkit.getServer().broadcastMessage(p.getName() + "" + ChatColor.GOLD + " has used a freeze bomb!");
                e.getItemDrop().remove();
            default:
        }
    }
}
