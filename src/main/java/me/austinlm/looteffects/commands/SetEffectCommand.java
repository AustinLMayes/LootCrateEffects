package me.austinlm.looteffects.commands;

import me.austinlm.looteffects.CrateOpenEffect;
import me.austinlm.looteffects.EffectListener;
import me.austinlm.looteffects.EffectsManager;
import me.austinlm.looteffects.utils.CommandUtils;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;

public class SetEffectCommand implements CommandExecutor {

	private final EffectsManager manager;
	private final EffectListener listener;

	public SetEffectCommand(EffectsManager manager, EffectListener listener) {
		this.manager = manager;
		this.listener = listener;
	}

	@Override
	public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command,
			@NotNull String label, @NotNull String[] args) {
		if (CommandUtils.checkMinArgs(sender, args, 1)) {
			if (args[0].equalsIgnoreCase("random")) {
				this.listener.selectEffect(null);
				sender.sendMessage(Component.text().content("A random effect will be chosen on each click")
						.color(NamedTextColor.AQUA).build());
			} else {
				CrateOpenEffect found = this.manager.search(CommandUtils.joinRemaining(args, 0));
				if (found != null) {
					this.listener.selectEffect(found);
					Bukkit.broadcast(
							Component.text().content("Selected Effect: ").append(
									Component.text().content(found.getName()).color(NamedTextColor.LIGHT_PURPLE)
							).color(NamedTextColor.GREEN).build());
				} else {
					CommandUtils.sendError(sender, "No effects matched query!");
				}
			}
			return true;
		}

		return false;
	}
}
