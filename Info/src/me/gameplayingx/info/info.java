package me.gameplayingx.info;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public class info extends JavaPlugin  {
	
	


	protected static final CommandSender p = null;

	public void onEnable() {
		getCommand("team").setExecutor(new teamClass());
		getCommand("vote").setExecutor(new Vote());
		getCommand("Spenden").setExecutor(new Spenden());
		getCommand("Skype").setExecutor(new Skype());
		getCommand("ts3").setExecutor(new Teamspeak3());
		
	}



	public void onDisable() {
		
	}
		
		

	
	public boolean onCommand(CommandSender sender, Command cmd, String cmdLabel, String[] args) {
		Player p = (Player) sender;
		if(cmd.getName().equalsIgnoreCase("Information"));
		p.sendMessage("§9▉▉▉▉▉▉▉▉▉▉▉▉§7[§eInformation§7]§9▉▉▉▉▉▉▉▉▉▉▉▉");
		p.sendMessage("§bSkype§7: §7Server-Team in Skype §7/Skype");
		p.sendMessage("§4Teamspeak: §7Community-Teamspeak /ts3");
		p.sendMessage("§bSpenden: §7Alle Kaufbaren Ränge");
		p.sendMessage("§bVote: §7Vote für uns");
		p.sendMessage("§bTeam: §7Um zusehen wer Online ist");
		p.sendMessage("§9▉▉▉▉▉▉▉▉▉▉▉▉§7[§eInformation§7]§9▉▉▉▉▉▉▉▉▉▉▉▉");
		return true;
	
		
		
		}

	
	
	}
					

			

	
	
	


