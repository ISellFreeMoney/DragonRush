package fr.ziberty.dragonrush.tasks;

import fr.ziberty.dragonrush.PluginListener;
import fr.ziberty.dragonrush.teams.Teams;
import fr.ziberty.dragonrush.configuration.Config;
import fr.ziberty.dragonrush.scoreboards.InGame;
import fr.ziberty.dragonrush.scoreboards.SimpleScoreboard;
import org.bukkit.Bukkit;
import org.bukkit.Difficulty;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

public class GameTimer extends BukkitRunnable {

    public static int seconds = 0;
    public static int minutes = 0;
    private boolean pvp = false;

    @Override
    @Deprecated
    public void run() {

        StringBuilder sb = new StringBuilder();
        SimpleScoreboard sc = new SimpleScoreboard("§c§l»§6§l Dragon Rush §c§l«");

        for (String team : Teams.allTeams) {
            switch (team) {
                case "redTeam":
                    for (String p : Teams.redTeam) {
                        Teams.actionBarPlayers(p, sb, "§c", Teams.redTeam);
                    }
                case "blueTeam":
                    for (String p : Teams.blueTeam) {
                        Teams.actionBarPlayers(p, sb, "§9", Teams.blueTeam);
                    }
                case "yellowTeam":
                    for (String p : Teams.yellowTeam) {
                        Teams.actionBarPlayers(p, sb, "§e", Teams.yellowTeam);
                    }
                case "greenTeam":
                    for (String p : Teams.greenTeam) {
                        Teams.actionBarPlayers(p, sb, "§2", Teams.greenTeam);
                    }
                case "orangeTeam":
                    for (String p : Teams.orangeTeam) {
                        Teams.actionBarPlayers(p, sb, "§6", Teams.orangeTeam);
                    }
                case "cyanTeam":
                    for (String p : Teams.lightblueTeam) {
                        Teams.actionBarPlayers(p, sb, "§b", Teams.lightblueTeam);
                    }
                case "limeTeam":
                    for (String p : Teams.limeTeam) {
                        Teams.actionBarPlayers(p, sb, "§a", Teams.limeTeam);
                    }
                case "grayTeam":
                    for (String p : Teams.grayTeam) {
                        Teams.actionBarPlayers(p, sb, "§7", Teams.grayTeam);
                    }
                case "purpleTeam":
                    for (String p : Teams.purpleTeam) {
                        Teams.actionBarPlayers(p, sb, "§5", Teams.purpleTeam);
                    }
                case "pinkTeam":
                    for (String p : Teams.pinkTeam) {
                        Teams.actionBarPlayers(p, sb, "§d", Teams.pinkTeam);
                    }
            }
        }
        if (seconds == 60) {
            minutes ++;
            seconds = 0;
        }
        if (minutes == 0 && seconds == 0) {
            Bukkit.getWorld("world").setPVP(false);
            Bukkit.getWorld("world_nether").setPVP(false);
            Bukkit.getWorld("world_the_end").setPVP(false);
            for (Player p : Bukkit.getOnlinePlayers()) {
                p.playSound(p.getLocation(), Sound.ENDERDRAGON_GROWL, 1, 1);
            }
        }
        if (minutes == 0 && seconds == 30) {
            PluginListener.introPassed = true;
            Bukkit.getServer().getWorld("world").setDifficulty(Difficulty.NORMAL);
            Bukkit.broadcastMessage("§6» §eLa période d'invincibilité est terminée !");
            for (Player p : Bukkit.getOnlinePlayers()) {
                p.playSound(p.getLocation(), Sound.GLASS, 1, 1);
            }
        }
        if (minutes == Config.pvp && seconds == 0) {
            Bukkit.broadcastMessage("§6» §eLe PvP est désormais §cactif !");
            for (Player p : Bukkit.getOnlinePlayers()) {
                p.playSound(p.getLocation(), Sound.ANVIL_LAND, 1, 1);
            }
            pvp = true;
            Bukkit.getWorld("world").setPVP(true);
            Bukkit.getWorld("world_nether").setPVP(true);
            Bukkit.getWorld("world_the_end").setPVP(true);
        }

        if (seconds < 10) {
            InGame.updateScoreboard("0" + seconds, String.valueOf(minutes), pvp, PluginListener.pilier, sc);
        }
        else {
            InGame.updateScoreboard(String.valueOf(seconds), String.valueOf(minutes), pvp, PluginListener.pilier, sc);
        }
        seconds ++;
    }
}