package me.austinlm.looteffects;

import net.kyori.adventure.audience.MessageType;
import net.kyori.adventure.identity.Identity;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextComponent;
import net.kyori.adventure.text.format.NamedTextColor;
import org.bukkit.Sound;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CommandUtils {

	private static final String WARNING = "\u26A0"; // ⚠

	/**
	 * Send an error message and sound to a user
	 *
	 * @param sender to send the error to
	 * @param message describing the error
	 */
	public static void sendError(CommandSender sender, Component... message) {
		if (sender instanceof Player && !((Player) sender).isOnline()) {
			return;
		}

		TextComponent.Builder builder = Component.text()
				.append(
						Component.text("[" + WARNING + "]").color(NamedTextColor.GOLD)
				)
				.append(
						Component.text().append(message).color(NamedTextColor.RED)
				);

		sender.sendMessage(Identity.nil(), builder.build(), MessageType.CHAT);
		if (sender instanceof Player) {
			((Player) sender)
					.playSound(((Player) sender).getLocation(), Sound.BLOCK_NOTE_BLOCK_BASS, 1f, .5f);
		}
	}

	public static void sendError(CommandSender sender, String message) {
		sendError(sender, Component.text(message));
	}

	/**
	 * Ensure the user supplied at least this many arguments
	 *
	 * @param sender executing the command
	 * @param args to check
	 * @param minArgs required
	 * @return if the user submitted enough arguments
	 */
	public static boolean checkMinArgs(CommandSender sender, String[] args, int minArgs) {
		if (args.length < minArgs) {
			sendError(sender,
					"Please provide at least " + minArgs + " argument" + (minArgs > 1 ? "s" : "") + "!");
			return false;
		}
		return true;
	}

	/**
	 * @param args to join
	 * @param startIndex where in the args to begin joining from
	 * @return a joined string delimited bu spaces
	 */
	public static String joinRemaining(String[] args, int startIndex) {
		StringBuilder res = new StringBuilder();
		for (int i = startIndex; i < args.length; i++) {
			res.append(args[i]).append(" ");
		}
		return res.toString().trim();
	}
}
