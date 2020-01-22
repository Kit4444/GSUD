package at.mlps.gsud.main;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import com.hotmail.steven.bconomy.account.AccountData;

import mysql.lb.MySQL;
import net.md_5.bungee.api.ChatColor;
import net.minecraft.server.v1_12_R1.MinecraftServer;
import ru.tehkode.permissions.PermissionUser;
import ru.tehkode.permissions.bukkit.PermissionsEx;

public class Updater {
	
	public static void updater(int delay, int period) {
		new BukkitRunnable() {
			@SuppressWarnings("deprecation")
			@Override
			public void run() {
				if(MySQL.isConnected()) {
					Runtime run = Runtime.getRuntime();
					long ram = (run.totalMemory() - run.freeMemory()) / 1048576L;
					long allocram = run.totalMemory() / 1048576L;
					String server = Bukkit.getServerName();
					String sid = Bukkit.getServerId();
					String sver = Bukkit.getBukkitVersion();
					String ssver = sver.substring(0, 6);
					int players = Bukkit.getOnlinePlayers().size();
					int playersmax = Bukkit.getMaxPlayers();
					Timestamp ts = new Timestamp(System.currentTimeMillis());
					long tss = ts.getTime();
					SimpleDateFormat time = new SimpleDateFormat("dd.MM.yy - HH:mm:ss");
					String date = time.format(new Date());
					StringBuilder sb = new StringBuilder("");
					for(double tps : MinecraftServer.getServer().recentTps) {
						sb.append(format(tps));
					}
					String str = sb.substring(0, sb.length() - 1);
					String str2;
					if(str.startsWith("*", 2)) {
						str2 = str.substring(3, 7);
					}else {
						str2 = str.substring(2, 6);
					}
					try {
						PreparedStatement pps = MySQL.getConnection().prepareStatement("UPDATE serverstats SET tps = ?, ram = ?, serverid = ?, currentPlayers = ?, maxPlayers = ?, lastupdated = ?, serverversion = ?, lastUpdatedString = ?, allocram = ? WHERE server = ?");
						pps.setString(10, server);
						pps.setString(1, str2);
						pps.setInt(2, (int)ram);
						pps.setString(3, sid);
						pps.setInt(4, players);
						pps.setInt(5, playersmax);
						pps.setLong(6, tss);
						pps.setString(7, ssver);
						pps.setString(8, date);
						pps.setInt(9, (int)allocram);
						pps.executeUpdate();
						Bukkit.getConsoleSender().sendMessage(Main.prefix + "§7DB-Stats updated!");
					}catch (SQLException e) {
						e.printStackTrace();
						Bukkit.getConsoleSender().sendMessage(Main.prefix + "§7DB-Stats not updated!");
					}
					for(Player all : Bukkit.getOnlinePlayers()) {
						try {
							String uuid = all.getUniqueId().toString().replaceAll("-", "");
							PermissionUser po = PermissionsEx.getUser(all);
								int moneten = AccountData.getAccountBalance(all.getUniqueId().toString(), "default");
								PreparedStatement ps = MySQL.getConnection().prepareStatement("UPDATE playerigid SET rank = ?, money = ?, ifingamewhichserver = ?, iscurrentlyafk = ? WHERE uuid = ?");
								ps.setString(5, uuid);
								if (po.inGroup("Developer")) {
								    ps.setString(1, "Developer");
								}else if (po.inGroup("Projectmanager")) {
								    ps.setString(1, "Projectmanager");
								}else if (po.inGroup("CMan")) {
									ps.setString(1, "Community Manager");
								}else if (po.inGroup("AMan")) {
								    ps.setString(1, "Administrations Manager");
								}else if (po.inGroup("Admin")) {
								    ps.setString(1, "Administrator");
								}else if (po.inGroup("Support")) {
								    ps.setString(1, "Support Team");
								}else if (po.inGroup("Mod")) {
								    ps.setString(1, "Moderator");
								}else if (po.inGroup("Builder")) {
								    ps.setString(1, "Builder");
								}else if (po.inGroup("RediFMTeam")) {
								    ps.setString(1, "RediFM Team");
								}else if (po.inGroup("RLTM")) {
								    ps.setString(1, "Retired Legend Team Member");
								}else if (po.inGroup("RTM")) {
								    ps.setString(1, "Retired Team Member");
								}else if (po.inGroup("Beta")) {
								    ps.setString(1, "Beta");
								}else if (po.inGroup("Friend")) {
								    ps.setString(1, "Friend");
								}else {
								    ps.setString(1, "User");
								}
								ps.setInt(2, moneten);
								ps.setString(3, "Lobby");
								if(Main.afk_list.contains(all.getName())) {
									ps.setBoolean(4, true);
								}else {
									ps.setBoolean(4, false);
								}
								ps.executeUpdate();
						}catch (SQLException sql) {
							sql.printStackTrace();
						}
					}
				}else {
					Bukkit.getConsoleSender().sendMessage(Main.prefix + "§cDB is not connected.");
				}
			}
		}.runTaskTimerAsynchronously(Main.instance, delay, period);
	}
	
	private static String format(double tps) {
		return String.valueOf((tps > 18.0 ? ChatColor.GREEN : (tps > 16.0 ? ChatColor.YELLOW : ChatColor.RED)).toString()) + (tps > 20.0 ? "*" : "") + Math.min((double)Math.round(tps * 100.0) / 100.0, 20.0);
	}
}