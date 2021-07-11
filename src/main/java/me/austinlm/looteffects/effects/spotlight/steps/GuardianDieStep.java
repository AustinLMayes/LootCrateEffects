package me.austinlm.looteffects.effects.spotlight.steps;

import java.util.List;
import java.util.Random;
import me.austinlm.looteffects.EffectStep;
import me.austinlm.looteffects.LootEffectsPlugin;
import me.austinlm.looteffects.runner.EffectExecutionInfo;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.entity.Guardian;

public class GuardianDieStep implements EffectStep {

	private static final Random RANDOM = new Random();

	@Override
	public void begin(EffectExecutionInfo info) throws Exception {

	}

	@Override
	public void finish(EffectExecutionInfo info) throws Exception {
		List<Guardian> guardians = info.removeData("guardians");
		for (Guardian guardian : guardians) {
			Bukkit.getScheduler().runTask(LootEffectsPlugin.INSTANCE, () -> {
				Location guardianLoc = guardian.getLocation().clone();
				guardian.remove();
				guardianLoc.getWorld().spawnParticle(Particle.LIGHT, guardianLoc, 2);
				guardianLoc.getWorld().playSound(guardianLoc, Sound.ENTITY_ELDER_GUARDIAN_DEATH, 1f, 1.6f);
			});
			Thread.sleep((1 + RANDOM.nextInt(3)) * 1000);
		}
	}

	@Override
	public int getExecutionDelay() {
		return 0;
	}

	@Override
	public String getId() {
		return "guardian-destroy";
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
