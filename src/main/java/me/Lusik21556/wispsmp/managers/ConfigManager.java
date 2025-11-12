package me.Lusik21556.wispsmp.managers;

import me.Lusik21556.wispsmp.WispSMP;
import org.bukkit.Location;
import org.bukkit.configuration.file.FileConfiguration;

import java.util.UUID;

public class ConfigManager {

    private final WispSMP plugin;
    private FileConfiguration config;

    public ConfigManager(WispSMP plugin) {
        this.plugin = plugin;
        plugin.saveDefaultConfig();
        this.config = plugin.getConfig();
    }

    public void reloadConfiguration() {
        plugin.reloadConfig();
        this.config = plugin.getConfig();
    }

    public int getPlayerLives(UUID uuid) {
        return config.getInt("players." + uuid + ".lives", 3);
    }

    public void setPlayerLives(UUID uuid, int lives) {
        config.set("players." + uuid + ".lives", lives);
        plugin.saveConfig();
    }

    public Location getSpawnLocation() {
        String worldName = config.getString("spawn.world", "world");
        double x = config.getDouble("spawn.x", 0.0);
        double y = config.getDouble("spawn.y", 64.0);
        double z = config.getDouble("spawn.z", 0.0);
        float yaw = (float) config.getDouble("spawn.yaw", 0.0);
        float pitch = (float) config.getDouble("spawn.pitch", 0.0);

        return new Location(plugin.getServer().getWorld(worldName), x, y, z, yaw, pitch);
    }

    public int getMaxLives() {
        return config.getInt("max-lives", 3);
    }

    public boolean useBanSystem() {
        return config.getBoolean("use-ban-system", false);
    }

    public String getBanMessage() {
        return config.getString("ban-message", "§cYou have run out of lives and have been banned from the server!");
    }
}