package me.austinlm.looteffects.effects.spotlight.steps;

import java.util.List;
import me.austinlm.looteffects.EffectStep;
import me.austinlm.looteffects.runner.EffectExecutionInfo;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Guardian;

public class GuardianTargetStep implements EffectStep {

	@Override
	public void begin(EffectExecutionInfo info) throws Exception {
		List<Guardian> guardians = info.getData("guardians");
		for (Guardian guardian : guardians) {
			guardian.setAI(true);
			guardian.setAware(true);
			guardian.setSilent(false);
			guardian.setTarget(info.getData("entity"));
		}
	}

	@Override
	public void finish(EffectExecutionInfo info) {
		Entity stand = info.removeData("entity");
		Location where = stand.getLocation().clone();
		stand.remove();
		where.getWorld().spawnParticle(Particle.EXPLOSION_HUGE, where, 1);
		where.getWorld().playSound(where, Sound.ENTITY_DRAGON_FIREBALL_EXPLODE, 1f, 1.4f);
		List<Guardian> guardians = info.getData("guardians");
		for (Guardian guardian : guardians) {
			guardian.setAI(false);
			guardian.setAware(false);
			guardian.setSilent(true);
			guardian.setTarget(null);
		}
	}

	@Override
	public int getExecutionDelay() {
		return 4;
	}

	@Override
	public String getId() {
		return "guardian-target";
	}

	@Override
	public boolean isThreadSafe() {
		return false;
	}

	@Override
	public boolean hasCallback() {
		return false;
	}
}
