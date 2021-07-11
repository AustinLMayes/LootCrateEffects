package me.austinlm.looteffects.effects.spotlight.steps;

import com.google.common.collect.Lists;
import java.util.List;
import me.austinlm.looteffects.EffectStep;
import me.austinlm.looteffects.LootEffectsPlugin;
import me.austinlm.looteffects.runner.EffectExecutionInfo;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.entity.Bat;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Guardian;

public class GuardianSpawnStep implements EffectStep {

	@Override
	public void begin(EffectExecutionInfo info) throws Exception {
		Location where = info.getWhere().clone();
		where.subtract(0, 0.5, 0);
		List<Guardian> spawned = Lists.newArrayList();
		List<Bat> holders = Lists.newArrayList();
		for (int x = -2; x < 3; x = x + 4) {
			for (int z = -2; z < 3; z = z + 4) {
				Location spawnLoc = where.clone();
				spawnLoc.add(x, 0, z);
				float yaw = (float) Math
						.toDegrees(Math.atan2(-where.getX() + spawnLoc.getX(), where.getZ() - spawnLoc.getZ()));
				spawnLoc.setYaw(yaw);
				Bukkit.getScheduler().runTask(LootEffectsPlugin.INSTANCE, () -> {
					Bat holder = (Bat) where.getWorld().spawnEntity(spawnLoc, EntityType.BAT);
					holder.setInvisible(true);
					holder.setSilent(true);
					holder.setAI(false);
					Guardian guardian = (Guardian) where.getWorld()
							.spawnEntity(spawnLoc, EntityType.GUARDIAN);
					guardian.setInvulnerable(true);
					guardian.setAware(false);
					guardian.setAI(false);
					guardian.setSilent(true);
					holder.addPassenger(guardian);
					spawned.add(guardian);
					holders.add(holder);
					where.getWorld().playSound(where, Sound.BLOCK_DEEPSLATE_HIT, 1f, 0.4f);
				});
				System.out.println(x + ", " + z);
				Thread.sleep(1000);
			}
		}
		info.storeData("guardians", spawned);
		info.storeData("holders", holders);
	}

	@Override
	public void finish(EffectExecutionInfo info) {

	}

	@Override
	public int getExecutionDelay() {
		return 0;
	}

	@Override
	public String getId() {
		return "guardian-spawn";
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
