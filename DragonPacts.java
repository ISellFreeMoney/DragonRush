package fr.ziberty.dragonrush;

import com.mojang.authlib.GameProfile;
import com.mojang.authlib.properties.Property;
import fr.ziberty.dragonrush.tasks.StalkedPact;
import fr.ziberty.dragonrush.teams.Teams;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;

import java.lang.reflect.Field;
import java.util.*;

public class DragonPacts implements Listener {

    static public int pactNumber;
    private Inventory pactInventory;
    public static boolean hasPactPassed, isPactAccepted, isPactRefused;
    static public List<Player> pactTeam = new ArrayList<>();
    private DragonRush main;

    public DragonPacts(DragonRush dragonRush) {
        pactNumber = setPactNumber();
        this.pactInventory = setPactInventory();
        this.main = dragonRush;
    }

    private int setPactNumber() {
        Random random = new Random();
        return random.nextInt(5 - 1 + 1) + 1;
    }

    @EventHandler
    private void onPactInteract(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        Action action = event.getAction();
        ItemStack item = event.getItem();

        if (item == null) return;

        if (item.getType() == Material.BOOK && item.hasItemMeta() && item.getItemMeta().hasDisplayName() && item.getItemMeta().getDisplayName().equalsIgnoreCase("§5Pacte du dragon")) {
            ItemStack dragonPact = item;
            if (action == Action.RIGHT_CLICK_AIR || action == Action.RIGHT_CLICK_BLOCK) {
                if (!hasPactPassed) {
                    String team = Teams.playerInTeam(player);
                    for (Player p : Bukkit.getOnlinePlayers()) {
                        if (p.getInventory().contains(dragonPact)) {
                            p.getInventory().remove(dragonPact);
                        }
                        p.playSound(p.getLocation(), Sound.ZOMBIE_REMEDY, 1, 1);
                    }
                    Bukkit.broadcastMessage("§6» §eL'équipe " + team + " §eest en train de passer le pacte du §d§lDragon §r§e! Par conséquent, tous les pactes ont été supprimés, et plus personne ne peut les utiliser.");
                    player.openInventory(pactInventory);
                    hasPactPassed = true;
                }
                else {
                    player.sendMessage("§cLe pacte a déjà été passé par une équipe !");
                    player.getInventory().remove(dragonPact);
                }


            }
        }
    }

    @EventHandler
    private void onInventoryClose(InventoryCloseEvent event) {
        Player player = (Player) event.getPlayer();
        Inventory inventory = event.getInventory();
        if (inventory.getName().equalsIgnoreCase("§5§lPacte du dragon") && !isPactAccepted) {
            pactRefused(player);
        }
    }

    private void pactRefused(Player player) {
        if (!isPactRefused) {
            player.sendMessage("§6» §eVous avez §4refusé§e le pacte !");
            player.playSound(player.getLocation(), Sound.GHAST_SCREAM2, 1, 1);
            isPactRefused = true;
        }
    }

    private void pactAccepted(Player player) {
        isPactAccepted = true;
        player.sendMessage("§6» §eVous avez §2accepté§e le pacte !");
        player.playSound(player.getLocation(), Sound.LEVEL_UP, 1, 0.1f);
    }

    @EventHandler
    private void onInventoryClick(InventoryClickEvent event) {
        Inventory inventory = event.getInventory();
        Player player = (Player) event.getWhoClicked();
        ItemStack current = event.getCurrentItem();

        if (current == null) return;

        if (inventory.getName().equalsIgnoreCase("§5§lPacte du dragon")) {
            event.setCancelled(true);
            switch (current.getItemMeta().getDisplayName()) {
                case "§2Accepter le pacte":
                    pactAccepted(player);
                    pactTeam = setPactTeam(player);
                    setPactBuffs();
                    player.closeInventory();
                    break;
                case "§4Refuser le pacte":
                    pactRefused(player);
                    player.closeInventory();
                    break;
            }
        }
    }

