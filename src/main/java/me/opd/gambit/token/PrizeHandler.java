package me.opd.gambit.token;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.Random;

public class PrizeHandler {
    public static void spinItem(Player p, Location loc){
        Random rand = new Random();
        int got = rand.nextInt(9);
        if(got==0){
            ItemStack sugar = new ItemStack(Material.SUGAR, 3);
            ItemMeta sugarmeta = sugar.getItemMeta();
            sugarmeta.setDisplayName(ChatColor.GRAY + "" + ChatColor.BOLD + "Team Speed Boost");
            ArrayList<String> lore = new ArrayList<String>();
            lore.add(ChatColor.GRAY + "Drop this item to give");
            lore.add(ChatColor.GRAY + "your team a boost!");
            sugarmeta.setLore(lore);
            sugar.setItemMeta(sugarmeta);
            p.getWorld().dropItem(loc, sugar);
        }
        if(got==1){
            ItemStack inc = new ItemStack(Material.INK_SAC, 2);
            ItemMeta incmeta = inc.getItemMeta();
            ArrayList<String> lore = new ArrayList<String>();
            lore.add(ChatColor.GRAY + "Drop this item to");
            lore.add(ChatColor.GRAY + "blind your opponents!");
            incmeta.setLore(lore);
            incmeta.setDisplayName(ChatColor.DARK_GRAY + "" + ChatColor.BOLD + "Ink Bomb");
            inc.setItemMeta(incmeta);
            p.getWorld().dropItem(loc, inc);
        }
        if(got==2){
            ItemStack dia = new ItemStack(Material.DIAMOND, 1);
            ItemMeta diam = dia.getItemMeta();
            diam.setDisplayName(ChatColor.AQUA + "" + ChatColor.BOLD + "Armor Upgrade Module");
            ArrayList<String> lore = new ArrayList<String>();
            lore.add("Drop this item to");
            lore.add("upgrade your armor!");
            diam.setLore(lore);
            dia.setItemMeta(diam);
            p.getWorld().dropItem(loc, dia);
        }
        if(got==3){
            ItemStack dia = new ItemStack(Material.ANVIL, 1);
            ItemMeta diam = dia.getItemMeta();
            diam.setDisplayName(ChatColor.LIGHT_PURPLE + "" + ChatColor.BOLD + "Weapon Upgrade Module");
            ArrayList<String> lore = new ArrayList<String>();
            lore.add("Drop this item to");
            lore.add("upgrade your weapons!");
            diam.setLore(lore);
            dia.setItemMeta(diam);
            p.getWorld().dropItem(loc, dia);
        }
        if(got==4){
            ItemStack dia = new ItemStack(Material.BONE, 2);
            ItemMeta diam = dia.getItemMeta();
            diam.setDisplayName(ChatColor.BLUE + "" + ChatColor.BOLD + "Summon Wolf");
            ArrayList<String> lore = new ArrayList<String>();
            lore.add(ChatColor.GRAY + "Drop this item to");
            lore.add(ChatColor.GRAY + "summon a wolf!");
            diam.setLore(lore);
            dia.setItemMeta(diam);
            p.getWorld().dropItem(loc, dia);
        }
        if(got==5){
            ItemStack dia = new ItemStack(Material.ICE, 2);
            ItemMeta diam = dia.getItemMeta();
            diam.setDisplayName(ChatColor.DARK_BLUE + "" + ChatColor.BOLD + "Freeze Mobs");
            ArrayList<String> lore = new ArrayList<String>();
            lore.add(ChatColor.GRAY + "Drop this item to");
            lore.add(ChatColor.GRAY + "freeze nearby mobs!");
            diam.setLore(lore);
            dia.setItemMeta(diam);
            p.getWorld().dropItem(loc, dia);
        }
        if(got==6){
            ItemStack dia = new ItemStack(Material.MAGMA_CREAM, 1);
            ItemMeta diam = dia.getItemMeta();
            diam.setDisplayName(ChatColor.GOLD + "" + ChatColor.BOLD + "Hot Hand");
            ArrayList<String> lore = new ArrayList<String>();
            lore.add("Attack with this to");
            lore.add("set foes a blaze!");
            diam.setLore(lore);
            diam.addEnchant(Enchantment.FIRE_ASPECT, 2, true);
            dia.setItemMeta(diam);
            p.getWorld().dropItem(loc, dia);
        }
        if(got==7){
            ItemStack dia = new ItemStack(Material.ENCHANTED_GOLDEN_APPLE, 1);
            ItemMeta diam = dia.getItemMeta();
            diam.setDisplayName(ChatColor.YELLOW + "" + ChatColor.BOLD + "Help of Notch");
            dia.setItemMeta(diam);
            p.getWorld().dropItem(loc, dia);
        }
        if(got==8){
            ItemStack dia = new ItemStack(Material.SCUTE, 2);
            ItemMeta diam = dia.getItemMeta();
            diam.setDisplayName(ChatColor.GREEN + "" + ChatColor.BOLD + "Turtle Shell");
            ArrayList<String> lore = new ArrayList<String>();
            lore.add(ChatColor.GRAY + "Drop this item to");
            lore.add(ChatColor.GRAY + "slow your opponents!");
            diam.setLore(lore);
            dia.setItemMeta(diam);
            p.getWorld().dropItem(loc, dia);
        }
    }
}
