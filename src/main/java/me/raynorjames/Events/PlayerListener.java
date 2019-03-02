package me.raynorjames.Events;

import me.raynorjames.MCMan;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.plugin.Plugin;

public class PlayerListener implements Listener {
    private Plugin plugin;

    public PlayerListener(Plugin plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onChatEvent(AsyncPlayerChatEvent chatEvent){
        MCMan.getLog().logNormal(chatEvent.getPlayer().getDisplayName() + " said " + chatEvent.getMessage());
    }

    @EventHandler
    public void onCommand(PlayerCommandPreprocessEvent chatEvent){
        MCMan.getLog().logNormal(chatEvent.getPlayer().getDisplayName() + " used command " + chatEvent.getMessage());
    }



}
