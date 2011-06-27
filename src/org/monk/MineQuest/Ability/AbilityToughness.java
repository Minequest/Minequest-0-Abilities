package org.monk.MineQuest.Ability;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.LivingEntity;
import org.bukkit.inventory.ItemStack;
import org.monksanctum.MineQuest.Ability.Ability;
import org.monksanctum.MineQuest.Ability.DefendingAbility;
import org.monksanctum.MineQuest.Ability.PassiveAbility;
import org.monksanctum.MineQuest.Quester.Quester;

public class AbilityToughness extends Ability implements DefendingAbility, PassiveAbility {
	private boolean damage;
	
	public AbilityToughness() {
		super();
		config = new int[] {1};
		damage = false;
	}

	@Override
	public void castAbility(Quester quester, Location location,
			LivingEntity entity) {
		// Passive
	}

	@Override
	public List<ItemStack> getSpellComps() {
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
		
		return config[0];
	}

	@Override
	public String getSkillClass() {
		return "Warrior";
	}

}
