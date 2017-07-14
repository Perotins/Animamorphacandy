package me.perotin.animamorphacandy;

import java.util.ArrayList;
import java.util.Random;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;

import me.libraryaddict.disguise.DisguiseAPI;
import me.libraryaddict.disguise.disguisetypes.DisguiseType;
import me.libraryaddict.disguise.disguisetypes.MobDisguise;

public class Transformation {

	private String food;
	private UUID player;
	private String mobToBecome;
	private ArrayList<String> possiblePotions;
	private int percentageOutOfHundred;

	public Transformation(String food, UUID player){
		this.food = food;
		this.player = player;
		this.mobToBecome = Animamorphacandy.getInstance().getConfig().getString(food+".mob-to-turn-into");
		this.possiblePotions = (ArrayList<String>) Animamorphacandy.getInstance().getConfig().getStringList(food+".potion-effects");
		this.percentageOutOfHundred = Animamorphacandy.getInstance().getConfig().getInt(food+".percentage");

	}

	
	public String getFood(){
		return this.food;
	}

	public void transformIntoMob(){
		// only perform if the player to disguise is online!
		Player toDisguise = Bukkit.getPlayer(getUuid());
		String mobToTurn = getMobTransformation();
		MobDisguise disguise = new MobDisguise(DisguiseType.valueOf(mobToTurn), Animamorphacandy.getInstance().getConfig().getBoolean(getFood()+".is-adult"));
		DisguiseAPI.disguiseToAll(toDisguise, disguise);
		toDisguise.sendMessage(ChatColor.GRAY + "You have been transformed into a " + ChatColor.GREEN + mobToTurn+ChatColor.GRAY+
				"!");
		new BukkitRunnable(){

			@Override
			public void run() {
				DisguiseAPI.undisguiseToAll(toDisguise);

			}
			
		}.runTaskLater(Animamorphacandy.getInstance(), Animamorphacandy.getInstance().getConfig().getInt(food+".time-to-undisguise")*20);
	}


	public void performPotionEffects(){
		// only perform if the player to potion is online!
		Player toEffect = Bukkit.getPlayer(getUuid());
		Random random = new Random();
		int toTest = random.nextInt(100);
		//chance is on their side, apply potion effects
		if(getPercentage() < toTest ){
			Random potionRandom = new Random();
			// get a random potion
			if(!getPotions().isEmpty()){
			String potionToApply = getPotions().get(potionRandom.nextInt(getPotions().size()));
			
			toEffect.addPotionEffect(new PotionEffect(PotionEffectType.getByName(potionToApply), 500*20, 1));
			}
		}

	}
	private UUID getUuid(){
		return this.player;
	}

	private ArrayList<String> getPotions(){
		return this.possiblePotions;
	}

	private String getMobTransformation(){
		return this.mobToBecome;
	}

	private int getPercentage(){
		return this.percentageOutOfHundred;
	}
}
