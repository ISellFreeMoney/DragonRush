package fr.ziberty.dragonrush;

import com.google.common.collect.Lists;
import fr.ziberty.dragonrush.scoreboards.PreGame;
import fr.ziberty.dragonrush.tasks.EggCapture;
import fr.ziberty.dragonrush.tasks.GameTimer;
import fr.ziberty.dragonrush.tasks.LaunchingGame;
import fr.ziberty.dragonrush.tasks.PilarTimerPact;
import fr.ziberty.dragonrush.teams.TeamType;
import fr.ziberty.dragonrush.teams.Teams;
import org.bukkit.*;
import org.bukkit.block.Block;
import org.bukkit.block.CreatureSpawner;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.entity.FoodLevelChangeEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.*;
import org.bukkit.event.weather.WeatherChangeEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.*;

public class PluginListener implements Listener {

    private DragonRush main;
    public static Inventory confInv;

    public PluginListener(DragonRush dragonRush) {
        this.main = dragonRush;
        confInv = setConfigMenu();
    }

    public static boolean gameStarted, introPassed, gameFinished, firstTeamNether, firstTeamEnd, pilier, firsthit = true, secondhit = true, thirdhit = true;
    public static String eggTeam, eggTeam2, endTeam, endTeam2;
    public static Player trackedPlayer;
    public static List<Player> hasSleptPlayers = new ArrayList<>();
    public static int pilierx, piliery, pilierz, strongholdX, strongholdY, strongholdZ;
    public static EggCapture captureTask;
    public static Player dragonKiller;


    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();

        switch(player.getName()) {
            case "ziberty":
            case "MrMinou":
                event.setJoinMessage("§8(§a+§8)§6 " + player.getName());
                break;
            case "Kiruna13":
                event.setJoinMessage("§8(§a+§8)§0 " + player.getName());
                break;
            case "psaroluv":
                event.setJoinMessage("§8(§a+§8) §3α§6 " + player.getName());
                break;
            case "AAAero":
            case "Tayra_":
            case "Belqe":
            case "JeSuisRonflex":
                event.setJoinMessage("§8(§a+§8) §3α§7 " + player.getName());
                break;
            default:
                event.setJoinMessage("§8(§a+§8)§7 " + player.getName());
                break;
        }

