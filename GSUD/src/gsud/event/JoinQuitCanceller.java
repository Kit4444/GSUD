package gsud.event;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import at.mlps.gsud.main.Main;
import mysql.lb.MySQL;

public class JoinQuitCanceller implements Listener{
	
	@EventHandler
	public void onJoin(PlayerJoinEvent e) {
		Player p = e.getPlayer();
		e.setJoinMessage(null);
		p.sendMessage(Main.prefix + "§7Welcome " + p.getDisplayName() + " §7, you are now located on §6" + Bukkit.getServerName());
	}
	
	@EventHandler
	public void onQuit(PlayerQuitEvent e) {
		e.setQuitMessage(null);
		Player p = e.getPlayer();
		String uuid = p.getUniqueId().toString().replaceAll("-", "");
		SimpleDateFormat time = new SimpleDateFormat("dd/MM/yy - HH:mm:ss");
        String stime = time.format(new Date());
		Timestamp ts = new Timestamp(System.currentTimeMillis());
		long tss = ts.getTime();
		try {
			PreparedStatement ps = MySQL.getConnection().prepareStatement("UPDATE playerigid SET iscurrentlyingame = ?, lastOnlineTS = ?, lastOnline = ?, ifingamewhichserver = ? WHERE uuid = ?");
			ps.setBoolean(1, false);
			ps.setLong(2, tss);
			ps.setString(3, stime);
			ps.setString(4, Bukkit.getServerName());
			ps.setString(5, uuid);
			ps.executeUpdate();
		}catch (SQLException e1) {
		}
	}

}
