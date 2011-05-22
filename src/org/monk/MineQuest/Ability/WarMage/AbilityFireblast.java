package org.monk.MineQuest.Ability.WarMage;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.LivingEntity;
import org.bukkit.inventory.ItemStack;
import org.monk.MineQuest.MineQuest;
import org.monk.MineQuest.Ability.Ability;
import org.monk.MineQuest.Event.AbilityEvent;
import org.monk.MineQuest.Event.Absolute.ExplosionEvent;
import org.monk.MineQuest.Quester.Quester;

public class AbilityFireblast extends Ability {

	@Override
	public void castAbility(Quester quester, Location location,
			LivingEntity entity) {
		if (entity == null) {
			quester.sendMessage("Must be cast on an entity");
			giveManaCost(quester.getPlayer());
		}
		
		AbilityFireball ability;
		for (LivingEntity lentity : getEntities(entity, 4)) {
			ability = new AbilityFireball();
			ability.setCast(quester, new Location(lentity.getWorld(),
					lentity.getLocation().getX(),
					lentity.getLocation().getY(),
					lentity.getLocation().getZ()), entity);
			MineQuest.getEventParser().addEvent(new AbilityEvent(100, ability));
		}
		
		if ((quester == null) || quester.canEdit(location.getBlock())) {
			MineQuest.getEventParser().addEvent(new ExplosionEvent(10, location.getWorld(), 
					location.getX(), location.getY(), location.getZ(), 3.25f, 3));
		}
	}
	
	@Override
	public int getCastTime() {
		return 10000;
	}
	
	public static List<LivingEntity> getEntities(LivingEntity entity, int radius) {
		List<LivingEntity> entities = new ArrayList<LivingEntity>(0);
		List<LivingEntity> serverList = entity.getWorld().getLivingEntities();
		int i;
		
		for (i = 0; i < serverList.size(); i++) {
			if ((MineQuest.distance(entity.getLocation(), serverList.get(i).getLocation()) <= radius)) {
				entities.add(serverList.get(i));
			}
		}
		
		return entities;
	}

	@Override
	public List<ItemStack> getManaCost() {
		List<ItemStack> cost = new ArrayList<ItemStack>();
		int i;
		
		for (i = 0; i < 10; i++) {
			cost.add(new ItemStack(Material.COAL, 1));
		}
		
		return cost;
	}

	@Override
	public String getName() {
		return "Fireblast";
	}

	@Override
	public int getReqLevel() {
		return 15;
	}

}
