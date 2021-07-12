package me.austinlm.looteffects;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import me.austinlm.looteffects.effects.common.EntitySpinStep;
import me.austinlm.looteffects.effects.common.StarterStep;
import me.austinlm.looteffects.runner.EffectExecutionInfo;

/**
 * A complete set of {@link EffectStep}s that should be shwon in a specific order.
 */
public abstract class CrateOpenEffect {

	private final String name;
	private final List<EffectStep> steps;
	private final boolean threadSafe;

	/**
	 * @param name of the effect for UI
	 * @param steps that make up this effect
	 * @param threadSafe if the callbacks in this class are safe to execute on threads other than the
	 * main server thread
	 */
	public CrateOpenEffect(String name, boolean threadSafe, EffectStep... steps) {
		this.name = name;
		this.steps = new ArrayList<>(Arrays.asList(steps)); // Wrap so we can add steps below
		this.threadSafe = threadSafe;
		this.steps.addAll(0, Arrays.asList(new StarterStep(), new EntitySpinStep()));
	}

	/**
	 * Called when the effect is first executed before any steps have run
	 */
	public abstract void onStart(EffectExecutionInfo info);

	/**
	 * Called when all steps have completed successfully
	 */
	public abstract void onComplete(EffectExecutionInfo info);

	/**
	 * Called when any of the steps fails to execute
	 */
	public abstract void onFail(EffectExecutionInfo info);

	public List<EffectStep> getSteps() {
		return steps;
	}

	public boolean isThreadSafe() {
		return threadSafe;
	}

	public String getName() {
		return name;
	}
}
