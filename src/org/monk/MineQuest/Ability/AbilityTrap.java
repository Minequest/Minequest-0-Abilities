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
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.entity.LivingEntity;
import org.bukkit.inventory.ItemStack;
import org.monksanctum.MineQuest.Ability.Ability;
import org.monksanctum.MineQuest.Quester.Quester;

public class AbilityTrap extends Ability {

	public AbilityTrap() {
	}
	
	@Override
	public List<ItemStack> getSpellComps() {
		List<ItemStack> list = new ArrayList<ItemStack>();
		
		list.add(new ItemStack(3, 1));
		list.add(new ItemStack(3, 1));
		list.add(new ItemStack(3, 1));
		list.add(new ItemStack(3, 1));
		list.add(new ItemStack(3, 1));
		list.add(new ItemStack(3, 1));
		list.add(new ItemStack(269, 1));
		
		return list;
	}
	
	@Override
	public int getReqLevel() {
		return 2;
	}
	
	@Override
	public String getName() {
		return "Trap";
	}
	
	@Override
	public int getCastTime() {
		return 2500;
	}
	
	@Override
	public void castAbility(Quester quester, Location location,
			LivingEntity entity) {
		int i, j, k;
		int x, y, z;
		World world = location.getWorld();
		x = (int)location.getX();
		y = (int)location.getY();
		z = (int)location.getZ();
		
		for (i = 1; i < 3; i++) {
			for (j = -1; j < 2; j++) {
				for (k = -1; k < 2; k++) {
					Block nblock = world.getBlockAt(x + j, y - i, z + k);
					if ((quester == null) || (quester.canEdit(nblock))) {
						if (nblock.getType() != Material.BEDROCK) {
							nblock.setTypeId(0);
						}
					}
				}
			}
		}
	}

	@Override
	public String getSkillClass() {
		return "WarMage";
	}

}
