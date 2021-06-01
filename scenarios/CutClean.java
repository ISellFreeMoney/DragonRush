package fr.ziberty.dragonrush.scenarios;

import fr.ziberty.dragonrush.configuration.Config;
import fr.ziberty.dragonrush.DragonRush;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.inventory.ItemStack;

import java.util.Random;

public class CutClean implements Listener {

    private DragonRush main;

    public CutClean (DragonRush dragonRush) {
        this.main = dragonRush;
    }

    @EventHandler
    private void CutCleanMobLoots(EntityDeathEvent event) {
        if (Config.cutclean) {
            Random random = new Random();
            Entity entity = event.getEntity();
            if (entity instanceof Cow) {
                int leather = random.nextInt(2 - 1 + 1) + 1;
                int food = random.nextInt(3 - 1 + 1) + 1;
                event.getDrops().clear();
                entity.getLocation().getWorld().dropItemNaturally(entity.getLocation(), new ItemStack(Material.LEATHER, leather));
                entity.getLocation().getWorld().dropItemNaturally(entity.getLocation(), new ItemStack(Material.COOKED_BEEF, food));
            }
            if (entity instanceof Chicken) {
                int feather = Config.nbPlumes;
                int food = 1;
                event.getDrops().clear();
                entity.getLocation().getWorld().dropItemNaturally(entity.getLocation(), new ItemStack(Material.FEATHER, feather));
                entity.getLocation().getWorld().dropItemNaturally(entity.getLocation(), new ItemStack(Material.COOKED_CHICKEN, food));
            }
            if (entity instanceof Pig) {
                int food = random.nextInt(3 - 1 + 1) + 1;
                event.getDrops().clear();
                entity.getLocation().getWorld().dropItemNaturally(entity.getLocation(), new ItemStack(Material.GRILLED_PORK, food));
            }
            if (entity instanceof Rabbit) {
                int hide = random.nextInt(2 - 1 + 1) + 1;
                int food = random.nextInt(2 - 1 + 1) + 1;
                event.getDrops().clear();
                entity.getLocation().getWorld().dropItemNaturally(entity.getLocation(), new ItemStack(Material.RABBIT_HIDE, hide));
                entity.getLocation().getWorld().dropItemNaturally(entity.getLocation(), new ItemStack(Material.COOKED_RABBIT, food));
            }
            if (entity instanceof Sheep) {
                int wool = 1;
                int food = random.nextInt(2 - 1 + 1) + 1;
                event.getDrops().clear();
                entity.getLocation().getWorld().dropItemNaturally(entity.getLocation(), new ItemStack(Material.WOOL, wool));
                entity.getLocation().getWorld().dropItemNaturally(entity.getLocation(), new ItemStack(Material.COOKED_MUTTON, food));
            }
        }
    }

    @EventHandler
    private void CutCleanOreDrops(BlockBreakEvent event) {
        if (Config.cutclean) {
            Block block = event.getBlock();
            if (block.getType().equals(Material.IRON_ORE)) {
                event.setCancelled(true);
                block.setType(Material.AIR);
                block.getLocation().getWorld().dropItemNaturally(block.getLocation(), new ItemStack(Material.IRON_INGOT, 1));
                ExperienceOrb orb = block.getWorld().spawn(block.getLocation(), ExperienceOrb.class);
                orb.setExperience(1);
            }
            if (block.getType().equals(Material.GOLD_ORE)) {
                event.setCancelled(true);
                block.setType(Material.AIR);
                block.getLocation().getWorld().dropItemNaturally(block.getLocation(), new ItemStack(Material.GOLD_INGOT, 1));
                ExperienceOrb orb = block.getWorld().spawn(block.getLocation(), ExperienceOrb.class);
                orb.setExperience(2);
            }
        }
    }

}
