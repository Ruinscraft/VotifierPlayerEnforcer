package com.ruinscraft.votifierplayerenforcer;

import com.vexsoftware.votifier.model.VotifierEvent;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

public class VotifierPlayerEnforcerPlugin extends JavaPlugin implements Listener {

    @Override
    public void onEnable() {
        getServer().getPluginManager().registerEvents(this, this);
    }

    // if the user hasn't played before, don't show the username
    @EventHandler
    public void onVote(VotifierEvent event) {
        String username = event.getVote().getUsername();

        if (Bukkit.getOfflinePlayer(username) == null) {
            event.getVote().setUsername("?");
        }
    }

}
