package at.mlps.gsud.main;

import java.io.File;
import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import gsud.event.JoinQuitCanceller;

public class Main extends JavaPlugin{
	
	public static String mysqlprefix = "§6MySQL §7» ";
	public static Main instance;
	public static String prefix = Prefix.returnPrefix("prefix");
	public static ArrayList<String> afk_list = new ArrayList<>();
	
	public void onEnable() {
		instance = this;
		File mysqlfile = new File("plugins/GSUD/config.yml");
		YamlConfiguration cfg = YamlConfiguration.loadConfiguration(mysqlfile);
		mysql.lb.MySQL.connect(cfg.getString("MySQL.Host"), cfg.getString("MySQL.Port"), cfg.getString("MySQL.Database"), cfg.getString("MySQL.User"), cfg.getString("MySQL.Password"));
		Updater.updater(0, 50);
		regCMD();
		regEvent();
	}
	
	public void onDisable() {
		instance = null;
	}
	
	private void regCMD() {
		
	}
	
	private void regEvent() {
		PluginManager pl = Bukkit.getPluginManager();
		pl.registerEvents(new JoinQuitCanceller(), this);
	}
}