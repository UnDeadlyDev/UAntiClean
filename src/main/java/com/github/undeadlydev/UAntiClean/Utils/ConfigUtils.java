package com.github.undeadlydev.UAntiClean.Utils;

import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;

import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import com.github.undeadlydev.UAntiClean.Main;

public class ConfigUtils extends YamlConfiguration {
   private File File;
   private FileConfiguration FileC = null;
   private String Path;
   
   public void SaveDefault() {
      try {
         if (!this.File.exists()) {
            Main.getInt().saveResource(this.Path, false);
         }
      } catch (Exception var2) {
         ConsoleUtils.getError("&cSaveDefault Path: &9" + this.Path + " &cError Ex>> &f" + var2, true);
      }

   }

   public ConfigUtils(String var1) {
      this.Path = var1 + ".yml";
      this.File = new File(Main.getInt().getDataFolder(), this.Path);
      this.SaveDefault();
      this.Reload();
   }

   public void ReloadUFT8() {
      if (this.FileC == null) {
         this.File = new File(Main.getInt().getDataFolder(), this.Path);
      }

      this.FileC = YamlConfiguration.loadConfiguration(this.File);

      try {
         InputStreamReader var1 = new InputStreamReader(Main.getInt().getResource(this.Path), "UTF8");
         if (var1 != null) {
            YamlConfiguration var2 = YamlConfiguration.loadConfiguration(var1);
            this.FileC.setDefaults(var2);
         }
      } catch (UnsupportedEncodingException var3) {
         var3.printStackTrace();
      }

   }

   public void Reload() {
      try {
         super.load(this.File);
         this.ReloadUFT8();
      } catch (IOException | InvalidConfigurationException var2) {
    	  ConsoleUtils.getError("&cReload Path: &d" + this.Path + " &cError Ex>> &f" + var2, true);
      }

   }

   public void Save() {
      try {
         super.save(this.File);
      } catch (IOException var2) {
    	  ConsoleUtils.getError("&cSave Path: &c" + this.Path + " &cError Ex>> &f" + var2, true);
      }

   }
}
