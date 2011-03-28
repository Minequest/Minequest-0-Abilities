package org.monk.MineQuest.Ability;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.LivingEntity;
import org.bukkit.inventory.ItemStack;
import org.monk.MineQuest.MineQuest;
import org.monk.MineQuest.Quester.Quester;
import org.monk.MineQuest.Quester.SkillClass.SkillClass;
import org.monk.MineQuest.Quester.SkillClass.Combat.Archer;

public class AbilityCriticalShot extends Ability {

	@Override
	public void castAbility(Quester quester, Location location,
			LivingEntity entity) {
		if (entity != null) {
			MineQuest.damage(entity, 10);
		} else {
			giveManaCost(quester.getPlayer());
			notify(quester, "Must be bound to an attack");
		}
	}
	
	@Override
	public int getCastTime() {
		return 500;
	}

	@Override
	public SkillClass getClassType() {
		return new Archer();
	}

	@Override
	public List<ItemStack> getManaCost() {
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

}
