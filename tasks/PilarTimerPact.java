package fr.ziberty.dragonrush.tasks;

import fr.ziberty.dragonrush.DragonPacts;
import fr.ziberty.dragonrush.DragonRush;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

public class PilarTimerPact extends BukkitRunnable {


    private DragonRush main;
    private int seconds = 0;
    private final int pilierX, pilierY, pilierZ;
    public static boolean isPillarRevealed;

    public PilarTimerPact(DragonRush dragonRush, int pilierX, int pilierY, int pilierZ) {
        this.main = dragonRush;
        this.pilierX = pilierX;
        this.pilierY = pilierY;
        this.pilierZ = pilierZ;
    }

    @Override
    public void run() {
        if (seconds == 30) {
            isPillarRevealed = true;
            for (Player p : Bukkit.getOnlinePlayers()) {
                if (!DragonPacts.pactTeam.contains(p)) {
                    p.sendMessage("§6» §eLe pilier se trouve en §ax = " + pilierX + " §e| §ay = " + pilierY + " §e| §az = " + pilierZ);
                }
            }
            cancel();
        }

        seconds ++;
    }
}
