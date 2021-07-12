package me.austinlm.looteffects.effects.lava;

import me.austinlm.looteffects.CrateOpenEffect;
import me.austinlm.looteffects.effects.common.PlatformChangeStep;
import me.austinlm.looteffects.effects.common.UnderResetStep;
import me.austinlm.looteffects.effects.lava.steps.FizzStep;
import me.austinlm.looteffects.effects.lava.steps.LavaSpawnStep;
import me.austinlm.looteffects.effects.lava.steps.WaterSpawnStep;
import me.austinlm.looteffects.runner.EffectExecutionInfo;
import org.bukkit.Material;
import org.bukkit.entity.Entity;

public class RisingLavaEffect extends CrateOpenEffect {

	public RisingLavaEffect() {
		super("Rising Lava", false, new PlatformChangeStep(1, Material.BARRIER), new WaterSpawnStep(),
				new LavaSpawnStep(), new FizzStep(), new PlatformChangeStep(0, Material.QUARTZ_BLOCK),
				new UnderResetStep());
	}

	@Override
	public void onStart(EffectExecutionInfo info) {

	}

	@Override
	public void onComplete(EffectExecutionInfo info) {
		Entity stand = info.removeData("entity");
		stand.remove();
	}

	@Override
	public void onFail(EffectExecutionInfo info) {

	}
}
