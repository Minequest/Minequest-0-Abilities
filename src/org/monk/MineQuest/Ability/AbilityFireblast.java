package org.monk.MineQuest.Ability;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.LivingEntity;
import org.bukkit.inventory.ItemStack;
import org.monksanctum.MineQuest.MineQuest;
import org.monksanctum.MineQuest.Ability.Ability;
import org.monksanctum.MineQuest.Event.AbilityEvent;
import org.monksanctum.MineQuest.Event.Absolute.ExplosionEvent;
import org.monksanctum.MineQuest.Quester.Quester;

public class AbilityFireblast extends Ability {
	public AbilityFireblast() {
		super();
		config = new int[] {4, 325, 3};
	}

	@Override
	public void castAbility(Quester quester, Location location,
			LivingEntity entity) {
		if (entity == null) {
			quester.sendMessage("Must be cast on an entity");
			giveCost(quester.getPlayer());
		}
		
		AbilityFireball ability;
		for (LivingEntity lentity : getEntities(entity, config[0])) {
			ability = new AbilityFireball();
			ability.setCast(quester, new Location(lentity.getWorld(),
					lentity.getLocation().getX(),
					lentity.getLocation().getY(),
					lentity.getLocation().getZ()), entity);
			MineQuest.getEventQueue().addEvent(new AbilityEvent(100, ability));
		}
		
		if ((quester == null) || quester.canEdit(location.getBlock())) {
			MineQuest.getEventQueue().addEvent(new ExplosionEvent(10, location.getWorld(), 
					location.getX(), location.getY(), location.getZ(), ((float)config[1]) / 100, config[2]));
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
	public List<ItemStack> getSpellComps() {
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

	@Override
	public String getSkillClass() {
		return "WarMage";
	}

}
