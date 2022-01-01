package com.github.undeadlydev.UAntiClean.Listeners;

import java.util.concurrent.ThreadLocalRandom;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.plugin.Plugin;

import com.github.undeadlydev.UAntiClean.Main;

public class PlayerEvents implements Listener {
	public Main plugin;
	  
    public PlayerEvents(Main plugin) {
        this.plugin = plugin;
    }
    
    @EventHandler
    public void onKill(PlayerKilledEvent event) {
    	Player killer = event.getKiller();
    	if (!Main.GetCfg().getStringList("DENY-WORLDS").contains(killer.getWorld().getName())) {
            if (event.hasKiller()) {
        	    double to = killer.getHealth() + ThreadLocalRandom.current().nextDouble(3.5D, 5.5D);
                killer.setHealth(Math.min(killer.getMaxHealth(), to));
            }
        }
    }
    
    @EventHandler
    public void onDeathbyPlayer(PlayerDeathEvent e) {
        Player player = e.getEntity();
        Player player1 = player.getKiller();
        if (player1 instanceof Player && !player1.isDead() && player1 != player) {
            Player killer = player1;
            Bukkit.getScheduler().runTask((Plugin)this.plugin, () -> Bukkit.getPluginManager().callEvent((Event)new PlayerKilledEvent(player, killer, (Event)e)));
            return;
        }
        Bukkit.getScheduler().runTask((Plugin)this.plugin, () -> Bukkit.getPluginManager().callEvent((Event)new PlayerKilledEvent(player, null, (Event)e)));
    }
}
