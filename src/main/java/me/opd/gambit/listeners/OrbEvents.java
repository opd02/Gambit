package me.opd.gambit.listeners;

import java.util.ArrayList;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.entity.EntityPickupItemEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import me.opd.gambit.GambitPlugin;
import me.opd.gambit.managers.PlayerManager;

public class OrbEvents implements Listener {

    private GambitPlugin plugin;
    PlayerManager pm = new PlayerManager(plugin);
    public OrbEvents(GambitPlugin plugin){
        this.plugin = plugin;
    }

    @EventHandler
    public void onMobDie(EntityDeathEvent e){
        Entity en = e.getEntity();
        Location l = en.getLocation();

        if(e.getEntityType().equals(EntityType.ZOMBIE)||e.getEntityType().equals(EntityType.PILLAGER)){
            ItemStack item = new ItemStack(Material.NETHER_STAR, 1);
            ItemMeta itemmeta = item.getItemMeta();
            itemmeta.setDisplayName(ChatColor.AQUA + "" + ChatColor.BOLD + "Orb");
            ArrayList<String> lore = new ArrayList<String>();
            lore.add("Bank these orbs in");
            lore.add("your command tower!");
            itemmeta.setLore(lore);
            item.setItemMeta(itemmeta);
            en.getWorld().dropItemNaturally(l, item);
            return;
        }
    }

    @EventHandler
    public void onItemPickUp(EntityPickupItemEvent e){

        if(!(e.getEntity() instanceof Player)){
            return;
        }
        Player p = (Player) e.getEntity();

        Item i = e.getItem();
        int in = i.getItemStack().getAmount();
        if(!(i.getItemStack().getType()==Material.NETHER_STAR)){
            return;
        }
        if(p.getLevel()==15){
            p.sendMessage(ChatColor.RED + "You are already carrying the maximum number of motes!");
            i.setPickupDelay(20 * 10);
            e.setCancelled(true);
            return;
        }
        p.setExp((float) 1);
        p.giveExpLevels(in);
        p.playSound(p.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 1, 1);
        p.spawnParticle(Particle.CLOUD, p.getLocation(), 100);
    }
}
