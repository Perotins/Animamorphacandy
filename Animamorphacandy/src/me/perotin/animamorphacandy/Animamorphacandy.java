package me.perotin.animamorphacandy;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import me.perotin.animamorphacandy.events.PlayerEatFood;

public class Animamorphacandy extends JavaPlugin {
	
	/*
	 * Animamorphacandy
	 * Plugin that when a player eats food, they turn into a mob 
	 */
	
	private static Animamorphacandy instance;
	
	@Override
	public void onEnable(){
		saveDefaultConfig();
		instance = this;
		Bukkit.getPluginManager().registerEvents(new PlayerEatFood(), this);
	
		
	}
	
	
	public static Animamorphacandy getInstance(){
		return instance;
	}
	

}
