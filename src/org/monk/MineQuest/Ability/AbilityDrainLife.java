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
import org.monksanctum.MineQuest.MineQuest;
import org.monksanctum.MineQuest.Ability.Ability;
import org.monksanctum.MineQuest.Quester.Quester;

public class AbilityDrainLife extends Ability {
	
	public AbilityDrainLife() {
		super();
		config = new int[] {1, 0, 3, 100};
	}
	
	@Override
	public List<ItemStack> getSpellComps() {
		List<ItemStack> list = new ArrayList<ItemStack>();
		
		list.add(new ItemStack(348, 1));
		list.add(new ItemStack(348, 1));
		list.add(new ItemStack(348, 1));
		list.add(new ItemStack(348, 1));
		list.add(new ItemStack(348, 1));
		
		return list;
	}
	
	@Override
	public int getReqLevel() {
		return 8;
	}
	
	@Override
	public String getName() {
		return "Drain Life";
	}
	
	@Override
	public void castAbility(Quester quester, Location location,
			LivingEntity entity) {
		if (quester == null) return;
		Player player = quester.getPlayer();
		int min = config[0] + (int)(((double)config[1] / 100) * myclass.getCasterLevel());
		int max = config[2] + (int)(((double)config[3] / 100) * myclass.getCasterLevel());
		int drain = myclass.getGenerator().nextInt(1 + max - min) + min;
		if (entity != null) {
			MineQuest.damage(entity, drain, quester);
			if (quester != null) quester.setHealth(quester.getHealth() + drain);
		} else {
			player.sendMessage("Must be called on an Entity");
		}
	}

	@Override
	public String getSkillClass() {
		return "WarMage";
	}

	@Override
	public int getIconLoc() {
		return 9;
	}

}
