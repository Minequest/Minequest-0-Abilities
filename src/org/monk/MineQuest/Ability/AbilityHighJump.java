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
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.Vector;
import org.monksanctum.MineQuest.Ability.Ability;
import org.monksanctum.MineQuest.Quester.Quester;

public class AbilityHighJump extends Ability {

	public AbilityHighJump() {
		super();
		config = new int[] {2, 50};
	}
	
	@Override
	public List<ItemStack> getSpellComps() {
		List<ItemStack> list = new ArrayList<ItemStack>();
		
		list.add(new ItemStack(288, 1));

		return list;
	}
	
	@Override
	public String getName() {
		return "High Jump";
	}
	
	@Override
	public int getReqLevel() {
		return 22;
	}
	
	@Override
	public void castAbility(Quester quester, Location location,
			LivingEntity entity) {
		if (quester == null) return;
		Player player = quester.getPlayer();
		Location locp = player.getLocation();
		double rot = Math.PI * (locp.getYaw() / -180);

		double scale = ((double)config[1]) / 100;
		Vector vel = new Vector(scale*(Math.sin(rot)), config[0], scale*(Math.cos(rot)));
		player.setVelocity(vel);
//		if ((loc.getBlock().getType() != Material.AIR) && (quester.canEdit(loc.getBlock()))) {
//			player.teleport(loc);
//		}
	}
	
	@Override
	public int getCastTime() {
		return 2000;
	}

	@Override
	public String getSkillClass() {
		return "Warrior";
	}
}
