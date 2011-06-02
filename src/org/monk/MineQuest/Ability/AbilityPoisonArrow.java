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
import org.monk.MineQuest.MineQuest;
import org.monk.MineQuest.Ability.Ability;
import org.monk.MineQuest.Event.PoisonEvent;
import org.monk.MineQuest.Quester.Quester;

public class AbilityPoisonArrow extends Ability {
	public AbilityPoisonArrow() {
		super();
		config = new int[] {1500, 1, 10};
	}

	@Override
	public void castAbility(Quester quester, Location location,
			LivingEntity entity) {
		if (entity != null) {
			MineQuest.getEventQueue().addEvent(new PoisonEvent(config[0], entity, config[1], config[2]));
		} else {
			giveManaCost(quester.getPlayer());
			quester.sendMessage("Must be bound to an attack");
		}
	}

	@Override
	public List<ItemStack> getManaCost() {
		List<ItemStack> cost = new ArrayList<ItemStack>();
		
		cost.add(new ItemStack(Material.PORK, 1));
		
		return cost;
	}

	@Override
	public String getName() {
		return "Poison Arrow";
	}

	@Override
	public int getReqLevel() {
		return 12;
	}

	@Override
	public String getSkillClass() {
		return "Archer";
	}

}
