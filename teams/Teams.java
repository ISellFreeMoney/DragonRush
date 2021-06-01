package fr.ziberty.dragonrush.teams;

import fr.ziberty.dragonrush.ActionBar;
import fr.ziberty.dragonrush.DragonPacts;
import fr.ziberty.dragonrush.PilarActionBar;
import fr.ziberty.dragonrush.PluginListener;
import fr.ziberty.dragonrush.tasks.PilarTimerPact;
import org.bukkit.*;
import org.bukkit.entity.Player;
import org.bukkit.util.Vector;

import java.util.ArrayList;
import java.util.List;

public class Teams {
    public static List<String> allTeams = new ArrayList<>();
    public static List<String> redTeam = new ArrayList<>();
    public static List<String> blueTeam = new ArrayList<>();
    public static List<String> yellowTeam = new ArrayList<>();
    public static List<String> greenTeam = new ArrayList<>();
    public static List<String> orangeTeam = new ArrayList<>();
    public static List<String> lightblueTeam = new ArrayList<>();
    public static List<String> limeTeam = new ArrayList<>();
    public static List<String> grayTeam = new ArrayList<>();
    public static List<String> purpleTeam = new ArrayList<>();
    public static List<String> pinkTeam = new ArrayList<>();

    @Deprecated
    public static void addToTeam(TeamType type, Player player) {
        removeFromTeam(player);
        switch (type) {
            case RED:
                redTeam.add(player.getName());
                player.setDisplayName(ChatColor.RED + player.getName() + ChatColor.WHITE);
                player.setPlayerListName(ChatColor.RED + player.getName());
                player.sendMessage("» Vous appartenez désormais à l'équipe §crouge!");
                break;
            case BLUE:
                blueTeam.add(player.getName());
                player.setDisplayName(ChatColor.BLUE + player.getName() + ChatColor.WHITE);
                player.setPlayerListName(ChatColor.BLUE + player.getName());
                player.sendMessage("» Vous appartenez désormais à l'équipe §9bleu!");
                break;
            case YELLOW:
                yellowTeam.add(player.getName());
                player.setDisplayName(ChatColor.YELLOW + player.getName() + ChatColor.WHITE);
                player.setPlayerListName(ChatColor.YELLOW + player.getName());
                player.sendMessage("» Vous appartenez désormais à l'équipe §ejaune!");
                break;
            case GREEN:
                greenTeam.add(player.getName());
                player.setDisplayName(ChatColor.DARK_GREEN + player.getName() + ChatColor.WHITE);
                player.setPlayerListName(ChatColor.DARK_GREEN + player.getName());
                player.sendMessage("» Vous appartenez désormais à l'équipe §2vert!");
                break;
            case ORANGE:
                orangeTeam.add(player.getName());
                player.setDisplayName(ChatColor.GOLD + player.getName() + ChatColor.WHITE);
                player.setPlayerListName(ChatColor.GOLD + player.getName());
                player.sendMessage("» Vous appartenez désormais à l'équipe §6orange!");
                break;
            case LIGHTBLUE:
                lightblueTeam.add(player.getName());
                player.setDisplayName(ChatColor.AQUA + player.getName() + ChatColor.WHITE);
                player.setPlayerListName(ChatColor.AQUA + player.getName());
                player.sendMessage("» Vous appartenez désormais à l'équipe §bturquoise!");
                break;
            case LIME:
                limeTeam.add(player.getName());
                player.setDisplayName(ChatColor.GREEN + player.getName() + ChatColor.WHITE);
                player.setPlayerListName(ChatColor.GREEN + player.getName());
                player.sendMessage("» Vous appartenez désormais à l'équipe §avert clair!");
                break;
            case GRAY:
                grayTeam.add(player.getName());
                player.setDisplayName(ChatColor.GRAY + player.getName() + ChatColor.WHITE);
                player.setPlayerListName(ChatColor.GRAY + player.getName());
                player.sendMessage("» Vous appartenez désormais à l'équipe §7gris!");
                break;
            case PURPLE:
                purpleTeam.add(player.getName());
                player.setDisplayName(ChatColor.DARK_PURPLE + player.getName() + ChatColor.WHITE);
                player.setPlayerListName(ChatColor.DARK_PURPLE + player.getName());
                player.sendMessage("» Vous appartenez désormais à l'équipe §5violet!");
                break;
            case PINK:
                pinkTeam.add(player.getName());
                player.setDisplayName(ChatColor.LIGHT_PURPLE + player.getName() + ChatColor.WHITE);
                player.setPlayerListName(ChatColor.LIGHT_PURPLE + player.getName());
                player.sendMessage("» Vous appartenez désormais à l'équipe §drose!");
                break;
            default:
                break;
        }
    }

