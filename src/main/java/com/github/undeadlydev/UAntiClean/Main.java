package com.github.undeadlydev.UAntiClean;

import org.bukkit.event.Listener;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import com.github.undeadlydev.UAntiClean.Listeners.PlayerEvents;
import com.github.undeadlydev.UAntiClean.Utils.ConfigUtils;
import com.github.undeadlydev.UAntiClean.Utils.ConsoleUtils;

public class Main extends JavaPlugin implements Listener {
	private static Main instance;
	
	private static ConfigUtils config;
	
    public static ConfigUtils GetCfg() {
        return config;
    }
    
    public static JavaPlugin getInt() {
        return (JavaPlugin)instance;
    }
    
	public void onEnable() {
		instance = this;
		ConsoleUtils.getLoggs("&eLoading Configs...", true);
		config = new ConfigUtils("Config");
		ConsoleUtils.getLoggs("&eLoading Events...", true);
        getServer().getPluginManager().registerEvents((Listener)this, (Plugin)this);
        PluginManager pm = getServer().getPluginManager();
        pm.registerEvents((Listener)new PlayerEvents(this), (Plugin)this);
        ConsoleUtils.getLoggs("&7-----------------------------------", true);
        ConsoleUtils.getLoggs(" ", true);
        ConsoleUtils.getLoggs("&fServer: &c" + getServer().getVersion() + " " , true);
        ConsoleUtils.getLoggs("&fSuccessfully Plugin &aEnabled! &cv" + getDescription().getVersion(), true);
        ConsoleUtils.getLoggs("&fCreator: &eBrunoAvixdub", true);
        ConsoleUtils.getLoggs("&fThanks for use my plugin :D", true);
        ConsoleUtils.getLoggs(" ", true);
        ConsoleUtils.getLoggs("&7-----------------------------------", true);
	}
	
    public void onDisable() {
    	ConsoleUtils.getLoggs("&7-----------------------------------", true);
    	ConsoleUtils.getLoggs("", true);
    	ConsoleUtils.getLoggs("&fSuccessfully Plugin &cDisable!", true);
    	ConsoleUtils.getLoggs("&fCreator: &eBrunoAvixdub", true);
    	ConsoleUtils.getLoggs("&fThanks for use my plugin :D", true);
    	ConsoleUtils.getLoggs("", true);
    	ConsoleUtils.getLoggs("&7-----------------------------------", true);
    }
    
}
