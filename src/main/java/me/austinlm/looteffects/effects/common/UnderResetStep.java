package me.austinlm.looteffects.effects.common;

import me.austinlm.looteffects.EffectStep;
import me.austinlm.looteffects.runner.EffectExecutionInfo;
import org.bukkit.Location;
import org.bukkit.Material;

/**
 * Reset all blocks under the platform to air
 */
public class UnderResetStep implements EffectStep {

	@Override
	public void begin(EffectExecutionInfo info) throws Exception {
		Location level = info.getWhere().clone().subtract(0, 14, 0);
		for (int y = 0; y < 13; y++) {
			for (int x = -1; x < 2; x++) {
				for (int z = -1; z < 2; z++) {
					Location where = level.clone().add(x, y, z);
					where.getWorld().getBlockAt(where).setType(Material.AIR);
				}
			}
		}
	}

	@Override
	public void finish(EffectExecutionInfo info) throws Exception {

	}

	@Override
	public int getExecutionDelay() {
		return 0;
	}

	@Override
	public String getId() {
		return "under-reset";
	}

	@Override
	public boolean hasCallback() {
		return false;
	}
}
