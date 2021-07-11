package me.austinlm.looteffects.effects.laser;

import java.util.List;
import me.austinlm.looteffects.CrateOpenEffect;
import me.austinlm.looteffects.effects.laser.steps.EntityRiseStep;
import me.austinlm.looteffects.effects.laser.steps.GuardianBlastStep;
import me.austinlm.looteffects.effects.laser.steps.GuardianDieStep;
import me.austinlm.looteffects.effects.laser.steps.GuardianSpawnStep;
import me.austinlm.looteffects.effects.laser.steps.GuardianTargetStep;
import me.austinlm.looteffects.runner.EffectExecutionInfo;
import org.bukkit.entity.Bat;

public class LaserEffect extends CrateOpenEffect {

	public LaserEffect() {
		super("Guardian Lasers", false, new GuardianSpawnStep(), new EntityRiseStep(),
				new GuardianTargetStep(), new GuardianBlastStep(), new GuardianDieStep());
	}

	@Override
	public void onStart(EffectExecutionInfo info) {

	}

	@Override
	public void onComplete(EffectExecutionInfo info) {
		List<Bat> holders = info.removeData("holders");
		for (Bat holder : holders) {
			holder.remove();
		}
	}

	@Override
	public void onFail(EffectExecutionInfo info) {

	}
}
