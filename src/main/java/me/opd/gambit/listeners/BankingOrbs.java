package me.opd.gambit.listeners;

import me.opd.gambit.GambitPlugin;
import me.opd.gambit.managers.ChatManager;
import me.opd.gambit.token.TokenGenerator;
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

    public BankingOrbs(GambitPlugin plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onButtonPush(PlayerInteractEvent e) {
        Player p = e.getPlayer();
        Block b = e.getClickedBlock();
        if (!(e.getAction().equals(Action.RIGHT_CLICK_BLOCK))) {
            return;
        }
        if (!(b.getType() == Material.STONE_BUTTON)) {
            return;
        }
        int levels = p.getLevel();
        if (levels == 0) {
            p.sendMessage(ChatColor.RED + "You have nothing to bank!");
            p.playSound(p.getLocation(), Sound.ENTITY_ZOMBIE_INFECT, 10, (float) 1);
            return;
        }

        ChatColor color = plugin.alive.get(p);
        if (color == null) {
            p.sendMessage(ChatManager.prefix + ChatManager.format("&cYou are not on a team silly goose."));
            return;
        }
        if (color == ChatColor.RED) {
            if (GambitPlugin.scoreManager.redBossLevel == 1 || GambitPlugin.scoreManager.redBossLevel == 3 || GambitPlugin.scoreManager.redBossLevel == 5) {
                p.sendMessage(ChatManager.prefix + ChatManager.format("&cYou are not allowed to bank while a boss is out in your area!"));
                p.playSound(p.getLocation(), Sound.ENTITY_ZOMBIE_INFECT, 10, (float) 1);
                return;
            }
        } else if (color == ChatColor.BLUE) {
            if (GambitPlugin.scoreManager.blueBossLevel == 1 || GambitPlugin.scoreManager.blueBossLevel == 3 || GambitPlugin.scoreManager.blueBossLevel == 5) {
                p.sendMessage(ChatManager.prefix + ChatManager.format("&cYou are not allowed to bank while a boss is out in your area!"));
                p.playSound(p.getLocation(), Sound.ENTITY_ZOMBIE_INFECT, 10, (float) 1);
                return;
            }
        }
        GambitPlugin.scoreManager.increaseScore(color, p.getLevel());

        p.playSound(p.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 10, 1);
        p.getInventory().remove(Material.NETHER_STAR);
        p.sendTitle(ChatColor.BOLD + "ORBS BANKED",ChatColor.YELLOW + String.valueOf(levels) + " orbs banked", 4, 40, 4);

        int spingive = levels / 5;
        p.getInventory().addItem(TokenGenerator.tokenItem(spingive));
        p.setLevel(0);
        p.sendMessage(ChatManager.prefix + ChatColor.YELLOW + "You received §b" + Integer.toString(spingive) + " §espin token(s)!");
    }
}
