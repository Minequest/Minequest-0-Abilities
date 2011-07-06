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

public class AbilityCriticalShot extends Ability {
	public AbilityCriticalShot() {
		super();
		config = new int[] {10};
	}

	@Override
	public void castAbility(Quester quester, Location location,
			LivingEntity entity) {
		if (entity != null) {
			MineQuest.damage(entity, config[0], quester);
		} else {
			giveCost(quester.getPlayer());
			notify(quester, "Must be bound to an attack");
		}
	}
	
	@Override
	public int getCastTime() {
		return 500;
	}

	@Override
	public List<ItemStack> getSpellComps() {
		List<ItemStack> cost = new ArrayList<ItemStack>();
		
		cost.add(new ItemStack(Material.ARROW, 1));
		cost.add(new ItemStack(Material.ARROW, 1));
		
		return cost;
	}

	@Override
	public String getName() {
		return "Critical Shot";
	}

	@Override
	public int getReqLevel() {
		return 20;
	}

	@Override
	public String getSkillClass() {
		return "Archer";
	}

	@Override
	public int getIconLoc() {
		return 2;
	}

}