    @EventHandler
    private void damageDealt(EntityDamageByEntityEvent event) {
        Entity dmgDealer = event.getDamager();
        Entity dmgReceiver = event.getEntity();
        if (pactNumber == 1 && isPactAccepted) {
            if (dmgDealer instanceof Arrow) {
                Arrow arrow = (Arrow) event.getDamager();
                if (arrow.getShooter() instanceof Player && pactTeam.contains((Player) arrow.getShooter())) {
                    if (dmgReceiver instanceof EnderDragon) {
                        event.setDamage(event.getDamage() * 2);
                    }
                    if (dmgReceiver instanceof Player) {
                        event.setDamage(event.getDamage() * 0.3);
                    }
                }
            }
        }
        if (pactNumber == 4 && isPactAccepted) {
            if (
                    dmgDealer instanceof Blaze ||
                    dmgDealer instanceof Creeper ||
                    dmgDealer instanceof Enderman ||
                    dmgDealer instanceof Endermite ||
                    dmgDealer instanceof EnderDragon ||
                    dmgDealer instanceof Ghast ||
                    dmgDealer instanceof Guardian ||
                    dmgDealer instanceof Skeleton ||
                    dmgDealer instanceof Spider ||
                    dmgDealer instanceof Slime ||
                    dmgDealer instanceof Silverfish ||
                    dmgDealer instanceof Witch ||
                    dmgDealer instanceof Wolf ||
                    dmgDealer instanceof Wither ||
                    dmgDealer instanceof Zombie
            ) {
                if (dmgReceiver instanceof Player && pactTeam.contains(dmgReceiver)) {
                    event.setDamage(event.getDamage() * 2);
                }
            }
            if (dmgDealer instanceof Player) {
                if (dmgReceiver instanceof Player && pactTeam.contains(dmgReceiver)) {
                    event.setDamage(event.getDamage() * 0.75);
                }
            }
        }

    }

    private List<Player> setPactTeam(Player player) {
        if (pactNumber == 3) {
            pactTeam.add(player);
            StalkedPact task = new StalkedPact(main, player);
            task.runTaskTimer(main, 0, 20);
        }
        else {
            List<String> playerTeam = Teams.teamPlayers(player);
            for (String p : playerTeam) {
                pactTeam.add(Bukkit.getPlayer(p));
            }
        }
        return pactTeam;
    }

    private void setPactBuffs() {
        for (Player p : pactTeam) {
            switch (pactNumber) {
                case 2:
                    p.setMaxHealth(30);
                    break;
                case 3:
                    ItemStack bow = new ItemStack(Material.BOW);
                    ItemMeta bowMeta = bow.getItemMeta();
                    bowMeta.addEnchant(Enchantment.ARROW_DAMAGE, 4, true);
                    bowMeta.addEnchant(Enchantment.ARROW_INFINITE, 1, true);
                    bow.setItemMeta(bowMeta);
                    p.getInventory().addItem(bow);
                    break;
            }
        }
    }

    private Inventory setPactInventory() {
        pactInventory = Bukkit.createInventory(null, 45, "§5§lPacte du dragon");
        ItemStack purple = new ItemStack(Material.STAINED_GLASS_PANE, 1, (short) 10);
        ItemStack pink = new ItemStack(Material.STAINED_GLASS_PANE, 1, (short) 2);
        ItemStack green = new ItemStack(Material.STAINED_CLAY, 1, (short) 5);
        ItemStack red = new ItemStack(Material.STAINED_CLAY, 1, (short) 14);
        ItemStack paper = setPaperMeta();
        ItemStack dragon = getCustomTextureHead("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYzI3OWRjOTEzNzNiNDI3NzY5MDQzZmFlODg5Y2UyYWRkM2FlMzIxNjY0OTY1MzRhNGQ2YThhOGFhYTJkIn19fQ==");

        ItemMeta purpleM = purple.getItemMeta();
        purpleM.setDisplayName(" ");
        purple.setItemMeta(purpleM);

        ItemMeta pinkM = pink.getItemMeta();
        pinkM.setDisplayName(" ");
        pink.setItemMeta(pinkM);

        ItemMeta greenM = green.getItemMeta();
        greenM.setDisplayName("§2Accepter le pacte");
        green.setItemMeta(greenM);

        ItemMeta redM = red.getItemMeta();
        redM.setDisplayName("§4Refuser le pacte");
        red.setItemMeta(redM);

        for (int i = 20; i <= 24; i++) {
            pactInventory.setItem(i,purple);
        }
        pactInventory.setItem(0,purple);
        pactInventory.setItem(1,purple);
        pactInventory.setItem(7,purple);
        pactInventory.setItem(8,purple);
        pactInventory.setItem(9,purple);
        pactInventory.setItem(12,purple);
        pactInventory.setItem(14,purple);
        pactInventory.setItem(17,purple);
        pactInventory.setItem(30,purple);
        pactInventory.setItem(32,purple);
        pactInventory.setItem(36,purple);
        pactInventory.setItem(44,purple);

        for (int i = 2; i <= 6; i++) {
            pactInventory.setItem(i,pink);
        }
        for (int i = 37; i <= 43; i++) {
            pactInventory.setItem(i,pink);
        }
        pactInventory.setItem(10,pink);
        pactInventory.setItem(11,pink);
        pactInventory.setItem(15,pink);
        pactInventory.setItem(16,pink);
        pactInventory.setItem(18,pink);
        pactInventory.setItem(19,pink);
        pactInventory.setItem(25,pink);
        pactInventory.setItem(26,pink);
        pactInventory.setItem(27,pink);
        pactInventory.setItem(28,pink);
        pactInventory.setItem(34,pink);
        pactInventory.setItem(35,pink);

        pactInventory.setItem(13, dragon);
        pactInventory.setItem(29, green);
        pactInventory.setItem(31, paper);
        pactInventory.setItem(33, red);

        return pactInventory;
    }

