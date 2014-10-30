package me.gameplayingx.info;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Skype implements CommandExecutor {
	
	
	public boolean onCommand(CommandSender sender, Command cmd, String cmdLabel, String[] args) {
		Player p = (Player) sender;
		if(cmd.getName().equalsIgnoreCase("Skype"));
		   p.sendMessage("§9▉▉▉▉▉▉▉▉▉▉▉▉§7[§bSkype§7]§9▉▉▉▉▉▉▉▉▉▉▉▉");
		   p.sendMessage("§7GameplayingX§8: §4Max12185");
		   p.sendMessage("§7Mecaanic§8: §4dennis80621");
		   p.sendMessage("§7Pit910§8: §4Pieter905");
		   p.sendMessage("§9§9▉▉▉▉▉▉▉▉▉▉▉▉§7[§bSkype§7]§9▉▉▉▉▉▉▉▉▉▉▉▉");
		return true;
	}

}
