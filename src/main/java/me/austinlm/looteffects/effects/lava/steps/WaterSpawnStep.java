package me.austinlm.looteffects.effects.lava.steps;

import me.austinlm.looteffects.EffectStep;
import me.austinlm.looteffects.runner.EffectExecutionInfo;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;

public class WaterSpawnStep implements EffectStep {

	@Override
	public void begin(EffectExecutionInfo info) throws Exception {
		Location base = info.getWhere().clone().subtract(0, 3, 0);
		Material to = Material.BARRIER;
		base.getWorld().playSound(base, Sound.ENTITY_PLAYER_SPLASH_HIGH_SPEED, 1f, 1.3f);
		for (int y = 0; y < 2; y++) {
			for (int x = -1; x < 2; x++) {
				for (int z = -1; z < 2; z++) {
					base.getWorld().getBlockAt(base.clone().add(x, y, z)).setType(to);
				}
			}
			to = Material.WATER;
		}
	}

	@Override
	public void finish(EffectExecutionInfo info) throws Exception {

	}

	@Override
	public int getExecutionDelay() {
		return 1;
	}

	@Override
	public String getId() {
		return "water-spawn";
	}

	@Override
	public boolean hasCallback() {
		return false;
	}
}
