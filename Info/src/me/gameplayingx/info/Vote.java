package me.gameplayingx.info;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Vote implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String cmdLabel, String[] args) {
		Player p = (Player) sender;
		if(cmd.getName().equalsIgnoreCase("vote"));
		p.sendMessage("§9▉▉▉▉▉▉▉▉▉▉▉▉§7[§6Vote§7]§9▉▉▉▉▉▉▉▉▉▉▉▉");
		p.sendMessage("                                        ");
		p.sendMessage("§a✓§bVote-Seite 1§7: §6http://bit.ly/Vote_1");
		p.sendMessage("                                        ");
		p.sendMessage("                                        ");
		p.sendMessage("§a✓§bVote-Seite 2§7: §6http://bit.ly/Vote_2");
		p.sendMessage("                                        ");
		p.sendMessage("                                        ");
		p.sendMessage("§a✓§bVote-Seite 3§7: §6http://bit.ly/Vote_3");
		p.sendMessage("                                        ");
		p.sendMessage("§6Um zu Voten drückst du auf die Seiten!");
		p.sendMessage("§6Gibst in als Erstes deinen InGame Namen ein.");
		p.sendMessage("§6Danach den Code der da drinne steht.");
		p.sendMessage("§6Und drückst auf Voten! Viel Spass mit der Belohnung");
		p.sendMessage("                                        ");
		p.sendMessage("§9▉▉▉▉▉▉▉▉▉▉▉▉§7[§6Vote§7]§9▉▉▉▉▉▉▉▉▉▉▉▉");
		
		
		
		return true;
	}

}
