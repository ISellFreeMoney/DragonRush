package fr.ziberty.dragonrush.scoreboards;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.*;

public class PreGame {

    public static void updateScoreboard(int nb) {
        ScoreboardManager sm = Bukkit.getScoreboardManager();
        Scoreboard board = sm.getNewScoreboard();
        Objective objective = board.registerNewObjective("PreGame", "");
        objective.setDisplaySlot(DisplaySlot.SIDEBAR);
        objective.setDisplayName("§c§l»§6§l Dragon Rush §c§l«");

        Score scoreNbPlayers = objective.getScore("Joueurs connectés: §b" + nb);
        scoreNbPlayers.setScore(3);
        Score blank = objective.getScore(" ");
        blank.setScore(2);
        Score dvlp = objective.getScore("§9§lDéveloppé par §6§o§lziberty");
        dvlp.setScore(1);

        for (Player onlineplayer : Bukkit.getOnlinePlayers()) {
            onlineplayer.setScoreboard(board);
        }
    }

}
