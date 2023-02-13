package me.opd.gambit.token;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class TokenGenerator {

    public static ItemStack tokenItem(int amount){
        ItemStack token = new ItemStack(Material.SUNFLOWER, amoumt);

        ItemMeta tokemMeta = token.getItemMeta();

        return token;
    }
}
