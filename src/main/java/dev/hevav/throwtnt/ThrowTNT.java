package dev.hevav.throwtnt;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

public class ThrowTNT extends JavaPlugin implements CommandExecutor {
    public static FileConfiguration config;
    public static boolean toggle = false;

    @Override
    public void onEnable() {
        this.saveDefaultConfig();
        config = this.getConfig();

        this.getCommand("tnttoggle").setExecutor(this);

        Bukkit.getPluginManager().registerEvents(new TNTEvents(), this);
    }

    public boolean onCommand(CommandSender var1, Command var2, String var3, String[] var4){
        toggle = !toggle;
        return true;
    }
}