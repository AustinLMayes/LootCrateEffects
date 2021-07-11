package me.austinlm.looteffects.effects;

import me.austinlm.looteffects.EffectStep;
import me.austinlm.looteffects.runner.EffectExecutionInfo;
import me.austinlm.looteffects.utils.EntityMover;
import org.bukkit.Sound;
import org.bukkit.util.Vector;

public class EntitySpinStep implements EffectStep {

	@Override
	public void begin(EffectExecutionInfo info) {
		info.getWhere().getWorld()
				.playSound(info.getWhere(), Sound.ENTITY_ZOMBIE_VILLAGER_CURE, 2f, 1.3f);
		EntityMover.move(info.getData("entity"), new Vector(0, 0, 0), 2, 3, () -> {
			info.getRunner().goToNextStep();
		});
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
		return "entity-spin";
	}
}
