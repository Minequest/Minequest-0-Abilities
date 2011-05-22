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
package org.monk.MineQuest.Ability.WarMage;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.monk.MineQuest.MineQuest;
import org.monk.MineQuest.Ability.Ability;
import org.monk.MineQuest.Event.Absolute.BlockCDEvent;
import org.monk.MineQuest.Quester.Quester;

public class AbilityWallofLava extends Ability {
	
	@Override
	public List<ItemStack> getManaCost() {
		List<ItemStack> list = new ArrayList<ItemStack>();
		
		list.add(new ItemStack(Material.LAVA_BUCKET, 1));
		list.add(new ItemStack(Material.LAVA_BUCKET, 1));
		list.add(new ItemStack(Material.DIRT, 1));
		list.add(new ItemStack(Material.DIRT, 1));
		list.add(new ItemStack(Material.DIRT, 1));
		list.add(new ItemStack(Material.DIRT, 1));
		list.add(new ItemStack(Material.DIRT, 1));
		list.add(new ItemStack(Material.DIRT, 1));
		list.add(new ItemStack(Material.DIRT, 1));
		list.add(new ItemStack(Material.DIRT, 1));
		
		return list;
	}
	
	@Override
	public int getReqLevel() {
		return 16;
	}
	
	@Override
	public String getName() {
		return "Wall of Lava";
	}
	
	@Override
	public void castAbility(Quester quester, Location location,
			LivingEntity entity) {
		if (quester == null) return;
		Player player = quester.getPlayer();
		double rot = player.getLocation().getYaw() % 360 - 90;
		int x_change = 0, z_change = 0;
		int dirt_x = 0, dirt_z = 0;
		int x, z;
		int i;
		
		player.getInventory().addItem(new ItemStack(325, 1));
		player.getInventory().addItem(new ItemStack(325, 1));
		
		while (rot < 0) rot += 360;
		
		
		if ((rot  < 45) || (rot > 315)) {
			z_change = 1;
			dirt_x = 1;
			x = (int)player.getLocation().getX() - 2;
			z = (int)player.getLocation().getZ() - 3;
		} else if ((rot > 45) && (rot < 135)) {
			x_change = 1;
			dirt_z = 1;
			x = (int)player.getLocation().getX() - 3;
			z = (int)player.getLocation().getZ() - 2;
		} else if ((rot > 135) && (rot < 225)) {
			z_change = 1;
			dirt_x = -1;
			x = (int)player.getLocation().getX() + 3;
			z = (int)player.getLocation().getZ() - 3;
		} else {
			x_change = 1;
			dirt_z = -1;
			x = (int)player.getLocation().getX() - 3;
			z = (int)player.getLocation().getZ() + 3;
		}

		World world = player.getWorld();
		for (i = 0; i < 7; i++) {
			Block nblock = world.getBlockAt(x, getNearestY(player.getWorld(), x, (int)player.getLocation().getY(), z), z);
			Block dirt_block = world.getBlockAt(x + dirt_x, getNearestY(player.getWorld(), x + dirt_x, (int)player.getLocation().getY(), z + dirt_z), z + dirt_z);
			MineQuest.getEventParser().addEvent(new BlockCDEvent(0, 60000, nblock, Material.LAVA));
			MineQuest.getEventParser().addEvent(new BlockCDEvent(0, 60000, dirt_block, Material.DIRT));
			x += x_change;
			z += z_change;
		}
	}

}