    public static boolean isInTeam(Player player) {
        return redTeam.contains(player.getName())
                || blueTeam.contains(player.getName())
                || yellowTeam.contains(player.getName())
                || greenTeam.contains(player.getName())
                || orangeTeam.contains(player.getName())
                || lightblueTeam.contains(player.getName())
                || limeTeam.contains(player.getName())
                || grayTeam.contains(player.getName())
                || purpleTeam.contains(player.getName())
                || pinkTeam.contains(player.getName());
    }

    public static void clearTeams() {

        List<String> allPlayers = getAllPlayersInTeams();
        for (String teamPlayer : allPlayers) {
            Player player = Bukkit.getServer().getPlayer(teamPlayer);
            player.setDisplayName(ChatColor.WHITE + player.getName());
            player.setPlayerListName(ChatColor.WHITE + player.getName());
        }

        redTeam.clear();
        blueTeam.clear();
        yellowTeam.clear();
        greenTeam.clear();
        orangeTeam.clear();
        lightblueTeam.clear();
        limeTeam.clear();
        grayTeam.clear();
        purpleTeam.clear();
        pinkTeam.clear();
    }

    public static List<String> getRedTeam() {
        return redTeam;
    }

    public static List<String> getBlueTeam() {
        return blueTeam;
    }

    public static List<String> getYellowTeam() {
        return yellowTeam;
    }

    public static List<String> getGreenTeam() {
        return greenTeam;
    }

    public static List<String> getOrangeTeam() {
        return orangeTeam;
    }

    public static List<String> getlightblueTeam() {
        return lightblueTeam;
    }

    public static List<String> getLimeTeam() {
        return limeTeam;
    }

    public static List<String> getGrayTeam() {
        return grayTeam;
    }

    public static List<String> getPurpleTeam() {
        return purpleTeam;
    }

    public static List<String> getPinkTeam() {
        return pinkTeam;
    }

    public static List<String> getAllPlayersInTeams() {
        List<String> combinedTeams = new ArrayList<String>();

        combinedTeams.addAll(redTeam);
        combinedTeams.addAll(blueTeam);
        combinedTeams.addAll(yellowTeam);
        combinedTeams.addAll(greenTeam);
        combinedTeams.addAll(orangeTeam);
        combinedTeams.addAll(lightblueTeam);
        combinedTeams.addAll(limeTeam);
        combinedTeams.addAll(grayTeam);
        combinedTeams.addAll(purpleTeam);
        combinedTeams.addAll(pinkTeam);

        return combinedTeams;
    }

    public static void removeFromTeam(Player player) {
        player.setDisplayName(ChatColor.WHITE + player.getName());
        player.setPlayerListName(ChatColor.WHITE + player.getName());
        redTeam.remove(player.getName());
        blueTeam.remove(player.getName());
        yellowTeam.remove(player.getName());
        greenTeam.remove(player.getName());
        orangeTeam.remove(player.getName());
        lightblueTeam.remove(player.getName());
        limeTeam.remove(player.getName());
        grayTeam.remove(player.getName());
        purpleTeam.remove(player.getName());
        pinkTeam.remove(player.getName());
    }

    public static void putInSpectatorIfNoTeam(Player player) {
        List<String> allTeamsPlayers = Teams.getAllPlayersInTeams();
        for (String ps : allTeamsPlayers) {
            if (player.getName().equalsIgnoreCase(ps)) {
                return;
            }
        }
        player.setGameMode(GameMode.SPECTATOR);
        player.getInventory().clear();
    }

    public static boolean emptyTeams(List<String> team) {
        return team.size() != 0;
    }

    public static List<String> checkFullTeams() {
        if (emptyTeams(redTeam)) {allTeams.add("redTeam");}
        if (emptyTeams(blueTeam)) {allTeams.add("blueTeam");}
        if (emptyTeams(yellowTeam)) {allTeams.add("yellowTeam");}
        if (emptyTeams(greenTeam)) {allTeams.add("greenTeam");}
        if (emptyTeams(orangeTeam)) {allTeams.add("orangeTeam");}
        if (emptyTeams(lightblueTeam)) {allTeams.add("lightblueTeam");}
        if (emptyTeams(limeTeam)) {allTeams.add("limeTeam");}
        if (emptyTeams(grayTeam)) {allTeams.add("grayTeam");}
        if (emptyTeams(purpleTeam)) {allTeams.add("purpleTeam");}
        if (emptyTeams(pinkTeam)) {allTeams.add("pinkTeam");}

        return allTeams;
    }

