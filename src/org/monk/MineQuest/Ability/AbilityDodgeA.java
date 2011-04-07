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
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.monk.MineQuest.Quester.Quester;
import org.monk.MineQuest.Quester.SkillClass.SkillClass;
import org.monk.MineQuest.Quester.SkillClass.Combat.Archer;

public class AbilityDodgeA extends Ability implements PassiveAbility, DefendingAbility {
	
	public AbilityDodgeA() {
	}
	
	@Override
	public List<ItemStack> getManaCost() {
		List<ItemStack> list = new ArrayList<ItemStack>();
		
		list.add(new ItemStack(288, 1));
		list.add(new ItemStack(288, 1));
		list.add(new ItemStack(288, 1));
		list.add(new ItemStack(288, 1));
		list.add(new ItemStack(288, 1));
		
		return list;
	}
	
	@Override
	public int getReqLevel() {
		return 1;
	}
	
	@Override
	public String getName() {
		return "Dodge";
	}
	
	@Override
	public int parseDefend(Quester quester, LivingEntity mob, int amount) {
		if (!enabled) return 0;
		
		Player player = quester.getPlayer();
		
		if (myclass.getGenerator().nextDouble() < (.01 + (.0025 * myclass.getLevel()))) {
			double rot = player.getLocation().getYaw() % 360;
			while (rot < 0) rot += 360;
			Location location = player.getLocation();
			
			if ((rot  < 45) && (rot > 315)) {
				player.sendMessage("Dodging 1");
				location = new Location(player.getWorld(), 
						(int)player.getLocation().getX() - 1, 
						(int)player.getLocation().getY(), 
						player.getLocation().getZ(), player.getLocation().getYaw(), 
						player.getLocation().getPitch());
			} else if ((rot > 45) && (rot < 135)) {
				player.sendMessage("Dodging 2");
				location = new Location(player.getWorld(), 
						player.getLocation().getX(), 
						(int)player.getLocation().getY(), 
						player.getLocation().getZ() - 1, player.getLocation().getYaw(), 
						player.getLocation().getPitch());
			} else if ((rot > 135) && (rot < 225)) {
				player.sendMessage("Dodging 3");
				location = new Location(player.getWorld(), 
						(int)player.getLocation().getX() + 1, 
						(int)player.getLocation().getY(), 
						player.getLocation().getZ(), player.getLocation().getYaw(), 
						player.getLocation().getPitch());
			} else {
				player.sendMessage("Dodging 4");
				location = new Location(player.getWorld(), 
						player.getLocation().getX(), 
						getNearestY(player.getWorld(), (int)player.getLocation().getX(), 
								(int)player.getLocation().getY(), 
								(int)player.getLocation().getZ()),
						player.getLocation().getZ() + 1, player.getLocation().getYaw(), 
						player.getLocation().getPitch());
			}
			if (player.getWorld().getBlockAt(location).getType() == Material.AIR) {
				player.teleport(location);
			}
			return amount;
		}

		return 0;
	}

	@Override
	public void castAbility(Quester quester, Location location,
			LivingEntity entity) {
		// Passive
	}

	@Override
	public SkillClass getClassType() {
		return new Archer();
	}
}
