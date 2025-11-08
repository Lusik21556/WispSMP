package me.Lusik21556.wispsmp.managers;

import me.Lusik21556.wispsmp.WispSMP;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.Team;

public class NametagManager {

    private final WispSMP plugin;
    private final LivesManager livesManager;
    private final Scoreboard scoreboard;

    public NametagManager(WispSMP plugin) {
        this.plugin = plugin;
        this.livesManager = plugin.getLivesManager();
        this.scoreboard = Bukkit.getScoreboardManager().getMainScoreboard();
    }

    public void updateNametag(Player player) {
        String teamName = "wisp_" + player.getName();
        int lives = livesManager.getLives(player);

        Team team = scoreboard.getTeam(teamName);
        if (team != null) {
            team.unregister();
        }

        team = scoreboard.registerNewTeam(teamName);
        team.setSuffix(buildHeartSuffix(lives));
        team.addEntry(player.getName());

        applyScoreboardToAll();
    }

    private String buildHeartSuffix(int lives) {
        StringBuilder suffix = new StringBuilder(" ");
        int maxLives = plugin.getConfigManager().getMaxLives();

        for (int i = 0; i < maxLives; i++) {
            if (i < lives) {
                suffix.append("\uE001");
            } else {
                suffix.append("\uE002");
            }
        }

        return suffix.toString();
    }

    private void applyScoreboardToAll() {
        for (Player online : Bukkit.getOnlinePlayers()) {
            online.setScoreboard(scoreboard);
        }
    }

    public void removeNametag(Player player) {
        String teamName = "wisp_" + player.getName();
        Team team = scoreboard.getTeam(teamName);

        if (team != null) {
            team.unregister();
        }
    }
}