package com.gameplayingx.splegg.misc;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import com.gameplayingx.splegg.Splegg;
import com.gameplayingx.splegg.games.Game;
import com.gameplayingx.splegg.players.UtilPlayer;

public class Chat {
	
	private String prefix = "�7[�3Splegg�7] �6";
	
	public String getPrefix() {
		return this.prefix;
	}
	
	public void log(String s) {
		Bukkit.getConsoleSender().sendMessage(prefix + s);
	}
	
	public void sendMessage(Player player, String s) {
		player.sendMessage(prefix + ChatColor.translateAlternateColorCodes('&', s));
	}

	public void bc(String s) {
		Bukkit.broadcastMessage(prefix + ChatColor.translateAlternateColorCodes('&', s));
	}
	
	public void bc(String string, Game game) {
		for (UtilPlayer u : Splegg.getSplegg().pm.PLAYERS.values()) {
			if (u.getGame() == game && u.isAlive()) {
				sendMessage(u.getPlayer(), string);
			}
		}
	}

	public void bcNotForPlayer(Player player, String string, Game game) {
		for (UtilPlayer u : Splegg.getSplegg().pm.PLAYERS.values()) {
			if (u.getGame() == game && u.isAlive()) {
				if (!(u.getPlayer() == player)) {
					sendMessage(u.getPlayer(), string);
				}
			}
		}
	}
	
}
