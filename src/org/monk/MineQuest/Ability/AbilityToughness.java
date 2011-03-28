package org.monk.MineQuest.Ability;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.LivingEntity;
import org.bukkit.inventory.ItemStack;
import org.monk.MineQuest.Quester.Quester;
import org.monk.MineQuest.Quester.SkillClass.SkillClass;
import org.monk.MineQuest.Quester.SkillClass.Combat.Warrior;

public class AbilityToughness extends Ability implements DefendingAbility, PassiveAbility {
	private boolean damage;
	
	public AbilityToughness() {
		damage = false;
	}

	@Override
	public void castAbility(Quester quester, Location location,
			LivingEntity entity) {
		// Passive
	}

	@Override
	public SkillClass getClassType() {
		return new Warrior();
	}

	@Override
	public List<ItemStack> getManaCost() {
		List<ItemStack> cost = new ArrayList<ItemStack>();
		
		cost.add(new ItemStack(Material.GRILLED_PORK, 1));
		cost.add(new ItemStack(Material.GRILLED_PORK, 1));
		
		return cost;
	}

	@Override
	public String getName() {
		return "Toughness";
	}

	@Override
	public int getReqLevel() {
		return 10;
	}

	@Override
	public int parseDefend(Quester quester, LivingEntity mob, int amount) {
		if ((amount == 1) && !damage) {
			damage = true;
			return 0;
		}
		damage = false;
		
		return 1;
	}

}