    public static void scatterPlayers(List<String> teams) {
        int amountOfSpawns = teams.size();
        double delta = (2 * Math.PI) / amountOfSpawns;
        double angle = 0;

        for (String team : allTeams) {
            if (team.equalsIgnoreCase("redTeam") && emptyTeams(redTeam)) {
                teleportTeam(angle, redTeam);
            }
            if (team.equalsIgnoreCase("blueTeam") && emptyTeams(blueTeam)) {
                teleportTeam(angle, blueTeam);
            }
            if (team.equalsIgnoreCase("yellowTeam") && emptyTeams(yellowTeam)) {
                teleportTeam(angle, yellowTeam);
            }
            if (team.equalsIgnoreCase("greenTeam") && emptyTeams(greenTeam)) {
                teleportTeam(angle, greenTeam);
            }
            if (team.equalsIgnoreCase("orangeTeam") && emptyTeams(orangeTeam)) {
                teleportTeam(angle, orangeTeam);
            }
            if (team.equalsIgnoreCase("lightblueTeam") && emptyTeams(lightblueTeam)) {
                teleportTeam(angle, lightblueTeam);
            }
            if (team.equalsIgnoreCase("limeTeam") && emptyTeams(limeTeam)) {
                teleportTeam(angle, limeTeam);
            }
            if (team.equalsIgnoreCase("grayTeam") && emptyTeams(grayTeam)) {
                teleportTeam(angle, grayTeam);
            }
            if (team.equalsIgnoreCase("purpleTeam") && emptyTeams(purpleTeam)) {
                teleportTeam(angle, purpleTeam);
            }
            if (team.equalsIgnoreCase("pinkTeam") && emptyTeams(pinkTeam)) {
                teleportTeam(angle, pinkTeam);
            }
            angle += delta;
        }
    }

    public static void teleportTeam(double angle, List<String> team) {
        double x = 0 + 1250 * Math.sin(angle);
        double z = 0 + 1250 * Math.cos(angle);
        Location teamSpawn = new Location(Bukkit.getWorld("world"), x, Bukkit.getWorld("world").getHighestBlockYAt((int)x, (int)z) + 1, z);
        for (String p : team) {
            Player player = Bukkit.getServer().getPlayer(p);
            player.teleport(teamSpawn);
        }
    }

    public static String playerInTeam(Player player) {
        if (redTeam.contains(player.getName())) {
            player.setDisplayName(ChatColor.RED + player.getName() + ChatColor.WHITE);
            player.setPlayerListName(ChatColor.RED + player.getName());
            return "§crouge";
        }
        else if (blueTeam.contains(player.getName())) {
            player.setDisplayName(ChatColor.BLUE + player.getName() + ChatColor.WHITE);
            player.setPlayerListName(ChatColor.BLUE + player.getName());
            return "§9bleu";
        }
        else if (yellowTeam.contains(player.getName())) {
            player.setDisplayName(ChatColor.YELLOW + player.getName() + ChatColor.WHITE);
            player.setPlayerListName(ChatColor.YELLOW + player.getName());
            return "§ejaune";
        }
        else if (greenTeam.contains(player.getName())) {
            player.setDisplayName(ChatColor.DARK_GREEN + player.getName() + ChatColor.WHITE);
            player.setPlayerListName(ChatColor.DARK_GREEN + player.getName());
            return "§2vert";
        }
        else if (orangeTeam.contains(player.getName())) {
            player.setDisplayName(ChatColor.GOLD + player.getName() + ChatColor.WHITE);
            player.setPlayerListName(ChatColor.GOLD + player.getName());
            return "§6orange";
        }
        else if (lightblueTeam.contains(player.getName())) {
            player.setDisplayName(ChatColor.AQUA + player.getName() + ChatColor.WHITE);
            player.setPlayerListName(ChatColor.AQUA + player.getName());
            return "§bturquoise";
        }
        else if (limeTeam.contains(player.getName())) {
            player.setDisplayName(ChatColor.GREEN + player.getName() + ChatColor.WHITE);
            player.setPlayerListName(ChatColor.GREEN + player.getName());
            return "§avert clair";
        }
        else if (grayTeam.contains(player.getName())) {
            player.setDisplayName(ChatColor.GRAY + player.getName() + ChatColor.WHITE);
            player.setPlayerListName(ChatColor.GRAY + player.getName());
            return "§7gris";
        }
        else if (purpleTeam.contains(player.getName())) {
            player.setDisplayName(ChatColor.DARK_PURPLE + player.getName() + ChatColor.WHITE);
            player.setPlayerListName(ChatColor.DARK_PURPLE + player.getName());
            return "§5violet";
        }
        else {
            player.setDisplayName(ChatColor.LIGHT_PURPLE + player.getName() + ChatColor.WHITE);
            player.setPlayerListName(ChatColor.LIGHT_PURPLE + player.getName());
            return "§drose";
        }
    }

