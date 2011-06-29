package org.monk.MineQuest.Ability;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.LivingEntity;
import org.bukkit.inventory.ItemStack;
import org.monksanctum.MineQuest.MineQuest;
import org.monksanctum.MineQuest.Ability.Ability;
import org.monksanctum.MineQuest.Event.Absolute.BlockCDEvent;
import org.monksanctum.MineQuest.Quester.Quester;

public class AbilityLightningArrow extends Ability {
	public AbilityLightningArrow() {
		config = new int[] {2, 2};
	}

	@Override
	public void castAbility(Quester quester, Location location,
			LivingEntity entity) {
		location.getWorld().strikeLightning(location);
		MineQuest.getEventQueue().addEvent(new BlockCDEvent(10, 30000, 
				location.getBlock(), Material.FIRE));
		
		if (entity != null) {
			int level = MineQuest.getAdjustment();
			if (myclass != null) {
				level = myclass.getCasterLevel() / config[1];
			}
			
			MineQuest.damage(entity, level + config[0]);
		}
	}

	@Override
	public List<ItemStack> getSpellComps() {
		List<ItemStack> list = new ArrayList<ItemStack>();

		list.add(new ItemStack(Material.GLOWSTONE_DUST, 3));
		
		return list;
	}

	@Override
	public String getName() {
		return "LightningArrow";
	}

	@Override
	public int getReqLevel() {
		return 18;
	}

	@Override
	public String getSkillClass() {
		return "Archer";
	}

	@Override
	public int getIconLoc() {
		return 30;
	}

}
