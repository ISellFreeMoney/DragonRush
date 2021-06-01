package fr.ziberty.dragonrush.scoreboards;

import fr.ziberty.dragonrush.configuration.Config;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class InGame {

    @Deprecated
    public static void updateScoreboard(String seconds, String minutes, boolean pvp, boolean pilier, SimpleScoreboard scoreboard) {
        for (Player p : Bukkit.getOnlinePlayers()) {
            StringBuilder sb = new StringBuilder();
            scoreboard.clear();
            scoreboard.blankLine();
            if (pvp) {
                scoreboard.add("PvP: §4§lActif");
            } else {
                scoreboard.add("PvP: §b " + Config.pvp + ":00");
            }
            scoreboard.add("Timer: §b" + minutes + ":" + seconds);
            if (pilier) {
                scoreboard.add("Pilier: §2✔");
            } else {
                scoreboard.add("Pilier: §4✖");
            }
            scoreboard.draw();
            scoreboard.send(p);
        }
    }
}
