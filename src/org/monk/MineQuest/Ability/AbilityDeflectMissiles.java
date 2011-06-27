package org.monk.MineQuest.Ability;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Skeleton;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.Vector;
import org.monksanctum.MineQuest.Ability.Ability;
import org.monksanctum.MineQuest.Ability.DefendingAbility;
import org.monksanctum.MineQuest.Ability.PassiveAbility;
import org.monksanctum.MineQuest.Quester.Quester;

public class AbilityDeflectMissiles extends Ability implements DefendingAbility, PassiveAbility {
	
	public AbilityDeflectMissiles() {
		super();
		config = new int[] {60};
	}

	@Override
	public void castAbility(Quester quester, Location location,
			LivingEntity entity) {
		// Passive
	}

	@Override
	public List<ItemStack> getSpellComps() {
		List<ItemStack> cost = new ArrayList<ItemStack>();
		int i;
		
		for (i = 0; i < 10; i++) {
			cost.add(new ItemStack(Material.LEATHER, 1));
		}
		
		return cost;
	}

	@Override
	public String getName() {
		return "Deflect Missiles";
	}

	@Override
	public int getReqLevel() {
		return 15;
	}

	@Override
	public int parseDefend(Quester quester, LivingEntity mob, int amount) {
		if (!enabled) return 0;
		double chance = ((double)config[0]) / 100;
		
		if ((mob instanceof Skeleton) && (myclass.getGenerator().nextDouble() < chance)) {
			Location location = quester.getPlayer().getLocation();
			World world = location.getWorld();
			Random generator = myclass.getGenerator();
			location.setY(location.getY() + 1.5);
			Vector vector = new Vector(generator.nextDouble(), .2, generator.nextDouble());
			
			world.spawnArrow(location, vector, .6f, 12);
			
			return amount;
		}
		
		return 0;
	}

	@Override
	public String getSkillClass() {
		return "Archer";
	}

}
