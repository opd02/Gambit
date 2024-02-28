package me.opd.gambit.prizes;

import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scoreboard.Team;

public class TeamSpeedBoost {

    public static void TriggerTeamSpeedBoost(Player p){
        for (Player pl : Bukkit.getOnlinePlayers()){
            Team team = pl.getScoreboard().getPlayerTeam(pl);
            if(team.getColor() == p.getScoreboard().getPlayerTeam(p).getColor()){
                pl.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 300, 1, false));
                pl.playSound(p.getLocation(), Sound.ITEM_ARMOR_EQUIP_ELYTRA, 1, 1);
            }
        }
    }
}
