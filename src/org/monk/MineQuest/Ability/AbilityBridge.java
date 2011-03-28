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
import org.bukkit.block.Block;
import org.bukkit.entity.LivingEntity;
import org.bukkit.inventory.ItemStack;
import org.monk.MineQuest.Quester.Quester;
import org.monk.MineQuest.Quester.SkillClass.SkillClass;
import org.monk.MineQuest.Quester.SkillClass.Resource.Miner;

public class AbilityBridge extends Ability {
	
	@Override
	public List<ItemStack> getManaCost() {
		List<ItemStack> cost = new ArrayList<ItemStack>();
		
		cost.add(new ItemStack(Material.COBBLESTONE, 1));
		cost.add(new ItemStack(Material.COBBLESTONE, 1));
		cost.add(new ItemStack(Material.COBBLESTONE, 1));
		cost.add(new ItemStack(Material.COBBLESTONE, 1));
		cost.add(new ItemStack(Material.COBBLESTONE, 1));
		cost.add(new ItemStack(Material.COBBLESTONE, 1));
		cost.add(new ItemStack(Material.COBBLESTONE, 1));
		cost.add(new ItemStack(Material.COBBLESTONE, 1));
		cost.add(new ItemStack(Material.COBBLESTONE, 1));
		cost.add(new ItemStack(Material.COBBLESTONE, 1));
		cost.add(new ItemStack(Material.COBBLESTONE, 1));
		cost.add(new ItemStack(Material.COBBLESTONE, 1));
		cost.add(new ItemStack(Material.COBBLESTONE, 1));
		cost.add(new ItemStack(Material.COBBLESTONE, 1));
		cost.add(new ItemStack(Material.COBBLESTONE, 1));
		cost.add(new ItemStack(Material.COBBLESTONE, 1));
		cost.add(new ItemStack(Material.COBBLESTONE, 1));
		cost.add(new ItemStack(Material.COBBLESTONE, 1));
		cost.add(new ItemStack(Material.COBBLESTONE, 1));
		cost.add(new ItemStack(Material.COBBLESTONE, 1));
		cost.add(new ItemStack(Material.COBBLESTONE, 1));
		cost.add(new ItemStack(Material.COBBLESTONE, 1));
		cost.add(new ItemStack(Material.COBBLESTONE, 1));
		cost.add(new ItemStack(Material.COBBLESTONE, 1));
		cost.add(new ItemStack(Material.COBBLESTONE, 1));
		cost.add(new ItemStack(Material.COBBLESTONE, 1));
		cost.add(new ItemStack(Material.COBBLESTONE, 1));
		cost.add(new ItemStack(Material.COBBLESTONE, 1));
		cost.add(new ItemStack(Material.COBBLESTONE, 1));
		cost.add(new ItemStack(Material.COBBLESTONE, 1));
		
		return cost;
	}
	
	@Override
	public String getName() {
		return "Bridge";
	}

	@Override
	public void castAbility(Quester quester, Location location,
			LivingEntity entity) {
		int x_change = 0;
		int z_change = 0;
		int i;
		double rot = location.getYaw() % 360 - 90;
		Location loc_1 = new Location(location.getWorld(), (int)location.getX(),
				(int)(location.getY() - 1), (int)location.getZ());
		Location loc_2 = new Location(location.getWorld(), (int)location.getX(),
				(int)(location.getY() - 1), (int)location.getZ());
		while (rot < 0) rot += 360;
		
		if ((rot  < 45) || (rot > 315)) {
			x_change = -1;
			loc_2.setZ(loc_2.getZ() + 1);
		} else if ((rot > 45) && (rot < 135)) {
			z_change = -1;
			loc_2.setX(loc_2.getX() + 1);
		} else if ((rot > 135) && (rot < 225)) {
			x_change = 1;
			loc_2.setZ(loc_2.getZ() + 1);
		} else {
			z_change = 1;
			loc_2.setX(loc_2.getX() + 1);
		}
		
		for (i = 0; i < 15; i++) {
			loc_1.setX(loc_1.getX() + x_change);
			loc_1.setZ(loc_1.getZ() + z_change);
			loc_2.setX(loc_2.getX() + x_change);
			loc_2.setZ(loc_2.getZ() + z_change);

			Block block = loc_1.getWorld().getBlockAt(loc_1);
			if (block.getType() == Material.AIR) {
				if ((quester == null) || (quester.canEdit(block))) {
					block.setType(Material.COBBLESTONE);
				}
			}

			block = loc_2.getWorld().getBlockAt(loc_2);
			if (block.getType() == Material.AIR) {
				if ((quester == null) || (quester.canEdit(block))) {
					block.setType(Material.COBBLESTONE);
				}
			}
		}
	}

	@Override
	public SkillClass getClassType() {
		return new Miner();
	}

	@Override
	public int getReqLevel() {
		return 10;
	}

}
