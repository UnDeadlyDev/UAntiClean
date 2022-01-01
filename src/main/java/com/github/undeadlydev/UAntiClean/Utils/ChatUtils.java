package com.github.undeadlydev.UAntiClean.Utils;

import org.bukkit.ChatColor;

public class ChatUtils {

    public static String colorCodes(String nonColoredText) {
        if (nonColoredText == null)
            return nonColoredText; 
        if (nonColoredText.isEmpty())
            return nonColoredText; 
        if (nonColoredText.length() <= 0)
            return nonColoredText; 
        if (nonColoredText == "")
            return nonColoredText; 
        if (nonColoredText == " ")
            return nonColoredText; 
        String coloredText = ChatColor.translateAlternateColorCodes('&', nonColoredText);
        return coloredText;
    }
}
