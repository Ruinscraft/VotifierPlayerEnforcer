package com.ruinscraft.votifierplayerenforcer;

import com.vexsoftware.votifier.model.Vote;
import com.vexsoftware.votifier.model.VotifierEvent;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

import java.lang.reflect.Field;

public class VotifierPlayerEnforcerPlugin extends JavaPlugin implements Listener {

    @Override
    public void onEnable() {
        getServer().getPluginManager().registerEvents(this, this);
    }

    // if the user hasn't played before, don't show the username
    @EventHandler(priority = EventPriority.MONITOR)
    public void onVote(VotifierEvent event) {
        String username = event.getVote().getUsername();

        if (Bukkit.getOfflinePlayer(username) == null) {
            try {
                stripUsernameFromVoteEvent(event);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private void stripUsernameFromVoteEvent(VotifierEvent event) throws Exception {
        Vote vote = event.getVote();
        vote.setUsername("?");
        Field voteField = event.getClass().getField("vote");
        voteField.setAccessible(true);
        voteField.set(event, vote);
    }

}
