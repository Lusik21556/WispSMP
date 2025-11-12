package me.Lusik21556.wispsmp.managers;

import me.Lusik21556.wispsmp.WispSMP;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;

import java.util.UUID;

public class LivesManager {

    private final WispSMP plugin;
    private final ConfigManager configManager;
    private final BanManager banManager;

    public LivesManager(WispSMP plugin) {
        this.plugin = plugin;
        this.configManager = plugin.getConfigManager();
        this.banManager = plugin.getBanManager();
    }

    public int getLives(Player player) {
        return configManager.getPlayerLives(player.getUniqueId());
    }

    public void setLives(Player player, int lives) {
        configManager.setPlayerLives(player.getUniqueId(), lives);
    }

    public void decrementLives(Player player) {
        int currentLives = getLives(player);
        setLives(player, currentLives - 1);
    }

    public void resetLives(Player player) {
        setLives(player, configManager.getMaxLives());
    }

    public boolean hasLives(Player player) {
        return getLives(player) > 0;
    }

    public void initializePlayer(Player player) {
        UUID uuid = player.getUniqueId();
        if (!plugin.getConfig().contains("players." + uuid + ".lives")) {
            setLives(player, configManager.getMaxLives());
        }
    }

    public void handleDeath(Player player) {
        decrementLives(player);

        if (!hasLives(player)) {
            if (configManager.useBanSystem()) {
                String banMessage = configManager.getBanMessage();
                banManager.banPlayer(player, banMessage);
            } else {
                player.setGameMode(GameMode.SPECTATOR);
            }
        }
    }
}