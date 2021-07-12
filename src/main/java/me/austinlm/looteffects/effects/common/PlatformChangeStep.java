package me.austinlm.looteffects.effects.common;

import me.austinlm.looteffects.EffectStep;
import me.austinlm.looteffects.runner.EffectExecutionInfo;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.block.Block;

/**
 * Change the platform under the frame to a specific material
 *
 * @param delay after the step completes to go to the next one
 * @param to material to change the platform to
 */
public record PlatformChangeStep(int delay, Material to) implements EffectStep {

	@Override
	public void begin(EffectExecutionInfo info) throws Exception {
		Location base = info.getWhere().clone().subtract(0, 1, 0);
		for (int x = -1; x < 2; x++) {
			for (int z = -1; z < 2; z++) {
				Location where = base.clone().add(x, 0, z);
				Block toChange = base.getWorld().getBlockAt(where);
				base.getWorld().spawnParticle(Particle.BLOCK_DUST, where, 1, toChange.getBlockData());
				base.getWorld().playSound(where, toChange.getSoundGroup().getBreakSound(), 1f, 1f);
				toChange.setType(this.to);
			}
		}
	}

	@Override
	public void finish(EffectExecutionInfo info) throws Exception {

	}

	@Override
	public int getExecutionDelay() {
		return this.delay;
	}

	@Override
	public String getId() {
		return "platform-change-" + this.to.name().toLowerCase().replaceAll("_", "-");
	}

	@Override
	public boolean hasCallback() {
		return false;
	}
}
