package me.austinlm.looteffects.effects.laser.steps;

import java.util.List;
import java.util.Random;
import me.austinlm.looteffects.EffectStep;
import me.austinlm.looteffects.runner.EffectExecutionInfo;
import org.bukkit.entity.Bat;
import org.bukkit.util.Vector;

public class GuardianBlastStep implements EffectStep {

	private static final Random RANDOM = new Random();

	@Override
	public void begin(EffectExecutionInfo info) throws Exception {
		List<Bat> holders = info.getData("holders");
		for (Bat holder : holders) {
			holder.setAI(true);
			holder.setAwake(true);
			holder.setVelocity(new Vector(1, 2.5, 1).multiply(RANDOM.nextDouble()));
		}
	}

	@Override
	public void finish(EffectExecutionInfo info) {

	}

	@Override
	public int getExecutionDelay() {
		return 4;
	}

	@Override
	public String getId() {
		return "guardian-blast-off";
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
