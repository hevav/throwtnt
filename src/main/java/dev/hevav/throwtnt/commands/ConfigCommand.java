package dev.hevav.throwtnt.commands;

import dev.hevav.throwtnt.ThrowTNT;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class ConfigCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender var1, Command var2, String var3, String[] var4) {
        if (var4.length < 5)
            return false;

        try {
            int idForThrow = Integer.parseInt(var4[0]);
            int timeInSeconds = Integer.parseInt(var4[1]);
            int velocity = Integer.parseInt(var4[2]);
            int killRadius = Integer.parseInt(var4[3]);
            int playerVelocity = Integer.parseInt(var4[4]);

            ThrowTNT.config.set("idForThrow", idForThrow);
            ThrowTNT.config.set("timeInSeconds", timeInSeconds);
            ThrowTNT.config.set("velocity", velocity);
            ThrowTNT.config.set("killRadius", killRadius);
            ThrowTNT.config.set("playerVelocity", playerVelocity);

            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
