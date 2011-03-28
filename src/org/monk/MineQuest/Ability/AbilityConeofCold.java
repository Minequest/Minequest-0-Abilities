package org.monk.MineQuest.Ability;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.LivingEntity;
import org.bukkit.inventory.ItemStack;
import org.monk.MineQuest.Quester.Quester;
import org.monk.MineQuest.Quester.SkillClass.SkillClass;
import org.monk.MineQuest.Quester.SkillClass.Combat.WarMage;

public class AbilityConeofCold extends Ability {

	@Override
	public void castAbility(Quester quester, Location location,
			LivingEntity entity) {
		
	}

	@Override
	public SkillClass getClassType() {
		return new WarMage();
	}

	@Override
	public List<ItemStack> getManaCost() {
		List<ItemStack> cost = new ArrayList<ItemStack>();
		int i;
		
		for (i = 0; i < 10; i++) {
			cost.add(new ItemStack(Material.WATER_BUCKET, 1));
		}
		
		for (i = 0; i < 3; i++) {
			cost.add(new ItemStack(Material.SNOW_BLOCK, 1));
		}
		
		return cost;
	}

	@Override
	public String getName() {
		return "Cone of Cold";
	}

	@Override
	public int getReqLevel() {
		return 12;
	}

}
