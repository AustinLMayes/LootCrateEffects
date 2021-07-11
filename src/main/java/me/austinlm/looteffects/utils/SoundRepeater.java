package me.austinlm.looteffects.utils;

import java.util.Random;
import me.austinlm.looteffects.LootEffectsPlugin;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.scheduler.BukkitRunnable;

public class SoundRepeater extends BukkitRunnable {

	private static final Random RANDOM = new Random();

	private final Sound toSend;
	private final Location source;
	private final float pitchMin;
	private final float pitchMax;

	public SoundRepeater(Sound toSend, Location source, float pitchMin, float pitchMax) {
		this.toSend = toSend;
		this.source = source;
		this.pitchMin = pitchMin;
		this.pitchMax = pitchMax;
	}

	public static SoundRepeater play(Sound toSend, Location source, float pitchMin, float pitchMax,
			long delay) {
		SoundRepeater repeater = new SoundRepeater(toSend, source, pitchMin, pitchMax);
		repeater.runTaskTimer(LootEffectsPlugin.INSTANCE, 0, delay);
		return repeater;
	}

	@Override
	public void run() {
		this.source.getWorld().playSound(this.source, this.toSend, 1f,
				(this.pitchMax - this.pitchMin + RANDOM.nextFloat()));
	}
}
