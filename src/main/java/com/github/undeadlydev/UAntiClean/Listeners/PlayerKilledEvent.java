package com.github.undeadlydev.UAntiClean.Listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public class PlayerKilledEvent extends Event {
    private final Event event;
    private final Player player;
    private final Player killer;
  
    public PlayerKilledEvent(Player player, Player killer, Event event) {
        this.player = player;
       this.killer = killer;
       this.event = event;
    }
  
    public PlayerKilledEvent(Player player, Player killer) {
        this(player, killer, null);
    }
  
    public Player getPlayer() {
        return this.player;
    }
  
    public Player getKiller() {
        return this.killer;
    }
  
    public boolean hasKiller() {
        return (this.killer != null);
    }
  
    public Event getEvent() {
        return this.event;
    }
  
    private static final HandlerList handlers = new HandlerList();
  
    public HandlerList getHandlers() {
        return handlers;
    }
  
    public static HandlerList getHandlerList() {
        return handlers;
    }
}