package me.Lusik21556.wispsmp.commands;

import me.Lusik21556.wispsmp.WispSMP;
import me.Lusik21556.wispsmp.managers.BanManager;
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
    private final BanManager banManager;

    public ReviveCommand(WispSMP plugin) {
        this.plugin = plugin;
        this.livesManager = plugin.getLivesManager();
        this.nametagManager = plugin.getNametagManager();
        this.configManager = plugin.getConfigManager();
        this.banManager = plugin.getBanManager();
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

        String targetName = args[0];

        if (banManager.isBanned(targetName)) {
            banManager.unbanPlayer(targetName);
            sender.sendMessage(ChatColor.GREEN + "Unbanned " + targetName + "!");
        }

        Player target = Bukkit.getPlayer(targetName);

        if (target != null) {
            revivePlayer(target);
            sender.sendMessage(ChatColor.GREEN + "Revived " + target.getName() + "!");
            target.sendMessage(ChatColor.GREEN + "You have been revived!");
        } else {
            sender.sendMessage(ChatColor.YELLOW + "Player is offline, but has been unbanned and will be revived on next join.");
        }

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