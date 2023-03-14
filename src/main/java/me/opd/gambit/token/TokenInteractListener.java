package me.opd.gambit.token;

import me.opd.gambit.managers.ChatManager;
import org.bukkit.*;
import org.bukkit.block.Block;
import org.bukkit.entity.Firework;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.meta.FireworkMeta;

public class TokenInteractListener implements Listener {
    @EventHandler
    public void onOpenEnderchest(PlayerInteractEvent e){
        Block b = e.getClickedBlock();
        if(!(e.getAction().equals(Action.RIGHT_CLICK_BLOCK))){
            return;
        }
        if(!(e.getClickedBlock().getType().equals(Material.ENDER_CHEST))){
            return;
        }
        Player p = (Player) e.getPlayer();
        if(p.getInventory().getItemInMainHand().getType()==Material.AIR){
            e.setCancelled(true);
            return;
        }
        if(!(p.getInventory().getItemInMainHand().getItemMeta().getDisplayName().contains("Spin"))){
            p.sendMessage(ChatManager.prefix + ChatColor.RED + "" + ChatColor.BOLD + "That is not a spin token!");
            p.playSound(p.getLocation(), Sound.ITEM_SHIELD_BREAK, (float) 0.5, (float) 0.5);
            e.setCancelled(true);
            return;
        }
        int num = p.getInventory().getItemInMainHand().getAmount() - 1;
        p.getInventory().getItemInMainHand().setAmount(num);
        Location fwl = new Location(p.getWorld(), b.getX() + 0.5, b.getY() + 1, b.getZ() + 0.5);
        Firework fw = (Firework) p.getWorld().spawn(fwl, Firework.class);
        FireworkMeta fm = fw.getFireworkMeta();
        fm.setPower(0);
        fm.addEffect(FireworkEffect.builder().withColor(Color.YELLOW).withFade(Color.RED).with(FireworkEffect.Type.BALL).build());
        fw.setFireworkMeta(fm);
        fw.detonate();
        p.playSound(p.getLocation(), Sound.ENTITY_GENERIC_EXPLODE, (float) 1, (float) 1);
        PrizeHandler.spinItem(p,p.getLocation());
        e.setCancelled(true);
    }

    @EventHandler
    public void onPlayerDamage(EntityDamageEvent e){
        if(e.getCause().equals(EntityDamageEvent.DamageCause.ENTITY_EXPLOSION)){
            e.setCancelled(true);
        }
    }
}
