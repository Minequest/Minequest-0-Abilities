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
package org.monk.MineQuest.Ability.Warrior;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.Vector;
import org.monk.MineQuest.Ability.Ability;
import org.monk.MineQuest.Quester.Quester;

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
		Location locp = player.getLocation();
		Location loc = new Location(locp.getWorld(), locp.getX(), locp.getY(), locp.getZ(), locp.getYaw(), locp.getPitch());
		double rot = Math.PI * (loc.getYaw() / -180);
//		while (rot < 0) rot += 360;
		
		//yaw = (float)(-180 * Math.atan2(move_x , move_z) / Math.PI);
//		loc.setX(loc.getX() + Math.sin(rot));
//		loc.setZ(loc.getZ() + Math.cos(rot));
//		double difference = Ability.getNearestY(loc.getWorld(), (int) loc.getX(),
//				(int) loc.getY(), (int) loc.getZ())
//				- loc.getY();
//		if (Math.abs(difference) < 2) {
//			loc.setY(loc.getY() + difference);
//		}
		loc.setX(loc.getX() + Math.sin(rot));
		loc.setZ(loc.getZ() + Math.cos(rot));
		double difference = Ability.getNearestY(loc.getWorld(), (int) loc.getX(),
				(int) loc.getY(), (int) loc.getZ())
				- loc.getY();
		if (Math.abs(difference) < 2) {
			loc.setY(loc.getY() + difference);
		}
		Vector vel = new Vector(2*(loc.getX() - locp.getX()), .5*(loc.getY() - locp.getY()), 2*(loc.getZ() - locp.getZ()));
		player.setVelocity(vel);
		if ((loc.getBlock().getType() != Material.AIR) && (quester.canEdit(loc.getBlock()))) {
			player.teleport(loc);
		}
//		if (quester.canEdit(location.getWorld().getBlockAt(location))) {
//			player.teleport(loc);
//		}
	}

}
