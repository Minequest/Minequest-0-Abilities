package org.monk.MineQuest.Ability;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.entity.Creeper;
import org.bukkit.entity.LivingEntity;
import org.bukkit.inventory.ItemStack;
import org.monk.MineQuest.MineQuest;
import org.monk.MineQuest.Ability.Ability;
import org.monk.MineQuest.Event.Absolute.HealthEvent;
import org.monk.MineQuest.Event.Relative.ExplosionEvent;
import org.monk.MineQuest.Quester.Quester;

public class AbilityExplodeCreepers extends Ability {

	@Override
	public void castAbility(Quester quester, Location location,
			LivingEntity entity) {
		World world;
		if (quester != null) {
			world = quester.getPlayer().getWorld();
		} else {
			world = entity.getWorld();
		}
		
		for (LivingEntity lentity : getEntities((quester != null)?quester.getPlayer():entity, 15)) {
			if (lentity instanceof Creeper) {
				MineQuest.getEventParser().addEvent(new ExplosionEvent(10, world, lentity, 0, 0, 0, 4, 2));
				MineQuest.getEventParser().addEvent(new HealthEvent(20, lentity, 0));
			}
		}
	}

	@Override
	public List<ItemStack> getManaCost() {
		List<ItemStack> cost = new ArrayList<ItemStack>();
		
		cost.add(new ItemStack(Material.SULPHUR, 1));
		cost.add(new ItemStack(Material.SULPHUR, 1));
		cost.add(new ItemStack(Material.SULPHUR, 1));
		
		return cost;
	}

	@Override
	public String getName() {
		return "Explode Creeper";
	}

	@Override
	public int getReqLevel() {
		return 15;
	}

	@Override
	public String getSkillClass() {
		return "PeaceMage";
	}

}
