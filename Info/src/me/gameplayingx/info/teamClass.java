package me.gameplayingx.info;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class teamClass implements CommandExecutor {
	
	@SuppressWarnings("deprecation")
	public boolean onCommand(CommandSender sender, Command cmd, String cmdLabel, String[] args) {
		Player p = (Player) sender;
		if(args.length == 0 ) {
			p.sendMessage("§9▉▉▉▉▉▉▉▉▉▉▉▉§7[§4Server-Team§7]§9▉▉▉▉▉▉▉▉▉▉▉▉");
			if(Bukkit.getPlayer("GamePlayingX") != null) {
				p.sendMessage("§7[§4Owner§7] §4GamePlayingX §7: §2Online");
			} else if(Bukkit.getPlayer("GamePlayingX") == null) {
				p.sendMessage("§7[§4Owner§7] §4GamePlayingX §7: §cOffline");
			}
			if(Bukkit.getPlayer("Mecaanic") != null) {
				p.sendMessage("§7[§4Owner§7] §4Mecaanic §7: §2Online");
			} else if(Bukkit.getPlayer("Mecaanic") == null) {
				p.sendMessage("§7[§4Owner§7] §4Mecaanic §7: §cOffline");
			}
			if(Bukkit.getPlayer("Pit910") != null) {
				p.sendMessage("§7[§4Owner§7] §4Pit910 §7: §2Online");
			} else if(Bukkit.getPlayer("Pit910") == null) {
				p.sendMessage("§7[§4Owner§7] §4Pit910X §7: §cOffline");
			}
			if(Bukkit.getPlayer("mmgiller") != null) {
				p.sendMessage("§7[§5Moderator§7] §5mmgiller §7: §2Online");
			} else if(Bukkit.getPlayer("mmgiller") == null) {
				p.sendMessage("§7[§5Moderator§7] §5mmgiller §7: §cOffline");
			}
			if(Bukkit.getPlayer("Xoro123") != null) {
				p.sendMessage("§7[§5Moderator§7] §5Xoro123 §7: §2Online");
			} else if(Bukkit.getPlayer("Xoro123") == null) {
				p.sendMessage("§7[§5Moderator§7] §5Xoro123 §7: §cOffline");
				p.sendMessage("§9▉▉▉▉▉▉▉▉▉▉▉▉§7[§4Server-Team§7]§9▉▉▉▉▉▉▉▉▉▉▉▉");
			}
		} else {
			p.sendMessage("§4/team");
		}
		
		return true;
	}

}
