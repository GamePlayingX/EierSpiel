package me.gameplayingx.info;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Teamspeak3 implements CommandExecutor {
	
	
	public boolean onCommand(CommandSender sender, Command cmd, String cmdLabel, String[] args) {
		Player p = (Player) sender;
		if(cmd.getName().equalsIgnoreCase("ts3"));
		   p.sendMessage("§9▉▉▉▉▉▉▉▉▉▉▉▉§7[§bTeamspeak3§7]§9▉▉▉▉▉▉▉▉▉▉▉▉");
		   p.sendMessage("§4TeamSpeak3 §7Coming soon...");
		   p.sendMessage("§9▉▉▉▉▉▉▉▉▉▉▉▉ §7[§bTeamspeak3§7]§9▉▉▉▉▉▉▉▉▉▉▉▉");
		
		
		
		
		return true;
	}

}
