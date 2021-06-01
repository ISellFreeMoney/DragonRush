package fr.ziberty.dragonrush;

import fr.ziberty.dragonrush.commands.basicCommands;
import fr.ziberty.dragonrush.configuration.Config;
import fr.ziberty.dragonrush.configuration.Loots;
import fr.ziberty.dragonrush.scenarios.CutClean;
import fr.ziberty.dragonrush.teams.Teams;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.plugin.java.JavaPlugin;

public class DragonRush extends JavaPlugin {
    @Override
    public void onEnable() {
        Bukkit.broadcastMessage("Plugin correctement initialisé.");
        getServer().getPluginManager().registerEvents(new PluginListener(this), this);
        getServer().getPluginManager().registerEvents(new DragonPacts(this), this);
        getServer().getPluginManager().registerEvents(new Config(this), this);
        getServer().getPluginManager().registerEvents(new CutClean(this), this);
        getServer().getPluginManager().registerEvents(new Loots(this), this);
        getCommand("aide").setExecutor(new basicCommands());
        getCommand("force").setExecutor(new basicCommands());
        getCommand("t").setExecutor(new basicCommands());
        getCommand("coords").setExecutor(new basicCommands());
        getCommand("eyes").setExecutor(new basicCommands());
        CustomCrafting crafting = new CustomCrafting();
        crafting.customCraft();

        Teams.clearTeams();
        PluginListener.hasSleptPlayers.clear();
        spawnMaker();
    }

    @Override
    public void onDisable() {
        Teams.clearTeams();
    }

    public void spawnMaker() {
        int x = 0;
        int y = 0;
        int z = 0;
        if (Bukkit.getWorld("world").getBlockAt(-25, 149, -25).getType() == Material.AIR){
            for (x = -25; x <= 25; x++)
                for (z = -25; z <= 25; z++)
                    Bukkit.getWorld("world").getBlockAt(x, 149, z).setType(Material.BARRIER);
            for (x = -25; x <= 24; x++)
                for (y = 150; y <= 200; y++)
                    Bukkit.getWorld("world").getBlockAt(x, y, -25).setType(Material.BARRIER);
            for (z = -25; z <= 25; z++)
                for (y = 150; y <= 200; y++)
                    Bukkit.getWorld("world").getBlockAt(-25, y, z).setType(Material.BARRIER);
            for (z = -25; z <= 25; z++)
                for (y = 150; y <= 200; y++)
                    Bukkit.getWorld("world").getBlockAt(25, y, z).setType(Material.BARRIER);
            for (x = -25; x <= 25; x++)
                for (y = 150; y <= 200; y++)
                    Bukkit.getWorld("world").getBlockAt(x, y, 25).setType(Material.BARRIER);

        }

    }
}
