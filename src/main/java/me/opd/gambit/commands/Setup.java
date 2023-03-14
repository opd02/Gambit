package me.opd.gambit.commands;

import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import me.opd.gambit.GambitPlugin;
import me.opd.gambit.managers.ChatManager;

public class Setup implements CommandExecutor {

    private GambitPlugin plugin;

    public Setup(GambitPlugin gambitPlugin) {
        this.plugin = gambitPlugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        if(cmd.getName().equalsIgnoreCase("setup")){
            if(!sender.isOp()){
                sender.sendMessage(ChatManager.permission);
                return true;
            }

            if(!(sender instanceof Player)){
                sender.sendMessage(ChatManager.format("&cThis command is for players only."));
                return false;
            }

            Player p = (Player) sender;

            if(plugin.setup.contains(p)){

                plugin.setup.remove(p);
                p.sendMessage(ChatManager.prefix + ChatManager.format("You have been removed from the set-up crew."));
                p.setGameMode(GameMode.ADVENTURE);
                p.getInventory().clear();
                plugin.getConfig().set("locations.glassBreakPoints", GambitPlugin.glassBreakPoints);
                plugin.saveConfig();
                //GambitPlugin.glassBreakPoints.clear();
                return true;

            }else{

                plugin.setup.add(p);
                p.sendMessage(ChatManager.prefix + ChatManager.format("You have been added to the set-up crew."));
                p.setGameMode(GameMode.CREATIVE);
                p.getInventory().clear();
                p.getInventory().addItem(new ItemStack(Material.EMERALD_BLOCK,1));
                p.getInventory().addItem(new ItemStack(Material.BLUE_BANNER,1));
                p.getInventory().addItem(new ItemStack(Material.RED_BANNER,1));
                p.getInventory().addItem(new ItemStack(Material.BLUE_GLAZED_TERRACOTTA,1));
                p.getInventory().addItem(new ItemStack(Material.RED_GLAZED_TERRACOTTA,1));
                p.getInventory().addItem(new ItemStack(Material.BLUE_STAINED_GLASS,1));
                p.getInventory().addItem(new ItemStack(Material.RED_STAINED_GLASS,1));
                p.getInventory().addItem(new ItemStack(Material.ENDER_CHEST,1));
                return true;

            }
        }
        return false;
    }
}