    public static List<String> teamPlayers(Player player) {
        if (redTeam.contains(player.getName())) {
            return redTeam;
        }
        else if (blueTeam.contains(player.getName())) {
            return blueTeam;
        }
        else if (yellowTeam.contains(player.getName())) {
            return yellowTeam;
        }
        else if (greenTeam.contains(player.getName())) {
            return greenTeam;
        }
        else if (orangeTeam.contains(player.getName())) {
            return orangeTeam;
        }
        else if (lightblueTeam.contains(player.getName())) {
            return lightblueTeam;
        }
        else if (limeTeam.contains(player.getName())) {
            return limeTeam;
        }
        else if (grayTeam.contains(player.getName())) {
            return grayTeam;
        }
        else if (purpleTeam.contains(player.getName())) {
            return purpleTeam;
        }
        else {
            return pinkTeam;
        }
    }

    public static void actionBarPlayers(String p, StringBuilder sb, String color, List<String> team) {
        sb.setLength(0);
        List<Player> allPlayers = new ArrayList<>();
        allPlayers.addAll(Bukkit.getOnlinePlayers());
        for (Player player : allPlayers) {
            World ploc = Bukkit.getPlayer(p).getWorld();
            World playerloc = player.getWorld();
            if (Bukkit.getPlayer(p) != player && team.contains(player.getName()) && ploc == playerloc) {
                Vector vector = player.getLocation().clone().subtract(Bukkit.getPlayer(p).getLocation()).toVector();
                Vector playerDirection = Bukkit.getPlayer(p).getLocation().getDirection();
                double x1 = vector.getX();
                double z1 = vector.getZ();
                double x2 = playerDirection.getX();
                double z2 = playerDirection.getZ();
                double angle = Math.atan2(x1*z2-z1*x2, x1*x2+z1*z2)*180/Math.PI;
                String arrow = "•";

                if (Math.round(Bukkit.getPlayer(p).getLocation().distance(player.getLocation())) == 0) {
                    arrow = "•";
                }
                else if (angle >= -180 && angle < -157.5) {
                    arrow = "⬇";
                }
                else if (angle >= -157.5 && angle < -112.5) {
                    arrow = "↘";
                }
                else if (angle >= -112.5 && angle < -67.5) {
                    arrow = "➡";
                }
                else if (angle >= -67.5 && angle < -22.5) {
                    arrow = "↗";
                }
                else if (angle >= -22.5 && angle < 22.5) {
                    arrow = "⬆";
                }
                else if (angle >= 22.5 && angle < 67.5) {
                    arrow = "↖";
                }
                else if (angle >= 67.5 && angle < 112.5) {
                    arrow = "⬅";
                }
                else if (angle >= 112.5 && angle < 157.5) {
                    arrow = "↙";
                }
                else {
                    arrow = "⬇";
                }
                sb.append("§l" + color + player.getName() + " ");
                sb.append("§r§a" + Math.round(Bukkit.getPlayer(p).getLocation().distance(player.getLocation())) + " ");
                sb.append(arrow + " ");
            }
            if (PluginListener.pilier && allPlayers.indexOf(player) == allPlayers.size() - 1 && DragonPacts.pactNumber == 5 && DragonPacts.isPactAccepted) {
                if (DragonPacts.pactTeam.contains(Bukkit.getPlayer(p)) || PilarTimerPact.isPillarRevealed) {
                    sb.append(PilarActionBar.PilarBar(Bukkit.getPlayer(p)));
                }
            }
            else {
                if (PluginListener.pilier && allPlayers.indexOf(player) == allPlayers.size() - 1) {
                    sb.append(PilarActionBar.PilarBar(Bukkit.getPlayer(p)));
                }
            }
            ActionBar.sendActionBar(Bukkit.getPlayer(p), sb.toString());
        }
    }

}