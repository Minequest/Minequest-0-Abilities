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
import org.bukkit.inventory.ItemStack;
import org.monk.MineQuest.MineQuest;
import org.monk.MineQuest.Ability.Ability;
import org.monk.MineQuest.Event.Relative.AuraEvent;
import org.monk.MineQuest.Quester.Quester;

public class AbilityHealAura extends Ability {
	public AbilityHealAura() {
		super();
		config = new int[] {10000, 150000, 0, 25, 15};
	}
	
	@Override
	public List<ItemStack> getManaCost() {
		List<ItemStack> list = new ArrayList<ItemStack>();
		
		list.add(new ItemStack(297, 1));
		list.add(new ItemStack(297, 1));
		
		return list;
	}
	
	@Override
	public int getReqLevel() {
		return 8;
	}
	
	@Override
	public String getName() {
		return "Heal Aura";
	}
	
	@Override
	public void castAbility(Quester quester, Location location,
			LivingEntity entity) {
		int heal;
		if (myclass != null) {
			heal = config[2] + (int)(((double)config[3]) / 100 * myclass.getCasterLevel());
		} else {
			heal = config[2] + (int)(((double)config[3]) / 100 * MineQuest.getAdjustment() * MineQuest.getAdjustmentMultiplier());
		}
		MineQuest.getEventQueue().addEvent(new AuraEvent(quester, config[0], config[1], heal, true, config[4]));
	}

	@Override
	public String getSkillClass() {
		return "PeaceMage";
	}
}
