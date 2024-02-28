package me.opd.gambit.prizes;

import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scoreboard.Team;

public class InkBomb {
    public static void TriggerInkBomb(Player p){
        for (Player pl : Bukkit.getOnlinePlayers()){
            Team team = pl.getScoreboard().getPlayerTeam(pl);
            if(!(team.getColor()==p.getScoreboard().getPlayerTeam(p).getColor())){
                pl.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, 160, 2, false));
                pl.playSound(pl.getLocation(), Sound.ENTITY_SQUID_SQUIRT, 1, 1);
            }
        }
    }
}
