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
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.monksanctum.MineQuest.MineQuest;
import org.monksanctum.MineQuest.Ability.Ability;
import org.monksanctum.MineQuest.Event.Absolute.BlockCDEvent;
import org.monksanctum.MineQuest.Quester.Quester;

public class AbilityWallofFire extends Ability {

	public AbilityWallofFire() {
	}
	
	@Override
	public List<ItemStack> getSpellComps() {
		List<ItemStack> list = new ArrayList<ItemStack>();
		
		list.add(new ItemStack(263, 1));
		list.add(new ItemStack(263, 1));
		list.add(new ItemStack(263, 1));
		list.add(new ItemStack(3, 1));
		list.add(new ItemStack(3, 1));
		list.add(new ItemStack(3, 1));
		list.add(new ItemStack(3, 1));
		list.add(new ItemStack(3, 1));
		list.add(new ItemStack(3, 1));
		list.add(new ItemStack(3, 1));
		
		return list;
	}
	
	@Override
	public int getReqLevel() {
		return 5;
	}
	
	@Override
	public String getName() {
		return "Wall of Fire";
	}
	
	@Override
	public void castAbility(Quester quester, Location location,
			LivingEntity entity) {
		Player player = null;
		if (quester != null) {
			player = quester.getPlayer();
		}
		double rot = 0;
		if (player != null) {
			rot = player.getLocation().getYaw() % 360 - 90;
		} else {
			rot = entity.getLocation().getYaw() % 360 - 90;
		}
		if (player != null) {
			location = player.getLocation();
		} else {
			location = entity.getLocation();
		}
		int x_change, z_change;
		int x, z;
		int i;
		while (rot < 0) rot += 360;
		
		
		if ((rot  < 45) || (rot > 315)) {
			x_change = 0;
			z_change = 1;
			x = (int)location.getX() - 2;
			z = (int)location.getZ() - 3;
		} else if ((rot > 45) && (rot < 135)) {
			x_change = 1;
			z_change = 0;
			x = (int)location.getX() - 3;
			z = (int)location.getZ() - 2;
		} else if ((rot > 135) && (rot < 225)) {
			x_change = 0;
			z_change = 1;
			x = (int)location.getX() + 3;
			z = (int)location.getZ() - 3;
		} else {
			x_change = 1;
			z_change = 0;
			x = (int)location.getX() - 3;
			z = (int)location.getZ() + 3;
		}
		
		World world = location.getWorld();
		for (i = 0; i < 7; i++) {
			Block nblock = world.getBlockAt(x, getNearestY(location.getWorld(), x, (int)location.getY(), z), z);
			MineQuest.getEventQueue().addEvent(new BlockCDEvent(0, 60000, nblock, Material.FIRE));
			x += x_change;
			z += z_change;
		}
	}

	@Override
	public String getSkillClass() {
		return "WarMage";
	}

	@Override
	public int getIconLoc() {
		return 41;
	}

}
