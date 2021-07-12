package me.austinlm.looteffects.effects.lava.steps;

import me.austinlm.looteffects.EffectStep;
import me.austinlm.looteffects.LootEffectsPlugin;
import me.austinlm.looteffects.runner.EffectExecutionInfo;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;

public class LavaSpawnStep implements EffectStep {

	@Override
	public void begin(EffectExecutionInfo info) throws Exception {
		Location level = info.getWhere().clone().subtract(0, 14, 0);
		for (int y = 0; y < 10; y++) {
			level.add(0, 1, 0);
			for (int x = -1; x < 2; x++) {
				for (int z = -1; z < 2; z++) {
					Location where = level.clone().add(x, 0, z);
					Bukkit.getScheduler().runTask(LootEffectsPlugin.INSTANCE,
							() -> where.getWorld().getBlockAt(where).setType(Material.LAVA));
				}
			}
			Bukkit.getScheduler().runTask(LootEffectsPlugin.INSTANCE,
					() -> level.getWorld().playSound(level, Sound.ENTITY_GENERIC_EXTINGUISH_FIRE, 1f, 1f));
			Thread.sleep(800);
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
		return "lava-spawn";
	}

	@Override
	public boolean isThreadSafe() {
		return true;
	}

	@Override
	public boolean hasCallback() {
		return false;
	}
}
