package me.austinlm.looteffects.effects.spotlight;

import java.util.List;
import me.austinlm.looteffects.CrateOpenEffect;
import me.austinlm.looteffects.effects.spotlight.steps.EntityRiseStep;
import me.austinlm.looteffects.effects.spotlight.steps.GuardianBlastStep;
import me.austinlm.looteffects.effects.spotlight.steps.GuardianDieStep;
import me.austinlm.looteffects.effects.spotlight.steps.GuardianSpawnStep;
import me.austinlm.looteffects.effects.spotlight.steps.GuardianTargetStep;
import me.austinlm.looteffects.runner.EffectExecutionInfo;
import org.bukkit.entity.Bat;

public class SpotLightEffect extends CrateOpenEffect {

	public SpotLightEffect() {
		super("Guardian Spotlight", false, new GuardianSpawnStep(), new EntityRiseStep(),
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
