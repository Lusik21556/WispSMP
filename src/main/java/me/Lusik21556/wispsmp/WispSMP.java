package me.Lusik21556.wispsmp;

import me.Lusik21556.wispsmp.commands.ReviveCommand;
import me.Lusik21556.wispsmp.listeners.PlayerListener;
import me.Lusik21556.wispsmp.managers.BanManager;
import me.Lusik21556.wispsmp.managers.ConfigManager;
import me.Lusik21556.wispsmp.managers.LivesManager;
import me.Lusik21556.wispsmp.managers.NametagManager;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public class WispSMP extends JavaPlugin {

    private static WispSMP instance;
    private ConfigManager configManager;
    private LivesManager livesManager;
    private NametagManager nametagManager;
    private BanManager banManager;

    @Override
    public void onEnable() {
        instance = this;

        printBanner();

        initializeManagers();
        registerListeners();
        registerCommands();

        getLogger().info("Plugin successfully enabled!");
    }

    @Override
    public void onDisable() {
        getLogger().info("Plugin disabled!");
        instance = null;
    }

    private void printBanner() {
        Bukkit.getConsoleSender().sendMessage("");
        Bukkit.getConsoleSender().sendMessage(".____                 .__ __   ________  ____ .________.________ ________");
        Bukkit.getConsoleSender().sendMessage("|    |    __ __  _____|__|  | _\\_____  \\/_   ||   ____/|   ____//  _____/");
        Bukkit.getConsoleSender().sendMessage("|    |   |  |  \\/  ___/  |  |/ //  ____/ |   ||____  \\ |____  \\/   __  \\ ");
        Bukkit.getConsoleSender().sendMessage("|    |___|  |  /\\___ \\|  |    </       \\ |   |/       \\/       \\  |__\\  \\");
        Bukkit.getConsoleSender().sendMessage("|_______ \\____//____  >__|__|_ \\_______ \\|___/______  /______  /\\_____  /");
        Bukkit.getConsoleSender().sendMessage("        \\/          \\/        \\/       \\/           \\/       \\/       \\/ ");
        Bukkit.getConsoleSender().sendMessage("");
        Bukkit.getConsoleSender().sendMessage("Plugin made by §bLusik21556 §7(§3@lusik_2155§7)");
        Bukkit.getConsoleSender().sendMessage("");
    }

    private void initializeManagers() {
        configManager = new ConfigManager(this);
        banManager = new BanManager(this);
        livesManager = new LivesManager(this);
        nametagManager = new NametagManager(this);
    }

    private void registerListeners() {
        getServer().getPluginManager().registerEvents(new PlayerListener(this), this);
    }

    private void registerCommands() {
        getCommand("revive").setExecutor(new ReviveCommand(this));
    }

    public static WispSMP getInstance() {
        return instance;
    }

    public ConfigManager getConfigManager() {
        return configManager;
    }

    public LivesManager getLivesManager() {
        return livesManager;
    }

    public NametagManager getNametagManager() {
        return nametagManager;
    }

    public BanManager getBanManager() {
        return banManager;
    }
}