package me.opd.gambit.commands;

import me.opd.gambit.GambitPlugin;
import me.opd.gambit.GameStates;
import me.opd.gambit.managers.ChatManager;
import me.opd.gambit.managers.ConfigManager;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;

public class GAbort implements CommandExecutor {
    private GambitPlugin plugin;

    public GAbort(GambitPlugin gambitPlugin) {
        this.plugin = gambitPlugin;
    }
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if(command.getName().equalsIgnoreCase("gabort")){
            if(commandSender.isOp()){
                commandSender.sendMessage(ChatManager.prefix + ChatManager.format("&cGameplay has been suspended."));
                GambitPlugin.setGameState(GameStates.LOBBY);
                Player playerSender = (Player) commandSender;

                for(Entity e : playerSender.getWorld().getEntities()){
                    if(e.getType()==EntityType.WOLF||e.getType()==EntityType.ZOMBIE||e.getType()==EntityType.PILLAGER||e.getType()==EntityType.RAVAGER||e.getType()==EntityType.WITHER||e.getType()== EntityType.VINDICATOR){
                        e.remove();
                    }
                }

                ConfigManager cm = new ConfigManager(plugin);
                for(Player p : Bukkit.getServer().getOnlinePlayers()){
                    p.getInventory().clear();
                    p.getEquipment().clear();
                    p.setLevel(0);
                    p.setHealth(20);
                    p.teleport(cm.locationFromConfig("locations.lobby"));
                    p.playSound(p.getLocation(), Sound.ENTITY_VILLAGER_NO,1,(float)0.1);
                }
                return true;

            }else{
                commandSender.sendMessage(ChatColor.RED + "This command is for opperators only.");
                return false;
            }
        }
        return false;
    }
}
