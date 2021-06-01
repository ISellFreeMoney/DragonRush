package fr.ziberty.dragonrush.tasks;

import fr.ziberty.dragonrush.DragonRush;
import fr.ziberty.dragonrush.PluginListener;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

public class StalkedPact extends BukkitRunnable {

    private DragonRush main;
    private Player player;
    private int seconds = 0;

    public StalkedPact(DragonRush dragonRush, Player player) {
        this.main = dragonRush;
        this.player = player;
    }

    @Override
    public void run() {
        if (PluginListener.gameFinished) {
            cancel();
        }
        if (seconds == 600) {
            seconds = 0;
            Location loc = player.getLocation();
            World world = player.getWorld();
            switch (world.getName()) {
                case "world":
                    Bukkit.broadcastMessage("§6» §c[§6Traqué§c] §4§l" + player.getName() + " §r§6se trouve dans §l§2l'overworld §r§6en §ax = " + loc.getBlockX() + " §e| §ay = " + loc.getBlockY() + " §e| §az = " + loc.getBlockZ());
                    break;
                case "world_nether":
                    Bukkit.broadcastMessage("§6» §c[§6Traqué§c] §4§l" + player.getName() + " §r§6se trouve dans §l§4le nether §r§6en §ax = " + loc.getBlockX() + " §e| §ay = " + loc.getBlockY() + " §e| §az = " + loc.getBlockZ());
                    break;
                case "world_the_end":
                    Bukkit.broadcastMessage("§6» §c[§6Traqué§c] §4§l" + player.getName() + " §r§6se trouve dans §l§3l'end §r§6en §ax = " + loc.getBlockX() + " §e| §ay = " + loc.getBlockY() + " §e| §az = " + loc.getBlockZ());
                    break;
            }
        }
        seconds ++;
    }
}
