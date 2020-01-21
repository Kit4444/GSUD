package at.mlps.gsud.main;

import java.io.File;

import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin{
	
	public static String mysqlprefix = "§6MySQL §7» ";
	public static Main instance;
	
	public void onEnable() {
		instance = this;
		File mysqlfile = new File("plugins/GSUD/config.yml");
		YamlConfiguration cfg = YamlConfiguration.loadConfiguration(mysqlfile);
		mysql.lb.MySQL.connect(cfg.getString("MySQL.Host"), cfg.getString("MySQL.Port"), cfg.getString("MySQL.Database"), cfg.getString("MySQL.User"), cfg.getString("MySQL.Password"));
	}
	
	public void onDisable() {
		instance = null;
	}

}