package dev.hevav.throwtnt;

import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.entity.TNTPrimed;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityExplodeEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.util.Vector;

import static dev.hevav.throwtnt.ThrowTNT.config;
import static dev.hevav.throwtnt.ThrowTNT.toggle;

public class TNTEvents implements Listener {

    @EventHandler(priority = EventPriority.NORMAL)
    public void onEvent(PlayerInteractEvent event){
        if(toggle
                && event.getPlayer().hasPermission("throwtnt.throw")
                && event.getPlayer().getInventory().getItemInMainHand().getTypeId() == config.getInt("idForThrow")){

            Location location = event.getPlayer().getLocation();
            TNTPrimed tnt = (TNTPrimed) location.getWorld().spawnEntity(location, EntityType.PRIMED_TNT);
            tnt.setFuseTicks(config.getInt("timeInSeconds") * 20);
            Vector tntVector = tnt.getVelocity();
            tnt.setVelocity(tntVector.multiply(config.getInt("velocity")));
        }
    }

    @EventHandler(priority = EventPriority.NORMAL)
    public void onEvent(EntityExplodeEvent event){
        if(toggle && event.getEntityType() == EntityType.PRIMED_TNT){
            event.setCancelled(true);

            int radius = config.getInt("killRadius");

            Location location = event.getLocation();
            location.getWorld().getNearbyEntities(location, radius, radius, radius).forEach(entity -> {
                if(entity instanceof Player){
                    Player player = (Player) entity;
                    player.setGameMode(GameMode.SPECTATOR);
                }
            });
        }
    }
}
