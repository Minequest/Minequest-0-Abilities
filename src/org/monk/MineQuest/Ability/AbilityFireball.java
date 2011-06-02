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
import org.monk.MineQuest.MineQuest;
import org.monk.MineQuest.Ability.Ability;
import org.monk.MineQuest.Event.Absolute.BlockCDEvent;
import org.monk.MineQuest.Quester.Quester;

public class AbilityFireball extends Ability {

	private Location location;
	private Quester quester;
	private LivingEntity entity;
	
	public AbilityFireball() {
		super();
		config = new int[] {2, 3};
	}

	@Override
	public List<ItemStack> getManaCost() {
		List<ItemStack> list = new ArrayList<ItemStack>();

		list.add(new ItemStack(Material.COAL, 1));
		
		return list;
	}
	
	@Override
	public String getName() {
		return "Fireball";
	}
	
	public void setCast(Quester quester, Location location, LivingEntity entity)
	{
		this.quester = quester;
		this.location = location;
		this.entity = entity;
	}
	
	@Override
	public void eventActivate() {
		castAbility(quester, location, entity);
		
		super.eventActivate();
	}
	
	@Override
	public void castAbility(Quester quester, Location location,
			LivingEntity entity) {
		Player player = null;
		World world;
		if (quester != null) {
			player = quester.getPlayer();
		}
		
		if (player != null) {
			world = player.getWorld();
		} else {
			world = entity.getWorld();
		}
		
		double leftx, leftz;
		int x, z;
		if ((location.getX() == 0) && (location.getY() == 0) && (location.getZ() == 0)) {
			giveManaCost(player);
			return;
		}
		
		leftx = location.getX() % 1;
		leftz = location.getZ() % 1;
		x = (leftx < .5)?-1:1;
		z = (leftz < .5)?-1:1;
		
		Block nblock = world.getBlockAt((int)location.getX(), 
				getNearestY(location.getWorld(), (int)location.getX(), (int)location.getY(), (int)location.getZ()), 
				(int)location.getZ());
		MineQuest.getEventQueue().addEvent(new BlockCDEvent(10, 30000, nblock, Material.FIRE));
		
		nblock = world.getBlockAt((int)location.getX() + x, 
				getNearestY(location.getWorld(), (int)location.getX() + x, (int)location.getY(), (int)location.getZ()), 
				(int)location.getZ());
		MineQuest.getEventQueue().addEvent(new BlockCDEvent(10, 30000, nblock, Material.FIRE));
		
		nblock = world.getBlockAt((int)location.getX() + x, 
				getNearestY(location.getWorld(), (int)location.getX() + x, (int)location.getY(), (int)location.getZ() + z), 
				(int)location.getZ() + z);
		MineQuest.getEventQueue().addEvent(new BlockCDEvent(10, 30000, nblock, Material.FIRE));
		
		nblock = world.getBlockAt((int)location.getX(), 
				getNearestY(location.getWorld(), (int)location.getX(), (int)location.getY(), (int)location.getZ() + z), 
				(int)location.getZ() + z);
		MineQuest.getEventQueue().addEvent(new BlockCDEvent(10, 30000, nblock, Material.FIRE));
		
		if (entity != null) {
			int level = MineQuest.getAdjustment();
			if (myclass != null) {
				level = myclass.getCasterLevel() / config[1];
			}
			MineQuest.damage(entity, config[0] + level);
		}
	}

	@Override
	public int getReqLevel() {
		return 0;
	}

	@Override
	public String getSkillClass() {
		return "WarMage";
	}
}
