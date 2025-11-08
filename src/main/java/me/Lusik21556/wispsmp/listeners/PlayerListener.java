package me.Lusik21556.wispsmp.listeners;

import me.Lusik21556.wispsmp.WispSMP;
import me.Lusik21556.wispsmp.managers.LivesManager;
import me.Lusik21556.wispsmp.managers.NametagManager;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerJoinEvent;

public class PlayerListener implements Listener {

    private final WispSMP plugin;
    private final LivesManager livesManager;
    private final NametagManager nametagManager;

    public PlayerListener(WispSMP plugin) {
        this.plugin = plugin;
        this.livesManager = plugin.getLivesManager();
        this.nametagManager = plugin.getNametagManager();
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();

        livesManager.initializePlayer(player);
        nametagManager.updateNametag(player);
    }

    @EventHandler
    public void onPlayerDeath(PlayerDeathEvent event) {
        Player player = event.getEntity();

        livesManager.handleDeath(player);

        if (!livesManager.hasLives(player)) {
            Bukkit.broadcastMessage(ChatColor.RED + player.getName() + " has died");
        }

        nametagManager.updateNametag(player);
    }
}