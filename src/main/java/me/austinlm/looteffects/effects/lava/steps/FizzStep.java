package me.austinlm.looteffects.effects.lava.steps;

import me.austinlm.looteffects.EffectStep;
import me.austinlm.looteffects.runner.EffectExecutionInfo;
import org.bukkit.Effect;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;

public class FizzStep implements EffectStep {

	@Override
	public void begin(EffectExecutionInfo info) throws Exception {
		Location base = info.getWhere().clone().subtract(0, 2, 0);
		base.getWorld().playSound(base, Sound.BLOCK_LAVA_EXTINGUISH, 2f, 1f);
		for (int x = -1; x < 2; x++) {
			for (int z = -1; z < 2; z++) {
				Location where = base.clone().add(x, 0, z);
				base.getWorld().getBlockAt(where).setType(Material.OBSIDIAN);
				base.getWorld().playEffect(where, Effect.LAVA_CONVERTS_BLOCK, 0);
			}
		}
	}

	@Override
	public void finish(EffectExecutionInfo info) throws Exception {

	}

	@Override
	public int getExecutionDelay() {
		return 2;
	}

	@Override
	public String getId() {
		return "fizz";
	}

	@Override
	public boolean hasCallback() {
		return false;
	}
}
