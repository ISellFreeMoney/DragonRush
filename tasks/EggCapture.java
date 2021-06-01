package fr.ziberty.dragonrush.tasks;

import fr.ziberty.dragonrush.DragonRush;
import fr.ziberty.dragonrush.PluginListener;
import fr.ziberty.dragonrush.teams.Teams;
import fr.ziberty.dragonrush.scoreboards.PostGame;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.inventivetalent.bossbar.BossBarAPI;

public class EggCapture extends BukkitRunnable {

    private DragonRush main;
    private Player player;

    public EggCapture(DragonRush dragonRush, Player player) {
        this.main = dragonRush;
        this.player = player;
    }

    int timer = 60;

    @Deprecated
    @Override
    public void run() {

        if (timer == 60) {
            for (String p : Teams.teamPlayers(player)) {
                if (PluginListener.dragonKiller == Bukkit.getPlayer(p)) {
                    timer = 30;
                    break;
                }
            }
        }

        if (timer > 0) {
            for (Player p : Bukkit.getOnlinePlayers()) {
                BossBarAPI.setMessage(p, "§eTemps de capture: §6" + timer + " secondes");
            }
        }

        if (timer == 0) {
            for (Player p : Bukkit.getOnlinePlayers()) {
                BossBarAPI.removeAllBars(p);
                p.setGameMode(GameMode.SPECTATOR);
                p.sendTitle("Victoire de l'équipe " + Teams.playerInTeam(player) + " !", " ");
                LaunchingGame.task.cancel();
                PostGame.updateScoreboard(String.valueOf(GameTimer.seconds), String.valueOf(GameTimer.minutes), p, "Victoire de l'équipe " + Teams.playerInTeam(player));
                for (int i = 0; i <= 10; i++) {
                    p.playSound(p.getLocation(), Sound.FIREWORK_LAUNCH, 1, 1);
                }
                PluginListener.gameFinished = true;
                PluginListener.captureTask.cancel();
            }
        }

        if (Bukkit.getWorld("world").getBlockAt(PluginListener.pilierx, PluginListener.piliery + 4, PluginListener.pilierz).getType() == Material.AIR) {
            PluginListener.captureTask.cancel();
            Bukkit.broadcastMessage("§6» §eLa capture est été annulée !");
            for (Player p : Bukkit.getOnlinePlayers()) {
                BossBarAPI.removeAllBars(p);
                p.playSound(p.getLocation(), Sound.ANVIL_BREAK, 1, 1);
            }
        }

        timer --;
    }
}
