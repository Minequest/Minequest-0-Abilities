package org.monk.MineQuest.Ability;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.monk.MineQuest.Quester.Quester;
import org.monk.MineQuest.Quester.SkillClass.SkillClass;
import org.monk.MineQuest.Quester.SkillClass.Combat.PeaceMage;

public class AbilityTeleport extends Ability {

	@Override
	public void castAbility(Quester quester, Location location,
			LivingEntity entity) {
		if (quester == null) return;
		Player player = quester.getPlayer();
		
		Location loc = player.getLocation();
		double rot = loc.getYaw() % 360 - 90;
		while (rot < 0) rot += 360;
		
		if ((rot  < 45) || (rot > 315)) {
			loc.setX(loc.getX() - 40);
		} else if ((rot > 45) && (rot < 135)) {
			loc.setZ(loc.getZ() - 40);
		} else if ((rot > 135) && (rot < 225)) {
			loc.setX(loc.getX() + 40);
		} else {
			loc.setZ(loc.getZ() + 40);
		}
		loc.setY(getNearestY(loc.getWorld(), (int)loc.getX(), (int)loc.getY(), (int)loc.getZ()));
		if (quester.canEdit(location.getWorld().getBlockAt(location))) {
			player.teleportTo(loc);
		}
	}

	@Override
	public SkillClass getClassType() {
		return new PeaceMage();
	}

	@Override
	public List<ItemStack> getManaCost() {
		List<ItemStack> cost = new ArrayList<ItemStack>();
		
		cost.add(new ItemStack(Material.SIGN, 1));
		
		return cost;
	}

	@Override
	public String getName() {
		return "Teleport";
	}

	@Override
	public int getReqLevel() {
		return 15;
	}

}
