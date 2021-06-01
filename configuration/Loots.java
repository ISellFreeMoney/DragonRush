package fr.ziberty.dragonrush.configuration;

import fr.ziberty.dragonrush.DragonRush;
import org.bukkit.Material;
import org.bukkit.entity.Chicken;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Skeleton;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.inventory.ItemStack;

import java.util.Random;

public class Loots implements Listener {

    private DragonRush main;

    public Loots (DragonRush dragonRush) {
        this.main = dragonRush;
    }

    @EventHandler
    private void onMobDeath(EntityDeathEvent event) {
        Entity entity = event.getEntity();
        Random random = new Random();
        if (entity instanceof Chicken && !Config.cutclean) {
            event.getDrops().clear();
            entity.getLocation().getWorld().dropItemNaturally(entity.getLocation(), new ItemStack(Material.FEATHER, Config.nbPlumes));
            entity.getLocation().getWorld().dropItemNaturally(entity.getLocation(), new ItemStack(Material.RAW_CHICKEN, 1));
        }
        if (entity instanceof Skeleton) {
            event.getDrops().clear();
            int bones = random.nextInt(2 + 1);
            entity.getLocation().getWorld().dropItemNaturally(entity.getLocation(), new ItemStack(Material.ARROW, Config.nbFleches));
            entity.getLocation().getWorld().dropItemNaturally(entity.getLocation(), new ItemStack(Material.BONE, bones));
        }
    }

}
