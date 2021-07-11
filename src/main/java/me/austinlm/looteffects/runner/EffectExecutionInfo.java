package me.austinlm.looteffects.runner;

import com.google.common.collect.Maps;
import java.util.Map;
import me.austinlm.looteffects.CrateOpenEffect;
import org.bukkit.Location;
import org.bukkit.entity.Player;

/**
 * Holder class containing information sent to all components of the {@link CrateOpenEffect} system
 * during execution
 */
public class EffectExecutionInfo {

	private final Location where;
	private final Player viewer;
	private final EffectRunner runner;
	private final Map<String, Object> data = Maps.newHashMap();

	public EffectExecutionInfo(Location where, Player viewer,
			EffectRunner runner) {
		this.where = where;
		this.viewer = viewer;
		this.runner = runner;
	}

	public Location getWhere() {
		return where;
	}

	public Player getViewer() {
		return viewer;
	}

	public EffectRunner getRunner() {
		return runner;
	}

	public void storeData(String key, Object toStore) {
		this.data.put(key, toStore);
	}

	public <D> D removeData(String key) {
		if (!this.data.containsKey(key)) {
			throw new IllegalStateException("No data found for key: " + key);
		}
		return (D) this.data.remove(key);
	}

	public <D> D getData(String key) {
		if (!this.data.containsKey(key)) {
			throw new IllegalStateException("No data found for key: " + key);
		}
		return (D) this.data.get(key);
	}
}
