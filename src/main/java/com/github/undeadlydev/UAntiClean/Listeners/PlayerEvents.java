package com.github.undeadlydev.UAntiClean.Listeners;

import java.util.concurrent.ThreadLocalRandom;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.plugin.Plugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import com.github.undeadlydev.UAntiClean.Main;

public class PlayerEvents implements Listener {
	public Main plugin;
	  
    public PlayerEvents(Main plugin) {
        this.plugin = plugin;
    }
    
    @EventHandler
    public void onKill(PlayerKilledEvent event) {
    	Player killer = event.getKiller();
    	if (event.hasKiller()) {
        	if (!Main.GetCfg().getStringList("DENY-WORLDS").contains(killer.getWorld().getName())) {
        		if (Main.GetCfg().getBoolean("RECEIVED_HEALING.NEED_PERMS")) {
            		if (killer.hasPermission("uanticlean.received.heal")) {
            			double to = killer.getHealth() + ThreadLocalRandom.current().nextDouble(3.5D, 5.5D);
                        killer.setHealth(Math.min(killer.getMaxHealth(), to));
            		}
        		} else {
        			double to = killer.getHealth() + ThreadLocalRandom.current().nextDouble(3.5D, 5.5D);
                    killer.setHealth(Math.min(killer.getMaxHealth(), to));
        		}
        	    if (Main.GetCfg().getBoolean("STRENGTH_EFFECT.Enable")) {
        	    	if (Main.GetCfg().getBoolean("STRENGTH_EFFECT.NEED_PERMS")) {
                        if (killer.hasPermission("uanticlean.received.strength")) {
                            ApplyEffect(killer);
                        }
            		} else {
            			ApplyEffect(killer);
            		}
        	    }
            }	
    	}
    }
    
    @EventHandler
    private void onJoinVoid(PlayerMoveEvent e) {
    	Location t = e.getTo();
    	Player p = e.getPlayer();
    	if (t.getBlockY() <= 0 && !p.isDead()) {
    		if (!Main.GetCfg().getBoolean("INSTAKILL_VOID.Enable")) {
    			return;
    		}
    		p.setHealth(0);
    	}
    }
    
    private void  ApplyEffect(Player player) {
    	int time = Main.GetCfg().getInt("STRENGTH_EFFECT.Duration");
    	int level = Main.GetCfg().getInt("STRENGTH_EFFECT.Level");
    	player.addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, Integer.valueOf(time) * 20 , Integer.valueOf(level) - 1));
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
