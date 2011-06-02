package org.monk.MineQuest.Ability;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.LivingEntity;
import org.bukkit.inventory.ItemStack;
import org.monk.MineQuest.MineQuest;
import org.monk.MineQuest.Ability.Ability;
import org.monk.MineQuest.Ability.PurgeType;
import org.monk.MineQuest.Event.Absolute.EntityTeleportEvent;
import org.monk.MineQuest.Event.Relative.AuraEvent;
import org.monk.MineQuest.Quester.Quester;

public class AbilityWhirlwind extends Ability {
	public AbilityWhirlwind() {
		super();
		config = new int[] {9};
	}

	@Override
	public void castAbility(Quester quester, Location location,
			LivingEntity entity) {
		if (quester == null) return;
		Location loc = quester.getPlayer().getLocation();
		loc = new Location(loc.getWorld(),
				loc.getX(), loc.getY(), loc.getZ(),
				loc.getYaw(), loc.getPitch());
		int i;
		for (i = 0; i < 10; i++) {
			loc= new Location(loc.getWorld(), loc.getX(), loc.getY(), loc.getZ(), loc.getYaw() + (360 / 10), loc.getPitch());
			MineQuest.getEventQueue().addEvent(new EntityTeleportEvent(100 * (i + 1), quester, loc));
		}
		purgeEntities(quester.getPlayer(), 2, PurgeType.ALL);
		MineQuest.getEventQueue().addEvent(new AuraEvent(quester, 500, 500, -config[0], false, 3));
	}

	@Override
	public List<ItemStack> getManaCost() {
		List<ItemStack> cost = new ArrayList<ItemStack>();
		
		cost.add(new ItemStack(Material.STONE_SWORD, 1));
		cost.add(new ItemStack(Material.FEATHER, 1));
		cost.add(new ItemStack(Material.FEATHER, 1));
		
		return cost;
	}

	@Override
	public String getName() {
		return "Whirlwind";
	}

	@Override
	public int getReqLevel() {
		return 15;
	}

	@Override
	public String getSkillClass() {
		return "Warrior";
	}

}
