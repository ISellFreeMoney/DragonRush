package fr.ziberty.dragonrush.configuration;

import fr.ziberty.dragonrush.DragonRush;
import fr.ziberty.dragonrush.PluginListener;
import fr.ziberty.dragonrush.scenarios.HasteyBoys;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Collections;

public class Config implements Listener {

    private DragonRush main;
    private Inventory scenariosInv, lootInv, timerInv;
    public static boolean cateyes, cutclean, hasteyboys;
    public static int nbPlumes = 1, nbFleches = 1, pvp = 10;

    public Config (DragonRush dragonRush) {
        this.main = dragonRush;
    }

    @EventHandler
    private void onConfigMenuInteract(InventoryClickEvent event) {
        Inventory inv = event.getInventory();
        Player player = (Player) event.getWhoClicked();
        ItemStack current = event.getCurrentItem();
        ClickType clickType = event.getClick();

        if (current == null) return;
        ItemMeta mt = current.getItemMeta();
        if (mt == null) return;

        if (inv.getName().equalsIgnoreCase("§8Menu de configuration")) {
            event.setCancelled(true);
            switch (current.getType()) {
                case BOOK_AND_QUILL:
                    scenariosInv = setScenarioInventory();
                    player.openInventory(scenariosInv);
                    break;
                case ARROW:
                    lootInv = setLootInventory(clickType, "nothing");
                    player.openInventory(lootInv);
                    break;
                case WATCH:
                    timerInv = setTimerInventory(clickType, "nothing");
                    player.openInventory(timerInv);
                    break;
            }
        }
    }

    @EventHandler
    private void onScenarioMenuInteract(InventoryClickEvent event) {
        Inventory inv = event.getInventory();
        Player player = (Player) event.getWhoClicked();
        ItemStack current = event.getCurrentItem();

        if (current == null) return;
        ItemMeta mt = current.getItemMeta();
        if (mt == null) return;

        if (inv.getName().equalsIgnoreCase("§8Choix des scénarios")) {
            event.setCancelled(true);
            switch (current.getType()) {
                case EYE_OF_ENDER:
                    cateyes = !cateyes;
                    scenariosInv = setScenarioInventory();
                    player.openInventory(scenariosInv);
                    break;
                case IRON_INGOT:
                    cutclean = !cutclean;
                    scenariosInv = setScenarioInventory();
                    player.openInventory(scenariosInv);
                    break;
                case GOLD_PICKAXE:
                    hasteyboys = !hasteyboys;
                    scenariosInv = setScenarioInventory();
                    player.openInventory(scenariosInv);
                    break;
                case BARRIER:
                    player.openInventory(PluginListener.confInv);
                    break;
            }
        }
    }

    @EventHandler
    private void onLootMenuInteract(InventoryClickEvent event) {
        Inventory inv = event.getInventory();
        Player player = (Player) event.getWhoClicked();
        ItemStack current = event.getCurrentItem();
        ClickType clickType = event.getClick();

        if (current == null) return;
        ItemMeta mt = current.getItemMeta();
        if (mt == null) return;

        if (inv.getName().equalsIgnoreCase("§8Configuration des loots")) {
            event.setCancelled(true);
            switch (current.getType()) {
                case FEATHER:
                    lootInv = setLootInventory(clickType, "plume");
                    player.openInventory(lootInv);
                    break;
                case ARROW:
                    lootInv = setLootInventory(clickType, "fleche");
                    player.openInventory(lootInv);
                    break;
                case BARRIER:
                    player.openInventory(PluginListener.confInv);
                    break;
            }
        }
    }

    @EventHandler
    private void onTimerMenuInteract(InventoryClickEvent event) {
        Inventory inv = event.getInventory();
        Player player = (Player) event.getWhoClicked();
        ItemStack current = event.getCurrentItem();
        ClickType clickType = event.getClick();

        if (current == null) return;
        ItemMeta mt = current.getItemMeta();
        if (mt == null) return;

        if (inv.getName().equalsIgnoreCase("§8Configuration des timers")) {
            event.setCancelled(true);
            switch (current.getType()) {
                case DIAMOND_SWORD:
                    timerInv = setTimerInventory(clickType, "pvp");
                    player.openInventory(timerInv);
                    break;
                case BARRIER:
                    player.openInventory(PluginListener.confInv);
                    break;
            }
        }
    }

    private Inventory setScenarioInventory() {
        scenariosInv = Bukkit.createInventory(null, 45, "§8Choix des scénarios");
        ItemStack glass = new ItemStack(Material.STAINED_GLASS_PANE, 1, (short) 15);

        for (int i = 0; i <= 8; i++) {
            scenariosInv.setItem(i, glass);
        }
        for (int i = 35; i <= 44; i++) {
            scenariosInv.setItem(i, glass);
        }
        for (int i = 0; i <= 35; i += 9) {
            scenariosInv.setItem(i, glass);
        }
        for (int i = 8; i <= 44; i += 9) {
            scenariosInv.setItem(i, glass);
        }

        ItemStack itCatEyes = new ItemStack(Material.EYE_OF_ENDER);
        ItemStack itCutClean = new ItemStack(Material.IRON_INGOT);
        ItemStack itHasteyBoys = new ItemStack(Material.GOLD_PICKAXE);

        ItemMeta mCatEyes = itCatEyes.getItemMeta();
        ItemMeta mCutClean = itCutClean.getItemMeta();
        ItemMeta mHasteyBoys = itHasteyBoys.getItemMeta();

        mCatEyes.setDisplayName("§bCat Eyes");
        mCutClean.setDisplayName("§bCut Clean");
        mHasteyBoys.setDisplayName("§bHastey Boys");

        if (cateyes) {
            mCatEyes.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 1, false);
            mCatEyes.addItemFlags(ItemFlag.HIDE_ENCHANTS);
            mCatEyes.setLore(Collections.singletonList("§2Activé"));
        }
        else {
            mCatEyes.setLore(Collections.singletonList("§4Désactivé"));
        }

