package com.github.undeadlydev.UAntiClean.Utils;

import org.bukkit.Bukkit;

public class ConsoleUtils {
    public static void getLoggs(String paramString, boolean paramBoolean) {
	    if (paramBoolean) {
	        Bukkit.getConsoleSender().sendMessage(ChatUtils.colorCodes("&7[&bUAntiClean&7] &r" + paramString));
		} else if (!paramBoolean) {
		    Bukkit.getConsoleSender().sendMessage(ChatUtils.colorCodes(paramString));
		}
	}

    public static void getError(String paramString, boolean paramBoolean) {
	    if (paramBoolean) {
	        Bukkit.getConsoleSender().sendMessage(ChatUtils.colorCodes("&cERROR &r" + paramString));
		} else if (!paramBoolean) {
		    Bukkit.getConsoleSender().sendMessage(ChatUtils.colorCodes(paramString));
		}
	}
}
