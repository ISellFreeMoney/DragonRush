package fr.ziberty.dragonrush.scoreboards;

import org.bukkit.Bukkit;
import org.bukkit.Statistic;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.*;

public class PostGame {

    public static void updateScoreboard(String seconds, String minutes, Player player, String team) {

        if (Integer.parseInt(seconds) < 10) {
            seconds = "0" + seconds;
        }

        ScoreboardManager sm = Bukkit.getScoreboardManager();
        Scoreboard board = sm.getNewScoreboard();
        Objective objective = board.registerNewObjective("PostGame", "");
        objective.setDisplaySlot(DisplaySlot.SIDEBAR);
        objective.setDisplayName("§c§l»§6§l Dragon Rush §c§l«");

        Score killCount = objective.getScore("Kills: §b" + player.getStatistic(Statistic.PLAYER_KILLS));
        Score timer = objective.getScore("Timer: §b" + minutes + ":" + seconds);
        Score winningTeam = objective.getScore(team);

        Score blank = objective.getScore(" ");
        blank.setScore(4);
        timer.setScore(3);
        killCount.setScore(2);
        winningTeam.setScore(1);

        player.setScoreboard(board);
    }

}