        if (cutclean) {
            mCutClean.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 1, false);
            mCutClean.addItemFlags(ItemFlag.HIDE_ENCHANTS);
            mCutClean.setLore(Collections.singletonList("§2Activé"));
        }
        else {
            mCutClean.setLore(Collections.singletonList("§4Désactivé"));
        }

        if (hasteyboys) {
            mHasteyBoys.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 1, false);
            mHasteyBoys.addItemFlags(ItemFlag.HIDE_ENCHANTS);
            mHasteyBoys.setLore(Collections.singletonList("§2Activé"));
        }
        else {
            mHasteyBoys.setLore(Collections.singletonList("§4Désactivé"));
        }

        HasteyBoys hasteyBoys = new HasteyBoys();
        hasteyBoys.customRecipes();

        itCatEyes.setItemMeta(mCatEyes);
        itCutClean.setItemMeta(mCutClean);
        itHasteyBoys.setItemMeta(mHasteyBoys);

        scenariosInv.setItem(40, getItem(Material.BARRIER,"§cRetour"));
        scenariosInv.setItem(20, itCatEyes);
        scenariosInv.setItem(22, itCutClean);
        scenariosInv.setItem(24, itHasteyBoys);

        return scenariosInv;
    }

    private Inventory setLootInventory(ClickType click, String item) {
        lootInv = Bukkit.createInventory(null, 45, "§8Configuration des loots");
        ItemStack glass = new ItemStack(Material.STAINED_GLASS_PANE, 1, (short) 15);

        for (int i = 0; i <= 8; i++) {
            lootInv.setItem(i, glass);
        }
        for (int i = 35; i <= 44; i++) {
            lootInv.setItem(i, glass);
        }
        for (int i = 0; i <= 35; i += 9) {
            lootInv.setItem(i, glass);
        }
        for (int i = 8; i <= 44; i += 9) {
            lootInv.setItem(i, glass);
        }

        ItemStack itPlumes = new ItemStack(Material.FEATHER);
        ItemStack itFleches = new ItemStack(Material.ARROW);

        ItemMeta mPlumes = itPlumes.getItemMeta();
        ItemMeta mFleches = itFleches.getItemMeta();
        mPlumes.setDisplayName("§bPlumes par poulet");
        mFleches.setDisplayName("§bFlèches par squelette");

        if (click.isLeftClick()) {
            switch (item) {
                case "plume":
                    nbPlumes ++;
                    break;
                case "fleche":
                    nbFleches ++;
                    break;
                default:
                    break;
            }
        }
        if (click.isRightClick()) {
            switch (item) {
                case "plume":
                    if (nbPlumes != 1) {
                        nbPlumes --;
                    }
                    break;
                case "fleche":
                    if (nbFleches != 1) {
                        nbFleches --;
                    }
                    break;
                default:
                    break;
            }
        }

        if (nbPlumes == 1) {
            mPlumes.setLore(Collections.singletonList("§b" + nbPlumes + " §7plume"));
        }
        else {
            mPlumes.setLore(Collections.singletonList("§b" + nbPlumes + " §7plumes"));
        }

        if (nbFleches == 1) {
            mFleches.setLore(Collections.singletonList("§b" + nbFleches + " §7flèche"));
        }
        else {
            mFleches.setLore(Collections.singletonList("§b" + nbFleches + " §7flèches"));
        }

        itPlumes.setItemMeta(mPlumes);
        itFleches.setItemMeta(mFleches);

        lootInv.setItem(40, getItem(Material.BARRIER,"§cRetour"));
        lootInv.setItem(21, itPlumes);
        lootInv.setItem(23, itFleches);


        return lootInv;
    }
    private Inventory setTimerInventory(ClickType click, String item) {
        timerInv = Bukkit.createInventory(null, 45, "§8Configuration des timers");
        ItemStack glass = new ItemStack(Material.STAINED_GLASS_PANE, 1, (short) 15);

        for (int i = 0; i <= 8; i++) {
            timerInv.setItem(i, glass);
        }
        for (int i = 35; i <= 44; i++) {
            timerInv.setItem(i, glass);
        }
        for (int i = 0; i <= 35; i += 9) {
            timerInv.setItem(i, glass);
        }
        for (int i = 8; i <= 44; i += 9) {
            timerInv.setItem(i, glass);
        }

        ItemStack itSword = new ItemStack(Material.DIAMOND_SWORD);
        ItemMeta mSword = itSword.getItemMeta();
        mSword.setDisplayName("§bActivation du PvP");

        if (click.isLeftClick()) {
            switch (item) {
                case "pvp":
                    pvp ++;
                    break;
                default:
                    break;
            }
        }
        if (click.isRightClick()) {
            switch (item) {
                case "pvp":
                    if (pvp != 1) {
                        pvp --;
                    }
                    break;
                default:
                    break;
            }
        }

        if (pvp == 1) {
            mSword.setLore(Collections.singletonList("§b" + pvp + " §7minute"));
        }
        else {
            mSword.setLore(Collections.singletonList("§b" + pvp + " §7minutes"));
        }

        itSword.setItemMeta(mSword);

        timerInv.setItem(40, getItem(Material.BARRIER,"§cRetour"));
        timerInv.setItem(22, itSword);

        return timerInv;
    }

    private ItemStack getItem(Material material, String name) {
        ItemStack it = new ItemStack(material);
        ItemMeta itM = it.getItemMeta();
        if (name != null) itM.setDisplayName(name);
        it.setItemMeta(itM);
        return it;
    }

}
