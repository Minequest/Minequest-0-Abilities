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
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.monk.MineQuest.MineQuest;
import org.monk.MineQuest.Ability.Ability;
import org.monk.MineQuest.Event.Absolute.BlockCDEvent;
import org.monk.MineQuest.Quester.Quester;

public class AbilityFireArrow extends Ability {
	public AbilityFireArrow() {
		super();
		config = new int[] {0};
	}
	
	@Override
	public List<ItemStack> getManaCost() {
		List<ItemStack> list = new ArrayList<ItemStack>();
		
		list.add(new ItemStack(263, 1));
		
		return list;
	}
	
	@Override
	public int getReqLevel() {
		return 7;
	}
	
	@Override
	public String getName() {
		return "Fire Arrow";
	}
	
	@Override
	public void castAbility(Quester quester, Location location,
			LivingEntity entity) {
		Player player = quester.getPlayer();
		
		if ((entity != null) && (player.getInventory().getItemInHand().getTypeId() == 261)) {
			Location loc = entity.getLocation();
			Block block = player.getWorld().getBlockAt((int)loc.getX(), 
					getNearestY(location.getWorld(), (int)location.getX(), (int)location.getY(), (int)location.getZ()), 
					(int)loc.getZ());
			MineQuest.getEventParser().addEvent(new BlockCDEvent(10, 30000, block, Material.FIRE));
			
			if ((entity != null) && (config[0] > 0)) {
				MineQuest.damage(entity, config[0], quester);
			}
		} else {
			giveManaCost(player);
			player.sendMessage("Fire Arrow must be bound to a bow attack - Recommended that it is ranged");
			return;
		}
	}

	@Override
	public String getSkillClass() {
		return "Archer";
	}

}