        if (!gameStarted) {
            player.setStatistic(Statistic.DEATHS, 0);
            player.setStatistic(Statistic.PLAYER_KILLS, 0);
            int nb = getOnlinePlayers().size() + 1;
            PreGame.updateScoreboard(nb);

            player.getInventory().clear();
            player.setGameMode(GameMode.ADVENTURE);
            player.setMaxHealth(20);
            player.setHealth(20);
            player.setFoodLevel(20);
            player.setLevel(0);
            player.setExp(0);
            for (PotionEffect effect : player.getActivePotionEffects()) {
                player.removePotionEffect(effect.getType());
            }

            ItemStack compassMenu = new ItemStack(Material.COMPASS);
            ItemStack teamMenu = new ItemStack(Material.BANNER);

            ItemMeta compassMenuMeta = compassMenu.getItemMeta();
            compassMenuMeta.setDisplayName("§bConfiguration");
            compassMenu.setItemMeta(compassMenuMeta);

            ItemMeta teamMenuMeta = teamMenu.getItemMeta();
            teamMenuMeta.setDisplayName("§bChoix des équipes");
            teamMenu.setItemMeta(teamMenuMeta);

            if (player.isOp()) {
                player.getInventory().setItem(0, compassMenu);
            }
            player.getInventory().setItem(4, teamMenu);

            Location spawn = new Location(Bukkit.getWorld("world"), 0, 150, 0, 0f, 0f);
            player.teleport(spawn);
        }
        else {
           if (Teams.isInTeam(player)) {
                Teams.playerInTeam(player);
           }
           else {
                player.setGameMode(GameMode.SPECTATOR);
           }
        }
    }

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent event) {
        Player player = event.getPlayer();

        switch(player.getName()) {
            case "ziberty":
            case "MrMinou":
                event.setQuitMessage("§8(§c-§8)§6 " + player.getName());
                break;
            case "Kiruna13":
                event.setQuitMessage("§8(§c-§8)§0 " + player.getName());
                break;
            case "psaroluv":
                event.setQuitMessage("§8(§c-§8) §3α§6 " + player.getName());
                break;
            case "AAAero":
            case "Tayra_":
            case "Belqe":
            case "JeSuisRonflex":
                event.setQuitMessage("§8(§c-§8) §3α§7 " + player.getName());
                break;
            default:
                event.setQuitMessage("§8(§c-§8)§7 " + player.getName());
                break;
        }

        if (!gameStarted) {
            int nb = getOnlinePlayers().size() - 1;
            PreGame.updateScoreboard(nb);
        }
    }

    @EventHandler
    public void onFoodLevelChange(FoodLevelChangeEvent event) {
        if (!introPassed) {
            event.setCancelled(true);
        }
    }

    @EventHandler
    public void onLifeChange(EntityDamageEvent event) {
        Entity entity = event.getEntity();
        if (!introPassed) {
            if (entity instanceof Player) {
                event.setCancelled(true);
            }
        }
        if (entity instanceof EnderDragon) {
            double hp = ((EnderDragon) entity).getHealth();
            if (hp <= 180 && hp > 100 && firsthit) {
                firsthit = false;
                Bukkit.broadcastMessage("§6» §eLe dragon a perdu §c§l10% §r§ede sa vie !");
                for (Player p : Bukkit.getOnlinePlayers()) {
                    p.playSound(p.getLocation(), Sound.IRONGOLEM_HIT, 1, 1);
                }
            }
            if (hp <= 100 && hp > 50 && secondhit) {
                secondhit = false;
                Bukkit.broadcastMessage("§6» §eLe dragon a perdu §c§l50% §r§ede sa vie !");
                for (Player p : Bukkit.getOnlinePlayers()) {
                    p.playSound(p.getLocation(), Sound.IRONGOLEM_HIT, 1, 1);
                }
            }
            if (hp <= 50 && thirdhit) {
                thirdhit = false;
                Bukkit.broadcastMessage("§6» §eLe dragon a perdu §c§l75% §r§ede sa vie !");
                for (Player p : Bukkit.getOnlinePlayers()) {
                    p.playSound(p.getLocation(), Sound.IRONGOLEM_HIT, 1, 1);
                }
            }
        }
    }

    @EventHandler
    public void onInteract(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        Action action = event.getAction();
        ItemStack it = event.getItem();

        if (it == null) return;

        //Menu des équipes

        if (it.getType() == Material.BANNER && it.hasItemMeta() && it.getItemMeta().hasDisplayName() && it.getItemMeta().getDisplayName().equalsIgnoreCase("§bChoix des équipes")) {
            if (action == Action.RIGHT_CLICK_AIR || action == Action.RIGHT_CLICK_BLOCK) {
                Inventory teamInv = Bukkit.createInventory(null, 54, "§8Menu des équipes");

                for (int i = 0; i <= 8; i++) {
                    teamInv.setItem(i, getColoredItem(Material.STAINED_GLASS_PANE, 1, 15, " ", " "));
                }
                for (int i = 45; i <= 53; i++) {
                    teamInv.setItem(i, getColoredItem(Material.STAINED_GLASS_PANE, 1, 15, " ", " "));
                }
                for (int i = 0; i <= 45; i += 9) {
                    teamInv.setItem(i, getColoredItem(Material.STAINED_GLASS_PANE, 1, 15, " ", " "));
                }
                for (int i = 8; i <= 53; i += 9) {
                    teamInv.setItem(i, getColoredItem(Material.STAINED_GLASS_PANE, 1, 15, " ", " "));
                }

                teamInv.setItem(20, getColoredItem(Material.BANNER, 1, 1, "§cÉquipe rouge", " "));
                teamInv.setItem(21, getColoredItem(Material.BANNER, 1, 4, "§9Équipe bleu", " "));
                teamInv.setItem(22, getColoredItem(Material.BANNER, 1, 11, "§eÉquipe jaune", " "));
                teamInv.setItem(23, getColoredItem(Material.BANNER, 1, 2, "§2Équipe vert", " "));
                teamInv.setItem(24, getColoredItem(Material.BANNER, 1, 14, "§6Équipe orange", " "));
                teamInv.setItem(29, getColoredItem(Material.BANNER, 1, 12, "§bÉquipe turquoise", " "));
                teamInv.setItem(30, getColoredItem(Material.BANNER, 1, 10, "§aÉquipe vert clair", " "));
                teamInv.setItem(31, getColoredItem(Material.BANNER, 1, 7, "§7Équipe gris", " "));
                teamInv.setItem(32, getColoredItem(Material.BANNER, 1, 13, "§5Équipe violet", " "));
                teamInv.setItem(33, getColoredItem(Material.BANNER, 1, 9, "§dÉquipe rose", " "));
                teamInv.setItem(49, getItem(Material.BARRIER, "Quitter son équipe"));

                player.openInventory(teamInv);
            }
        }

        //Menu de configuration

        if (it.getType() == Material.COMPASS && it.hasItemMeta() && it.getItemMeta().hasDisplayName() && it.getItemMeta().getDisplayName().equalsIgnoreCase("§bConfiguration")) {
            if (action == Action.RIGHT_CLICK_AIR || action == Action.RIGHT_CLICK_BLOCK) {
                player.openInventory(confInv);
            }
        }

        // Tracking Compass

        if (gameStarted) {
            if (it.getType() == Material.COMPASS) {
                if (action == Action.RIGHT_CLICK_AIR || action == Action.RIGHT_CLICK_BLOCK) {
                    try {
                        player.setCompassTarget(trackedPlayer.getLocation());
                        player.sendMessage("Position actualisée");
                    } catch (Exception e) {
                        player.sendMessage("§cL'oeuf n'a pas encore été récupéré !");
                    }
                }
            }
            /*if (it.getType() == Material.EYE_OF_ENDER && firstTeamEnd && player.getWorld().getName().equalsIgnoreCase("world")) {
                if (action == Action.RIGHT_CLICK_BLOCK || action == Action.RIGHT_CLICK_AIR) {
                    Location loc = new Location(Bukkit.getWorld("world"), strongholdX, strongholdY, strongholdZ);
                    int distance = (int) Math.round(player.getLocation().distance(loc));
                    if (distance >= 1000) {
                        player.sendMessage("§b§oVous vous situez à plus de 1000 blocks du stronghold");
                    }
                    else if (distance > 500) {
                        player.sendMessage("§b§oVous vous situez entre 500 et 1000 blocks du stronghold");
                    }
                    else {
                        player.sendMessage("§b§oVous vous situez à moins de 500 blocks du stronghold");
                    }
                }
            }*/
        }
    }

    private Inventory setConfigMenu() {
        Inventory confInv = Bukkit.createInventory(null, 54, "§8Menu de configuration");

        for (int i = 0; i <= 8; i++) {
            confInv.setItem(i, getColoredItem(Material.STAINED_GLASS_PANE, 1, 15, " ", " "));
        }
        for (int i = 44; i <= 53; i++) {
            confInv.setItem(i, getColoredItem(Material.STAINED_GLASS_PANE, 1, 15, " ", " "));
        }
        for (int i = 0; i <= 44; i += 9) {
            confInv.setItem(i, getColoredItem(Material.STAINED_GLASS_PANE, 1, 15, " ", " "));
        }
        for (int i = 8; i <= 44; i += 9) {
            confInv.setItem(i, getColoredItem(Material.STAINED_GLASS_PANE, 1, 15, " ", " "));
        }

        confInv.setItem(40, getColoredItem(Material.STAINED_CLAY, 1, 5, "§bCommencer la partie", ""));
        confInv.setItem(20, getItem(Material.BOOK_AND_QUILL, "§bScénarios"));
        confInv.setItem(22, getItem(Material.ARROW, "§bLoot des mobs"));
        confInv.setItem(24, getItem(Material.WATCH, "§bTimers"));

        return confInv;
    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        Inventory inv = event.getInventory();
        Player player = (Player) event.getWhoClicked();
        ItemStack current = event.getCurrentItem();

        if (current == null) return;

        ItemMeta mt = current.getItemMeta();

        if (mt == null) return;

        //Interaction menu équipes

        if (inv.getName().equalsIgnoreCase("§8Menu des équipes")) {
            event.setCancelled(true);

            switch (mt.getDisplayName()) {
                case "§cÉquipe rouge":
                    Teams.addToTeam(TeamType.RED, player);
                    player.closeInventory();
                    break;
                case "§9Équipe bleu":
                    Teams.addToTeam(TeamType.BLUE, player);
                    player.closeInventory();
                    break;
                case "§eÉquipe jaune":
                    Teams.addToTeam(TeamType.YELLOW, player);
                    player.closeInventory();
                    break;
                case "§2Équipe vert":
                    Teams.addToTeam(TeamType.GREEN, player);
                    player.closeInventory();
                    break;
                case "§6Équipe orange":
                    Teams.addToTeam(TeamType.ORANGE, player);
                    player.closeInventory();
                    break;
                case "§bÉquipe turquoise":
                    Teams.addToTeam(TeamType.LIGHTBLUE, player);
                    player.closeInventory();
                    break;
                case "§aÉquipe vert clair":
                    Teams.addToTeam(TeamType.LIME, player);
                    player.closeInventory();
                    break;
                case "§7Équipe gris":
                    Teams.addToTeam(TeamType.GRAY, player);
                    player.closeInventory();
                    break;
                case "§5Équipe violet":
                    Teams.addToTeam(TeamType.PURPLE, player);
                    player.closeInventory();
                    break;
                case "§dÉquipe rose":
                    Teams.addToTeam(TeamType.PINK, player);
                    player.closeInventory();
                    break;
                case "Quitter son équipe":
                    if (Teams.isInTeam(player)) {
                        player.sendMessage("» Vous avez quitté votre équipe!");
                    }
                    else {
                        player.sendMessage("» §cVous n'appartenez à aucune équipe!");
                    }
                    Teams.removeFromTeam(player);
                    player.closeInventory();
                    break;
                default: break;
            }
        }

        //Interaction menu config

        if (inv.getName().equalsIgnoreCase("§8Menu de configuration")) {
            event.setCancelled(true);
            switch (current.getType()) {
                case STAINED_CLAY:
                    for (Player p : getOnlinePlayers()) {
                        p.getInventory().clear();
                    }
                    LaunchingGame task = new LaunchingGame(main);
                    task.runTaskTimer(main, 0, 20);
                    player.closeInventory();
                    break;
                default: break;
            }
        }

    }

    @EventHandler
    public void onPickingDragonEgg(PlayerPickupItemEvent event) {
        Player player = event.getPlayer();
        eggTeam = Teams.playerInTeam(player);

        int count = 0;
        for (ItemStack item : player.getInventory().getContents()) {
            if (item != null && item.getType().equals(Material.DRAGON_EGG)) {
                count += item.getAmount();
            }
            if (count > 1) {
                player.getInventory().removeItem(new ItemStack(Material.DRAGON_EGG, 1));
            }
        }

        if (!gameStarted || eggTeam.equalsIgnoreCase(eggTeam2)) {
            return;
        }

        if (event.getItem().getItemStack().getType().equals(Material.DRAGON_EGG)) {
            trackedPlayer = player;
            eggTeam2 = eggTeam;
            Bukkit.broadcastMessage("§6» §eL'oeuf a été récupéré par l'équipe§c " + eggTeam + " !");
            for (Player p : Bukkit.getOnlinePlayers()) {
                p.playSound(p.getLocation(), Sound.GHAST_SCREAM, 1, 1);
            }
        }
    }

    @Deprecated
    @EventHandler
    public void onPlacingDragonEgg(BlockPlaceEvent event) {
        Block block = event.getBlock();
        Player player = event.getPlayer();
        Location loc = block.getLocation();

        if (block.getType() == Material.DRAGON_EGG) {
            if (loc.getBlockX() == pilierx && loc.getBlockY() == piliery + 4 && loc.getBlockZ() == pilierz) {
                Bukkit.broadcastMessage("§6» §eL'équipe " + Teams.playerInTeam(player) + "§e capture le pilier !");
                for (Player p : Bukkit.getOnlinePlayers()) {
                    p.playSound(p.getLocation(), Sound.WITHER_SPAWN, 1, 2);
                }
                captureTask = new EggCapture(main, player);
                captureTask.runTaskTimer(main, 0, 20);
            }
        }
    }

    @Deprecated
    @EventHandler
    public void onPlayerDeath(PlayerDeathEvent event) {
        Player player = event.getEntity().getPlayer();

        if (player.getStatistic(Statistic.DEATHS) <= 1) {
            ItemStack it = new ItemStack(Material.DRAGON_EGG);
            if (player.getInventory().contains(Material.DRAGON_EGG)) {
                player.getInventory().remove(Material.DRAGON_EGG);
                Bukkit.getWorld("world").dropItemNaturally(player.getLocation(), it);
            }
            if (DragonPacts.pactNumber == 2 && DragonPacts.pactTeam.contains(player)) {
                player.setGameMode(GameMode.SPECTATOR);
                Teams.removeFromTeam(player);
                return;
            }
            event.setKeepInventory(true);
            player.setGameMode(GameMode.SURVIVAL);
        }
        else {
            player.setGameMode(GameMode.SPECTATOR);
            Teams.removeFromTeam(player);
        }

        /*if (!gameFinished) {
            for (Player p : Bukkit.getOnlinePlayers()) {
                if (p.getGameMode() != GameMode.SPECTATOR) {
                    break;
                }
                LaunchingGame.task.cancel();
                p.sendTitle("Aucune équipe gagnante", "Tous les joueurs sont morts");
                PostGame.updateScoreboard(String.valueOf(GameTimer.seconds), String.valueOf(GameTimer.minutes), p, "Aucune équipe gagnante");
                break;
            }
        }*/
    }

    @EventHandler
    public void onPlayerRespawn(PlayerRespawnEvent event) {
        Player player = event.getPlayer();

        if (player.getStatistic(Statistic.DEATHS) <= 2) {
            Random rand = new Random();
            if (DragonPacts.pactNumber == 2 && DragonPacts.pactTeam.contains(player)) {
                return;
            }
            if (player.getStatistic(Statistic.DEATHS) == 2) {
                player.sendMessage("§6» §eVous êtes mort deux fois. Par conséquent, vous réapparaissez avec §c7 coeurs.");
                player.setMaxHealth(14);
            }
            if (!hasSleptPlayers.contains(player)) {
                int x = rand.nextInt(1000 - (-1000) + 1) + (-1000);
                int z = rand.nextInt(1000 - (-1000) + 1) + (-1000);
                Location respawnLoc = new Location(Bukkit.getWorld("world"), x, Bukkit.getWorld("world").getHighestBlockYAt(x, z),z);
                event.setRespawnLocation(respawnLoc);
            }
            else {
                int x = player.getLocation().getBlockX();
                int z = player.getLocation().getBlockZ();
                Location respawnLoc = new Location(Bukkit.getWorld("world"), x, Bukkit.getWorld("world").getHighestBlockYAt(x, z),z);
                event.setRespawnLocation(respawnLoc);
            }
            return;
        }
        int x = player.getLocation().getBlockX();
        int z = player.getLocation().getBlockZ();
        Location respawnLoc = new Location(Bukkit.getWorld("world"), x, Bukkit.getWorld("world").getHighestBlockYAt(x, z),z);
        event.setRespawnLocation(respawnLoc);
    }

    @EventHandler
    public void onChangingDimension(PlayerChangedWorldEvent event) {
        Player player = event.getPlayer();
        World world = event.getFrom();
        endTeam = Teams.playerInTeam(player);
        if (world.getName().equals("world_the_end")) {
            int x = player.getLocation().getBlockX();
            int z = player.getLocation().getBlockZ();
            Location respawnLoc = new Location(Bukkit.getWorld("world"), x, Bukkit.getWorld("world").getHighestBlockYAt(x, z),z);
            player.teleport(respawnLoc);
        }
        if (!firstTeamNether && player.getWorld().getName().equals("world_nether")) {
            firstTeamNether = true;
            String seconde = String.valueOf(GameTimer.seconds);
            if (GameTimer.seconds < 10) {
                seconde = "0" + String.valueOf(GameTimer.seconds);
            }
            Bukkit.broadcastMessage("§6» §eUne équipe vient d'entrer dans le nether ! (§b" + GameTimer.minutes + ":" + seconde + "§e)");
            for (Player p : Bukkit.getOnlinePlayers()) {
                p.playSound(p.getLocation(), Sound.AMBIENCE_THUNDER, 1 , 1);
            }
        }
        if (player.getWorld().getName().equals("world_the_end") && !endTeam.equals(endTeam2)) {
            endTeam2 = Teams.playerInTeam(player);
            Bukkit.broadcastMessage("§6» §eUne équipe vient d'entrer dans l'end !");
        }
    }

    @EventHandler
    public void goingInEndPortal(PlayerPortalEvent event) {
        Location loc = event.getFrom();
        if (!firstTeamEnd && event.getTo().getWorld().getEnvironment() == World.Environment.THE_END) {
            firstTeamEnd = true;
            strongholdX = loc.getBlockX();
            strongholdY = loc.getBlockY();
            strongholdZ = loc.getBlockZ();
            double chance = Math.random();
            if (chance < 0.5) {
                Bukkit.broadcastMessage("§6» §eVoici une des deux coordonnées du Stronghold: §a" + "X = " + strongholdX);
            }
            else {
                Bukkit.broadcastMessage("§6» §eVoici une des deux coordonnées du Stronghold: §a" + "Z = " + strongholdZ);
            }
        }
    }

    @EventHandler
    public void onItemDestroy(EntityDamageEvent event) {
        Entity entity = event.getEntity();
        if (entity instanceof Item) {
            Material item = ((Item) entity).getItemStack().getType();
            if (item.equals(Material.DRAGON_EGG)) {
                event.setCancelled(true);
            }
        }
    }

    @EventHandler
    public void onPlayerSleep(PlayerBedLeaveEvent event) {
        Player player = event.getPlayer();
        hasSleptPlayers.add(player);
    }

    @EventHandler
    public void onWheatherChange(WeatherChangeEvent event) {
        event.setCancelled(true);
    }

    public ItemStack getItem(Material material, String name) {
        ItemStack it = new ItemStack(material);
        ItemMeta itM = it.getItemMeta();
        if (name != null) itM.setDisplayName(name);
        it.setItemMeta(itM);
        return it;
    }

    @EventHandler
    public void onMobDeath(EntityDeathEvent event) {
        LivingEntity entity = event.getEntity();
        double chance = Math.random();
        if (
                entity instanceof Blaze ||
                entity instanceof Creeper ||
                entity instanceof Guardian ||
                entity instanceof Ghast ||
                entity instanceof Silverfish ||
                entity instanceof Skeleton ||
                entity instanceof Spider ||
                entity instanceof Witch ||
                entity instanceof Zombie ||
                entity instanceof Bat ||
                entity instanceof Chicken ||
                entity instanceof Rabbit ||
                entity instanceof Cow ||
                entity instanceof Horse ||
                entity instanceof Sheep ||
                entity instanceof Pig ||
                entity instanceof Squid ||
                entity instanceof Wolf ||
                entity instanceof Ocelot ||
                entity instanceof Villager
        ) {
            if (chance < 0.05) {
                entity.getLocation().getWorld().dropItemNaturally(entity.getLocation(), new ItemStack(Material.ENDER_PEARL, 1));
            }
        }
        if (entity instanceof Enderman) {
            event.getDrops().clear();
            if (chance < 0.7) {
                entity.getLocation().getWorld().dropItemNaturally(entity.getLocation(), new ItemStack(Material.ENDER_PEARL, 1));
            }
            else if (chance < 0.95) {
                entity.getLocation().getWorld().dropItemNaturally(entity.getLocation(), new ItemStack(Material.ENDER_PEARL, 2));
            }
            else {
                entity.getLocation().getWorld().dropItemNaturally(entity.getLocation(), new ItemStack(Material.ENDER_PEARL, 3));
            }
        }
        if (entity instanceof EnderDragon) {
            pilier = true;
            Random rand = new Random();
            pilierx = rand.nextInt(1000 - (-1000) + 1) + (-1000);
            pilierz = rand.nextInt(1000 - (-1000) + 1) + (-1000);
            piliery = Bukkit.getWorld("world").getHighestBlockYAt(pilierx, pilierz);
            int maxy = piliery + 20;
            int maxpilier = piliery + 3;
            int maxx = pilierx + 15;
            int maxz = pilierz + 15;
            int minx = pilierx - 15;
            int minz = pilierz - 15;

            for (int y2 = piliery; y2 <= maxy; y2++) {
                for (int x2 = minx; x2 <= maxx; x2 ++) {
                    for (int z2 = minz; z2 <= maxz; z2 ++) {
                        Bukkit.getWorld("world").getBlockAt(x2, y2, z2).setType(Material.BARRIER);
                        Bukkit.getWorld("world").getBlockAt(x2, y2, z2).setType(Material.AIR);
                    }
                }
            }
            for (int y2 = piliery; y2 <= maxpilier; y2++) {
                Bukkit.getWorld("world").getBlockAt(pilierx, y2, pilierz).setType(Material.BEDROCK);
            }

            if (DragonPacts.pactNumber == 5 && DragonPacts.isPactAccepted) {
                PilarTimerPact task = new PilarTimerPact(main, pilierx, piliery, pilierz);
                task.runTaskTimer(main, 0, 20);

                for (Player p : Bukkit.getOnlinePlayers()) {
                    if (DragonPacts.pactTeam.contains(p)) {
                        p.addPotionEffect(new PotionEffect(PotionEffectType.WEAKNESS, Integer.MAX_VALUE, 1, true, false));
                        p.sendMessage("§6» §eLe pilier pour déposer l'oeuf §4vient d'apparaître !");
                        p.sendMessage("§6» §eIl se trouve en §ax = " + pilierx + " §e| §ay = " + piliery + " §e| §az = " + pilierz);
                        p.sendMessage("§6» §e(Vous connaissez l'emplacement du pilier 30 secondes avant tout le monde parce que votre équipe a conclu le pacte du Dragon)");
                    }
                    else {
                        p.sendMessage("§6» §eLes coordonnées du pilier vous seront dévoilées dans 30 secondes car une équipe a conclu le pacte du Dragon !");
                    }
                }
            }
            else {
                Bukkit.broadcastMessage("§6» §eLe pilier pour déposer l'oeuf §4vient d'apparaître !");
                Bukkit.broadcastMessage("§6» §eIl se trouve en §ax = " + pilierx + " §e| §ay = " + piliery + " §e| §az = " + pilierz);
            }
            dragonKiller = event.getEntity().getKiller();
            for (String p : Teams.teamPlayers(dragonKiller)) {
                Bukkit.getPlayer(p).addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, Integer.MAX_VALUE, 0, true, false));
            }
        }
    }

    @EventHandler
    public void onBlockBreak(BlockBreakEvent event) {
        Block block = event.getBlock();
        if (block.getType().equals(Material.GRAVEL)) {
            event.setCancelled(true);
            block.setType(Material.AIR);
            block.getLocation().getWorld().dropItemNaturally(block.getLocation(), new ItemStack(Material.FLINT, 1));
        }

        if (block.getType().equals(Material.MOB_SPAWNER)) {
            CreatureSpawner spawner = (CreatureSpawner) block.getState();
            EntityType spawnerType = spawner.getSpawnedType();
            if (spawnerType.equals(EntityType.BLAZE)) {
                event.setCancelled(true);
            }
        }
    }

    public ItemStack getColoredItem(Material material, int amount, int color,  String name, String lore) {
        ItemStack it = new ItemStack(material, amount, (short) color);
        ItemMeta itM = it.getItemMeta();
        if (name != null) itM.setDisplayName(name);
        if (lore != null) itM.setLore(Arrays.asList(lore));
        it.setItemMeta(itM);
        return it;
    }

    public static List<Player> getOnlinePlayers() {
        List<Player> list = Lists.newArrayList();
        for (World world : Bukkit.getWorlds()) {
            list.addAll(world.getPlayers());
        }
        return Collections.unmodifiableList(list);
    }

}
