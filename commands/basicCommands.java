package fr.ziberty.dragonrush.commands;

import fr.ziberty.dragonrush.PluginListener;
import fr.ziberty.dragonrush.teams.Teams;
import fr.ziberty.dragonrush.tasks.GameTimer;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class basicCommands implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        if (sender instanceof Player){
            Player player = (Player) sender;

            if (cmd.getName().equalsIgnoreCase("aide")) {
                player.sendMessage("§fAdresse de contact: zlafeestudio@gmail.com");
            }

            if(cmd.getName().equalsIgnoreCase("force")){

                if (!PluginListener.gameStarted) {
                    player.sendMessage("§cLa partie n'a pas encore démarré!");
                    return false;
                }

                if (args.length != 1 || !args[0].equals("pvp")){
                    player.sendMessage("Erreur: /force §cpvp§f");
                    return false;
                }

                if (GameTimer.minutes >= 20) {
                    player.sendMessage("§cLe PvP est déjà activé!");
                    return false;
                }

                GameTimer.minutes = 20;
                GameTimer.seconds = 0;
                PluginListener.introPassed = true;
                return true;
            }

            if (cmd.getName().equalsIgnoreCase("t")) {

                if (!PluginListener.gameStarted) {
                    player.sendMessage("§cLa partie n'a pas encore démarré!");
                    return false;
                }

                if (args.length == 0){
                    player.sendMessage("Erreur: §c/t <message>§f");
                    return false;
                }

                if (args.length >= 1){
                    StringBuilder bc = new StringBuilder();
                    for (String part : args){
                        bc.append(part + " ");
                    }
                    for (Player p : Bukkit.getOnlinePlayers()) {
                        if (Teams.playerInTeam(player).equals(Teams.playerInTeam(p))) {
                            p.sendMessage("§e[Équipe]§f " + player.getDisplayName() + " : " + bc.toString());
                        }
                    }
                }
                return true;
            }

            if (cmd.getName().equalsIgnoreCase("coords")) {

                if (!PluginListener.gameStarted) {
                    player.sendMessage("§cLa partie n'a pas encore démarré!");
                    return false;
                }

                int x = player.getLocation().getBlockX();
                int y = player.getLocation().getBlockY();
                int z = player.getLocation().getBlockZ();

                for (Player p : Bukkit.getOnlinePlayers()) {
                    if (Teams.playerInTeam(player).equals(Teams.playerInTeam(p))) {
                        p.sendMessage("§e[Équipe]§f coordonnées de " + player.getDisplayName() + " : §ax = " + x + " §f| §ay = " + y + " §f| §az = " + z);
                    }
                }
                return true;
            }

            if (cmd.getName().equalsIgnoreCase("eyes")) {
                if (!PluginListener.gameStarted) {
                    player.sendMessage("§cLa partie n'a pas encore démarré!");
                    return false;
                }
                int pearls = 0;
                int eyes = 0;
                for (String p : Teams.teamPlayers(player)) {
                    for (ItemStack it : Bukkit.getPlayer(p).getInventory().getContents()) {
                        if (it != null) {
                            if (it.getType().equals(Material.ENDER_PEARL)) {
                                pearls = pearls + it.getAmount();
                            }
                            if (it.getType().equals(Material.EYE_OF_ENDER)) {
                                eyes = eyes + it.getAmount();
                            }
                        }

                    }
                }
                player.sendMessage("§fL'équipe possède §3§l" + pearls + " ender pearls §fet §2§l" + eyes + " ender eyes§f.");
                return true;
            }

        }

        return false;
    }

}
