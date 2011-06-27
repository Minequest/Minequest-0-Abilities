package org.monk.MineQuest.Ability;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.LivingEntity;
import org.bukkit.inventory.ItemStack;
import org.monksanctum.MineQuest.MineQuest;
import org.monksanctum.MineQuest.Ability.Ability;
import org.monksanctum.MineQuest.Quester.Quester;

public class AbilityBash extends Ability {
	
	public AbilityBash() {
		super();
		config = new int[] { 15 };
	}
	

	@Override
	public void castAbility(Quester quester, Location location,
			LivingEntity entity) {
		if (entity == null) {
			quester.sendMessage("Must be cast on an entity");
			giveCost(quester.getPlayer());
		}
		
		MineQuest.damage(entity, config[0], quester);

		double rot = entity.getLocation().getYaw() % 360;
		while (rot < 0) rot += 360;
		Location loc = entity.getLocation();
		
		if ((rot  < 45) && (rot > 315)) {
			loc = new Location(entity.getWorld(), 
					(int)entity.getLocation().getX() + 1, 
					(int)entity.getLocation().getY(), 
					entity.getLocation().getZ(), entity.getLocation().getYaw(), 
					entity.getLocation().getPitch());
		} else if ((rot > 45) && (rot < 135)) {
			loc = new Location(entity.getWorld(), 
					entity.getLocation().getX(), 
					(int)entity.getLocation().getY(), 
					entity.getLocation().getZ() + 1, entity.getLocation().getYaw(), 
					entity.getLocation().getPitch());
		} else if ((rot > 135) && (rot < 225)) {
			loc = new Location(entity.getWorld(), 
					(int)entity.getLocation().getX() - 1, 
					(int)entity.getLocation().getY(), 
					entity.getLocation().getZ(), entity.getLocation().getYaw(), 
					entity.getLocation().getPitch());
		} else {
			loc = new Location(entity.getWorld(), 
					entity.getLocation().getX(), entity.getLocation().getY(),
					entity.getLocation().getZ() - 1, entity.getLocation().getYaw(), 
					entity.getLocation().getPitch());
		}

		if (entity.getWorld().getBlockAt(loc).getType() == Material.AIR) {
			entity.teleport(loc);
		}
	}

	@Override
	public List<ItemStack> getSpellComps() {
		List<ItemStack> cost = new ArrayList<ItemStack>();
		
		cost.add(new ItemStack(Material.STONE_SWORD, 1));
		
		return cost;
	}

	@Override
	public String getName() {
		return "Bash";
	}

	@Override
	public int getReqLevel() {
		return 7;
	}

	@Override
	public String getSkillClass() {
		return "Warrior";
	}

}
