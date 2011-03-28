package org.monk.MineQuest.Ability;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.LivingEntity;
import org.bukkit.inventory.ItemStack;
import org.monk.MineQuest.MineQuest;
import org.monk.MineQuest.Event.Absolute.BlockEvent;
import org.monk.MineQuest.Quester.Quester;
import org.monk.MineQuest.Quester.SkillClass.SkillClass;
import org.monk.MineQuest.Quester.SkillClass.Combat.WarMage;

public class AbilityEncase extends Ability {

	@Override
	public void castAbility(Quester quester, Location location,
			LivingEntity entity) {
		double leftx, leftz;
		int x, z;
		
		leftx = location.getX() - (double)((int)location.getX());
		leftz = location.getZ() - (double)((int)location.getZ());
		if (leftx < 0) leftx += 1;
		if (leftz < 0) leftz += 1;
		MineQuest.log(leftx + " " + leftz);
		x = (leftx < .5)?-1:1;
		z = (leftz < .5)?-1:1;
		
		// In progress
		if (x > 0) {
			MineQuest.log("1");
			Location loc = new Location(location.getWorld(),
					location.getX() + 2,
					location.getY(),
					location.getZ());
			encase(loc.getWorld().getBlockAt(loc), true);
			loc = new Location(location.getWorld(),
					location.getX() + 2,
					location.getY(),
					location.getZ() + z);
			encase(loc.getWorld().getBlockAt(loc), true);
			loc = new Location(location.getWorld(),
					location.getX() - 1,
					location.getY(),
					location.getZ() + z);
			encase(loc.getWorld().getBlockAt(loc), true);
			loc = new Location(location.getWorld(),
					location.getX() - 1,
					location.getY(),
					location.getZ());
			encase(loc.getWorld().getBlockAt(loc), true);
		} else {
			MineQuest.log("2");
			Location loc = new Location(location.getWorld(),
					location.getX() + 1,
					location.getY(),
					location.getZ());
			encase(loc.getWorld().getBlockAt(loc), true);
			loc = new Location(location.getWorld(),
					location.getX() + 1,
					location.getY(),
					location.getZ() + z);
			encase(loc.getWorld().getBlockAt(loc), true);
			loc = new Location(location.getWorld(),
					location.getX() - 2,
					location.getY(),
					location.getZ() + z);
			encase(loc.getWorld().getBlockAt(loc), true);
			loc = new Location(location.getWorld(),
					location.getX() - 2,
					location.getY(),
					location.getZ());
			encase(loc.getWorld().getBlockAt(loc), true);
		}
		
		// In progress
		if (z > 0) {
			MineQuest.log("3");
			Location loc = new Location(location.getWorld(),
					location.getX(),
					location.getY(),
					location.getZ() + 2);
			encase(loc.getWorld().getBlockAt(loc), true);
			loc = new Location(location.getWorld(),
					location.getX() + x,
					location.getY(),
					location.getZ() + 2);
			encase(loc.getWorld().getBlockAt(loc), true);
			loc = new Location(location.getWorld(),
					location.getX() + x,
					location.getY(),
					location.getZ() - 1);
			encase(loc.getWorld().getBlockAt(loc), true);
			loc = new Location(location.getWorld(),
					location.getX(),
					location.getY(),
					location.getZ() - 1);
			encase(loc.getWorld().getBlockAt(loc), true);
		} else {
			MineQuest.log("4");
			Location loc = new Location(location.getWorld(),
					location.getX(),
					location.getY(),
					location.getZ() + 1);
			encase(loc.getWorld().getBlockAt(loc), true);
			loc = new Location(location.getWorld(),
					location.getX() + x,
					location.getY(),
					location.getZ() + 1);
			encase(loc.getWorld().getBlockAt(loc), true);
			loc = new Location(location.getWorld(),
					location.getX() + x,
					location.getY(),
					location.getZ() - 2);
			encase(loc.getWorld().getBlockAt(loc), true);
			loc = new Location(location.getWorld(),
					location.getX(),
					location.getY(),
					location.getZ() - 2);
			encase(loc.getWorld().getBlockAt(loc), true);
		}
		
		Location loc = new Location(location.getWorld(),
				location.getX(),
				location.getY() + 2,
				location.getZ());
		encase(loc.getWorld().getBlockAt(loc), false);
		loc = new Location(location.getWorld(),
				location.getX() + x,
				location.getY() + 2,
				location.getZ());
		encase(loc.getWorld().getBlockAt(loc), false);
		loc = new Location(location.getWorld(),
				location.getX() + x,
				location.getY() + 2,
				location.getZ() + z);
		encase(loc.getWorld().getBlockAt(loc), false);
		loc = new Location(location.getWorld(),
				location.getX(),
				location.getY() + 2,
				location.getZ() + z);
		encase(loc.getWorld().getBlockAt(loc), false);
	}

	private void encase(Block block, boolean tall) {
		Material mat = block.getType();
		MineQuest.getEventParser().addEvent(new BlockEvent(15000, block, mat));
		MineQuest.getEventParser().addEvent(new BlockEvent(10, block, Material.COBBLESTONE));
		if (tall) {
			Location loc = new Location(block.getWorld(),
					block.getLocation().getX(),
					block.getLocation().getY() + 1,
					block.getLocation().getZ());
			encase(loc.getWorld().getBlockAt(loc), false);
		}
	}

	@Override
	public SkillClass getClassType() {
		return new WarMage();
	}

	@Override
	public List<ItemStack> getManaCost() {
		List<ItemStack> cost = new ArrayList<ItemStack>();
		int i;
		
		for (i = 0; i < 10; i++) {
			cost.add(new ItemStack(Material.COBBLESTONE, 1));
		}
		
		return cost;
	}

	@Override
	public String getName() {
		return "Encase";
	}

	@Override
	public int getReqLevel() {
		return 20;
	}

}
