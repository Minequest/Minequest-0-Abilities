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
import org.bukkit.entity.LivingEntity;
import org.bukkit.inventory.ItemStack;
import org.monk.MineQuest.Ability.Ability;
import org.monk.MineQuest.Quester.Quester;

public class AbilityGrowWheat extends Ability {
	public AbilityGrowWheat() {
		super();
		config = new int[] {2, 3};
	}

	@Override
	public void castAbility(Quester quester, Location location,
			LivingEntity entity) {
		int x, z;
		
		for (z = -config[0]; z < config[1]; z++) {
			for (x = -config[0]; x < config[1]; x++) {
				if (location.getWorld().getBlockAt(
						(int)location.getX() + x, 
						(int)location.getY() - 1, 
						(int)location.getZ() + z).getType() == Material.SOIL) {
					location.getWorld().getBlockAt(
							(int)location.getX() + x, 
							(int)location.getY(), 
							(int)location.getZ() + z).setType(Material.CROPS);
					location.getWorld().getBlockAt(
							(int)location.getX() + x, 
							(int)location.getY(), 
							(int)location.getZ() + z).setData((byte)0x07);
				}
			}
		}
	}

	@Override
	public List<ItemStack> getManaCost() {
		List<ItemStack> cost = new ArrayList<ItemStack>();
		int i;
		
		for (i = 0; i < 10; i++) {
			cost.add(new ItemStack(Material.SEEDS, 1));
		}
		
		return cost;
	}

	@Override
	public String getName() {
		return "Grow Wheat";
	}

	@Override
	public int getReqLevel() {
		return 30;
	}

	@Override
	public String getSkillClass() {
		return "Farmer";
	}

}