    private ItemStack setPaperMeta() {
        ItemStack it = new ItemStack(Material.PAPER);
        ItemMeta m = it.getItemMeta();

        switch (pactNumber) {
            case 1:
                m.setDisplayName("§6§l§oQuitte ou double");
                m.setLore(Arrays.asList("§a• §7Uniquement à l'arc, vous infligez §2deux", "  §2fois plus de dégâts §7au dragon, mais faites", "  §4trois fois moins de dégâts aux", "  §7autres joueurs", " ", "§a• §7Le pacte sera appliqué à l'ensemble", "  §7de l'équipe"));
                break;
            case 2:
                m.setDisplayName("§6§l§oPas de seconde chance");
                m.setLore(Arrays.asList("§a• §7Vous gagnez §25 coeurs permanents, §7mais si", "  §7vous mourrez, vous ne§4 réapparaitrez pas§7", " ", "§a• §7Le pacte sera appliqué à l'ensemble", "  §7de l'équipe"));
                break;
            case 3:
                m.setDisplayName("§6§l§oTraqué");
                m.setLore(Arrays.asList("§a• §7Vous gagnez un arc §2power IV infinity,", "  §7mais toutes les 10 minutes, vos coordonnées", "  §4seront dévoilées §7aux yeux de tous", " ", "§a• §7Le pacte sera appliqué uniquement", "  §7sur vous"));
                break;
            case 4:
                m.setDisplayName("§6§l§oLe reveil des monstres");
                m.setLore(Arrays.asList("§a• §7Vous prenez §2moins de dégâts §7face aux", "  §7autres joueurs, mais les monstres vous font", "  §4deux fois plus§7 de dégâts", " ", "§a• §7Le pacte sera appliqué à l'ensemble", "  §7de l'équipe"));
                break;
            case 5:
                m.setDisplayName("§6§l§oBrouilleur de pistes");
                m.setLore(Arrays.asList("§a• §7Vous connaissez l'emplacement du pilier", "  §230 secondes §7avant les autres joueurs,", " §7 mais vous disposez de l'effet §4weakness II", "  §7à partir de ce moment là", " ", "§a• §7Le pacte sera appliqué à l'ensemble", "  §7de l'équipe"));
                break;
        }
        it.setItemMeta(m);
        return it;
    }

    private ItemStack getCustomTextureHead(String value) {
        ItemStack head = new ItemStack(Material.SKULL_ITEM, 1, (short)3);
        SkullMeta meta = (SkullMeta) head.getItemMeta();
        GameProfile profile = new GameProfile(UUID.randomUUID(), "");
        profile.getProperties().put("textures", new Property("textures", value));
        Field profileField = null;
        try {
            profileField = meta.getClass().getDeclaredField("profile");
            profileField.setAccessible(true);
            profileField.set(meta, profile);
        } catch (IllegalArgumentException | IllegalAccessException | NoSuchFieldException | SecurityException e) {
            e.printStackTrace();
        }
        meta.setDisplayName("§d§l§oDragon");
        head.setItemMeta(meta);
        return head;
    }

}
