package me.austinlm.looteffects;

import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.concurrent.atomic.AtomicBoolean;
import me.austinlm.looteffects.effects.blast.BlastOffEffect;
import me.austinlm.looteffects.effects.spotlight.SpotLightEffect;
import me.austinlm.looteffects.runner.EffectRunner;
import org.apache.commons.lang.Validate;
import org.bukkit.Location;
import org.bukkit.entity.Player;

public class EffectsManager {

	private static final Random RANDOM = new Random();
	private final List<CrateOpenEffect> effects;
	private final AtomicBoolean running = new AtomicBoolean();

	public EffectsManager() {
		this.effects = Arrays.asList(new BlastOffEffect(), new SpotLightEffect());
	}

	public void showEffect(CrateOpenEffect toShow, Location where, Player viewer) {
		Validate.isTrue(!this.running.get(), "Cannot run more than one effect at once!");
		this.running.set(true);
		new EffectRunner(toShow, where, viewer, () -> this.running.set(false)).start();
	}

	public CrateOpenEffect showRandomEffect(Location where, Player viewer) {
		Validate.isTrue(!this.running.get(), "Cannot run more than one effect at once!");
		CrateOpenEffect chosen = this.effects.get(RANDOM.nextInt(this.effects.size()));
		showEffect(chosen, where, viewer);
		return chosen;
	}

	public boolean isRunningEffect() {
		return this.running.get();
	}

	public CrateOpenEffect search(String query) {
		return this.effects.stream().filter(
				effect -> effect.getName().toLowerCase().contains(query.toLowerCase()))
				.findFirst().orElse(null);
	}

}
