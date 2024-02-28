package me.opd.gambit.prizes;

import me.opd.gambit.GambitPlugin;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityShootBowEvent;
import org.bukkit.inventory.ItemStack;

public class FreezeBomb implements Listener {

    @EventHandler
    public void onBowShoot(EntityShootBowEvent e){
        if(GambitPlugin.frozen.contains(e.getEntity())){
            e.setCancelled(true);
        }
    }

    public static void TriggerFreezeBomb(Player p, GambitPlugin plugin){
        p.getWorld().playSound(p.getLocation(), Sound.BLOCK_GLASS_BREAK, 10, 1);
        for (Entity en : p.getNearbyEntities(15, 15, 15)){
            if((en instanceof LivingEntity)&&!(en instanceof Player)){
                GambitPlugin.frozen.add(en);
                LivingEntity le = (LivingEntity) en;
                le.getEquipment().setHelmet(new ItemStack(Material.ICE, 1));
                le.setAI(false);
                plugin.getServer().getScheduler().scheduleSyncDelayedTask(plugin, new Runnable() {
                    public void run() {
                        GambitPlugin.frozen.remove(en);
                        le.getEquipment().setHelmet(new ItemStack(Material.AIR));
                        p.getWorld().playSound(le.getLocation(), Sound.BLOCK_GLASS_PLACE, 1, 1);
                        le.setAI(true);
                    }
                }, 200L);
            }
        }
    }
}
