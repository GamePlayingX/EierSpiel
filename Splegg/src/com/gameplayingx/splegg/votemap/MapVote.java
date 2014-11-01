package com.gameplayingx.splegg.votemap;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.gameplayingx.splegg.Splegg;

public class MapVote implements CommandExecutor {
	
	private Splegg plugin;
	
	public MapVote(Splegg splegg) {
		this.plugin = splegg;
	}
	
	
	public boolean onCommand(CommandSender cs, Command cmd, String tag, String[] args) {
		if(sender instanceof Player){
			Player p = (Player) p;
			
			if(args[0].equalsIgnoreCase("result")){
				getResult();
			}
		}
			if(args[0].equalsIgnoreCase("list")){
				getList(p);
			}
			if(args[0].equalsIgnoreCase("votes")){
				if(!plugin.voted.contains(p.getName()));
				Player p = (Player) p;
				if(plugin.arenas.contains(args[1])) {
					
					
					int votes = plugin.votes.get(args[1].toLowerCase());
					votes++;
					plugin.votes.put(args[1].toLowerCase(), votes);
					
					p.sendMessage("§7Du hast für §3" + args[1] + " §7gestimmt");
					p.sendMessage("§7Die Map " + plugin.votes.get(args[1].toLowerCase() + " §7hat §3votes"));
					plugin.voted.add(p.getName());
				}
				
			} else {
				p.sendMessage("§7Du hat bereits abgestimmt!");
	
		
		return true;
	}
	
	public void getList(Player p) {
		p.sendMessage("§1▉▉▉▉▉§l§7[ §l§eVotes §l§7]§1▉▉▉▉▉");
		for(String all : plugin.arenas){
			p.sendMessage("§3" + all + " §e" + plugin.votes.get(all));
		}
	}
	
	public void getResult() {
		
		int max = 0;
		for(int i : plugin.votes.values());
		if(i > max) {
			max = i;
		}
	}
	String winner = "";
	
	for(String all : plugin.votes.keySet()) {
		if(plugin.votes.get(all) == max) {
			winner = all;
		}
	}
	
	Bukkit.broadcastMessage("§7Es wurde für die Map §3" + winner + " §7gestimmt");
}

}
