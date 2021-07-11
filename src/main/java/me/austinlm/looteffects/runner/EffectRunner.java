package me.austinlm.looteffects.runner;

import java.util.concurrent.Phaser;
import me.austinlm.looteffects.CrateOpenEffect;
import me.austinlm.looteffects.EffectStep;
import me.austinlm.looteffects.LootEffectsPlugin;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

/**
 * Class responsible for running a single {@link CrateOpenEffect}.
 */
public class EffectRunner extends BukkitRunnable {

	private final Phaser executionController = new Phaser(2);
	private final CrateOpenEffect toRun;
	private final Runnable completionCallback;
	private final EffectExecutionInfo info;

	public EffectRunner(CrateOpenEffect toRun, Location where, Player viewer,
			Runnable completionCallback) {
		this.toRun = toRun;
		this.completionCallback = completionCallback;
		this.info = new EffectExecutionInfo(where, viewer, this);
	}

	public void start() {
		this.runTaskAsynchronously(LootEffectsPlugin.INSTANCE);
	}

	@Override
	public void run() {
		Bukkit.getLogger().info("Executing " + this.toRun.getName());
		executeSync(() -> this.toRun.onStart(info), this.toRun.isThreadSafe());
		try {
			for (EffectStep step : this.toRun.getSteps()) {
				log("Beginning execution...", step);
				executeSync(() -> {
					try {
						step.begin(info);
					} catch (Exception e) {
						handleException(e);
					}
				}, step.isThreadSafe());
				if (step.hasCallback()) {
					log("Waiting for step to call back", step);
					this.executionController.arriveAndAwaitAdvance();
					log("Got callback", step);
				}
				if (step.getExecutionDelay() > 0) {
					log("Delaying for " + step.getExecutionDelay(), step);
					Thread.sleep(step.getExecutionDelay() * 1000L);
				}
				log("Finishing up", step);
				executeSync(() -> {
					try {
						step.finish(info);
					} catch (Exception e) {
						handleException(e);
					}
				}, step.isThreadSafe());
			}
		} catch (Exception e) {
			handleException(e);
		}
		Bukkit.getLogger().info("Completing...");
		executeSync(() -> this.toRun.onComplete(info), this.toRun.isThreadSafe());
		this.completionCallback.run();
	}

	private void log(String message, EffectStep step) {
		Bukkit.getLogger().info("[" + step.getId() + "]: " + message);
	}

	private void handleException(Exception e) {
		executeSync(() -> {
			this.toRun.onFail(this.info);
			e.printStackTrace();
			this.completionCallback.run();
		}, this.toRun.isThreadSafe());
	}

	private void executeSync(Runnable toRun, boolean here) {
		if (here) {
			toRun.run();
		} else {
			new BukkitRunnable() {
				@Override
				public void run() {
					toRun.run();
				}
			}.runTask(LootEffectsPlugin.INSTANCE);
		}
	}

	/**
	 * Notify the runner that the current step has completed
	 */
	public void goToNextStep() {
		this.executionController.arriveAndAwaitAdvance();
	}
}
