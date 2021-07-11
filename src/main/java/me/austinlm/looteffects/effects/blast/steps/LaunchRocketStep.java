package me.austinlm.looteffects.effects.blast.steps;

import java.util.Random;
import me.austinlm.looteffects.EffectStep;
import me.austinlm.looteffects.runner.EffectExecutionInfo;
import org.bukkit.Color;
import org.bukkit.FireworkEffect;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Firework;
import org.bukkit.event.entity.CreatureSpawnEvent.SpawnReason;
import org.bukkit.inventory.meta.FireworkMeta;

public class LaunchRocketStep implements EffectStep {

	private static final Random RANDOM = new Random();

	@Override
	public void begin(EffectExecutionInfo info) {
		Entity toLaunch = info.getData("entity");
		FireworkEffect effect = FireworkEffect.builder()
				.withColor(Color.fromRGB(RANDOM.nextInt(255), RANDOM.nextInt(255), RANDOM.nextInt(255)))
				.build();
		Firework spawned = (Firework) info.getWhere().getWorld()
				.spawnEntity(toLaunch.getLocation(), EntityType.FIREWORK,
						SpawnReason.CUSTOM, e -> {
							FireworkMeta meta = ((Firework) e).getFireworkMeta();
							meta.addEffect(effect);
							meta.setPower(2);

							((Firework) e).setFireworkMeta(meta);
						});
		info.storeData("rocket", spawned);

		spawned.addPassenger(toLaunch);
	}

	@Override
	public void finish(EffectExecutionInfo info) {

	}

	@Override
	public int getExecutionDelay() {
		return 5;
	}

	@Override
	public String getId() {
		return null;
	}

	@Override
	public boolean hasCallback() {
		return false;
	}
}
