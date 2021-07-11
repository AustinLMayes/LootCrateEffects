package me.austinlm.looteffects.utils;

import me.austinlm.looteffects.LootEffectsPlugin;
import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

/**
 * Utility class to move an {@link Entity} from one location to another smoothly.
 */
public class EntityMover extends BukkitRunnable {

	// ticks
	private static final int RATE = 1;

	private final Entity toMove;
	private final Vector per;
	private final int time;
	private final Runnable onEnd;
	private final float yawPer;
	private int runs = 0;
	private float curYaw;

	/**
	 * @param toMove entity this task is responsible for moving
	 * @param offset how much the entity should be moved
	 * @param time how long (in seconds) the move should take
	 * @param revolutions number of times the entity should spin while moving
	 * @param onEnd callback to execute when the task completes
	 */
	private EntityMover(Entity toMove, Vector offset, int time, int revolutions, Runnable onEnd) {
		this.toMove = toMove;
		this.time = time * (20 / RATE);
		this.onEnd = onEnd;

		double xPer = offset.getX() / (this.time);
		double yPer = offset.getY() / (this.time);
		double zPer = offset.getZ() / (this.time);
		this.per = new Vector(xPer, yPer, zPer);
		this.yawPer = revolutions == 0 ? 0 : 360 * ((float) revolutions / this.time);
	}

	public static void move(Entity toMove, Vector to, int time, int revolutions, Runnable onEnd) {
		new EntityMover(toMove, to, time, revolutions, onEnd)
				.runTaskTimer(LootEffectsPlugin.INSTANCE, RATE, RATE);
	}

	@Override
	public void run() {
		if (++runs > this.time) {
			onEnd.run();
			cancel();
			return;
		}

		if (curYaw >= 360) {
			curYaw = 0 - yawPer;
		}

		curYaw = curYaw + yawPer;
		Location to = toMove.getLocation().clone();
		to.add(this.per);
		to.setYaw(curYaw);
		toMove.teleport(to);
	}
}
