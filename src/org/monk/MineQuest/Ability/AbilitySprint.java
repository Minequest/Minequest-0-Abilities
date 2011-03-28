/*
 * MineQuest - Bukkit Plugin for adding RPG characteristics to minecraft
 * Copyright (C) 2011  Jason Monk
 * 
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * as published by the Free Software Foundation; either version 2
 * of the License, or (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301, USA.
 */
package org.monk.MineQuest.Ability;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Location;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.monk.MineQuest.Quester.Quester;
import org.monk.MineQuest.Quester.SkillClass.SkillClass;
import org.monk.MineQuest.Quester.SkillClass.Combat.Warrior;

public class AbilitySprint extends Ability {

	public AbilitySprint() {
	}
	
	@Override
	public List<ItemStack> getManaCost() {
		List<ItemStack> list = new ArrayList<ItemStack>();
		
		list.add(new ItemStack(288, 1));

		return list;
	}
	
	@Override
	public String getName() {
		return "Sprint";
	}
	
	@Override
	public int getReqLevel() {
		return 5;
	}
	
	@Override
	public void castAbility(Quester quester, Location location,
			LivingEntity entity) {
		if (quester == null) return;
		Player player = quester.getPlayer();
		
		Location loc = player.getLocation();
		double rot = loc.getYaw() % 360 - 90;
		while (rot < 0) rot += 360;
		
		if ((rot  < 45) || (rot > 315)) {
			loc.setX(loc.getX() - 1);
		} else if ((rot > 45) && (rot < 135)) {
			loc.setZ(loc.getZ() - 1);
		} else if ((rot > 135) && (rot < 225)) {
			loc.setX(loc.getX() + 1);
		} else {
			loc.setZ(loc.getZ() + 1);
		}
		if (quester.canEdit(location.getWorld().getBlockAt(location))) {
			player.teleportTo(loc);
		}
	}

	@Override
	public SkillClass getClassType() {
		return new Warrior();
	}

}
