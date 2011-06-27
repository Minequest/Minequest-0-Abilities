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
import org.bukkit.Material;
import org.bukkit.TreeType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.inventory.ItemStack;
import org.monksanctum.MineQuest.Ability.Ability;
import org.monksanctum.MineQuest.Quester.Quester;

public class AbilityGrowTrees extends Ability {

	@Override
	public void castAbility(Quester quester, Location location,
			LivingEntity entity) {
		Location loc = new Location(location.getWorld(), location.getX() - 2,
				location.getY(), location.getZ() - 2);
		Location loc_2 = new Location(location.getWorld(), location.getX() - 2,
				location.getY(), location.getZ() + 2);
		Location loc_3 = new Location(location.getWorld(), location.getX() + 2,
				location.getY(), location.getZ() + 2);
		Location loc_4 = new Location(location.getWorld(), location.getX() + 2,
				location.getY(), location.getZ() - 2);
		
		loc.getWorld().generateTree(loc, TreeType.TREE);
		loc.getWorld().generateTree(loc_2, TreeType.TREE);
		loc.getWorld().generateTree(loc_3, TreeType.TREE);
		loc.getWorld().generateTree(loc_4, TreeType.TREE);
	}

	@Override
	public List<ItemStack> getSpellComps() {
		List<ItemStack> cost = new ArrayList<ItemStack>();
		
		cost.add(new ItemStack(Material.SAPLING, 1));
		cost.add(new ItemStack(Material.SAPLING, 1));
		cost.add(new ItemStack(Material.SAPLING, 1));
		cost.add(new ItemStack(Material.SAPLING, 1));
		
		return cost;
	}

	@Override
	public String getName() {
		return "Grow Trees";
	}

	@Override
	public int getReqLevel() {
		return 30;
	}

	@Override
	public String getSkillClass() {
		return "Lumberjack";
	}

}
