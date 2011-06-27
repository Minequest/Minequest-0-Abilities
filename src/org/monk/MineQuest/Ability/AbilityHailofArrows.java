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
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.Vector;
import org.monksanctum.MineQuest.Ability.Ability;
import org.monksanctum.MineQuest.Quester.Quester;

public class AbilityHailofArrows extends Ability {
	
	@Override
	public List<ItemStack> getSpellComps() {
		List<ItemStack> list = new ArrayList<ItemStack>();

		list.add(new ItemStack(262, 1));
		list.add(new ItemStack(262, 1));
		list.add(new ItemStack(262, 1));
		list.add(new ItemStack(262, 1));
		list.add(new ItemStack(262, 1));
		list.add(new ItemStack(262, 1));
		list.add(new ItemStack(262, 1));
		list.add(new ItemStack(262, 1));
		list.add(new ItemStack(262, 1));
		list.add(new ItemStack(262, 1));
		
		return list;
	}
	
	@Override
	public int getReqLevel() {
		return 5;
	}
	
	@Override
	public String getName() {
		return "Hail of Arrows";
	}
	
	@Override
	public void castAbility(Quester quester, Location location,
			LivingEntity entity) {
		if (entity != null) {
			Location start = entity.getLocation();
			start.setZ(start.getZ() - 3);
			start.setY(start.getY() + 5);
			Vector vel = new Vector(0, -5, 0);
			int i, j;
			
			for (i = 0; i < 3; i++) {
				start.setX(entity.getLocation().getX() - 3);
				for (j = 0; j < 3; j++) {
					entity.getWorld().spawnArrow(start, vel, (float).8, (float)12.0);
					start.setX(start.getX() + 3);
				}
				start.setZ(start.getZ() + 3);
			}
		} else {
			giveCost(quester.getPlayer());
			quester.getPlayer().sendMessage("Hail of Arrows must be bound to an attack - Recommended that it is ranged");
			return;
		}
	}

	@Override
	public String getSkillClass() {
		return "Archer";
	}

}
