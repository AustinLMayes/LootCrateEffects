package me.austinlm.looteffects.effects.blast;

import me.austinlm.looteffects.CrateOpenEffect;
import me.austinlm.looteffects.effects.blast.steps.LaunchRocketStep;
import me.austinlm.looteffects.runner.EffectExecutionInfo;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Firework;
import org.bukkit.inventory.EquipmentSlot;

public class BlastOffEffect extends CrateOpenEffect {


	public BlastOffEffect() {
		super("Blast Off", false, new LaunchRocketStep());
	}

	@Override
	public void onStart(EffectExecutionInfo info) {

	}

	@Override
	public void onComplete(EffectExecutionInfo info) {
		Firework rocket = info.removeData("rocket");
		ArmorStand stand = info.removeData("entity");
		stand.setItem(EquipmentSlot.HEAD, null);
		rocket.detonate();
	}

	@Override
	public void onFail(EffectExecutionInfo info) {

	}
}
