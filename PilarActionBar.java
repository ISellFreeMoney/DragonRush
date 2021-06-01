package fr.ziberty.dragonrush;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.util.Vector;

public class PilarActionBar {

    public static StringBuilder PilarBar(Player p) {
        StringBuilder sb = new StringBuilder();
        sb.setLength(0);
        Location loc = new Location(Bukkit.getWorld("world"), PluginListener.pilierx, PluginListener.piliery, PluginListener.pilierz);
        if (p.getWorld().getName().equalsIgnoreCase("world")) {
            Vector vector = loc.clone().subtract(p.getLocation()).toVector();
            Vector playerDirection = p.getLocation().getDirection();
            double x1 = vector.getX();
            double z1 = vector.getZ();
            double x2 = playerDirection.getX();
            double z2 = playerDirection.getZ();
            double angle = Math.atan2(x1*z2-z1*x2, x1*x2+z1*z2)*180/Math.PI;
            String arrow = "•";

            if (Math.round(p.getLocation().distance(loc)) == 0) {
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
            sb.append("§fPillier §b" + Math.round(p.getLocation().distance(loc)) + " ");
            sb.append(arrow);
        }
        return sb;
    }
}
