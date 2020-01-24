package at.mlps.gsud.main;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

public class RestartClass {
	
	public static void onRestart(int delay, int period) {
		new BukkitRunnable() {
			@Override
			public void run() {
				SimpleDateFormat time = new SimpleDateFormat("HH:mm:ss");
				String stime = time.format(new Date());
				for(Player all : Bukkit.getOnlinePlayers()) {
					if(stime.matches("22:00:00")) {
						Bukkit.broadcastMessage(Main.prefix + "§7The server is restarting in §9120 minutes.");
					}else if(stime.matches("22:30:00")) {
						Bukkit.broadcastMessage(Main.prefix + "§7The server is restarting in §990 minutes.");
					}else if(stime.matches("23:00:00")) {
						Bukkit.broadcastMessage(Main.prefix + "§7The server is restarting in §960 minutes.");
					}else if(stime.matches("23:30:00")) {
						Bukkit.broadcastMessage(Main.prefix + "§7The server is restarting in §930 minutes.");
					}else if(stime.matches("23:45:00")) {
						Bukkit.broadcastMessage(Main.prefix + "§7The server is restarting in §915 minutes.");
					}else if(stime.matches("23:55:00")) {
						Bukkit.broadcastMessage(Main.prefix + "§7The server is restarting in §95 minutes.");
					}else if(stime.matches("23:56:00")) {
						Bukkit.broadcastMessage(Main.prefix + "§7The server is restarting in §94 minutes.");
					}else if(stime.matches("23:57:00")) {
						Bukkit.broadcastMessage(Main.prefix + "§7The server is restarting in §93 minutes.");
					}else if(stime.matches("23:58:00")) {
						Bukkit.broadcastMessage(Main.prefix + "§7The server is restarting in §92 minutes.");
					}else if(stime.matches("23:59:00")) {
						Bukkit.broadcastMessage(Main.prefix + "§7The server is restarting in §91 minute.");
					}else if(stime.matches("23:59:30")) {
						Bukkit.broadcastMessage(Main.prefix + "§7The server is restarting in §c30 seconds.");
					}else if(stime.matches("23:59:45")) {
						Bukkit.broadcastMessage(Main.prefix + "§7The server is restarting in §c15 seconds.");
					}else if(stime.matches("23:59:50")) {
						Bukkit.broadcastMessage(Main.prefix + "§7The server is restarting in §c10 seconds.");
					}else if(stime.matches("23:59:55")) {
						all.getWorld().save();
						Bukkit.broadcastMessage(Main.prefix + "§7The server is restarting in §c5 seconds.");
					}else if(stime.matches("23:59:56")) {
						Bukkit.broadcastMessage(Main.prefix + "§7The server is restarting in §44 seconds.");
					}else if(stime.matches("23:59:57")) {
						Bukkit.broadcastMessage(Main.prefix + "§7The server is restarting in §43 seconds.");
					}else if(stime.matches("23:59:58")) {
						Bukkit.broadcastMessage(Main.prefix + "§7The server is restarting in §42 seconds.");
					}else if(stime.matches("23:59:59")) {
						Bukkit.broadcastMessage(Main.prefix + "§7The server is restarting in §41 second.");
					}else if(stime.matches("00:00:00")) {
						all.kickPlayer("§aRedi§cCraft\n \n§7You have been kicked by: §aSystem\n§7Reason: §aServer restart\n§7Additional Message: §aRejoin in few seconds.");
						Bukkit.shutdown();
					}
				}
			}
		}.runTaskTimerAsynchronously(Main.instance, delay, period);
	}
}