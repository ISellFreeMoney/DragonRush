package fr.ziberty.dragonrush.tasks;

import fr.ziberty.dragonrush.configuration.Config;
import fr.ziberty.dragonrush.DragonRush;
import fr.ziberty.dragonrush.PluginListener;
import fr.ziberty.dragonrush.teams.Teams;
import org.bukkit.*;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.List;

public class LaunchingGame extends BukkitRunnable {

    private DragonRush main;

    public LaunchingGame(DragonRush dragonRush) {
        this.main = dragonRush;
    }

    private int timer = 10;
    public static GameTimer task;

    @Override
    @Deprecated
    public void run() {
        if (timer == 0) {
            for (Player player : Bukkit.getOnlinePlayers()) {
                player.setGameMode(GameMode.SURVIVAL);
                player.setExp(0);
                ItemStack steaks = new ItemStack(Material.COOKED_BEEF, 64);
                player.getInventory().setItem(8, steaks);
                Teams.putInSpectatorIfNoTeam(player);
                player.sendTitle(ChatColor.RED + "GO", " ");
                if (Config.cateyes) {
                    player.addPotionEffect(new PotionEffect(PotionEffectType.NIGHT_VISION, Integer.MAX_VALUE, 0, true, false));
                }
            }
            for (int x = -30; x <= 30; x++) {
                for (int z = -30; z <= 30; z++) {
                    for (int y = 145; y <= 254; y++) {
                        Bukkit.getWorld("world").getBlockAt(x, y, z).setType(Material.AIR);
                    }
                }
            }
            Bukkit.getServer().getWorld("world").setTime(0);
            Bukkit.getServer().getWorld("world").setDifficulty(Difficulty.PEACEFUL);
            PluginListener.gameStarted = true;
            cancel();
            List<String> teams = Teams.checkFullTeams();
            Teams.scatterPlayers(teams);
            task = new GameTimer();
            task.runTaskTimer(main, 0, 20);
        }
        else {
            String temps = String.valueOf(timer);
            for (Player player : Bukkit.getOnlinePlayers()) {
                player.sendTitle(ChatColor.YELLOW + temps, " ");
                player.playSound(player.getLocation(), Sound.ORB_PICKUP, 1, (float) 0.1);
            }
        }
        timer --;
    }
}
