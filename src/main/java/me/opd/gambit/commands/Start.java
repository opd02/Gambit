package me.opd.gambit.commands;

import me.opd.gambit.GambitPlugin;
import me.opd.gambit.GameStates;
import me.opd.gambit.countdowns.PregameTimer;
import me.opd.gambit.managers.ChatManager;
import me.opd.gambit.managers.PlayerManager;
import org.bukkit.GameRule;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Start implements CommandExecutor {

    private GambitPlugin plugin;

    public Start(GambitPlugin gambitPlugin) {
        this.plugin = gambitPlugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        if(cmd.getName().equalsIgnoreCase("start")){
            if(!sender.isOp()){
                sender.sendMessage(ChatManager.permission);
                return true;
            }

            if(!(sender instanceof Player)){
                sender.sendMessage(ChatManager.format("&cThis command is for players only."));
                return false;
            }

            Player p = (Player) sender;

            p.getWorld().setGameRule(GameRule.KEEP_INVENTORY, true);
            PlayerManager pm = new PlayerManager(plugin);

            for(Player alivePlayer : plugin.alive.keySet()){
                pm.handle(alivePlayer);
            }
            plugin.setGameState(GameStates.PREGAME);

            PregameTimer timer = new PregameTimer(plugin, p.getWorld());
            timer.startPregameTimer();
        }

        return false;
    }
}
