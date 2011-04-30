package org.monk.MineQuest.Ability;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.LivingEntity;
import org.bukkit.inventory.ItemStack;
import org.monk.MineQuest.MineQuest;
import org.monk.MineQuest.Event.Absolute.BlockCDEvent;
import org.monk.MineQuest.Quester.Quester;
import org.monk.MineQuest.Quester.SkillClass.SkillClass;
import org.monk.MineQuest.Quester.SkillClass.Combat.Archer;

public class AbilityLightningArrow extends Ability{

	@Override
	public void castAbility(Quester quester, Location location,
			LivingEntity entity) {
		location.getWorld().strikeLightning(location);
		MineQuest.getEventParser().addEvent(new BlockCDEvent(10, 30000, 
				location.getBlock(), Material.FIRE));
		
		if (entity != null) {
			int level = MineQuest.getAdjustment();
			if (myclass != null) {
				level = myclass.getCasterLevel() / 2;
			}
			
			MineQuest.damage(entity, level + 2);
		}
	}

	@Override
	public SkillClass getClassType() {
		return new Archer();
	}

	@Override
	public List<ItemStack> getManaCost() {
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

}
