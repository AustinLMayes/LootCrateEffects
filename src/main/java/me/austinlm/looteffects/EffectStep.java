package me.austinlm.looteffects;

import me.austinlm.looteffects.runner.EffectExecutionInfo;
import me.austinlm.looteffects.runner.EffectRunner;

/**
 * A single step of a {@link CrateOpenEffect}.
 */
public interface EffectStep {

	/**
	 * Called when this step is reached
	 */
	void begin(EffectExecutionInfo info) throws Exception;

	/**
	 * Callback executed when the step completes
	 */
	void finish(EffectExecutionInfo info) throws Exception;

	/**
	 * @return delay (in seconds) after {@link EffectRunner#goToNextStep()} is executed before {@link
	 * #finish(EffectExecutionInfo)} is called.
	 */
	int getExecutionDelay();

	/**
	 * @return identifier of this step for debugging
	 */
	String getId();

	/**
	 * @return if the methods in this class are safe to be executed on threads other than the main one
	 */
	default boolean isThreadSafe() {
		return false;
	}

	/**
	 * @return if the runner should wait for the step to notify that it has finished
	 */
	default boolean hasCallback() {
		return true;
	}
}
