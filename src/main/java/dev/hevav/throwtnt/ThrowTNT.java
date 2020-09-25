package dev.hevav.throwtnt;

import dev.hevav.throwtnt.commands.ConfigCommand;
import dev.hevav.throwtnt.commands.ToggleCommand;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

public class ThrowTNT extends JavaPlugin {
    public static FileConfiguration config;

    @Override
    public void onEnable() {
        this.saveDefaultConfig();
        config = this.getConfig();

        this.getCommand("tnttoggle").setExecutor(new ToggleCommand(this));
        this.getCommand("tntconfig").setExecutor(new ConfigCommand());
    }
}