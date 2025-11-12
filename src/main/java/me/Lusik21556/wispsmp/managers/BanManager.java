package me.Lusik21556.wispsmp.managers;

import me.Lusik21556.wispsmp.WispSMP;
import org.bukkit.BanList;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.time.Instant;

public class BanManager {

    private final WispSMP plugin;

    public BanManager(WispSMP plugin) {
        this.plugin = plugin;
    }

    public void banPlayer(Player player, String reason) {
        BanList banList = Bukkit.getBanList(BanList.Type.NAME);

        banList.addBan(player.getName(), reason, (Instant) null, "WispSMP");

        player.kickPlayer(reason);
    }

    public void unbanPlayer(String playerName) {
        BanList banList = Bukkit.getBanList(BanList.Type.NAME);
        banList.pardon(playerName);
    }

    public boolean isBanned(String playerName) {
        BanList banList = Bukkit.getBanList(BanList.Type.NAME);
        return banList.isBanned(playerName);
    }
}