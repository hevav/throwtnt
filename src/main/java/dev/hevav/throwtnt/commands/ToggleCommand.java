package dev.hevav.throwtnt.commands;

import dev.hevav.throwtnt.ThrowTNT;
import dev.hevav.throwtnt.events.TNTEvents;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.event.HandlerList;

public class ToggleCommand implements CommandExecutor {
    private final ThrowTNT plugin;
    private boolean toggle = false;

    public ToggleCommand(ThrowTNT plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender var1, Command var2, String var3, String[] var4) {
        toggle = !toggle;

        if (toggle) {
            Bukkit.getPluginManager().registerEvents(new TNTEvents(plugin), plugin);
        } else {
            HandlerList.unregisterAll(plugin);
        }
        return true;
    }
}
