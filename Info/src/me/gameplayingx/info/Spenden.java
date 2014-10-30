package me.gameplayingx.info;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Spenden implements CommandExecutor {
	

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String cmdLabel, String[] args) {
		Player p = (Player) sender;
		if(cmd.getName().equalsIgnoreCase("Spenden"));
		   p.sendMessage("§9▉▉▉▉▉▉▉▉▉▉▉▉§7[§bSpenden§7]§9▉▉▉▉▉▉▉▉▉▉▉▉");
		   p.sendMessage("                                           ");
		   p.sendMessage("§6Premium§7: §b10§2€");
		   p.sendMessage("§61 Monat");
		   p.sendMessage("                                           ");
		   p.sendMessage("§6Premium§7: §b20§2€");
		   p.sendMessage("§d3 Monate");
		   p.sendMessage("                                           ");
		   p.sendMessage("§6Premium§7: §b30§2€");
		   p.sendMessage("§d 6 Monate");
		   p.sendMessage("                                           ");
		   p.sendMessage("§6Premium§7: §b40§2€");
		   p.sendMessage("§dLifetime");
		   p.sendMessage("                                           ");
		   p.sendMessage("Um zu spenden nutze den /Skype Command um den jewahligen Owner zukontaktieren und zu Spenden");
		   p.sendMessage("                                             ");
		   p.sendMessage("§9§9▉▉▉▉▉▉▉▉▉▉▉▉§7[§bSpenden§7]§9▉▉▉▉▉▉▉▉▉▉▉▉");
		   return true;
	}

}
