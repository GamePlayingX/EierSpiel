package com.gameplayingx.splegg.commands;

import net.gameplayingx.updater.Updater;
import net.gameplayingx.updater.Updater.UpdateType;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.gameplayingx.splegg.Splegg;
import com.gameplayingx.splegg.games.Game;
import com.gameplayingx.splegg.games.Status;
import com.gameplayingx.splegg.maps.Map;
import com.gameplayingx.splegg.misc.Permissions;
import com.gameplayingx.splegg.players.UtilPlayer;
import com.sk89q.worldedit.bukkit.WorldEditPlugin;
import com.sk89q.worldedit.bukkit.selections.Selection;

public class SpleggCommand implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender cs, Command cmd, String tag, String[] args) {
		if (cs instanceof Player) {
			
			Player player = (Player)cs;
			UtilPlayer u = Splegg.getSplegg().pm.getPlayer(player);
			Permissions perms = new Permissions(player);
			
			if (args.length == 0) {
				Splegg.getSplegg().chat.sendMessage(player, "&b--- &6Splegg &b---");
				Splegg.getSplegg().chat.sendMessage(player, "&c/" + tag + " help <player|mod|admin>");
			} else if (args.length == 1) {
				if (args[0].equalsIgnoreCase("join")) {
					if (perms.canjoin()) {
						if (u.getGame() != null && u.isAlive()) {
							Splegg.getSplegg().chat.sendMessage(player, "&cDu Spielst bereits.");
						} else {
							if (lobbyset()) {
								player.teleport(Splegg.getSplegg().config.getLobby());
							} else {
								Splegg.getSplegg().chat.sendMessage(player, "&cSplegg ist unvollendet! Frag ein Admin damit er die Lobby setzt.");
							}
						}
					} else {
						Splegg.getSplegg().chat.sendMessage(player, "&cKeine Berechtigung.");
					}
				} else if (args[0].equalsIgnoreCase("leave")) {
					if (u.getGame() != null && u.isAlive()) {
						Game game = u.getGame();
						game.leaveGame(u);
						Splegg.getSplegg().chat.sendMessage(player, "Du verlässt die Lobby für die Map &c" + game.getMap().getName() + "&6.");
					}
				} else if (args[0].equalsIgnoreCase("update")) {
					if (perms.isAdmin()) {
						if (Splegg.getSplegg().updateOut) {
							try {
							Splegg.getSplegg().chat.sendMessage(player, "Commencing update...");
							@SuppressWarnings("unused")
							Updater update = new Updater(Splegg.getSplegg(), "splegg-game", Splegg.getSplegg().updateFile, UpdateType.NO_VERSION_CHECK, true);
							}catch(Exception e) { Splegg.getSplegg().chat.sendMessage(player, "An error occured whilst updating! Please download manually from &bhttp://dev.bukkit.org/bukkit-plugins/splegg-game"); }
						} else {
							Splegg.getSplegg().chat.sendMessage(player, "&bNo updates found.");
						}
					} else {
						Splegg.getSplegg().chat.sendMessage(player, "&cKeine Berechtigung.");
					}
				} else if (args[0].equalsIgnoreCase("maplist")) {
					Splegg.getSplegg().chat.sendMessage(player, "&bMap liste:");
					if (Splegg.getSplegg().maps.MAPS.size() == 0) {
						Splegg.getSplegg().chat.sendMessage(player, "Keine Maps geladen.");
					}
					for (Map map : Splegg.getSplegg().maps.MAPS.values()) {
						Splegg.getSplegg().chat.sendMessage(player, "Map: " + map.getName() + " | " + (map.isUsable() ? "Ist im Setup" : "Unkorekter Setup"));
					}
				} else if (args[0].equalsIgnoreCase("start")) {
					if (perms.canStartEnd()) {
						if (u.getGame() == null) {
							Splegg.getSplegg().chat.sendMessage(player, "&cDu bist in keinem Spiel.");
						} else if (u.getGame().getStatus() == Status.LOBBY) {
							Splegg.getSplegg().game.startGame(u.getGame());
							Splegg.getSplegg().chat.sendMessage(player, "&eSpiel startet!");
						} else if (u.getGame().getStatus() == Status.INGAME) {
							Splegg.getSplegg().chat.sendMessage(player, "§cDas Spiel hat bereits angefangen.");
						}
					} else {
						Splegg.getSplegg().chat.sendMessage(player, "&cKeine Berechtigung.");
					}
				} else if (args[0].equalsIgnoreCase("stop")) {
					if (perms.canStartEnd()) {
						if (u.getGame() == null) {
							Splegg.getSplegg().chat.sendMessage(player, "&cDu bist in keinem Spiel.");
						} else if (u.getGame().getStatus() == Status.LOBBY) {
							Splegg.getSplegg().chat.sendMessage(player, "&cDas Spiel fängt noch nicht an!");
						} else if (u.getGame().getStatus() == Status.INGAME) {
							Splegg.getSplegg().chat.bc("&5" + player.getName() + "&6 hat das Spiel gestoppt.", u.getGame());
							Splegg.getSplegg().game.stopGame(u.getGame(), 1);
							Splegg.getSplegg().chat.sendMessage(player, "§eDu hast das Spiel gestoppt.");
						}
					} else {
						Splegg.getSplegg().chat.sendMessage(player, "&cKeine Berechtigung.");
					}
				} else if (args[0].equalsIgnoreCase("setlobby")) {
					if (perms.canModifyMaps()) {
						Splegg.getSplegg().config.setLobby(player.getLocation());
						Splegg.getSplegg().chat.sendMessage(player, "Du hast die Splegg Lobby gesetzt.");
					} else {
						Splegg.getSplegg().chat.sendMessage(player, "&cKeine Berechtigung.");
					}
				} else if (args[0].equalsIgnoreCase("help")) {
//					Splegg.getSplegg().chat.sendMessage(player, "&b--- &4&lSplegg Help &b---");
					Splegg.getSplegg().chat.sendMessage(player, "&b--- &6Splegg &b---");
					Splegg.getSplegg().chat.sendMessage(player, "&c/" + tag + " help <player|mod|admin>");
				} else {
					Splegg.getSplegg().chat.sendMessage(player, "&cFalsche verwendung: &6/" + tag + " <join,leave,stop,start,setlobby,help>");
				}
			} else if (args.length == 2) {
				if (args[0].equalsIgnoreCase("create")) {
					if (perms.canModifyMaps()) {
						String name = args[1];
						if (Splegg.getSplegg().maps.mapExists(name)) {
							Splegg.getSplegg().chat.sendMessage(player, "Map existiert bereits.");
						} else {
							Splegg.getSplegg().maps.c.addMap(name);
							Splegg.getSplegg().maps.addMap(name);
							Map map = Splegg.getSplegg().maps.getMap(name);
							Game game = new Game(Splegg.getSplegg(), map);
							Splegg.getSplegg().games.addGame(map.getName(), game);
							if (!map.isUsable()) {
								game.setStatus(Status.DISABLED);
							}
							Splegg.getSplegg().chat.sendMessage(player, "Map wurde hinzugefügt und erstellt &c" + name + "&6.");
						}
					} else {
						Splegg.getSplegg().chat.sendMessage(player, "&cKeine Berechtigung.");
					}
				} else if (args[0].equalsIgnoreCase("delete")) {
					if (perms.canModifyMaps()) {
						String name = args[1];
						if (Splegg.getSplegg().maps.mapExists(name)) {
							Splegg.getSplegg().maps.deleteMap(name);
							Splegg.getSplegg().chat.sendMessage(player, "Map wurde gelöscht.");
						} else {
							Splegg.getSplegg().chat.sendMessage(player, "&cMap exisiert nicht.");
						}
					} else {
						Splegg.getSplegg().chat.sendMessage(player, "&cKeine Berechtigung.");
					}
				} else if (args[0].equalsIgnoreCase("start")) {
					if (perms.canStartEnd()) {
						String name = args[1];
						if (Splegg.getSplegg().maps.mapExists(name)) {
							Game game = Splegg.getSplegg().games.getGame(name);
							if (game == null) {
								Splegg.getSplegg().chat.sendMessage(player, "&cDu bist in keinem Spiel.");
							} else if (game.getStatus() == Status.LOBBY) {
								Splegg.getSplegg().game.startGame(game);
								Splegg.getSplegg().chat.sendMessage(player, "&eStartet " + name + ".");
							} else if (game.getStatus() == Status.INGAME) {
								Splegg.getSplegg().chat.sendMessage(player, "§cSpiel hat bereits angefangen.");
							}
						} else {
							Splegg.getSplegg().chat.sendMessage(player, "&cMap existiert nicht.");
						}
					} else {
						Splegg.getSplegg().chat.sendMessage(player, "&cKeine Berechtigung.");
					}
				} else if (args[0].equalsIgnoreCase("stop")) {
					if (perms.canStartEnd()) {
						String name = args[1];
						if (Splegg.getSplegg().maps.mapExists(name)) {
							Game game = Splegg.getSplegg().games.getGame(name);
							if (game == null) {
								Splegg.getSplegg().chat.sendMessage(player, "&cDu bist in keinem Spiel.");
							} else if (game.getStatus() == Status.LOBBY) {
								Splegg.getSplegg().chat.sendMessage(player, "&cSpiel hat noch nicht Angefangen!");
							} else if (game.getStatus() == Status.INGAME) {
								Splegg.getSplegg().chat.bc("&5" + player.getName() + "&6 hat das Spiel gestoppt.", game);
								Splegg.getSplegg().game.stopGame(game, 1);
								Splegg.getSplegg().chat.sendMessage(player, "§eDu hast das Spiel gestoppt.");
							}
						} else {
							Splegg.getSplegg().chat.sendMessage(player, "&cMap existiert nicht.");
						}
					} else {
						Splegg.getSplegg().chat.sendMessage(player, "&cKeine Berechtigung.");
					}
				} else if (args[0].equalsIgnoreCase("addfloor")) {
					if (perms.canModifyMaps()) {
						String name = args[1];
						if (Splegg.getSplegg().maps.mapExists(name)) {
							Map map = Splegg.getSplegg().maps.getMap(name);
							WorldEditPlugin we = Splegg.getSplegg().getWorldEdit();
							Selection sel = we.getSelection(player);
							if (sel == null) {
								Splegg.getSplegg().chat.sendMessage(player, "&5Please select an area with worldedit.");
							} else {
								map.addFloor(sel.getMinimumPoint(), sel.getMaximumPoint());
								Splegg.getSplegg().chat.sendMessage(player, "Floor " + map.getFloors() + " added to map " + map.getName() + ".");
							}
						} else {
							Splegg.getSplegg().chat.sendMessage(player, "&cMap does not exist.");
						}
					} else {
						Splegg.getSplegg().chat.sendMessage(player, "&cYou do not have permission.");
					}
				} else if (args[0].equalsIgnoreCase("join")) {
					if (perms.canjoin()) {
						if (u.getGame() != null && u.isAlive()) {
							Splegg.getSplegg().chat.sendMessage(player, "&cDu spielst bereits.");
						} else {
							String name = args[1];
							if (Splegg.getSplegg().maps.mapExists(name)) {
								Game game = Splegg.getSplegg().games.getGame(name);
								if (game != null && Splegg.getSplegg().maps.getMap(name).isUsable()) {
									game.joinGame(u);
								} else {
									Splegg.getSplegg().chat.sendMessage(player, "Diese Map wurde falsch erstellt - Guck in die Console um den Fehler rauszufinden.");
								}
							} else {
								Splegg.getSplegg().chat.sendMessage(player, "&cMap does not exist.");
							}
						}
					} else {
						Splegg.getSplegg().chat.sendMessage(player, "&cYou do not have permission.");
					}
				} else if (args[0].equalsIgnoreCase("leave")) {
					Splegg.getSplegg().chat.sendMessage(player, "Bitte benutz &e/" + tag + " leave");
				} else if (args[0].equalsIgnoreCase("help")) {
					if (args[1].equalsIgnoreCase("player")) {
						Splegg.getSplegg().chat.sendMessage(player, "&b--- &4&lSplegg Help &aPlayer &b---");
						sendUsage(player, tag, "join", "Teleportiert zur Lobby");
						sendUsage(player, tag, "join <mapname>", "Join eine bestimmte Map");
						sendUsage(player, tag, "leave", "verlässt dein jetziges Spiel");
					} else if (args[1].equalsIgnoreCase("mod")) {
						Splegg.getSplegg().chat.sendMessage(player, "&b--- &4&lSplegg Help &bMod &b---");
						sendUsage(player, tag, "start [mapname]", "Start yours or another game");
						sendUsage(player, tag, "stop [mapname]", "End yours or another game");
					} else if (args[1].equalsIgnoreCase("admin")) {
						Splegg.getSplegg().chat.sendMessage(player, "&b--- &4&lSplegg Help &6Admin &b---");
						sendUsage(player, tag, "create <mapname>", "Create a map");
						sendUsage(player, tag, "delete <mapname>", "Delete a map");
						sendUsage(player, tag, "setspawn <map> next", "Set next spawn in a map");
						sendUsage(player, tag, "setspawn <map> <id>", "Re-set spawn in a map");
						sendUsage(player, tag, "addfloor <map>", "Add a floor");
						sendUsage(player, tag, "setlobby", "Set the lobby");
					} else {
						Splegg.getSplegg().chat.sendMessage(player, "Nutzung: /" + tag + " help <player|mod|admin>");
					}
				} else {
					Splegg.getSplegg().chat.sendMessage(player, "Falsche Nutzung");
				}
			} else if (args.length == 3) {
				if (args[0].equalsIgnoreCase("setspawn")) {
					if (perms.canModifyMaps()) {
						String name = args[1];
						if (Splegg.getSplegg().maps.mapExists(name)) {
							Map map = Splegg.getSplegg().maps.getMap(name);
							if (args[2].equalsIgnoreCase("next")) {
								map.addSpawn(player.getLocation());
								Splegg.getSplegg().chat.sendMessage(player, "Spawn &a" + map.getSpawnCount() + "&6 set for map &c" + map.getName() + "&6.");
							} else {
								try{
									int id = Integer.parseInt(args[2]);
									if (spawnset(id, map)) {
										map.setSpawn(id, player.getLocation());
										Splegg.getSplegg().chat.sendMessage(player, "You have re-set the spawn " + id + " for map " + name + ".");
									} else {
										Splegg.getSplegg().chat.sendMessage(player, "Please set the spawn using &e/" + tag + " <mapname> next &6then try this command again.");
									}
								}catch(NumberFormatException ex) {
									Splegg.getSplegg().chat.sendMessage(player, "&cPlease type a number.");
								}
							}
						} else {
							Splegg.getSplegg().chat.sendMessage(player, "&cMap does not exist!");
						}
					} else {
						Splegg.getSplegg().chat.sendMessage(player, "&cYou do not have permission.");
					}
				} else {
					Splegg.getSplegg().chat.sendMessage(player, "Usage: &a/" + tag + " setspawn <mapname> <next|spawnid>");
				}
			} else {
				Splegg.getSplegg().chat.sendMessage(player, "Incorrect Usage!");
			}
			
		}
		return false;
	}
	
	boolean lobbyset() {
		try{
			Splegg.getSplegg().config.getLobby();
		}catch(Exception e) { return false;}
		return true;
	}
	
	boolean spawnset(int i, Map map) {
		if (map.getConfig().isString("Spawns." + i + ".world")) {
			return true;
		}
		return false;
	}
	
	public void sendUsage(Player player, String tag, String usage, String def) {
		Splegg.getSplegg().chat.sendMessage(player, "&c/" + tag + " &d" + usage + " &5- &b" + def);
	}
	
}
