package me.opd.gambit.managers;

import me.opd.gambit.GambitPlugin;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.LeatherArmorMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class PlayerManager {

    private GambitPlugin plugin;

    public PlayerManager(GambitPlugin plugin){
        this.plugin = plugin;
    }

    public void handle(Player p){
        PlayerInventory pi = p.getInventory();
        pi.clear();
        p.setLevel(0);
        invSet(p, plugin.alive.get(p));
        //p.sendMessage("locations." + plugin.alive.get(p).name() + "TeamSpawn");
        p.teleport(plugin.getConfig().getLocation("locations." + plugin.alive.get(p).name().toLowerCase() + "TeamSpawn"));

        Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(plugin, new Runnable() {
            public void run() {
                p.addPotionEffect(new PotionEffect(PotionEffectType.NIGHT_VISION, 1000000, 2));
            }
        }, 2L);
    }

    public void addToTeam(Player p, ChatColor color){
        plugin.alive.put(p, color);
        p.sendMessage(ChatManager.prefix + "You have joined the " + color.name().toLowerCase() + " team!");

        p.setDisplayName(color + p.getDisplayName());
        p.setCustomName(color + p.getDisplayName());
        p.setPlayerListName(color + p.getDisplayName());
        //p.sendMessage(color.toString());
    }

    public ChatColor getTeam(Player p){
        if(!plugin.alive.containsKey(p)){
            return null;
        }else{
            return plugin.alive.get(p);
        }
    }

    public void invSet(Player p, ChatColor ccolor){

        Color color = null;

        if(ccolor==ChatColor.RED){
            color = Color.RED;
        }else{
            color= Color.BLUE;
        }

        PlayerInventory pi = p.getInventory();
        ItemStack sword = new ItemStack(Material.IRON_SWORD, 1);
        ItemMeta sMeta = sword.getItemMeta();
        sMeta.addEnchant(Enchantment.DAMAGE_ALL, 1, true);
        sMeta.setUnbreakable(true);
        sword.setItemMeta(sMeta);
        pi.setItem(0, sword);
        ItemStack bow = new ItemStack(Material.BOW, 1);
        ItemMeta bm = bow.getItemMeta();
        bm.addEnchant(Enchantment.ARROW_INFINITE, 1, true);
        bm.setUnbreakable(true);
        bow.setItemMeta(bm);
        pi.setItem(8, bow);

        ItemStack arrow = new ItemStack(Material.ARROW, 1);
        pi.setItem(17, arrow);

        ItemStack steak = new ItemStack(Material.COOKED_BEEF, 64);
        pi.setItem(7, steak);

        ItemStack axe = new ItemStack(Material.IRON_AXE, 1);
        ItemMeta am = axe.getItemMeta();
        am.setUnbreakable(true);
        am.addEnchant(Enchantment.DAMAGE_UNDEAD, 1, true);
        axe.setItemMeta(am);
        pi.setItem(1, axe);

        ItemStack hat = new ItemStack(Material.LEATHER_HELMET, 1);
        LeatherArmorMeta hatLM = (LeatherArmorMeta) hat.getItemMeta();
        hatLM.setColor(color);
        hatLM.setUnbreakable(true);
        hatLM.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 1, true);
        hat.setItemMeta(hatLM);
        pi.setHelmet(hat);

        ItemStack ches = new ItemStack(Material.LEATHER_CHESTPLATE, 1);
        LeatherArmorMeta chesLM = (LeatherArmorMeta) ches.getItemMeta();
        chesLM.setColor(color);
        chesLM.setUnbreakable(true);
        chesLM.addEnchant(Enchantment.PROTECTION_PROJECTILE, 1, true);
        ches.setItemMeta(chesLM);
        pi.setChestplate(ches);

        ItemStack pa = new ItemStack(Material.LEATHER_LEGGINGS, 1);
        LeatherArmorMeta paLM = (LeatherArmorMeta) pa.getItemMeta();
        paLM.setColor(color);
        paLM.setUnbreakable(true);
        paLM.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 1, true);
        pa.setItemMeta(paLM);
        pi.setLeggings(pa);

        ItemStack boo = new ItemStack(Material.LEATHER_BOOTS, 1);
        LeatherArmorMeta booLM = (LeatherArmorMeta) boo.getItemMeta();
        booLM.setColor(color);
        booLM.setUnbreakable(true);
        booLM.addEnchant(Enchantment.PROTECTION_FALL, 1, true);
        boo.setItemMeta(booLM);
        pi.setBoots(boo);

        ItemStack apple = new ItemStack(Material.GOLDEN_APPLE, 2);
        pi.setItem(6, apple);

        ItemStack pearl = new ItemStack(Material.ENDER_PEARL, 4);
        pi.setItem(5, pearl);
    }
}
