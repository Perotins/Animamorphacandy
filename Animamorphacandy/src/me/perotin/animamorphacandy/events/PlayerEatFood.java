package me.perotin.animamorphacandy.events;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerItemConsumeEvent;
import org.bukkit.inventory.ItemStack;

import me.perotin.animamorphacandy.Animamorphacandy;
import me.perotin.animamorphacandy.Transformation;

public class PlayerEatFood implements Listener {

	@EventHandler
	public void onEat(PlayerItemConsumeEvent event){
		ItemStack food = event.getItem();
		Player eater = event.getPlayer();
		if(Animamorphacandy.getInstance().getConfig().contains(food.getType().toString())){
			// food is registered in the config, setup object for it and bam
			Transformation trans = new Transformation(food.getType().toString(), eater.getUniqueId());
			trans.transformIntoMob();
			trans.performPotionEffects();
			
			
			
		}
		
	}
	
}
