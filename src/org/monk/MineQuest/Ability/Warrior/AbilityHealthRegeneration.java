package org.monk.MineQuest.Ability.Warrior;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.LivingEntity;
import org.bukkit.inventory.ItemStack;
import org.monk.MineQuest.MineQuest;
import org.monk.MineQuest.Ability.Ability;
import org.monk.MineQuest.Ability.PassiveAbility;
import org.monk.MineQuest.Event.AbilityEvent;
import org.monk.MineQuest.Event.Relative.AuraEvent;
import org.monk.MineQuest.Quester.Quester;

public class AbilityHealthRegeneration extends Ability implements PassiveAbility {
	
	@Override
	public void enable(Quester quester) {
		super.enable(quester);
		MineQuest.getEventParser().addEvent(new AbilityEvent(300000, this));
		MineQuest.getEventParser().addEvent(new AuraEvent(quester, 5000, 300000, 1, true, 0));
	}
	
	@Override
	public void eventActivate() {
		super.eventActivate();
		disable();
		myclass.getQuester().sendMessage("Health Regeneration Complete!");
	}

	@Override
	public void castAbility(Quester quester, Location location,
			LivingEntity entity) {
		// Passive
	}

	@Override
	public List<ItemStack> getManaCost() {
		List<ItemStack> list = new ArrayList<ItemStack>();

		list.add(new ItemStack(Material.BREAD, 1));
		list.add(new ItemStack(Material.BREAD, 1));
		
		return list;
	}

	@Override
	public String getName() {
		return "Health Regeneration";
	}

	@Override
	public int getReqLevel() {
		return 20;
	}

}
