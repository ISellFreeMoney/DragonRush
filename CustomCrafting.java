package fr.ziberty.dragonrush;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Arrays;

public class CustomCrafting {

    public void customCraft() {
        ItemStack it = new ItemStack(Material.BOOK);
        ItemMeta meta = it.getItemMeta();

        meta.setDisplayName("§5Pacte du dragon");
        meta.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 1, false);
        meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        meta.setLore(Arrays.asList("§7Ce pacte vous permet de passer un accord avec le Dragon", "§7Si vous l'utilisez, il se détruira instantanément,", "§7et aucune autre équipe ne pourra l'utiliser"));
        it.setItemMeta(meta);

        ShapedRecipe pacte = new ShapedRecipe(it);
        pacte.shape("EDE", "GBG", "EPE");
        pacte.setIngredient('G', Material.GOLD_BLOCK);
        pacte.setIngredient('E', Material.ENDER_PEARL);
        pacte.setIngredient('D', Material.DIAMOND);
        pacte.setIngredient('P', Material.BLAZE_POWDER);
        pacte.setIngredient('B', Material.BOOK_AND_QUILL);

        Bukkit.getServer().addRecipe(pacte);
    }

}
