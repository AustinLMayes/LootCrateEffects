package me.austinlm.looteffects;

import java.util.Objects;
import me.austinlm.looteffects.commands.SetEffectCommand;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * Main plugin class
 *
 * @author Austin Mayes
 */
public class LootEffectsPlugin extends JavaPlugin {

	public static LootEffectsPlugin INSTANCE = null;

	@Override
	public void onLoad() {
		INSTANCE = this;

	}

	@Override
	public void onEnable() {
		EffectsManager manager = new EffectsManager();
		EffectListener listener = new EffectListener(manager);
		Objects.requireNonNull(getCommand("set-effect"))
				.setExecutor(new SetEffectCommand(manager, listener));
		getServer().getPluginManager().registerEvents(listener, this);
	}

	@Override
	public void onDisable() {
		INSTANCE = null;
	}

}
