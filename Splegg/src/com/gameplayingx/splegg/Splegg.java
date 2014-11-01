package com.gameplayingx.splegg;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import net.gameplayingx.updater.Updater;
import net.gameplayingx.updater.Updater.UpdateResult;
import net.gameplayingx.updater.Updater.UpdateType;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import com.gameplayingx.splegg.commands.SpleggCommand;
import com.gameplayingx.splegg.events.MapListener;
import com.gameplayingx.splegg.events.PlayerListener;
import com.gameplayingx.splegg.events.SignListener;
import com.gameplayingx.splegg.events.SpleggEvents;
import com.gameplayingx.splegg.games.Game;
import com.gameplayingx.splegg.games.GameManager;
import com.gameplayingx.splegg.games.GameUtilities;
import com.gameplayingx.splegg.games.Status;
import com.gameplayingx.splegg.maps.MapUtilities;
import com.gameplayingx.splegg.misc.Chat;
import com.gameplayingx.splegg.misc.Config;
import com.gameplayingx.splegg.misc.UpdateUtils;
import com.gameplayingx.splegg.misc.Utilities;
import com.gameplayingx.splegg.players.PlayerManager;
import com.gameplayingx.splegg.players.UtilPlayer;
import com.sk89q.worldedit.bukkit.WorldEditPlugin;

public class Splegg extends JavaPlugin implements Listener {
	public List<String> voted = new ArrayList<>();
	public HashMap<String, Integer> votes = new HashMap<>();
	
	public List<String> arenas = new ArrayList<>();
	
	public static Splegg getSplegg() {
		return (Splegg)Bukkit.getPluginManager().getPlugin("Splegg");
	}
	
	public Chat chat;
	public MapUtilities maps;
	public GameUtilities games;
	public GameManager game;
	public PlayerManager pm;
	public Utilities utils;
	public Config config;
	
	public Updater u;
	public boolean updateOut = false;
	public String newVer = "";
	public File updateFile = this.getFile();
	
	public List<String> special = Arrays.asList(new String[] { "GamePlayingX"});
	public boolean eco = false;
	public boolean disabling = false;
	
	@SuppressWarnings("deprecation")
	public void onEnable() {
		
		
		arenas.add("bigtrees");
		arenas.add("alien");
		arenas.add("mixedcolors");
		
		for(String all : arenas) {
			votes.put(all, 0);
		}
		
		this.chat = new Chat();
		if (getServer().getPluginManager().getPlugin("WorldEdit") == null) {
			chat.log("WorldEdit not found! Please download it from http://dev.bukkit.org/bukkit-plugins/worldedit");
			Bukkit.getPluginManager().disablePlugin(this);
			return;
		}
		this.maps = new MapUtilities();
		this.games = new GameUtilities();
		this.game = new GameManager(Splegg.getSplegg());
		this.pm = new PlayerManager();
		this.utils = new Utilities();
		this.config = new Config(this);
		
		maps.c.setup();
		config.setup();
		
		getConfig().options().copyDefaults(true);
		saveConfig();
		
		if (getConfig().getBoolean("auto-update")) {
			u = new Updater(this, "splegg-game", getFile(), UpdateType.NO_DOWNLOAD, false);
			updateOut = u.getResult() == UpdateResult.UPDATE_AVAILABLE;
			if (updateOut) {
				newVer = u.getLatestVersionString();
			}
			getServer().getPluginManager().registerEvents(new UpdateUtils(), this);
		}
		
//		economy.init(this);
		
		getServer().getPluginManager().registerEvents(new MapListener(this), this);
		getServer().getPluginManager().registerEvents(new PlayerListener(), this);
		getServer().getPluginManager().registerEvents(new SpleggEvents(), this);
		getServer().getPluginManager().registerEvents(new SignListener(), this);
		
		getCommand("splegg").setExecutor(new SpleggCommand());
		
		for (Player players : Bukkit.getOnlinePlayers()) {
			UtilPlayer u = new UtilPlayer(players);
			pm.PLAYERS.put(players.getName(), u);
		}
		
		super.onEnable();
	}
	
	public void onDisable() {
		disabling = true;
		for (Game game : this.games.GAMES.values()) {
			if (game.getStatus() == Status.INGAME) {
				this.game.stopGame(game, 1);
			}
		}
		super.onDisable();
	}
	
	public WorldEditPlugin getWorldEdit() {
		Plugin worldEdit = getServer().getPluginManager().getPlugin("WorldEdit");
		if ((worldEdit instanceof WorldEditPlugin)) {
			return (WorldEditPlugin) worldEdit;
		}
		return null;
	}
	
}
