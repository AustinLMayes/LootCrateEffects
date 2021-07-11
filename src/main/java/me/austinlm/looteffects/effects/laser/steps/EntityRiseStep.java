package me.austinlm.looteffects.effects.laser.steps;

import me.austinlm.looteffects.EffectStep;
import me.austinlm.looteffects.runner.EffectExecutionInfo;
import me.austinlm.looteffects.utils.EntityMover;
import me.austinlm.looteffects.utils.SoundRepeater;
import org.bukkit.Sound;
import org.bukkit.util.Vector;

public class EntityRiseStep implements EffectStep {

	@Override
	public void begin(EffectExecutionInfo info) throws Exception {
		SoundRepeater repeater = SoundRepeater
				.play(Sound.UI_BUTTON_CLICK, info.getWhere(), 1.2f, 2f, 2);
		EntityMover.move(info.getData("entity"), new Vector(0, 10, 0), 3, 0, () -> {
			repeater.cancel();
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
		return "entity-move";
	}

	@Override
	public boolean isThreadSafe() {
		return true;
	}
}
