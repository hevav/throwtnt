package dev.hevav.throwtnt.events;

import dev.hevav.throwtnt.ThrowTNT;
import org.bukkit.*;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.entity.TNTPrimed;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityExplodeEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerMoveEvent;

import static dev.hevav.throwtnt.ThrowTNT.config;

public class TNTEvents implements Listener {
    private final ThrowTNT plugin;

    public TNTEvents(ThrowTNT plugin) {
        this.plugin = plugin;
    }

    @EventHandler(priority = EventPriority.NORMAL)
    public void onEvent(PlayerInteractEvent event) {
        Player player = event.getPlayer();

        if (player.hasPermission("throwtnt.throw")
                && player.getInventory().getItemInMainHand().getTypeId() == config.getInt("idForThrow")) {

            event.setCancelled(true);

            Location location = player.getLocation();
            TNTPrimed tnt = (TNTPrimed) location.getWorld().spawnEntity(location.clone().add(0, 0.3, 0), EntityType.PRIMED_TNT);
            tnt.setFuseTicks(config.getInt("timeInSeconds") * 20);

            tnt.setVelocity(location.getDirection().multiply(config.getInt("velocity") * 0.3f));
        }
    }

    @EventHandler(priority = EventPriority.NORMAL)
    public void onEvent(EntityExplodeEvent event) {
        if (event.getEntityType() == EntityType.PRIMED_TNT) {
            event.setCancelled(true);

            int radius = config.getInt("killRadius");

            Location location = event.getLocation();
            location.getWorld().getNearbyEntities(location, radius, radius, radius).forEach(entity -> {
                if (entity instanceof Player) {
                    entity.setVelocity(
                            entity.getLocation()
                                    .subtract(event.getEntity().getLocation())
                                    .toVector()
                                    .multiply(config.getInt("playerVelocity")));
                }
            });
        }
    }

    @EventHandler(priority = EventPriority.NORMAL)
    public void onEvent(PlayerMoveEvent event) {
        Player player = event.getPlayer();

        if (player.getGameMode() == GameMode.SURVIVAL && event.getTo().getBlock().getType() == Material.STATIONARY_WATER) {
            player.setGameMode(GameMode.SPECTATOR);
            player.sendTitle(
                    ChatColor.GOLD + config.getString("strings.dieTitle"),
                    config.getString("strings.dieSubtitle"),
                    10, 40, 10);

            Bukkit.getServer().broadcastMessage(String.format(config.getString("strings.f"), ChatColor.GOLD, player.getName()));
        }
    }
}
