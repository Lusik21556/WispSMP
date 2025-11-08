package me.Lusik21556.wispsmp.commands;

import me.Lusik21556.wispsmp.WispSMP;
import me.Lusik21556.wispsmp.managers.ConfigManager;
import me.Lusik21556.wispsmp.managers.LivesManager;
import me.Lusik21556.wispsmp.managers.NametagManager;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class ReviveCommand implements CommandExecutor {

    private final WispSMP plugin;
    private final LivesManager livesManager;
    private final NametagManager nametagManager;
    private final ConfigManager configManager;

    public ReviveCommand(WispSMP plugin) {
        this.plugin = plugin;
        this.livesManager = plugin.getLivesManager();
        this.nametagManager = plugin.getNametagManager();
        this.configManager = plugin.getConfigManager();
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!sender.hasPermission("wispsmp.revive")) {
            sender.sendMessage(ChatColor.RED + "You don't have permission to use this command!");
            return true;
        }

        if (args.length != 1) {
            sender.sendMessage(ChatColor.RED + "Usage: /revive <playername>");
            return true;
        }

        Player target = Bukkit.getPlayer(args[0]);

        if (target == null) {
            sender.sendMessage(ChatColor.RED + "Player not found!");
            return true;
        }

        revivePlayer(target);

        sender.sendMessage(ChatColor.GREEN + "Revived " + target.getName() + "!");
        target.sendMessage(ChatColor.GREEN + "You have been revived!");

        return true;
    }

    private void revivePlayer(Player player) {
        livesManager.resetLives(player);
        player.setGameMode(GameMode.SURVIVAL);

        Location spawnLocation = configManager.getSpawnLocation();
        player.teleport(spawnLocation);

        nametagManager.updateNametag(player);
    }
}