package fr.ziberty.dragonrush.scenarios;

import fr.ziberty.dragonrush.configuration.Config;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.Recipe;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Iterator;
import java.util.Objects;

public class HasteyBoys {

    public void customRecipes() {

        if (Config.hasteyboys) {
            Iterator<Recipe> it = Bukkit.getServer().recipeIterator();
            Recipe recipe;
            while(it.hasNext())
            {
                recipe = it.next();
                if (recipe != null && Objects.requireNonNull(recipe).getResult().getType() == Material.WOOD_PICKAXE || Objects.requireNonNull(recipe).getResult().getType() == Material.STONE_PICKAXE || Objects.requireNonNull(recipe).getResult().getType() == Material.IRON_PICKAXE || Objects.requireNonNull(recipe).getResult().getType() == Material.GOLD_PICKAXE || Objects.requireNonNull(recipe).getResult().getType() == Material.DIAMOND_PICKAXE)
                {
                    it.remove();
                }
            }
        }

        ItemStack wood_pick = new ItemStack(Material.WOOD_PICKAXE);
        ItemStack stone_pick = new ItemStack(Material.STONE_PICKAXE);
        ItemStack iron_pick = new ItemStack(Material.IRON_PICKAXE);
        ItemStack gold_pick = new ItemStack(Material.GOLD_PICKAXE);
        ItemStack diamond_pick = new ItemStack(Material.DIAMOND_PICKAXE);

        ItemStack wood_axe = new ItemStack(Material.WOOD_AXE);
        ItemStack stone_axe = new ItemStack(Material.STONE_AXE);
        ItemStack iron_axe = new ItemStack(Material.IRON_AXE);
        ItemStack gold_axe = new ItemStack(Material.GOLD_AXE);
        ItemStack diamond_axe = new ItemStack(Material.DIAMOND_AXE);

        ItemStack wood_axe2 = new ItemStack(Material.WOOD_AXE);
        ItemStack stone_axe2 = new ItemStack(Material.STONE_AXE);
        ItemStack iron_axe2 = new ItemStack(Material.IRON_AXE);
        ItemStack gold_axe2 = new ItemStack(Material.GOLD_AXE);
        ItemStack diamond_axe2 = new ItemStack(Material.DIAMOND_AXE);

        ItemStack wood_shovel = new ItemStack(Material.WOOD_SPADE);
        ItemStack stone_shovel = new ItemStack(Material.STONE_SPADE);
        ItemStack iron_shovel = new ItemStack(Material.IRON_SPADE);
        ItemStack gold_shovel = new ItemStack(Material.GOLD_SPADE);
        ItemStack diamond_shovel = new ItemStack(Material.DIAMOND_SPADE);

        ItemMeta wpickmeta = wood_pick.getItemMeta();
        ItemMeta spickmeta = stone_pick.getItemMeta();
        ItemMeta ipickmeta = iron_pick.getItemMeta();
        ItemMeta gpickmeta = gold_pick.getItemMeta();
        ItemMeta dpickmeta = diamond_pick.getItemMeta();

        ItemMeta waxemeta = wood_axe.getItemMeta();
        ItemMeta saxemeta = stone_axe.getItemMeta();
        ItemMeta iaxemeta = iron_axe.getItemMeta();
        ItemMeta gaxemeta = gold_axe.getItemMeta();
        ItemMeta daxemeta = diamond_axe.getItemMeta();

        ItemMeta waxemeta2 = wood_axe.getItemMeta();
        ItemMeta saxemeta2 = stone_axe.getItemMeta();
        ItemMeta iaxemeta2 = iron_axe.getItemMeta();
        ItemMeta gaxemeta2 = gold_axe.getItemMeta();
        ItemMeta daxemeta2 = diamond_axe.getItemMeta();

        ItemMeta wshovelmeta = wood_shovel.getItemMeta();
        ItemMeta sshovelmeta = stone_shovel.getItemMeta();
        ItemMeta ishovelmeta = iron_shovel.getItemMeta();
        ItemMeta gshovelmeta = gold_shovel.getItemMeta();
        ItemMeta dshovelmeta = diamond_shovel.getItemMeta();

        wpickmeta.addEnchant(Enchantment.DIG_SPEED, 3, true);
        wpickmeta.addEnchant(Enchantment.DURABILITY, 3, true);
        spickmeta.addEnchant(Enchantment.DIG_SPEED, 3, true);
        spickmeta.addEnchant(Enchantment.DURABILITY, 3, true);
        ipickmeta.addEnchant(Enchantment.DIG_SPEED, 3, true);
        ipickmeta.addEnchant(Enchantment.DURABILITY, 3, true);
        gpickmeta.addEnchant(Enchantment.DIG_SPEED, 3, true);
        gpickmeta.addEnchant(Enchantment.DURABILITY, 3, true);
        dpickmeta.addEnchant(Enchantment.DIG_SPEED, 3, true);
        dpickmeta.addEnchant(Enchantment.DURABILITY, 3, true);

        waxemeta.addEnchant(Enchantment.DIG_SPEED, 3, true);
        waxemeta.addEnchant(Enchantment.DURABILITY, 3, true);
        saxemeta.addEnchant(Enchantment.DIG_SPEED, 3, true);
        saxemeta.addEnchant(Enchantment.DURABILITY, 3, true);
        iaxemeta.addEnchant(Enchantment.DIG_SPEED, 3, true);
        iaxemeta.addEnchant(Enchantment.DURABILITY, 3, true);
        gaxemeta.addEnchant(Enchantment.DIG_SPEED, 3, true);
        gaxemeta.addEnchant(Enchantment.DURABILITY, 3, true);
        daxemeta.addEnchant(Enchantment.DIG_SPEED, 3, true);
        daxemeta.addEnchant(Enchantment.DURABILITY, 3, true);

        waxemeta2.addEnchant(Enchantment.DIG_SPEED, 3, true);
        waxemeta2.addEnchant(Enchantment.DURABILITY, 3, true);
        saxemeta2.addEnchant(Enchantment.DIG_SPEED, 3, true);
        saxemeta2.addEnchant(Enchantment.DURABILITY, 3, true);
        iaxemeta2.addEnchant(Enchantment.DIG_SPEED, 3, true);
        iaxemeta2.addEnchant(Enchantment.DURABILITY, 3, true);
        gaxemeta2.addEnchant(Enchantment.DIG_SPEED, 3, true);
        gaxemeta2.addEnchant(Enchantment.DURABILITY, 3, true);
        daxemeta2.addEnchant(Enchantment.DIG_SPEED, 3, true);
        daxemeta2.addEnchant(Enchantment.DURABILITY, 3, true);

        wshovelmeta.addEnchant(Enchantment.DIG_SPEED, 3, true);
        wshovelmeta.addEnchant(Enchantment.DURABILITY, 3, true);
        sshovelmeta.addEnchant(Enchantment.DIG_SPEED, 3, true);
        sshovelmeta.addEnchant(Enchantment.DURABILITY, 3, true);
        ishovelmeta.addEnchant(Enchantment.DIG_SPEED, 3, true);
        ishovelmeta.addEnchant(Enchantment.DURABILITY, 3, true);
        gshovelmeta.addEnchant(Enchantment.DIG_SPEED, 3, true);
        gshovelmeta.addEnchant(Enchantment.DURABILITY, 3, true);
        dshovelmeta.addEnchant(Enchantment.DIG_SPEED, 3, true);
        dshovelmeta.addEnchant(Enchantment.DURABILITY, 3, true);

        wood_pick.setItemMeta(wpickmeta);
        stone_pick.setItemMeta(spickmeta);
        iron_pick.setItemMeta(ipickmeta);
        gold_pick.setItemMeta(gpickmeta);
        diamond_pick.setItemMeta(dpickmeta);

        wood_axe.setItemMeta(waxemeta);
        stone_axe.setItemMeta(saxemeta);
        iron_axe.setItemMeta(iaxemeta);
        gold_axe.setItemMeta(gaxemeta);
        diamond_axe.setItemMeta(daxemeta);

        wood_axe2.setItemMeta(waxemeta2);
        stone_axe2.setItemMeta(saxemeta2);
        iron_axe2.setItemMeta(iaxemeta2);
        gold_axe2.setItemMeta(gaxemeta2);
        diamond_axe2.setItemMeta(daxemeta2);

        wood_shovel.setItemMeta(wshovelmeta);
        stone_shovel.setItemMeta(sshovelmeta);
        iron_shovel.setItemMeta(ishovelmeta);
        gold_shovel.setItemMeta(gshovelmeta);
        diamond_shovel.setItemMeta(dshovelmeta);

        ShapedRecipe wpickrecipe = new ShapedRecipe(wood_pick);
        ShapedRecipe spickrecipe = new ShapedRecipe(stone_pick);
        ShapedRecipe ipickrecipe = new ShapedRecipe(iron_pick);
        ShapedRecipe gpickrecipe = new ShapedRecipe(gold_pick);
        ShapedRecipe dpickrecipe = new ShapedRecipe(diamond_pick);

        ShapedRecipe waxerecipe = new ShapedRecipe(wood_axe);
        ShapedRecipe saxerecipe = new ShapedRecipe(stone_axe);
        ShapedRecipe iaxerecipe = new ShapedRecipe(iron_axe);
        ShapedRecipe gaxerecipe = new ShapedRecipe(gold_axe);
        ShapedRecipe daxerecipe = new ShapedRecipe(diamond_axe);

        ShapedRecipe waxerecipe2 = new ShapedRecipe(wood_axe2);
        ShapedRecipe saxerecipe2 = new ShapedRecipe(stone_axe2);
        ShapedRecipe iaxerecipe2 = new ShapedRecipe(iron_axe2);
        ShapedRecipe gaxerecipe2 = new ShapedRecipe(gold_axe2);
        ShapedRecipe daxerecipe2 = new ShapedRecipe(diamond_axe2);

        ShapedRecipe wshovelrecipe = new ShapedRecipe(wood_shovel);
        ShapedRecipe sshovelrecipe = new ShapedRecipe(stone_shovel);
        ShapedRecipe ishovelrecipe = new ShapedRecipe(iron_shovel);
        ShapedRecipe gshovelrecipe = new ShapedRecipe(gold_shovel);
        ShapedRecipe dshovelrecipe = new ShapedRecipe(diamond_shovel);

        ItemStack wood = new ItemStack(Material.WOOD, 1, Short.MAX_VALUE);

        wpickrecipe.shape("MMM", " S ", " S ");
        wpickrecipe.setIngredient('M', wood.getData());
        wpickrecipe.setIngredient('S', Material.STICK);
        spickrecipe.shape("MMM", " S ", " S ");
        spickrecipe.setIngredient('M', Material.COBBLESTONE);
        spickrecipe.setIngredient('S', Material.STICK);
        ipickrecipe.shape("MMM", " S ", " S ");
        ipickrecipe.setIngredient('M', Material.IRON_INGOT);
        ipickrecipe.setIngredient('S', Material.STICK);
        gpickrecipe.shape("MMM", " S ", " S ");
        gpickrecipe.setIngredient('M', Material.GOLD_INGOT);
        gpickrecipe.setIngredient('S', Material.STICK);
        dpickrecipe.shape("MMM", " S ", " S ");
        dpickrecipe.setIngredient('M', Material.DIAMOND);
        dpickrecipe.setIngredient('S', Material.STICK);

        waxerecipe.shape("MM ", "MS ", " S ");
        waxerecipe.setIngredient('M', wood.getData());
        waxerecipe.setIngredient('S', Material.STICK);
        saxerecipe.shape("MM ", "MS ", " S ");
        saxerecipe.setIngredient('M', Material.COBBLESTONE);
        saxerecipe.setIngredient('S', Material.STICK);
        iaxerecipe.shape("MM ", "MS ", " S ");
        iaxerecipe.setIngredient('M', Material.IRON_INGOT);
        iaxerecipe.setIngredient('S', Material.STICK);
        gaxerecipe.shape("MM ", "MS ", " S ");
        gaxerecipe.setIngredient('M', Material.GOLD_INGOT);
        gaxerecipe.setIngredient('S', Material.STICK);
        daxerecipe.shape("MM ", "MS ", " S ");
        daxerecipe.setIngredient('M', Material.DIAMOND);
        daxerecipe.setIngredient('S', Material.STICK);

        waxerecipe2.shape(" MM", " SM", " S ");
        waxerecipe2.setIngredient('M', wood.getData());
        waxerecipe2.setIngredient('S', Material.STICK);
        saxerecipe2.shape(" MM", " SM", " S ");
        saxerecipe2.setIngredient('M', Material.COBBLESTONE);
        saxerecipe2.setIngredient('S', Material.STICK);
        iaxerecipe2.shape(" MM", " SM", " S ");
        iaxerecipe2.setIngredient('M', Material.IRON_INGOT);
        iaxerecipe2.setIngredient('S', Material.STICK);
        gaxerecipe2.shape(" MM", " SM", " S ");
        gaxerecipe2.setIngredient('M', Material.GOLD_INGOT);
        gaxerecipe2.setIngredient('S', Material.STICK);
        daxerecipe2.shape(" MM", " SM", " S ");
        daxerecipe2.setIngredient('M', Material.DIAMOND);
        daxerecipe2.setIngredient('S', Material.STICK);

        wshovelrecipe.shape(" M ", " S ", " S ");
        wshovelrecipe.setIngredient('M', wood.getData());
        wshovelrecipe.setIngredient('S', Material.STICK);
        sshovelrecipe.shape(" M ", " S ", " S ");
        sshovelrecipe.setIngredient('M', Material.COBBLESTONE);
        sshovelrecipe.setIngredient('S', Material.STICK);
        ishovelrecipe.shape(" M ", " S ", " S ");
        ishovelrecipe.setIngredient('M', Material.IRON_INGOT);
        ishovelrecipe.setIngredient('S', Material.STICK);
        gshovelrecipe.shape(" M ", " S ", " S ");
        gshovelrecipe.setIngredient('M', Material.GOLD_INGOT);
        gshovelrecipe.setIngredient('S', Material.STICK);
        dshovelrecipe.shape(" M ", " S ", " S ");
        dshovelrecipe.setIngredient('M', Material.DIAMOND);
        dshovelrecipe.setIngredient('S', Material.STICK);

        if (Config.hasteyboys) {
            Bukkit.getServer().addRecipe(wpickrecipe);
            Bukkit.getServer().addRecipe(spickrecipe);
            Bukkit.getServer().addRecipe(ipickrecipe);
            Bukkit.getServer().addRecipe(gpickrecipe);
            Bukkit.getServer().addRecipe(dpickrecipe);

            Bukkit.getServer().addRecipe(waxerecipe);
            Bukkit.getServer().addRecipe(saxerecipe);
            Bukkit.getServer().addRecipe(iaxerecipe);
            Bukkit.getServer().addRecipe(gaxerecipe);
            Bukkit.getServer().addRecipe(daxerecipe);

            Bukkit.getServer().addRecipe(waxerecipe2);
            Bukkit.getServer().addRecipe(saxerecipe2);
            Bukkit.getServer().addRecipe(iaxerecipe2);
            Bukkit.getServer().addRecipe(gaxerecipe2);
            Bukkit.getServer().addRecipe(daxerecipe2);

            Bukkit.getServer().addRecipe(wshovelrecipe);
            Bukkit.getServer().addRecipe(sshovelrecipe);
            Bukkit.getServer().addRecipe(ishovelrecipe);
            Bukkit.getServer().addRecipe(gshovelrecipe);
            Bukkit.getServer().addRecipe(dshovelrecipe);
        }

        if (!Config.hasteyboys) {
            Iterator<Recipe> it = Bukkit.getServer().recipeIterator();
            Recipe recipe;
            while(it.hasNext())
            {
                recipe = it.next();
                if (recipe != null && recipe.getResult().hasItemMeta()) {
                    if (!recipe.getResult().getItemMeta().hasDisplayName())
                    it.remove();
                }
            }
            ItemStack wood_pick2 = new ItemStack(Material.WOOD_PICKAXE);
            ItemStack stone_pick2 = new ItemStack(Material.STONE_PICKAXE);
            ItemStack iron_pick2 = new ItemStack(Material.IRON_PICKAXE);
            ItemStack gold_pick2 = new ItemStack(Material.GOLD_PICKAXE);
            ItemStack diamond_pick2 = new ItemStack(Material.DIAMOND_PICKAXE);

            ShapedRecipe wpickrecipe2 = new ShapedRecipe(wood_pick2);
            ShapedRecipe spickrecipe2 = new ShapedRecipe(stone_pick2);
            ShapedRecipe ipickrecipe2 = new ShapedRecipe(iron_pick2);
            ShapedRecipe gpickrecipe2 = new ShapedRecipe(gold_pick2);
            ShapedRecipe dpickrecipe2 = new ShapedRecipe(diamond_pick2);

            wpickrecipe2.shape("MMM", " S ", " S ");
            wpickrecipe2.setIngredient('M', wood.getData());
            wpickrecipe2.setIngredient('S', Material.STICK);
            spickrecipe2.shape("MMM", " S ", " S ");
            spickrecipe2.setIngredient('M', Material.COBBLESTONE);
            spickrecipe2.setIngredient('S', Material.STICK);
            ipickrecipe2.shape("MMM", " S ", " S ");
            ipickrecipe2.setIngredient('M', Material.IRON_INGOT);
            ipickrecipe2.setIngredient('S', Material.STICK);
            gpickrecipe2.shape("MMM", " S ", " S ");
            gpickrecipe2.setIngredient('M', Material.GOLD_INGOT);
            gpickrecipe2.setIngredient('S', Material.STICK);
            dpickrecipe2.shape("MMM", " S ", " S ");
            dpickrecipe2.setIngredient('M', Material.DIAMOND);
            dpickrecipe2.setIngredient('S', Material.STICK);

            Bukkit.getServer().addRecipe(wpickrecipe2);
            Bukkit.getServer().addRecipe(spickrecipe2);
            Bukkit.getServer().addRecipe(ipickrecipe2);
            Bukkit.getServer().addRecipe(gpickrecipe2);
            Bukkit.getServer().addRecipe(dpickrecipe2);
        }
    }

}
