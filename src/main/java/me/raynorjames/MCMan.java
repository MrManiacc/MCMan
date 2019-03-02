package me.raynorjames;

import me.raynorjames.Commands.Commands;
import me.raynorjames.Events.PlayerListener;
import me.raynorjames.Utility.Log;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class MCMan extends JavaPlugin {
    private static Log logInstance;
    private static PluginManager pm;

    @Override
    public void onEnable() {
        pm = this.getServer().getPluginManager();
        logInstance = new Log(getDataFolder());
        logInstance.logNormal("MCMan started.");
        registerEvents();
        registerCommands();
    }

    @Override
    public void onDisable() {
        logInstance.logNormal("MCMan shutdown.");
    }

    private void registerEvents(){
        getServer().getPluginManager().registerEvents(new PlayerListener(this), this);
    }

    private void registerCommands(){
        getCommand("mcman").setExecutor(new Commands(getDataFolder()));
    }

    public static Log getLog(){
        return logInstance;
    }

    public static PluginManager getPm(){
        return pm;
    }

}
