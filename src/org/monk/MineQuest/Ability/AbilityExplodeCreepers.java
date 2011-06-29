package org.monk.MineQuest.Ability;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.entity.Creeper;
import org.bukkit.entity.LivingEntity;
import org.bukkit.inventory.ItemStack;
import org.monksanctum.MineQuest.MineQuest;
import org.monksanctum.MineQuest.Ability.Ability;
import org.monksanctum.MineQuest.Event.Absolute.HealthEvent;
import org.monksanctum.MineQuest.Event.Relative.ExplosionEvent;
import org.monksanctum.MineQuest.Quester.Quester;

public class AbilityExplodeCreepers extends Ability {
	public AbilityExplodeCreepers() {
		super();
		config = new int[] {15, 4, 2};
	}

	@Override
	public void castAbility(Quester quester, Location location,
			LivingEntity entity) {
		World world;
		if (quester != null) {
			world = quester.getPlayer().getWorld();
		} else {
			world = entity.getWorld();
		}
		
		for (LivingEntity lentity : getEntities((quester != null)?quester.getPlayer():entity, config[0])) {
			if (lentity instanceof Creeper) {
				MineQuest.getEventQueue().addEvent(new ExplosionEvent(10, world, lentity, 0, 0, 0, config[1], config[2]));
				MineQuest.getEventQueue().addEvent(new HealthEvent(20, lentity, 0));
			}
		}
	}

	@Override
	public List<ItemStack> getSpellComps() {
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

	@Override
	public int getIconLoc() {
		return 12;
	}

}
