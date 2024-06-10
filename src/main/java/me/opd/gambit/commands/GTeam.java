package me.opd.gambit.commands;

import me.opd.gambit.GambitPlugin;
import me.opd.gambit.GameStates;
import me.opd.gambit.managers.ChatManager;
import me.opd.gambit.managers.PlayerManager;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class GTeam implements CommandExecutor {

    private GambitPlugin plugin;

    public GTeam(GambitPlugin gambitPlugin) {
        this.plugin = gambitPlugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String args[]) {

        if (cmd.getName().equalsIgnoreCase("gteam")) {

            if (!(sender instanceof Player)) {
                sender.sendMessage(ChatManager.format("&cThis command is for players only."));
                return false;
            }

            Player p = (Player) sender;
            PlayerManager pm = new PlayerManager(plugin);

            if (plugin.getGameState() != GameStates.LOBBY) {
                p.sendMessage(ChatManager.prefix + ChatManager.format("&cIt is too late to switch teams."));
                return true;
            }

            if (args.length == 0 || !args[0].equalsIgnoreCase("join")) {
                sender.sendMessage(ChatManager.format("&cUsage: /gteam join <color>"));
                return true;
            }

            if (plugin.alive.keySet().contains(p)) {
                plugin.alive.keySet().remove(p);
            }

            if (args[1].equalsIgnoreCase("red")) {
                pm.addToTeam(p, ChatColor.RED);
                return true;
            } else if (args[1].equalsIgnoreCase("blue")) {
                pm.addToTeam(p, ChatColor.BLUE);
                return true;
            } else if (args[1].equalsIgnoreCase("spectate")) {
                plugin.spectating.add(p.getUniqueId());
                p.sendMessage(ChatManager.prefix + "You have joined the spectating group");
                return true;
            }
            sender.sendMessage(ChatManager.format("&cUsage: /gteam join <color/spectate>"));
            return false;
        }
        return false;
    }
}
