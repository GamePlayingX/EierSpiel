package com.gameplayingx.splegg.lobby;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Sign;

import com.gameplayingx.splegg.Splegg;
import com.gameplayingx.splegg.games.Game;
import com.gameplayingx.splegg.games.Status;
import com.gameplayingx.splegg.maps.Map;

public class LobbySign {
	
	Splegg splegg;
	Map	map;
	
	public LobbySign(Map map, Splegg s) {
		this.splegg = s;
		this.map = map;
	}
	
	public void create(Location location, final Map map) {
		String loc = LobbySignUtils.get().locationToString(location);
		splegg.maps.c.addSign(map.getName(), loc);
		if (this.map == null) {
			this.map = map;
		}
		Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(splegg, new Runnable() {
			
			public void run() {
				update(map, true);
			}
			
		}, 5L);
	}
	
	public void delete(Location location) {
		String loc = LobbySignUtils.get().locationToString(location);
		splegg.maps.c.delSign(map.getName(), loc);
		splegg.maps.c.saveMaps();
		this.map = null;
	}
	
	public void update(Map map, boolean force) {
		for (String loc : splegg.maps.c.maps.getStringList("Signs." + map.getName() + ".lobby")) {
			Location location = LobbySignUtils.get().stringToLocation(loc);
			if (location.getBlock().getType() != Material.WALL_SIGN) {
				location.getBlock().setType(Material.WALL_SIGN);
			}
			Sign s = (Sign) location.getBlock().getState();
			if (force) {
				String[] array = new String[4];
				array[0] = ChatColor.BOLD + "▉▉▉▉▉▉▉▉";
				array[1] = "§4Splegg §dby";
				array[2] = "§4GamePlayingX";
				array[3] = ChatColor.BOLD + "▉▉▉▉▉▉▉▉";
				setSign(array, s);
				
				String[] sign = new String[4];
				Game game = splegg.games.getGame(map.getName());
				
				if (game == null) {
					
					sign[0] = "";
					sign[1] = ChatColor.DARK_RED + "Please remove";
					sign[2] = ChatColor.DARK_RED + "this sign";
					sign[3] = "";
					
				} else {
					
					sign[0] = "§4[§6Splegg§4]";
					sign[1] = ChatColor.AQUA + map.getName();
					sign[2] = getFancyStatus(game);
					sign[3] = ChatColor.BOLD + getPlayers(game);
					
				}
				
				Bukkit.getScheduler().scheduleSyncDelayedTask(splegg, new SignDelay(sign, s), 40L);
				
			} else {
				Game game = splegg.games.getGame(map.getName());
				String[] sign = new String[4];
				sign[0] = "§4[§6Splegg§4]";
				sign[1] = ChatColor.AQUA + map.getName();
				sign[2] = getFancyStatus(game);
				sign[3] = ChatColor.BOLD + getPlayers(game);
				setSign(sign, s);
			}
		}
	}
	
	private String getPlayers(Game game) {
		String players = "";
		if (game.getStatus() == Status.DISABLED) {
			players = "";
		} else {
			if (game.getMap().getSpawnCount() <= 1) {
				players = "Players: " + game.getPlayers().size();
			} else {
				players =  "§a"+ game.getPlayers().size() +  "§7/" +"§4"+ game.getMap().getSpawnCount();
			}
		}
		return players;
	}

	private void setSign(String[] lines, Sign s) {
		for (int i = 0; i < 	lines.length; i++) {
			s.setLine(i, lines[i]);
		}
		s.update();
	}
	
	private String getFancyStatus(Game game) {
		String status = "";
		Status st = game.getStatus();
		if (st == Status.LOBBY) {
			if (game.isStarting()) {
				status = ChatColor.AQUA + "Starting " + game.getLobbyCount();
			} else {
				status = ChatColor.GREEN + "Lobby";
			}
		} else if (st == Status.DISABLED) {
			status = ChatColor.DARK_RED + "Ausgeschaltet";
		} else if (st == Status.INGAME) {
			status = ChatColor.GOLD + "InGame";
		} else {
			status = ChatColor.DARK_GREEN + st.toString().toLowerCase();
		}
		return status;
	}
	
}
