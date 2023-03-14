package me.opd.gambit.commands;

import me.opd.gambit.managers.ChatManager;
import me.opd.gambit.token.TokenGenerator;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class TokenGive implements CommandExecutor {

    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args){
        if(cmd.getName().equalsIgnoreCase("tokengive")){
            if(!sender.isOp()){
                sender.sendMessage(ChatManager.prefix + ChatColor.RED + "This command is only for game admins.");
                return true;
            }
            Player p = (Player) sender;
            TokenGenerator.generateToken(p.getLocation(),Integer.valueOf(args[0]));
            return true;
        }
        return true;
    }
}
