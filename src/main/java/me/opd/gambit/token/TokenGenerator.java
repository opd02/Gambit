package me.opd.gambit.token;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;

public class TokenGenerator {

    public static ItemStack tokenItem(int amount){
        ItemStack token = new ItemStack(Material.SUNFLOWER, amount);
        ItemMeta itemmeta = token.getItemMeta();
        itemmeta.setDisplayName(ChatColor.GOLD + "" + ChatColor.BOLD + "Spin Token");
        ArrayList<String> lore = new ArrayList<String>();
        lore.add(ChatColor.GRAY + "Right click on the enderchest");
        lore.add(ChatColor.GRAY + "to get a prize!");
        itemmeta.setLore(lore);
        token.setItemMeta(itemmeta);

        return token;
    }

    public static void generateToken(Location location, int amount){
        location.getWorld().dropItemNaturally(location, tokenItem(amount));
    }
}
